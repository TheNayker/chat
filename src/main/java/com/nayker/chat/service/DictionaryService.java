package com.nayker.chat.service;


import com.nayker.chat.dto.Dictionary;

import java.util.List;

public interface DictionaryService {
    List<Dictionary> getDictionary();
    Dictionary addDictionaryWord(String word);
    Dictionary updateDictionaryWord(long id, String word);
    void deleteDictionaryWord(long id);
    void deleteAll();
}
