package com.nayker.chat.service;

import com.nayker.chat.dto.Message;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;


public interface MessageService {
    Page<Message> getMessages(int page);

    Message send(UserDetails author, String content);

    Message update(long id, String content);

    void delete(long id);

    void deleteAll();

    void invalidateMessages();
}
