package com.api.avaliacao.dto;


import com.api.avaliacao.model.Endereco;
import com.api.avaliacao.model.Pessoa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EnderecoDto{

    private Long id;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String cidade;

    @NotBlank
    @Pattern (regexp = "\\d{8}")
    private String cep;
    
    @NotBlank
    private String numero;

    @NotBlank
    private Pessoa pessoa;

    @NotBlank
    private Boolean principal;


    public EnderecoDto(Long id, String cidade, String logradouro, String cep, String numero, Boolean principal,
			Pessoa pessoa) {
		this.id = id;
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.pessoa = pessoa;
		setPrincipal(principal);
	}

	public EnderecoDto(Endereco endereco) {
		this.id = endereco.getId();
		this.cidade = endereco.getCidade();
		this.logradouro = endereco.getLogradouro();
		this.cep = endereco.getCep();
		this.numero = endereco.getNumero();
		this.pessoa = endereco.getPessoa();
		this.principal = endereco.getPrincipal();
	}    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = true;
	}

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
