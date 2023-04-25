package com.pessoa.pessoa.repository;

//********Query Dinamica *************


import com.pessoa.pessoa.document.Pessoa;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PessoacustomRepository {

    private final EntityManager em; // Pegar a permissão do jpa

    public PessoacustomRepository(EntityManager em) {
        this.em = em;
    }

    public List<Pessoa> find(Long id, String nome, Integer idade) {

     String query = "select P from Pessoa as P ";
     String condicao = "where";

     if(id != null){
         query += condicao + " P.id = :id ";
         condicao = "and";
     }

     if (nome != null){
         query += condicao + " P.nome = :nome ";
         condicao = "and";
     }

        if (idade != null){
            query += condicao + " P.idade = :idade";

        }

        // Passando nosso parametros para query dinamica

        var q = em.createQuery(query, Pessoa.class);

        if(id != null){
            q.setParameter("id", id);
        }

        if (nome != null){
            q.setParameter("nome", nome);

        }

        if (idade != null){
            q.setParameter("idade", idade);
        }

        // Executar a query

     return q.getResultList();

    }

}
