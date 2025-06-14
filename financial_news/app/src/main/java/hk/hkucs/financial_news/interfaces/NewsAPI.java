package hk.hkucs.financial_news.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPI {
    // Alpha Vantage Insider Transactions
    @GET("query")
    Call<Object> getInsiderTransactions(
            @Query("function") String function,
            @Query("symbol") String symbol,
            @Query("apikey") String apiKey
    );

    // Finnhub General News
    @GET("news")
    Call<Object> getGeneralNews(
            @Query("category") String category,
            @Query("token") String apiKey
    );

    // MarketStack Financial News
    @GET("news")
    Call<Object> getMarketStackNews(@Query("access_key") String apiKey);
}