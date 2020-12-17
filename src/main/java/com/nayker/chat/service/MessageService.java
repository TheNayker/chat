package com.nayker.chat.service;

import com.nayker.chat.dto.Message;
import org.springframework.data.domain.Page;


public interface MessageService {
    Page<Message> getMessages(int page);

    Message send(String name, String content);

    Message update(long id, String name, String content);

    void delete(long id);

    void deleteAll();

    void invalidateMessages();
}
