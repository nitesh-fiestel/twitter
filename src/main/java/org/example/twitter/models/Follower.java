package org.example.twitter.models;

import lombok.Data;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity(name = "follower")
@NamedQueries({
        @NamedQuery(
                name = "Follower.findFollower",
                query = "SELECT u FROM follower u WHERE u.followingId = :followingId"
        ),
        @NamedQuery(
                name = "Follower.findFollowing",
                query = "SELECT u FROM follower u WHERE u.followerId = :followerId"
        )
})
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long followerId;
    public Long followingId;
    public LocalDateTime timestamp = LocalDateTime.now();
}
