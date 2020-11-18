package com.nayker.chat.service;

import com.nayker.chat.dto.Message;
import org.springframework.data.domain.Page;


public interface DictionaryService {

    void addDictionaryWord(String word);
}
