package com.example.twintdockerdb.Controller.api;

import com.example.twintdockerdb.Interface.ITweetService;
import com.example.twintdockerdb.Models.Tweet;
import com.example.twintdockerdb.Utilities.SentimentAnalysis.SentimentAnalyzer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SentimentController {


    private final SentimentAnalyzer sentimentAnalyzer;
    private final ITweetService service;

    public SentimentController(SentimentAnalyzer sentimentAnalyzer, ITweetService service) {
        this.sentimentAnalyzer = sentimentAnalyzer;
        this.service = service;
    }

    @GetMapping("/analysis/{line}")
    String getSentimentFromLine(@PathVariable String line) {
        return sentimentAnalyzer.getSentiment(line);

    }

    @GetMapping("/analysis/allTweets")
    List<String> getAnalysisOfAllTweets() {

        List<Tweet> tweets = service.findAll();
        List<String> analysis = new ArrayList<>();
        for (Tweet t : tweets) {

            analysis.add(
                    sentimentAnalyzer.getSentiment(t.getContent())
                            + " " + t.getContent()
            );
        }

        return analysis;
    }
}
