package com.example.chat.dto;

import com.example.chat.entity.MessageEntity;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Message {

    private Long id;
    private String name;
    private String content;
    private ZonedDateTime createdAt;

    public static Message fromEntity(MessageEntity entity) {
        Message message = new Message();
        message.setId(entity.getId());
        message.setName(entity.getName());
        message.setContent(entity.getContent());
        message.setCreatedAt(entity.getCreatedAt());

        return message;
    }
}
