package com.example.twintdockerdb.Utilities.TweetProcessing;

public class ScrapingParameters {

    public static final int numberOfAvailableProcessors = Runtime.getRuntime().availableProcessors();
    public final int maximumNumberOfInquiries = 100;

    public int getNumberOfThreadsToAdd(int quantity) {
        return quantity / maximumNumberOfInquiries;
    }

    public int getReminder(int quantity) {
        return quantity % maximumNumberOfInquiries;
    }
}