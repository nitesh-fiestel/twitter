package org.example.twitter.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.errors.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.example.twitter.dto.FollowRequest;
import org.example.twitter.dto.SignupRequest;
import org.example.twitter.dto.response.FollowerResponse;
import org.example.twitter.models.Follower;
import org.example.twitter.models.Tweet;
import org.example.twitter.models.User;
import org.example.twitter.service.UserService;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @UnitOfWork
    @Path("/signup")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signup(SignupRequest request) {
        Long userId;

        try {
            userId = userService.signup(request);
//            FollowerResponse resp = userService.getFollowers(userId);
        }  catch (Exception e) {
            // Return a custom error response
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Signup failed")
                    .build();
        }
        return Response.ok(userId).build();
    }

    @POST
    @Path("/follow")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response follow(FollowRequest request) {

        try {
            userService.follow(request);
        }  catch (Exception e) {
            // Return a custom error response
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
        return Response.ok().build();
    }

    @GET
    @UnitOfWork
    @Path("/followers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response followers(@QueryParam("userId") Long userId) {
        FollowerResponse response;
        try {
            response = userService.getFollowers(userId);
            int a = 1;
        }  catch (Exception e) {
            // Return a custom error response
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Fetching followers failed")
                    .build();


        }
        return Response.ok(response).build();
    }

    @GET
    @UnitOfWork
    @Path("/{userId}/tweets")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTweets(@PathParam("userId") Long userId) {
        List<Tweet> tweets;
        try {
            tweets = userService.findTweetsByUserId(userId);
        }  catch (Exception e) {
            // Return a custom error response
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Getting tweets failed")
                    .build();
        }
        return Response.ok(tweets).build();
    }

    @GET
    @UnitOfWork
    @Path("/{userId}/feed")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getFeed(@PathParam("userId") Long userId) {
        List<Tweet> tweets;
        try {
            tweets = userService.getFeed(userId);
        }  catch (Exception e) {
            // Return a custom error response
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Getting users feed failed")
                    .build();
        }
        return Response.ok(tweets).build();
    }

}
