package com.example.chat.service;

import com.example.chat.dto.Message;
import org.springframework.data.domain.Page;
import com.example.chat.entity.MessageEntity;

public interface MessageService {
    Page<Message> paginateAll(int page);
    Message send(String name, String content);
    Message update(int id, String name, String content);
    Message delete(int id, String name, String content);
}
