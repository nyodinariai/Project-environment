package api.spring.bluebank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.spring.bluebank.model.Conta;
import api.spring.bluebank.repository.ClienteRepository;
import api.spring.bluebank.repository.ContaRepository;
import api.spring.bluebank.service.ContaService;

@RestController
@RequestMapping("/conta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContaController {
	private @Autowired ContaService service;
	private @Autowired ContaRepository repository;
	
	@PostMapping("deposita/{id}")
	public Optional<Conta> deposita(@PathVariable Long id, @RequestBody Conta conta) {
		return repository.findById(id).map(contaExistente -> {
			contaExistente.getSaldo(conta.deposita(0)); //fazer uma soma
			return Optional.ofNullable(repository.save(contaExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
	
	@PostMapping("criar")
	public ResponseEntity<Conta> criar(@RequestBody Conta conta) {
		return ResponseEntity.ok(repository.save(conta));	
	}
}
