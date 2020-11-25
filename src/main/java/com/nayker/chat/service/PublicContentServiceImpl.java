package com.nayker.chat.service;

import com.nayker.chat.entity.DictionaryWordEntity;
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

        for (DictionaryWordEntity dictionary : dictionaryList) {
            message = Pattern.compile(dictionary.getWord(),
                    Pattern.CASE_INSENSITIVE).matcher(message).replaceAll("*");

        }

        return message;
    }

}
