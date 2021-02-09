package com.nayker.chat.controller;

import com.nayker.chat.dto.Message;
import com.nayker.chat.form.MessageRequest;
import com.nayker.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/")
    public Page<Message> getMessages(
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        return service.getMessages(page);
    }

    @PostMapping("/")
    public Message send(@AuthenticationPrincipal UserDetails userDetails,
                        @Valid @RequestBody MessageRequest messageRequest) {
        return service.send(userDetails, messageRequest.getContent());
    }

    @PutMapping("/{id}")
    public Message update(@PathVariable("id") long id,
            @Valid @RequestBody MessageRequest messageRequest
    ) {
        return service.update(id, messageRequest.getContent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        service.deleteAll();
    }
}
