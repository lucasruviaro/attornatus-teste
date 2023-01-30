package com.api.avaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.api.avaliacao.dto.EnderecoDto;
import com.api.avaliacao.model.Endereco;
import com.api.avaliacao.model.Pessoa;
import com.api.avaliacao.repository.EnderecoRepository;

import jakarta.transaction.Transactional;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}

	public Endereco findById(Long id) {
		Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
		return enderecoOptional.get();
	}
    
    
	public Endereco cadastrarEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public Endereco atualizarEndereco(Long id, EnderecoDto enderecoDTO){
		Long pessoaId = enderecoDTO.getPessoa().getId();
		Endereco novoEndereco = new Endereco(enderecoDTO.getId(), enderecoDTO.getLogradouro(), enderecoDTO.getNumero(), enderecoDTO.getCidade(),
				enderecoDTO.getCep(), enderecoDTO.getPrincipal(), new Pessoa(pessoaId, null, null));

			Endereco endereco = enderecoRepository.getReferenceById(id);
			dtoParaEntidade(endereco, novoEndereco);
			return enderecoRepository.save(endereco);
		
	}

	private void dtoParaEntidade(Endereco endereco, Endereco end) {

		endereco.setPessoa(endereco.getPessoa());
		endereco.setLogradouro(endereco.getLogradouro());
		endereco.setNumero(endereco.getNumero());
		endereco.setCidade(endereco.getCidade());
		endereco.setCep(endereco.getCep());
		endereco.setPrincipal(endereco.getPrincipal());
	}

	@Transactional
	public EnderecoDto insert(EnderecoDto enderecoDto) {
		Endereco endereco = new Endereco();
		endereco.setPessoa(new Pessoa(enderecoDto.getPessoa().getId(), null, null));
		endereco.setLogradouro(enderecoDto.getLogradouro());
		endereco.setNumero(enderecoDto.getNumero());
		endereco.setCidade(enderecoDto.getCidade());
		endereco.setCep(enderecoDto.getCep());
		endereco.setPrincipal(enderecoDto.getPrincipal());


    	endereco = enderecoRepository.save(endereco);

		return new EnderecoDto(endereco);
	}
}