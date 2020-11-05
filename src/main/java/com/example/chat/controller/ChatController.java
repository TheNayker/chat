package com.example.chat.controller;

import com.example.chat.dto.Message;
import com.example.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

    private final MessageService service;

    @Autowired
    public ChatController(MessageService service) {
        this.service = service;
    }

    @GetMapping("/messages")
    public Page<Message> getMessages(@RequestParam(defaultValue = "0") int page) {
        return service.getMessages(page);
    }

    @PostMapping("/messages/send")
    public Message send(@RequestParam String name, @RequestParam String content) {
        return service.send(name, content);
    }

    @PutMapping("/messages/{id}")
    public ResponseEntity<Message> update(
            @PathVariable long id,
            @RequestParam String name,
            @RequestParam String content
    ) {
        return service.update(id, name, content);
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<String> delete(
            @PathVariable long id,
            @RequestParam String name,
            @RequestParam String content
    ) {
        return service.delete(id, name, content);
    }
}
