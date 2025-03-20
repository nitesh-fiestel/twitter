package org.example.twitter.repository.impl;

import io.dropwizard.hibernate.AbstractDAO;
import org.example.twitter.dto.SignupRequest;
import org.example.twitter.dto.TweetRequest;
import org.example.twitter.models.User;
import org.example.twitter.repository.UserRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserRepositoryImpl extends AbstractDAO<User> implements UserRepository {

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Long signup(SignupRequest request) {
        User user = new User();
        user.setContact(request.getContact());
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        return persist(user).getId();

    }

    @Override
    public void tweet(TweetRequest request) {

    }

    @Override
    public User findUser(String email) {
        return (User)namedQuery("User.findUser")
                .setParameter("email", email)
                .getSingleResult();
    }
}
