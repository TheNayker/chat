package com.nayker.chat.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String originalContent;
    private String content;
    private ZonedDateTime createdAt;
}
