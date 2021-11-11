package api.spring.bluebank.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import api.spring.bluebank.model.Cliente;
import api.spring.bluebank.repository.ClienteRepository;

@Service
public class ClienteService {
	private @Autowired ClienteRepository repository;
	
	public ResponseEntity<Cliente> cadastrarCliente(Cliente novocliente) {
		List<Cliente> clienteExistente = repository.findAllById(novocliente.getId());
		if (clienteExistente.isEmpty()) {
			return ResponseEntity.status(201).body(repository.save(novocliente));
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
	
	public Optional<Cliente> alterarEmail(Long id,
			Cliente clienteParaAtualizar) {
		return repository.findById(id).map(emailExistente -> {
			emailExistente.setEmail(clienteParaAtualizar.getEmail());
			return Optional.ofNullable(repository.save(emailExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});

	}
}
