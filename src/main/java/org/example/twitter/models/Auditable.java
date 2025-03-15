package org.example.twitter.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Auditable {

    public LocalDateTime createdAt = LocalDateTime.now();
    public String createBy;
}
