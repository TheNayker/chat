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
    private final PublicContentService publicContentService;

    @Autowired
    public DictionaryServiceImpl(DictionaryRepository repository, PublicContentService publicContentService) {
        this.repository = repository;
        this.publicContentService = publicContentService;
    }

    @Override
    public List<DictionaryWordEntity> getDictionary() {
        return repository.findAll();
    }

    @Override
    public void addDictionaryWord(String word) {
        var dictionary = new DictionaryWordEntity();
        dictionary.setWord(word);
        dictionary.setWord(publicContentService.getPublicContent(word));
        repository.save(dictionary);
    }

    @Override
    public void updateDictionaryWord(long id, String word) {
        var dictionary = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Word not found"));
        dictionary.setWord(word);
        dictionary.setWord(publicContentService.getPublicContent(word));
        repository.save(dictionary);

    }

    @Override
    public void deleteDictionaryWord(long id) {
        repository.deleteById(id);
        getDictionary();
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
