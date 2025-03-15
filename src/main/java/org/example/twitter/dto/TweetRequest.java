package org.example.twitter.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class TweetRequest {
    public String content;
    public List<String> hashTags = Lists.newArrayList();
    public Integer userId;
}
