package com.example.twintdockerdb.Services;

import com.example.twintdockerdb.Interface.ITweetService;
import com.example.twintdockerdb.Models.Tweet;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.example.twintdockerdb.Utilities.TweetProcessing.tweetFromLine;

@Service
public class TwintToolboxService {

    private final ITweetService service;

    public TwintToolboxService(ITweetService service) {
        this.service = service;
    }


    public int scrapeTweet(String hashtag, int quantity){

        String command = "twint -s " + hashtag;
        int counter = 0;
        try {
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                counter++;
                Tweet tweet = tweetFromLine(line, hashtag);
                if(tweet!=null) service.saveTweet(tweet);
                if (counter == quantity) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return service.findAll().size();
    }
}
