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

    @GetMapping("chat/messages")
    public Page<Message> getMessages(
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        return service.getMessages(page);
    }

    @PostMapping("chat/send")
    public Message send(
            @RequestParam("name") String name,
            @RequestParam("content") String content
    ) {
        return service.send(name, content);
    }

    @PutMapping("chat/{id}")
    public Message update(
            @PathVariable("id") long id,
            @RequestParam("name") String name,
            @RequestParam("content") String content
    ) {
        return service.update(id, name, content);
    }

    @DeleteMapping("chat/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        return service.delete(id);
    }

    @DeleteMapping("chat/messages")
    public ResponseEntity<String> deleteAll() {
        return service.deleteAll();
    }
}
