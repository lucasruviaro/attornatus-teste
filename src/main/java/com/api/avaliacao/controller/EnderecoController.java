package com.api.avaliacao.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.avaliacao.dto.EnderecoDto;
import com.api.avaliacao.model.Endereco;
import com.api.avaliacao.model.Pessoa;
import com.api.avaliacao.service.EnderecoService;
import com.api.avaliacao.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoas/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

    @Autowired
	private PessoaService pessoaService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Endereco> findById(@PathVariable Long id) {
		Endereco endereco = enderecoService.findById(id);
		return ResponseEntity.ok().body(endereco);
	}

	@PostMapping(value = "/{id}")
	public ResponseEntity<Endereco> cadastrarEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
		Optional<Pessoa> pessoa = pessoaService.findById(id);
        Pessoa pessoa1 = pessoa.get();
        Endereco end = new Endereco();
        end.setPessoa(pessoa1);
		endereco = enderecoService.cadastrarEndereco(endereco);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(endereco.getId()).toUri();
		return ResponseEntity.created(uri).body(endereco);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Endereco> update(@PathVariable Long id, @RequestBody EnderecoDto enderecoDto){
		Endereco endereco = enderecoService.atualizarEndereco(id, enderecoDto);
		return ResponseEntity.ok().body(endereco);
	}

	@PostMapping
	public ResponseEntity<EnderecoDto> insert(@RequestBody EnderecoDto enderecoDto) {
		enderecoDto = enderecoService.insert(enderecoDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(enderecoDto.getId()).toUri();
		return ResponseEntity.created(uri).body(enderecoDto);
	}
    
}