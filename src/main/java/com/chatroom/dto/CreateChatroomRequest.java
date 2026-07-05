package com.chatroom.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateChatroomRequest {

    @NotBlank(message = "name is required")
    private String name;

    private boolean isPrivate;
}