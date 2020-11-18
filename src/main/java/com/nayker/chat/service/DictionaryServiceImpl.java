package com.nayker.chat.service;


import com.nayker.chat.entity.DictionaryWordEntity;
import com.nayker.chat.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl implements DictionaryService{

    private final DictionaryRepository repository;

    @Autowired
    public DictionaryServiceImpl(DictionaryRepository repository) {
        this.repository = repository;
    }

    public void addDictionaryWord(String word) {
        var dictionary = new DictionaryWordEntity();
        dictionary.setWord(word);
        repository.save(dictionary);
    }
}
