package hk.hkucs.financial_news.interfaces;

import hk.hkucs.financial_news.model.YahooFinanceResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface YahooFinanceService {
    @GET("stock/v2/get-timeseries")
    Call<YahooFinanceResponse> getTimeseries(@Query("symbol") String symbol, @Query("region") String region, @Header("x-rapidapi-key") String apiKey);
}