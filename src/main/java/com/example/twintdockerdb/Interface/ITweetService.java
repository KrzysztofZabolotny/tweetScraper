package com.example.twintdockerdb.Interface;

import com.example.twintdockerdb.Models.Tweet;

import java.util.List;

public interface ITweetService {

    void saveTweet(Tweet tweet);
    List<Tweet> findAll();
    void saveAll(List<Tweet> tweets);
}
