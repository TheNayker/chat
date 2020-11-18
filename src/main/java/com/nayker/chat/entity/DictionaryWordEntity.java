package com.nayker.chat.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "dictionary")
public class DictionaryWordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String id;
    public String word;
}