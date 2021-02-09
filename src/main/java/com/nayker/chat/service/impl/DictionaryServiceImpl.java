package com.nayker.chat.service.impl;

import com.nayker.chat.dto.DictionaryWord;
import com.nayker.chat.entity.DictionaryWordEntity;
import com.nayker.chat.publisher.DictionaryPublisher;
import com.nayker.chat.repository.DictionaryRepository;
import com.nayker.chat.service.DictionaryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryRepository repository;
    private final DictionaryPublisher publisher;

    public DictionaryServiceImpl(DictionaryRepository repository, DictionaryPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @Override
    @Cacheable("dictionary-list")
    public List<DictionaryWord> getDictionary() {
        return repository.findAll()
                .stream()
                .map(DictionaryWord::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "dictionary-list", allEntries = true, beforeInvocation = true)
    public DictionaryWord addDictionaryWord(String word) {
        var dictionary = new DictionaryWordEntity();
        dictionary.setWord(word);
        repository.save(dictionary);

        publisher.publishChangedEvent();

        return DictionaryWord.fromEntity(dictionary);
    }

    @Override
    @CacheEvict(value = "dictionary-list", allEntries = true, beforeInvocation = true)
    public DictionaryWord updateDictionaryWord(long id, String word) {
        var dictionary = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Word not found"));

        dictionary.setWord(word);
        repository.save(dictionary);

        publisher.publishChangedEvent();

        return DictionaryWord.fromEntity(dictionary);
    }

    @Override
    @CacheEvict(value = "dictionary-list", allEntries = true, beforeInvocation = true)
    public void deleteDictionaryWord(long id) {
        repository.deleteById(id);

        publisher.publishChangedEvent();
    }

    @Override
    @CacheEvict(value = "dictionary-list", allEntries = true, beforeInvocation = true)
    public void deleteAll() {
        repository.deleteAll();

        publisher.publishChangedEvent();
    }

}
