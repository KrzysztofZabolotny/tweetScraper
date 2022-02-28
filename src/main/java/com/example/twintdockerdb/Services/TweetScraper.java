package com.example.twintdockerdb.Services;

import com.example.twintdockerdb.Interface.ITweetService;
import com.example.twintdockerdb.Models.Tweet;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import static com.example.twintdockerdb.Utilities.TweetProcessing.tweetFromLine;

@Service
public class TweetScraper implements Runnable{

    private final ITweetService service;
    String hashtag;
    int quantity;


    public TweetScraper(ITweetService service) {
        this.service = service;
    }

    @Override
    public void run() {
        String command = "twint -s " + hashtag;
        int counter = 0;
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                counter++;
                Tweet tweet = tweetFromLine(line, hashtag);
                if (tweet != null) service.saveTweet(tweet);
                if (counter == quantity) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}