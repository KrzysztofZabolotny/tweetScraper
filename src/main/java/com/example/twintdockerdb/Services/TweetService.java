package com.example.twintdockerdb.Services;

import com.example.twintdockerdb.Interface.ITweetService;
import com.example.twintdockerdb.Models.Tweet;
import com.example.twintdockerdb.Repository.ITweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TweetService implements ITweetService {

    private final ITweetRepository repository;

    @Autowired
    public TweetService(ITweetRepository repository) {
        this.repository = repository;
    }


    @Override
    public void saveTweet(Tweet tweet) {

        if (tweet != null) repository.save(tweet);
    }

    @Override
    public List<Tweet> findAll() {
        return repository.findAll();
    }

    @Override
    public void saveAll(List<Tweet> tweets) {
        repository.saveAll(tweets);
    }

}
