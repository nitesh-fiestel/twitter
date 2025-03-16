package org.example.twitter.models;

import com.google.common.collect.Lists;
import lombok.Data;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "tweet")
@NamedQueries({
        @NamedQuery(
                name = "Tweet.findByUserIds",
                query = "SELECT t FROM tweet t WHERE t.userId in :userIds"
        )
})
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String content;
    public Long userId;
    public LocalDateTime createdAt = LocalDateTime.now();
}
