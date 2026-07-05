package com.chatroom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chatroom {
    private String roomId;
    private String roomName;
    private boolean isPrivate;
    private String createdBy;
    private String createdAt;
    private List<String> members;

}
