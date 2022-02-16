package com.example.twintdockerdb.Interface;

import com.example.twintdockerdb.Models.Tweet;

import java.util.List;

public interface ITweetService {

    void saveTweet(Tweet tweet);
    List<Tweet> findAll();
    List<Tweet> scrapeTweets(String hashtag, int size);
    void saveAll(List<Tweet> tweets);
}
