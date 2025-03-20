package org.example.twitter.service;

import org.example.twitter.dto.FollowRequest;
import org.example.twitter.dto.SignupRequest;
import org.example.twitter.dto.response.FollowerResponse;
import org.example.twitter.models.Follower;
import org.example.twitter.models.Tweet;
import org.example.twitter.models.User;
import org.example.twitter.repository.FollowerRepository;
import org.example.twitter.repository.TweetRepository;
import org.example.twitter.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class UserService {
    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;
    private final TweetRepository tweetRepository;

    public UserService(UserRepository userRepository, FollowerRepository followerRepository, TweetRepository tweetRepository) {
        this.userRepository = userRepository;
        this.followerRepository = followerRepository;
        this.tweetRepository = tweetRepository;
    }

    public Long signup(SignupRequest request) {
        return userRepository.signup(request);
    }

    public void follow(FollowRequest request) {
        followerRepository.follow(request);
    }

    public FollowerResponse getFollowers(Long userId) {
        FollowerResponse response = new FollowerResponse();
        response.setFollowers(followerRepository.getFollowers(userId));
        return response;
    }

    public User findUser(String email) {
        return userRepository.findUser(email);
    }

    public List<Tweet> findTweetsByUserId(Long userId) {
        return tweetRepository.findByUserIds(new ArrayList<>(Arrays.asList(userId))).get(userId);
    }

    public List<Tweet> getFeed(Long userId) {
        List<Long> followingUserIds = followerRepository.getFollowing(userId).stream()
                .map(x->x.getFollowingId()).collect(Collectors.toList());
        List<Tweet> tweets = tweetRepository.findByUserIds(followingUserIds)
                .values().stream().flatMap(List::stream).collect(Collectors.toList());
        tweets.sort(Comparator.comparing(Tweet::getCreatedAt).reversed());
        return tweets;
    }
}
