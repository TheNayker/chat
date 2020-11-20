package com.nayker.chat.service;


import com.nayker.chat.entity.DictionaryWordEntity;

import java.util.List;

public interface DictionaryService {
    List<DictionaryWordEntity> getDictionary();
    void addDictionaryWord(String word);
    void updateDictionaryWord(long id, String word);
    void deleteDictionaryWord(long id);
    void deleteAll();
}
