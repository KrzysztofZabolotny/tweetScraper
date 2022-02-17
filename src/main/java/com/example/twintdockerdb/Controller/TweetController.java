package com.example.twintdockerdb.Controller;

import com.example.twintdockerdb.Interface.ITweetService;
import com.example.twintdockerdb.Models.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.example.twintdockerdb.Utilities.TweetProcessing.tweetFromLine;

@RestController
public class TweetController {

    private final ITweetService service;

    @Autowired
    public TweetController(ITweetService service) {
        this.service = service;
    }

    @GetMapping("/scrape/{hashtag}/{size}")
    public int scrapeTweets(@PathVariable String hashtag, @PathVariable int size) {

        String command = "twint -s " + hashtag;
        int counter = 0;
        try {
            Process proc = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                counter++;
                service.saveTweet(tweetFromLine(line));
                if (counter == size) break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return counter;
    }

    @GetMapping("/allTweets")
    List<Tweet> getAllTweetsFromDatabase() {

        return service.findAll();
    }

    @GetMapping("/save")
    public void save(){

        List<Tweet> tweets = new ArrayList<>();

        tweets.add(new Tweet("1","2","3","4","5","6"));
        tweets.add(new Tweet("1","2","3","4","5","6"));
        tweets.add(new Tweet("1","2","3","4","5","6"));
        tweets.add(new Tweet("1","2","3","4","5","6"));

        service.saveAll(tweets);
        }


}
