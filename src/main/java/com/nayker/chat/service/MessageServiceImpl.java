package com.nayker.chat.service;

import com.nayker.chat.dto.Message;
import com.nayker.chat.entity.MessageEntity;
import com.nayker.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;

@Service
public class MessageServiceImpl implements MessageService {

    private static final int PER_PAGE = 10;

    private final MessageRepository repository;
    private final CensureService censureService;

    @Autowired
    public MessageServiceImpl(
            MessageRepository repository,
            CensureService censureService
    ) {
        this.censureService = censureService;
        this.repository = repository;
    }

    @Override
    public Page<Message> getMessages(int page) {
        return repository.findAll(
                PageRequest.of(
                        page,
                        PER_PAGE,
                        Sort.by(Sort.Direction.DESC, "createdAt")
                )
        ).map(Message::fromEntity);
    }

    @Override
    public Message send(String name, String content) {
        MessageEntity message = new MessageEntity();
        message.setName(name);
        message.setMessage(content);
        Message.setMessageCensure(censureService.getCensureMessage(content));
        message.setCreatedAt(ZonedDateTime.now());

        repository.save(message);

        return Message.fromEntity(message);
    }

    @Override
    public Message update(long id, String name, String content) {
        MessageEntity message = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Message not found")
        );
        message.setName(name);
        message.setContent(content);

        repository.save(message);

        return Message.fromEntity(message);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
