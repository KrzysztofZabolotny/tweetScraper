package com.example.twintdockerdb.Services;

import com.example.twintdockerdb.Interface.ITweetService;
import com.example.twintdockerdb.Models.Tweet;
import com.example.twintdockerdb.Repository.ITweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.example.twintdockerdb.Utilities.TweetProcessing.tweetFromLine;


@Service
public class TweetService implements ITweetService {

    private final ITweetRepository repository;

    @Autowired
    public TweetService(ITweetRepository repository) {
        this.repository = repository;
    }


    @Override
    public void saveTweet(Tweet tweet) {
        repository.save(tweet);
    }

    @Override
    public List<Tweet> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Tweet> scrapeTweets(String hashtag, int size) {
        String command = "twint -s " + hashtag;
        int counter = 0;
        List<Tweet> tweets = new ArrayList<>();
        try {
            Process proc = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;


            while ((line = reader.readLine()) != null) {
                counter++;
                tweets.add(tweetFromLine(line));
                if (counter == size) break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return tweets;
    }

    @Override
    public void saveAll(List<Tweet> tweets) {
        repository.saveAll(tweets);
    }
}
