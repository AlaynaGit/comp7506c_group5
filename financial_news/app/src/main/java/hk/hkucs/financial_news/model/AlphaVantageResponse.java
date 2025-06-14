package hk.hkucs.financial_news.model;

import java.util.List;


public class AlphaVantageResponse {
    public String items;
    public String sentiment_score_definition;
    public String relevance_score_definition;
    public List<FeedItem> feed;

    public static class FeedItem {
        public String title;
        public String url;
        public String time_published;
        public List<String> authors;
        public String summary;
        public String banner_image;
        public String source;
        public String category_within_source;
        public String source_domain;
        public List<Topic> topics;
        public double overall_sentiment_score;
        public String overall_sentiment_label;
        public List<TickerSentiment> ticker_sentiment;

        public static class Topic {
            public String topic;
            public String relevance_score;
        }

        public static class TickerSentiment {
            public String ticker;
            public String relevance_score;
            public String ticker_sentiment_score;
            public String ticker_sentiment_label;
        }
    }
}