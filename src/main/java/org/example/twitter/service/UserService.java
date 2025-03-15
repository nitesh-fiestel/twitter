package org.example.twitter.service;

import org.example.twitter.dto.FollowRequest;
import org.example.twitter.dto.SignupRequest;
import org.example.twitter.dto.response.FollowerResponse;
import org.example.twitter.repository.FollowerRepository;
import org.example.twitter.repository.UserRepository;


public class UserService {
    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;

    public UserService(UserRepository userRepository, FollowerRepository followerRepository) {
        this.userRepository = userRepository;
        this.followerRepository = followerRepository;
    }

    public Long signup(SignupRequest request) {
        return userRepository.signup(request);
    }

    public void follow(FollowRequest request) {
        followerRepository.follow(request);
    }
//    @Transactional
    public FollowerResponse getFollowers(Long userId) {
        FollowerResponse response = new FollowerResponse();
        response.setFollowers(followerRepository.getFollowers(userId));
        return response;
    }
}
