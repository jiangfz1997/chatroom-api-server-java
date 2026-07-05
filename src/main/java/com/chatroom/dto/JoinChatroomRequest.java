package com.chatroom.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JoinChatroomRequest {

    @NotBlank(message = "chatroomId is required")
    private String chatroomId;
}