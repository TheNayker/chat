package com.nayker.chat.service.impl;

import com.nayker.chat.dto.DictionaryWord;
import com.nayker.chat.service.DictionaryService;
import com.nayker.chat.service.PublicContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


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

        for (DictionaryWord dictionaryWord : dictionaryList) {
            message = Pattern.compile(Pattern.quote(dictionaryWord.getWord()),
                    Pattern.CASE_INSENSITIVE).matcher(message).replaceAll("*");

        }

        return message;
    }

}
