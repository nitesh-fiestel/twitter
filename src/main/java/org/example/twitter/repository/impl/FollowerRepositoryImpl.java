package org.example.twitter.repository.impl;

import io.dropwizard.hibernate.AbstractDAO;
import org.example.twitter.dto.FollowRequest;
import org.example.twitter.models.Follower;
import org.example.twitter.repository.FollowerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class FollowerRepositoryImpl extends AbstractDAO<Follower>  implements FollowerRepository {

    public FollowerRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void follow(FollowRequest request) {
        Follower follower = new Follower();
        follower.setFollowerId(request.getFollowerId());
        follower.setFollowingId(request.getFollowingId());
        follower.setTimestamp(LocalDateTime.now());
        persist(follower);
    }

    @Override
    public void unfollow(Long followerId, Long followingId) {

    }

    @Override
    public List<Follower> getFollowers(Long userId) {
        return (List<Follower>) namedQuery("Follower.findFollower")
                .setParameter("followingId", userId)
                .getResultList();
    }

    @Override
    public List<Follower> getFollowing(Long userId) {
        return (List<Follower>) namedQuery("Follower.findFollowing")
                .setParameter("followerId", userId)
                .getResultList();
    }
}
