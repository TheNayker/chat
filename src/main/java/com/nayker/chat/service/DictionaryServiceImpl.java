package com.nayker.chat.service;

import com.nayker.chat.dto.Dictionary;
import com.nayker.chat.entity.DictionaryWordEntity;
import com.nayker.chat.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryRepository repository;

    @Autowired
    public DictionaryServiceImpl(DictionaryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable("dictionary-list")
    public List<Dictionary> getDictionary() {
        return repository.findAll()
                .stream()
                .map(Dictionary::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict("dictionary-list")
    public Dictionary addDictionaryWord(String word) {
        var dictionary = new DictionaryWordEntity();
        dictionary.setWord(word);
        repository.save(dictionary);

        return Dictionary.fromEntity(dictionary);
    }

    @Override
    @CacheEvict("dictionary-list")
    public Dictionary updateDictionaryWord(long id, String word) {
        var dictionary = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Word not found"));
        dictionary.setWord(word);
        repository.save(dictionary);

        return Dictionary.fromEntity(dictionary);
    }

    @Override
    @CacheEvict("dictionary-list")
    public void deleteDictionaryWord(long id) {
        repository.deleteById(id);

    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
