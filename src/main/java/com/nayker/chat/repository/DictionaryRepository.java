package com.nayker.chat.repository;

import com.nayker.chat.entity.DictionaryWordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<DictionaryWordEntity, Long> {
    //none
}
