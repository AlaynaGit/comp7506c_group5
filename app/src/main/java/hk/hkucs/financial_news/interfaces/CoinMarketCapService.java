package hk.hkucs.financial_news.interfaces;

import hk.hkucs.financial_news.model.CoinMarketCapResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CoinMarketCapService {
    @GET("v1/cryptocurrency/listings/latest")
    Call<CoinMarketCapResponse> getCryptoListings(@Header("X-CMC_PRO_API_KEY") String apiKey);
}