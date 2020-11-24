package com.nayker.chat.service;

import com.nayker.chat.entity.DictionaryWordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PublicContentServiceImpl implements PublicContentService {

    private final DictionaryService service;

    @Autowired
    public PublicContentServiceImpl(DictionaryService service) {
        this.service = service;
    }

    @Override
    public String getPublicContent(String message) {
        var dictionaryList = service.getDictionary();

        for (DictionaryWordEntity dictionary : dictionaryList) {
            message = message.replace(dictionary.getWord(), "*");
        }

        return message;
    }

}
