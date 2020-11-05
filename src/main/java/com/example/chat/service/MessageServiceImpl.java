package com.example.chat.service;

import com.example.chat.dto.Message;
import com.example.chat.entity.MessageEntity;
import com.example.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

@Service
public class MessageServiceImpl implements MessageService {

    private static final int PER_PAGE = 10;

    private final MessageRepository repository;

    @Autowired
    public MessageServiceImpl(MessageRepository repository) {
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
        message.setContent(content);
        message.setCreatedAt(ZonedDateTime.now());

        repository.save(message);

        return Message.fromEntity(message);
    }

    @Override
    public Message update(long id, String name, String content) {
        MessageEntity message = repository.findById(id).orElseThrow();
        message.setName(name);
        message.setContent(content);

        repository.save(message);

        return Message.fromEntity(message);
    }

    @Override
    public ResponseEntity<String> delete(long id) {
        repository.deleteById(id);
        return new ResponseEntity<String>(
                "Message with id { " + id + " } was deleted",
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<String> deleteAll() {
        repository.deleteAll();
        return new ResponseEntity<String>(
                "All were deleted",
                HttpStatus.OK
        );
    }
}
