package com.nayker.chat.controller;

import com.nayker.chat.entity.DictionaryWordRequest;
import com.nayker.chat.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dictionary")
public class DictionaryController {

    private final DictionaryService service;

    @Autowired
    public DictionaryController(DictionaryService service) {
        this.service = service;
    }

    @PostMapping("/")
    public void addWord(@Valid @RequestBody DictionaryWordRequest request) {
        service.addDictionaryWord(request.word);
    }
}
