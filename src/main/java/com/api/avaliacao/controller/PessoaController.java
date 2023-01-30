package com.api.avaliacao.controller;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.avaliacao.dto.PessoaDto;
import com.api.avaliacao.model.Pessoa;
import com.api.avaliacao.service.PessoaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/pessoas")
public class PessoaController {
    
    final PessoaService pessoaService;

    public PessoaController (PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarPessoa(@RequestBody @Valid PessoaDto pessoaDto){
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoa));
    }

    @GetMapping
    public ResponseEntity<Page<Pessoa>> listarPessoas(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> listarUmaPessoa(@PathVariable(value = "id") Long id){
        Optional<Pessoa> pessoaOptional = pessoaService.findById(id);
        if (!pessoaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarPessoa(@PathVariable(value = "id") Long id,
                                                    @RequestBody @Valid PessoaDto pessoaDto){
        Optional<Pessoa> pessoaOptional = pessoaService.findById(id);
        if (!pessoaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        pessoa.setId(pessoaOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoa));
    }
}
