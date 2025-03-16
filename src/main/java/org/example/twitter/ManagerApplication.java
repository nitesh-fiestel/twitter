package org.example.twitter;


import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.twitter.models.Follower;
import org.example.twitter.models.Tweet;
import org.example.twitter.models.User;
import org.example.twitter.repository.FollowerRepository;
import org.example.twitter.repository.TweetRepository;
import org.example.twitter.repository.UserRepository;
import org.example.twitter.repository.impl.FollowerRepositoryImpl;
import org.example.twitter.repository.impl.TweetRepositoryImpl;
import org.example.twitter.repository.impl.UserRepositoryImpl;
import org.example.twitter.resources.TweetResource;
import org.example.twitter.resources.UserResource;
import org.example.twitter.service.TweetService;
import org.example.twitter.service.UserService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManagerApplication extends Application<ManagerConfiguration> {

    private final HibernateBundle<ManagerConfiguration> hibernateBundle = new HibernateBundle<>(User.class, Follower.class, Tweet.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(ManagerConfiguration configuration) {

            return configuration.getDatabase();
        }

        @Override
        public void configure(Configuration configuration) {
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        }
    };

    public static void main(String[] args) throws Exception {
        new ManagerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ManagerConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle); // Add Hibernate Bundle to Dropwizard
    }

    @Override
    public void run(ManagerConfiguration configuration, Environment environment) {
        final SessionFactory sessionFactory = hibernateBundle.getSessionFactory();
        final UserRepository userRepository = new UserRepositoryImpl(sessionFactory);
        final TweetRepository tweetRepository = new TweetRepositoryImpl(sessionFactory);
        final FollowerRepository followerRepository = new FollowerRepositoryImpl(sessionFactory);
        final UserService userService = new UserService(userRepository, followerRepository, tweetRepository);
        final TweetService tweetService = new TweetService(tweetRepository);

        environment.jersey().register(new UserResource(userService));
        environment.jersey().register( new TweetResource(tweetService));
    }


}


