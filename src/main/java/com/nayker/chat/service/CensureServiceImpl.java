package com.nayker.chat.service;

import com.nayker.chat.entity.DictionaryWordEntity;
import com.nayker.chat.repository.DictionaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CensureServiceImpl implements CensureService {

    private final DictionaryRepository repository;

    public CensureServiceImpl(DictionaryRepository repository) {
        this.repository = repository;
    }

    public String getCensureMessage(String message) {
        List<DictionaryWordEntity> dictionaryList = repository.findAll();
        String messageCensure = message;

        for (DictionaryWordEntity dictionary: dictionaryList) {
            var word = dictionary.word;
            messageCensure = message.replaceAll(word, "****");
        }
        return messageCensure;
    }
}
