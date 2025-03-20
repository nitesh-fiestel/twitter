package org.example.twitter.repository;

import org.example.twitter.dto.SignupRequest;
import org.example.twitter.dto.TweetRequest;
import org.example.twitter.models.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    Long signup(SignupRequest request);
    void tweet(TweetRequest request);
    User findUser(String email);
}
