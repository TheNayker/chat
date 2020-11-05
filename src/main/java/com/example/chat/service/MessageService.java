package com.example.chat.service;

import com.example.chat.dto.Message;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface MessageService {
    Page<Message> getMessages(int page);
    Message send(String name, String content);
    ResponseEntity<Message> update(long id, String name, String content);
    ResponseEntity<String> delete(long id, String name, String content);
}
