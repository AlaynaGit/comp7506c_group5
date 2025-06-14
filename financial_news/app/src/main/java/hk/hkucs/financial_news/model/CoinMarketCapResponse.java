package hk.hkucs.financial_news.model;

import java.util.List;

public class CoinMarketCapResponse {
    public List<CryptoData> data;

    public static class CryptoData {
        public String name;
        public Quote quote;
        public String lastUpdated;

        public static class Quote {
            public USD USD;

            public static class USD {
                public double price;
            }
        }
    }
}