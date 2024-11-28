package com.keles.discord.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessageRequest {
    @JsonProperty("user")
    private User user;
    @JsonProperty("message")
    private String message;

}
