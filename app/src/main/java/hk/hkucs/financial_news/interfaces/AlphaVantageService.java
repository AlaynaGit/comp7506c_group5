package hk.hkucs.financial_news.interfaces;

import hk.hkucs.financial_news.model.AlphaVantageResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AlphaVantageService {

//    https://www.alphavantage.co/query?function=NEWS_SENTIMENT&apikey=Q3YA0FOXSLCHNCGV&limit=50&time_from=20250612T1400
    @GET("query?function=NEWS_SENTIMENT&limit=50")
    Call<AlphaVantageResponse> getInsiderTransactions(@Query("time_from") String timeFrom, @Query("apikey") String apiKey);
}
