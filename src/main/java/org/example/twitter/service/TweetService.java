package org.example.twitter.service;

import org.example.twitter.dto.TweetRequest;
import org.example.twitter.models.Tweet;
import org.example.twitter.repository.TweetRepository;
import org.example.twitter.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TweetService {

    private final TweetRepository tweetRepository;


    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public Long tweet(TweetRequest req) {
       return tweetRepository.createTweet(req);
    }



}
