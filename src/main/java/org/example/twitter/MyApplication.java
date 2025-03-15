package org.example.twitter;


import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.twitter.models.Follower;
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

public class MyApplication extends Application<MyConfiguration> {

    private final HibernateBundle<MyConfiguration> hibernateBundle = new HibernateBundle<>(User.class, Follower.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(MyConfiguration configuration) {

            return configuration.getDatabase(); // This retrieves the database configuration from the YAML file
        }

        @Override
        public void configure(Configuration configuration) {
            // Configure the dialect programmatically
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");  // Set dialect for MySQL 8
        }
    };

    public static void main(String[] args) throws Exception {
        new MyApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<MyConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle); // Add Hibernate Bundle to Dropwizard
    }

    @Override
    public void run(MyConfiguration configuration, Environment environment) {
        // You can get the SessionFactory instance from the HibernateBundle
        final SessionFactory sessionFactory = hibernateBundle.getSessionFactory();
        final UserRepository userRepository = new UserRepositoryImpl(sessionFactory);
        final TweetRepository tweetRepository = new TweetRepositoryImpl(sessionFactory);
        final FollowerRepository followerRepository = new FollowerRepositoryImpl(sessionFactory);
        final UserService userService = new UserService(userRepository, followerRepository);
        final TweetService tweetService = new TweetService(tweetRepository);

        // Register the resource
        environment.jersey().register(new UserResource(userService));
        environment.jersey().register( new TweetResource(tweetService));
        // Now you can use the SessionFactory to interact with the database
    }


}


