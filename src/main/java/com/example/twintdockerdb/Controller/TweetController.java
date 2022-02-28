package com.example.twintdockerdb.Controller;

import com.example.twintdockerdb.Interface.ITweetService;
import com.example.twintdockerdb.Models.Tweet;
import com.example.twintdockerdb.Services.TweetScraper;
import com.example.twintdockerdb.Services.TwintToolboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController()
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

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        for (int i = 0; i < 8; i++) {
            TweetScraper tweetScraper = new TweetScraper(service);
            tweetScraper.setHashtag(hashtag);
            tweetScraper.setQuantity(quantity);
            executorService.submit(tweetScraper);
        }
        return "The report will be sent to you on your email";
    }

    @GetMapping("/allTweets")
    List<Tweet> getAllTweetsFromDatabase() {

        return service.findAll();
    }

    @GetMapping("/test")
    String test() {
        return "test";
    }

    @GetMapping("/scrapeAll/{hashtag}/{quantity}")
    public boolean scrapeAllTweets(@PathVariable String hashtag, @PathVariable int quantity) {

        boolean done = false;
        int cycles = 0;

        while (!done) {
            int result = twintService.scrapeTweet(hashtag, quantity);
            if (result >= quantity) done = true;
            cycles++;
            System.out.println(cycles);
        }
        return done;
    }
}

