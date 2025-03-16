package org.example.twitter.repository.impl;

import io.dropwizard.hibernate.AbstractDAO;
import org.apache.commons.lang3.StringUtils;
import org.example.twitter.dto.TweetRequest;
import org.example.twitter.models.Follower;
import org.example.twitter.models.Tweet;
import org.example.twitter.models.User;
import org.example.twitter.repository.TweetRepository;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TweetRepositoryImpl extends AbstractDAO<Tweet> implements TweetRepository {

    public TweetRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Long createTweet(TweetRequest tweetRequest) {
        Tweet tweet = new Tweet();
        tweet.setContent(tweetRequest.getContent());
        tweet.setUserId(tweetRequest.userId);
        tweet = persist(tweet);
        return tweet.getId();
    }

    @Override
    public void deleteTweet(Integer tweetId) {

    }

    @Override
    public Map<Long, List<Tweet>> findByUserIds(List<Long> userIds) {

        List<Tweet> tweets = (List<Tweet>) namedQuery("Tweet.findByUserIds")
                .setParameter("userIds", userIds)
                .getResultList();
        return tweets.stream().collect(Collectors.groupingBy(Tweet::getUserId));
    }
}
