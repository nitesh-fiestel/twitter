package org.example.twitter.repository;

import org.example.twitter.dto.FollowRequest;
import org.example.twitter.models.Follower;

import java.util.List;

public interface FollowerRepository {
    void follow(FollowRequest request);
    void unfollow(Long followerId, Long followingId);
    List<Follower> getFollowers(Long userId);
    List<Follower> getFollowing(Long userId);

}
