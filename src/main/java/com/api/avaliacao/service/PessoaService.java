package com.api.avaliacao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.avaliacao.model.Pessoa;
import com.api.avaliacao.repository.PessoaRepository;

import jakarta.transaction.Transactional;

@Service
public class PessoaService {
 
    final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
    
    public Page<Pessoa> findAll(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public Optional<Pessoa> findById(Long id){
        return pessoaRepository.findById(id);
    }

}
