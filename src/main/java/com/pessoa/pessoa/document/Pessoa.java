package com.pessoa.pessoa.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    long id;
    String nome;
    Integer idade;




}

