package com.api.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.avaliacao.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
}
