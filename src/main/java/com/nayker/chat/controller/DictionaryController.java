package com.nayker.chat.controller;

import com.nayker.chat.dto.Dictionary;
import com.nayker.chat.form.DictionaryWordRequest;
import com.nayker.chat.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/chat/dictionary")
public class DictionaryController {

    private final DictionaryService service;

    @Autowired
    public DictionaryController(DictionaryService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Dictionary> getDictionary() {
        return service.getDictionary();
    }

    @PostMapping("/")
    public Dictionary addDictionary(
            @Valid @RequestBody DictionaryWordRequest request) {
        return service.addDictionaryWord(request.getWord());
    }

    @PutMapping("/{id}")
    public Dictionary updateDictionary(
            @PathVariable("id") long id,
           @Valid @RequestBody DictionaryWordRequest request) {
         return service.updateDictionaryWord(id, request.getWord());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDictionary(@PathVariable("id") long id) {
        service.deleteDictionaryWord(id);
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        service.deleteAll();
    }
}

