package org.example.twitter.repository;

import org.example.twitter.dto.TweetRequest;

public interface TweetRepository {
    void createTweet(TweetRequest tweetRequest);
    void deleteTweet(Integer tweetId);
}
