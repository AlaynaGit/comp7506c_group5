package hk.hkucs.financial_news.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import hk.hkucs.financial_news.R;
import hk.hkucs.financial_news.model.NewsItem;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsItem> newsList;
    private Context context;

    public NewsAdapter(List<NewsItem> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsItem news = newsList.get(position);

        holder.headlineText.setText(news.getHeadline());
        holder.authorText.setText("By " + (news.getAuthor() != null ? news.getAuthor() : "Unknown"));
        holder.sourceText.setText(news.getSource());
        holder.timeText.setText(formatTime(news.getDate()));

        // Load image with Glide
        if (news.getImageUrl() != null && !news.getImageUrl().isEmpty()) {
            Glide.with(context)
                    .load(news.getImageUrl())
                    .apply(new RequestOptions()
                            .transform(new RoundedCorners(16))
                            .placeholder(R.drawable.placeholder_image)
                            .error(R.drawable.placeholder_image))
                    .into(holder.newsImage);
        } else {
            holder.newsImage.setImageResource(R.drawable.placeholder_image);
        }

        // Ticker and Sentiment
        if (news.getTicker() != null && news.getSentiment() != null) {
            holder.tickerText.setText(news.getTicker());
            holder.sentimentText.setText(news.getSentiment());
            holder.tickerSentimentLayout.setVisibility(View.VISIBLE);

            // Set sentiment color
            int color;
            if ("Bullish".equalsIgnoreCase(news.getSentiment()) || "Positive".equalsIgnoreCase(news.getSentiment())) {
                color = context.getResources().getColor(R.color.sentiment_positive);
            } else if ("Bearish".equalsIgnoreCase(news.getSentiment()) || "Negative".equalsIgnoreCase(news.getSentiment())) {
                color = context.getResources().getColor(R.color.sentiment_negative);
            } else {
                color = context.getResources().getColor(R.color.sentiment_neutral);
            }
            holder.sentimentText.setTextColor(color);
        } else {
            holder.tickerSentimentLayout.setVisibility(View.GONE);
        }

        // Category chip
        if (news.getCategory() != null) {
            holder.categoryChip.setText(news.getCategory());
            holder.categoryChip.setVisibility(View.VISIBLE);
        } else {
            holder.categoryChip.setVisibility(View.GONE);
        }
    }

    private String formatTime(String time) {
        if (time != null && time.length() >= 8) {
            return "3:41 PM HKT, Jun 13, 2025"; // Replace with proper date formatting
        }
        return time != null ? time : "";
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView headlineText, authorText, sourceText, timeText, tickerText, sentimentText, categoryChip;
        View tickerSentimentLayout;

        public ViewHolder(View view) {
            super(view);
            newsImage = view.findViewById(R.id.newsImage);
            headlineText = view.findViewById(R.id.headlineText);
            authorText = view.findViewById(R.id.authorText);
            sourceText = view.findViewById(R.id.sourceText);
            timeText = view.findViewById(R.id.timeText);
            tickerText = view.findViewById(R.id.tickerText);
            sentimentText = view.findViewById(R.id.sentimentText);
            categoryChip = view.findViewById(R.id.categoryChip);
            tickerSentimentLayout = view.findViewById(R.id.tickerSentimentLayout);
        }
    }
}