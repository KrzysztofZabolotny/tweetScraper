package com.example.twintdockerdb.Utilities.SentimentAnalysis;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentimentAnalyzer {

    public String getSentiment(String input) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();

        CoreDocument coreDocument = new CoreDocument(input);

        stanfordCoreNLP.annotate(coreDocument);
        List<CoreSentence> sentences = coreDocument.sentences();

        int sentimentValue = 0;


        for (CoreSentence sentence : sentences) {
            String sentiment = sentence.sentiment();
            if (sentiment.equals("Positive")) sentimentValue++;
            if (sentiment.equals("Negative")) sentimentValue--;
        }

        if (sentimentValue > 0) return "Positive";
        if (sentimentValue < 0) return "Negative";
        else return "Neutral";
    }
}
