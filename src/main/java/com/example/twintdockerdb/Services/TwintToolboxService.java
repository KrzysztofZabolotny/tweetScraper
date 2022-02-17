package com.example.twintdockerdb.Services;

import com.example.twintdockerdb.Models.Tweet;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.example.twintdockerdb.Utilities.TweetProcessing.tweetFromLine;

@Service
public class TwintToolboxService {


    public List<Tweet> scrapeTweet(String hashtag, int quantity){

        String command = "twint -s " + hashtag;

        List<Tweet> tweets = new ArrayList<>();
        int counter = 0;
        try {
            Process proc = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                counter++;
                Tweet tweet = tweetFromLine(line, hashtag);
                if(tweet!=null) tweets.add(tweet);
                if (counter == quantity) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tweets;
    }
}
