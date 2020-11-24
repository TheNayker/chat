package com.nayker.chat.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nayker.chat.entity.MessageEntity;
import lombok.Data;
import java.time.ZonedDateTime;


@Data
public class Message {

    private Long id;
    private String name;
    @JsonIgnore
    private String originalContent;
    private String content;
    private ZonedDateTime createdAt;

    public static Message fromEntity(MessageEntity entity) {
        Message message = new Message();
        message.setId(entity.getId());
        message.setName(entity.getName());
        message.setOriginalContent(entity.getOriginalContent());
        message.setContent(entity.getContent());
        message.setCreatedAt(entity.getCreatedAt());

        return message;
    }

}
