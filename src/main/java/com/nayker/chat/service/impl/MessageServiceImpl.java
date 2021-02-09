package com.nayker.chat.service.impl;

import com.nayker.chat.dto.Message;
import com.nayker.chat.entity.MessageEntity;
import com.nayker.chat.repository.MessageRepository;
import com.nayker.chat.service.MessageService;
import com.nayker.chat.service.PublicContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;

@Service
public class MessageServiceImpl implements MessageService {

    private static final int PER_PAGE = 10;

    private final MessageRepository repository;
    private final PublicContentService publicContentService;

    @Autowired
    public MessageServiceImpl(
            MessageRepository repository,
            PublicContentService publicContentService
    ) {
        this.repository = repository;
        this.publicContentService = publicContentService;
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
    public Message send(UserDetails author, String content) {
        MessageEntity message = new MessageEntity();
        message.setName(author.getUsername());
        message.setOriginalContent(content);
        message.setContent(publicContentService.getPublicContent(content));
        message.setCreatedAt(ZonedDateTime.now());

        repository.save(message);

        return Message.fromEntity(message);
    }

    @Override
    public Message update(long id, String content) {
        MessageEntity message = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Message not found")
        );

        message.setOriginalContent(content);
        message.setContent(publicContentService.getPublicContent(content));

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

    @Override
    public void invalidateMessages() {
        repository.findAll().forEach(message -> {

            message.setContent(publicContentService.getPublicContent(message.getOriginalContent()));

            repository.save(message);
        });
    }
}
