package hk.hkucs.financial_news.interfaces;

import java.util.List;

import hk.hkucs.financial_news.model.FinnhubNews;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FinnhubService {
    @GET("news")
    Call<List<FinnhubNews>> getGeneralNews(@Query("category") String category, @Query("token") String token);
}
