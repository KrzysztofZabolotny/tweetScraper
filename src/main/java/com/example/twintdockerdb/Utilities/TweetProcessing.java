package com.example.twintdockerdb.Utilities;

import com.example.twintdockerdb.Models.Tweet;

public class TweetProcessing {

    public static Tweet tweetFromLine(String line, String hashtag) {
        String[] arr = line.split(" ");
        if (line.equals("[!] No more data! Scraping will stop now.") || line.equals("found 0 deleted tweets in this search."))
            return null;

        for (int i = 6; i < arr.length; i++) {
            if (arr[i].startsWith("@") ||
                    arr[i].startsWith("#") ||
                    arr[i].startsWith("http") ||
                    arr[i].startsWith("https")) {

            } else arr[5] += " " + arr[i];
        }
        Tweet tweet;
        try {
            if (arr[5].length() < 255) {
                tweet = new Tweet(hashtag,arr[0],
                        arr[1],
                        arr[2],
                        arr[3],
                        arr[4],
                        arr[5]);
                return tweet;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
