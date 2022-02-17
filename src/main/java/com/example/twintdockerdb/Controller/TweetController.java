package com.example.twintdockerdb.Controller;

import com.example.twintdockerdb.Interface.ITweetService;
import com.example.twintdockerdb.Models.Tweet;
import com.example.twintdockerdb.Services.TwintToolboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TweetController {

    private final ITweetService service;
    private final TwintToolboxService twintService;

    @Autowired
    public TweetController(ITweetService service, TwintToolboxService twintService) {
        this.service = service;
        this.twintService = twintService;
    }

    @GetMapping("/scrape/{hashtag}/{quantity}")
    public String scrapeTweets(@PathVariable String hashtag, @PathVariable int quantity) {

        List<Tweet> tweets = twintService.scrapeTweet(hashtag, quantity);
        service.saveAll(tweets);
        return "Scraped: " +tweets.size() + " tweets with " + hashtag + " hashtag";
    }

    @GetMapping("/allTweets")
    List<Tweet> getAllTweetsFromDatabase() {

        return service.findAll();
    }
}
