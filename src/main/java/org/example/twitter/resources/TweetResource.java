package org.example.twitter.resources;

import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;
import org.example.twitter.dto.SignupRequest;
import org.example.twitter.dto.TweetRequest;
import org.example.twitter.models.Tweet;
import org.example.twitter.service.TweetService;
import org.example.twitter.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
        Long tweetId;
        try {
            tweetId = tweetService.tweet(request);
        }  catch (Exception e) {
            // Return a custom error response
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Posting tweet failed")
                    .build();
        }
        return Response.ok(tweetId).build();
    }


}
