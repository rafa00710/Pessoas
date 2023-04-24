package com.pessoa.pessoa.controller;


import com.pessoa.pessoa.document.Pessoa;
import com.pessoa.pessoa.repository.PessoaRepository;
import com.pessoa.pessoa.repository.PessoacustomRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor

public class PessoaController {

    PessoaRepository repository;
    private final PessoacustomRepository pessoacustomRepository;

    // listar Pessoas
    @GetMapping("/pessoa")
    public List<Pessoa> getAllPessoas() {
        return repository.findAll();
    }

    @GetMapping("/pessoa/{id}")
    public Pessoa getPessoaById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PostMapping("/pessoa")
    public Pessoa savePessoa(@RequestBody Pessoa pessoa) {
        return repository.save(pessoa);

    }

    @DeleteMapping("/pessoa/{id}")
    public void deletePessoa(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/pessoa/{id}")
    public Pessoa atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) throws Exception {
        //  var atualizar = getPessoaById(id);

        var atualizar = repository.findById(id);

        if (atualizar.isPresent()) {
           // var salvarPessoa = new Pessoa();
            var salvarPessoa = atualizar.get();
            salvarPessoa.setId(id);
            salvarPessoa.setIdade(pessoa.getIdade());
            salvarPessoa.setNome(pessoa.getNome());

            repository.save(salvarPessoa);
             return salvarPessoa;


        } else {
            throw new Exception("Pessoa não encontrada");
        }

        // Fazendo a busca utilizando outros metodos

    }

    //Fazendo o filtro, passando o nome!

    @GetMapping("/filtro")
    public List<String> findPersonByName(@RequestParam("name") String name){
        return this.repository.findByNomeContains(name)
                .stream()
                .map(Pessoa::getNome)
                .collect(Collectors.toList());


    }
        // Filtro pela Query Dinamica

    @GetMapping("/filtro/custom")
    public List<String> findPersonCustom(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "idade", required = false) Integer idade
    ){

        return this.pessoacustomRepository.find(id, name, idade)
                .stream()
                .map(Pessoa::getNome)
                .collect(Collectors.toList());


    }
}
