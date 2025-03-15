package org.example.twitter.service;

import org.example.twitter.repository.TweetRepository;
import org.example.twitter.repository.UserRepository;

public class TweetService {

    private final TweetRepository tweetRepository;


    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }
}
