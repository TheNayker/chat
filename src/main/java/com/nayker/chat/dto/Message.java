package com.nayker.chat.dto;

import com.nayker.chat.entity.MessageEntity;
import lombok.Data;
import java.time.ZonedDateTime;


@Data
public class Message {

    private Long id;
    private String name;
    private String content;
    private String public_content;
    private ZonedDateTime createdAt;

    public static Message fromEntity(MessageEntity entity) {
        Message message = new Message();
        message.setId(entity.getId());
        message.setName(entity.getName());
        message.setContent(entity.getContent());
        message.setPublic_content(entity.getPublic_content());
        message.setCreatedAt(entity.getCreatedAt());

        return message;
    }

}
