package org.example.twitter.repository;

import org.example.twitter.dto.TweetRequest;
import org.example.twitter.models.Tweet;

import java.util.List;
import java.util.Map;

public interface TweetRepository {
    Long createTweet(TweetRequest tweetRequest);
    void deleteTweet(Integer tweetId);
    Map<Long, List<Tweet>> findByUserIds(List<Long> userIds);
}
