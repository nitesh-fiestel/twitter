package org.example.twitter.repository.impl;

import io.dropwizard.hibernate.AbstractDAO;
import org.example.twitter.dto.TweetRequest;
import org.example.twitter.models.Tweet;
import org.example.twitter.models.User;
import org.example.twitter.repository.TweetRepository;
import org.hibernate.SessionFactory;

public class TweetRepositoryImpl extends AbstractDAO<Tweet> implements TweetRepository {

    public TweetRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void createTweet(TweetRequest tweetRequest) {

    }

    @Override
    public void deleteTweet(Integer tweetId) {

    }
}
