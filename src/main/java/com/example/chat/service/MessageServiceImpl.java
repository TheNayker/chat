package com.example.chat.service;

import com.example.chat.dto.Message;
import com.example.chat.entity.MessageEntity;
import com.example.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class MessageServiceImpl implements MessageService {

    private static final int PER_PAGE = 10;

    private final MessageRepository repository;

    @Autowired
    public MessageServiceImpl(MessageRepository repository) {

        this.repository = repository;
    }

    @Override
    public Page<Message> paginateAll(int page) {
        return repository
                .findAll(
                        PageRequest.of(
                                page,
                                PER_PAGE,
                                Sort.by(Sort.Direction.DESC, "createdAt")
                        )
                )
                .map(Message::fromEntity);
    }

    @Override
    public Message send(String name, String content) {
        MessageEntity message = new MessageEntity();
        message.setName(name);
        message.setContent(content);
        message.setCreatedAd(ZonedDateTime.now());

        repository.save(message);

        return Message.fromEntity(message);
    }

    @Override
    public Message update(int id, String name, String content) {
        MessageEntity message = new MessageEntity();
        message.setId((long) id);
        message.setName(name);
        message.setContent(content);
        message.setCreatedAd(ZonedDateTime.now());

        repository.save(message);

        return Message.fromEntity(message);

    }

    @Override
    public Message delete(int id, String name, String content) {
        MessageEntity message = new MessageEntity();
        message.setId((long) id);
        message.setName(name);
        message.setContent(content);
        message.setCreatedAd(ZonedDateTime.now());

        repository.save(message);

        return Message.fromEntity(message);
    }
}
