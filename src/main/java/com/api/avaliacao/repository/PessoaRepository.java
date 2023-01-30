package com.api.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.avaliacao.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
