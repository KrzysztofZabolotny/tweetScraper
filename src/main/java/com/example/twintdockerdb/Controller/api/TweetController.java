package com.example.twintdockerdb.Controller.api;

import com.example.twintdockerdb.Interface.ITweetService;
import com.example.twintdockerdb.Models.Tweet;
import com.example.twintdockerdb.Services.TweetScraper;
import com.example.twintdockerdb.Utilities.TweetProcessing.ScrapingParameters;
import com.example.twintdockerdb.Utilities.TweetProcessing.TwintToolboxService;
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
    private final ScrapingParameters scrapingParameters = new ScrapingParameters();

    @Autowired
    public TweetController(ITweetService service) {
        this.service = service;
    }

    @GetMapping("/scrape/{hashtag}/{quantity}")
    public String scrapeTweets(@PathVariable String hashtag, @PathVariable int quantity) {

        ExecutorService executorService = Executors.newFixedThreadPool(scrapingParameters.numberOfAvailableProcessors);
        for (int i = 0; i <= scrapingParameters.getNumberOfThreadsToAdd(quantity); i++) {
            TweetScraper tweetScraper = new TweetScraper(service);
            tweetScraper.setHashtag(hashtag);
            if (i != scrapingParameters.getNumberOfThreadsToAdd(quantity)) tweetScraper.setQuantity(scrapingParameters.maximumNumberOfInquiries);
            else tweetScraper.setQuantity(scrapingParameters.getReminder(quantity));
            executorService.submit(tweetScraper);
        }
        executorService.shutdown();

        return "The report will be sent to your email after the process is finished(approx " + TwintToolboxService.calculateScrapingAndAnalysisTime(quantity) + " seconds)";
    }

    @GetMapping("/allTweets")
    List<Tweet> getAllTweetsFromDatabase() {

        return service.findAll();
    }

    @GetMapping("/test")
    String test() {
        return "test";
    }

}

