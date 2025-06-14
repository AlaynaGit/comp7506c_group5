package hk.hkucs.financial_news.services;

import hk.hkucs.financial_news.interfaces.AlphaVantageService;
import hk.hkucs.financial_news.interfaces.CoinMarketCapService;
import hk.hkucs.financial_news.interfaces.FinnhubService;
import hk.hkucs.financial_news.interfaces.YahooFinanceService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit alphaRetrofit, finnhubRetrofit, yahooRetrofit, cmcRetrofit;

    public static AlphaVantageService getAlphaVantageService() {
        if (alphaRetrofit == null) {
            alphaRetrofit = new Retrofit.Builder()
                    .baseUrl("https://www.alphavantage.co/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return alphaRetrofit.create(AlphaVantageService.class);
    }

    public static FinnhubService getFinnhubService() {
        if (finnhubRetrofit == null) {
            finnhubRetrofit = new Retrofit.Builder()
                    .baseUrl("https://finnhub.io/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return finnhubRetrofit.create(FinnhubService.class);
    }

    public static YahooFinanceService getYahooFinanceService() {
        if (yahooRetrofit == null) {
            yahooRetrofit = new Retrofit.Builder()
                    .baseUrl("https://apidojo-yahoo-finance-v1.p.rapidapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return yahooRetrofit.create(YahooFinanceService.class);
    }

    public static CoinMarketCapService getCoinMarketCapService() {
        if (cmcRetrofit == null) {
            cmcRetrofit = new Retrofit.Builder()
                    .baseUrl("https://sandbox-api.coinmarketcap.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return cmcRetrofit.create(CoinMarketCapService.class);
    }
}