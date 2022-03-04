package com.example.twintdockerdb.Utilities.TweetProcessing;

public abstract class TwintToolboxService {

    public static int calculateScrapingAndAnalysisTime(int quantity) {

        final double timeToScrapeOneTweet = 0.0015625;
        final double timeToAnalyzeOneTweet = 0.12;
        return (int) (quantity * timeToScrapeOneTweet * timeToAnalyzeOneTweet);
    }



}




