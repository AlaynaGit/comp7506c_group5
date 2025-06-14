package hk.hkucs.financial_news.model;

public class FinnhubNews {
    public String category;
    public String datetime;
    public String headline;
    public String id;
    public String image;
    public String related; // Could include tickers
    public String source;
    public String summary;
    public String url;
    public String sentiment; // Optional, if Finnhub provides
    public double sentimentScore; // Optional
}