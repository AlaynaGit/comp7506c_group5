package hk.hkucs.financial_news;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import hk.hkucs.financial_news.interfaces.AlphaVantageService;
import hk.hkucs.financial_news.interfaces.FinnhubService;
import hk.hkucs.financial_news.model.AlphaVantageResponse;
import hk.hkucs.financial_news.model.FinnhubNews;
import hk.hkucs.financial_news.model.NewsItem;
import hk.hkucs.financial_news.adapters.NewsAdapter;
import hk.hkucs.financial_news.services.RetrofitClient;
import hk.hkucs.financial_news.util.CommonUtil;
import hk.hkucs.financial_news.util.PropertyUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<NewsItem> newsList = new ArrayList<>();
    private List<NewsItem> fullNewsList = new ArrayList<>();
    private SearchView searchView;
    private DrawerLayout drawerLayout;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupToolbar();
        setupRecyclerView();
        setupTabs();
        setupSearch();
        setupDrawer();
        setupSwipeRefresh();

        fetchNews();
    }

    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        searchView = findViewById(R.id.searchView);
        drawerLayout = findViewById(R.id.drawer_layout);
        tabLayout = findViewById(R.id.tabLayout);
        View newsContainer = findViewById(R.id.news_container);
    }

    private void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Financial News");
            }
        }
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        newsAdapter = new NewsAdapter(newsList, this);
        recyclerView.setAdapter(newsAdapter);
    }

    private void setupTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("All").setContentDescription("Show all news categories"));
        tabLayout.addTab(tabLayout.newTab().setText("Markets").setContentDescription("Show market news"));
        tabLayout.addTab(tabLayout.newTab().setText("Tech").setContentDescription("Show technology news"));
        tabLayout.addTab(tabLayout.newTab().setText("Finance").setContentDescription("Show finance news"));
        tabLayout.addTab(tabLayout.newTab().setText("Learn").setContentDescription("Show learning resources"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String category = tab.getText().toString();
                if ("Learn".equals(category)) {
                    showLearnFragment();
                } else {
                    filterByCategory(category);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void setupSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterNews(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterNews(newText);
                return true;
            }
        });
    }

    private void setupDrawer() {
        if (toolbar != null && drawerLayout != null) {
            toggle = new ActionBarDrawerToggle(
                    this, drawerLayout, toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_all) {
                    filterByCategory("All");
                } else if (id == R.id.nav_markets) {
                    filterByCategory("Markets");
                } else if (id == R.id.nav_tech) {
                    filterByCategory("Tech");
                } else if (id == R.id.nav_finance) {
                    filterByCategory("Finance");
                } else if (id == R.id.nav_learn) {
                    showLearnFragment();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            });
        }
    }

    private void setupSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            fullNewsList.clear();
            newsList.clear();
            newsAdapter.notifyDataSetChanged();
            fetchNews();
        });

        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorAccent
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle != null && toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchNews() {
        showLoading(true);
        Properties props = PropertyUtil.loadProperties(this);
        String alphaVantageKey = props.getProperty("ALPHA_VANTAGE_API_KEY");
        String finnhubKey = props.getProperty("FINNHUB_API_KEY");

        AlphaVantageService alphaService = RetrofitClient.getAlphaVantageService();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            alphaService.getInsiderTransactions(CommonUtil.generateAlphaVantageTimestamp(24), alphaVantageKey).enqueue(new Callback<AlphaVantageResponse>() {
                @Override
                public void onResponse(Call<AlphaVantageResponse> call, Response<AlphaVantageResponse> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().feed != null) {
                        for (AlphaVantageResponse.FeedItem item : response.body().feed) {
                            NewsItem news = new NewsItem("Alpha Vantage", item.title, item.time_published);
                            news.setImageUrl(item.banner_image);
                            news.setAuthor(item.authors != null && !item.authors.isEmpty() ? item.authors.get(0) : "Unknown");
                            news.setCategory(item.category_within_source);
                            if (item.ticker_sentiment != null && !item.ticker_sentiment.isEmpty()) {
                                news.setTicker(item.ticker_sentiment.get(0).ticker);
                                news.setSentiment(item.ticker_sentiment.get(0).ticker_sentiment_label);
                                news.setSentimentScore(Double.parseDouble(item.ticker_sentiment.get(0).ticker_sentiment_score));
                            }
                            news.setExtraData(item);
                            fullNewsList.add(news);
                        }
                        updateNewsList();
                    }
                    showLoading(false);
                }

                @Override
                public void onFailure(Call<AlphaVantageResponse> call, Throwable t) {
                    showLoading(false);
                }
            });
        }

        FinnhubService finnhubService = RetrofitClient.getFinnhubService();
        finnhubService.getGeneralNews("general", finnhubKey).enqueue(new Callback<List<FinnhubNews>>() {
            @Override
            public void onResponse(Call<List<FinnhubNews>> call, Response<List<FinnhubNews>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (FinnhubNews news : response.body()) {
                        NewsItem item = new NewsItem("Finnhub", news.headline, news.datetime);
                        item.setImageUrl(news.image);
                        item.setAuthor("Unknown");
                        item.setCategory(news.category);
                        item.setExtraData(news);
                        fullNewsList.add(item);
                    }
                    updateNewsList();
                }
            }

            @Override
            public void onFailure(Call<List<FinnhubNews>> call, Throwable t) {}
        });
    }

    private void showLoading(boolean show) {
        if (progressBar != null) {
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void updateNewsList() {
        newsList.clear();
        newsList.addAll(fullNewsList);
        if (newsAdapter != null) {
            newsAdapter.notifyDataSetChanged();
        }
    }

    private void filterNews(String query) {
        newsList.clear();
        if (query.isEmpty()) {
            newsList.addAll(fullNewsList);
        } else {
            for (NewsItem item : fullNewsList) {
                if (item.getHeadline() != null && item.getHeadline().toLowerCase().contains(query.toLowerCase())) {
                    newsList.add(item);
                }
            }
        }
        if (newsAdapter != null) {
            newsAdapter.notifyDataSetChanged();
        }
    }

    private void showLearnFragment() {
        // Hide the news container and show the Learn fragment
        View newsContainer = findViewById(R.id.news_container);
        if (newsContainer != null) {
            newsContainer.setVisibility(View.GONE);
        }
        
        // Add the Learn fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new LearnFragment())
                .commit();
    }

    private void showNewsList() {
        // Show the news container and hide the Learn fragment
        View newsContainer = findViewById(R.id.news_container);
        if (newsContainer != null) {
            newsContainer.setVisibility(View.VISIBLE);
        }
        
        // Remove any existing fragments
        getSupportFragmentManager().beginTransaction()
                .remove(getSupportFragmentManager().findFragmentById(R.id.fragment_container))
                .commit();
    }

    private void filterByCategory(String category) {
        showNewsList();
        newsList.clear();
        if ("All".equals(category)) {
            newsList.addAll(fullNewsList);
        } else {
            for (NewsItem item : fullNewsList) {
                if (item.getCategory() != null && item.getCategory().equalsIgnoreCase(category)) {
                    newsList.add(item);
                }
            }
        }
        if (newsAdapter != null) {
            newsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (toggle != null) {
            toggle.syncState();
        }
    }
}