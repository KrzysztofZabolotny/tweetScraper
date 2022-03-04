package com.example.twintdockerdb.Utilities.SentimentAnalysis;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

public class Pipeline {

    private static final Properties properties;
    private static final String propertiesName = "tokenize, ssplit, pos, parse, sentiment";
    private static StanfordCoreNLP stanfordCoreNLP;

    static {
        properties = new Properties();
        properties.setProperty("annotators", propertiesName);
    }

    private Pipeline() {

    }

    public static StanfordCoreNLP getPipeline() {
        if (stanfordCoreNLP == null) {
            stanfordCoreNLP = new StanfordCoreNLP(properties);
        }
        return stanfordCoreNLP;
    }

}

