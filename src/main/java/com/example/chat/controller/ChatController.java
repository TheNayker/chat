package com.example.chat.controller;

import com.example.chat.dto.Message;
import com.example.chat.entity.MessageEntity;
import com.example.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final MessageService service;

    @Autowired
    public ChatController(MessageService service) {
        this.service = service;
    }

    @GetMapping("/")
    public Page<Message> index(@RequestParam(defaultValue = "0") int page) {
        return service.paginateAll(page);
    }

    @PostMapping("/send")
    public Message send(@RequestParam String name, @RequestParam String content) {
        return service.send(name, content);
    }

}
