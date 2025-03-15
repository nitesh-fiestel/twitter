package org.example.twitter.models;

import com.google.common.collect.Lists;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String content;
    public Integer userId;
    public List<String> hashTags = Lists.newArrayList();
}
