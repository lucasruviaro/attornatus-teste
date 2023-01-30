package com.api.avaliacao.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cidade;
	private String logradouro;
	private String cep;
	private String numero;
	private Boolean principal;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Pessoa pessoa;
	
	public Endereco() {
	}

	public Endereco(Long id, String cidade, String logradouro, String cep, String numero, Boolean principal,
			Pessoa pessoa) {
		this.id = id;
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.principal = principal;
        definirPrincipal(principal);
		this.pessoa = pessoa;
	}

    public void definirPrincipal(Boolean principal) {
		List<Endereco> endereco = this.pessoa.getEndereco();
		if (principal) {
			endereco.stream().forEach((x) -> x.principal = false);
			this.principal = true;
		} else {
			this.principal = false;
			}
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
	
	public Boolean getPrincipal() {
		return principal;
	}

    public void setPrincipal(Boolean principal){
        this.principal = principal;
    }

}
