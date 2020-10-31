package com.example.chat.dto;

import com.example.chat.entity.MessageEntity;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Message {

    private Long id;
    private String name;
    private String content;
    private ZonedDateTime createdAd;

    public static Message fromEntity(MessageEntity entity) {
        Message message = new Message();
        message.setId(entity.getId());
        message.setName(entity.getName());
        message.setContent(entity.getContent());
        message.setCreatedAd(entity.getCreatedAd());

        return message;
    }

}
