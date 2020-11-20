package com.nayker.chat.service;

import com.nayker.chat.entity.DictionaryWordEntity;
import com.nayker.chat.repository.DictionaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplacePublicService implements PublicContentService {

    private final DictionaryRepository repository;

    public ReplacePublicService (DictionaryRepository repository){
        this.repository = repository;
    }
    @Override
    public String getPublicContent(String message) {
        List<DictionaryWordEntity> dictionaryList = repository.findAll();
        String PublicContent = message;

        for (DictionaryWordEntity dictionary: dictionaryList) {
            var word = dictionary.getWord();
            PublicContent = message.replaceAll("//[a-z-A-Z]//", "*****");
        }
    return PublicContent;
    }

}
