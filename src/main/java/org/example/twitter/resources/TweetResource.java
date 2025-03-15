package org.example.twitter.resources;

import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;
import org.example.twitter.dto.SignupRequest;
import org.example.twitter.dto.TweetRequest;
import org.example.twitter.service.TweetService;
import org.example.twitter.service.UserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tweet")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class TweetResource {

    private final TweetService tweetService;

    public TweetResource(TweetService tweetService) {
        this.tweetService = tweetService;
    }


    @POST
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response tweet(TweetRequest request) {

        return Response.ok().build();
    }
}
