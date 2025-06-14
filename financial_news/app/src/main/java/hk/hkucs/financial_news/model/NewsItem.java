package hk.hkucs.financial_news.model;


public class NewsItem {
    private String source;
    private String headline;
    private String date;
    private String imageUrl;
    private String author;
    private String category;
    private String ticker; // Stock ticker (e.g., AAPL)
    private String sentiment; // Sentiment label (e.g., Bullish)
    private double sentimentScore; // Numerical sentiment score
    private Object extraData;

    public NewsItem(String source, String headline, String date) {
        this.source = source;
        this.headline = headline;
        this.date = date;
    }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getHeadline() { return headline; }
    public void setHeadline(String headline) { this.headline = headline; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getTicker() { return ticker; }
    public void setTicker(String ticker) { this.ticker = ticker; }

    public String getSentiment() { return sentiment; }
    public void setSentiment(String sentiment) { this.sentiment = sentiment; }

    public double getSentimentScore() { return sentimentScore; }
    public void setSentimentScore(double sentimentScore) { this.sentimentScore = sentimentScore; }

    public Object getExtraData() { return extraData; }
    public void setExtraData(Object extraData) { this.extraData = extraData; }
}
