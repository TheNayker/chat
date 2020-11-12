package com.nayker.chat.controller;

import com.nayker.chat.dto.Message;
import com.nayker.chat.entity.MessageRequest;
import com.nayker.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/chat/messages")
public class ChatController {

    private final MessageService service;

    @Autowired
    public ChatController(MessageService service) {
        this.service = service;
    }

    @GetMapping("{/id}")
    public Page<Message> getMessages(
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        return service.getMessages(page);
    }

    @PostMapping("{/id}")
    public Message send(@Valid @RequestBody MessageRequest messageRequest
    ) {
        return service.send(messageRequest.name, messageRequest.content);
    }

    @PutMapping("/{id}")
    public Message update(
            @PathVariable("id") long id,
            @Valid @RequestBody MessageRequest messageRequest
    ) {
        return service.update(id, messageRequest.name, messageRequest.content);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
         service.delete(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}