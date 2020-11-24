package com.nayker.chat.service;

import com.nayker.chat.entity.DictionaryWordEntity;
import com.nayker.chat.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryRepository repository;

    @Autowired
    public DictionaryServiceImpl(DictionaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DictionaryWordEntity> getDictionary() {
        return repository.findAll();
    }

    @Override
    public void addDictionaryWord(String word) {
        var dictionary = new DictionaryWordEntity();
        dictionary.setWord(word);
        repository.save(dictionary);
    }

    @Override
    public void updateDictionaryWord(long id, String word) {
        var dictionary = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Word not found"));
        dictionary.setWord(word);
        repository.save(dictionary);

    }

    @Override
    public void deleteDictionaryWord(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
