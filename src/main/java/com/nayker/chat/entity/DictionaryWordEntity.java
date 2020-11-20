package com.nayker.chat.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "dictionary")
public class DictionaryWordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String word;
}
