package org.example.twitter.dto.response;

import com.google.common.collect.Lists;
import lombok.Data;
import org.example.twitter.models.Follower;

import java.util.List;

@Data
public class FollowerResponse {
    private List<Follower> followers = Lists.newArrayList();
}
