package com.example.twintdockerdb.Controller;

import com.example.twintdockerdb.Interface.ITweetService;
import com.example.twintdockerdb.Models.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TweetController {

    private final ITweetService service;

    @Autowired
    public TweetController(ITweetService service) {
        this.service = service;
    }

    @GetMapping("/scrape/{hashtag}/{size}")
    public void saveTweets(@PathVariable String hashtag, @PathVariable int size) {

        service.saveAll(service.scrapeTweets(hashtag, size));
    }

    @GetMapping("/allTweets")
    List<Tweet> getAllTweetsFromDatabase() {

        return service.findAll();
    }


}
