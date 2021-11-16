package api.spring.bluebank.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.spring.bluebank.model.Cliente;
import api.spring.bluebank.model.Conta;
import api.spring.bluebank.model.Movimentacoes;
import api.spring.bluebank.repository.ClienteRepository;
import api.spring.bluebank.repository.ContaRepository;
import api.spring.bluebank.repository.MovimentacoesRepository;

@Service
public class MovimentacoesService {

	@Autowired
	private MovimentacoesRepository mRepository;
	
	@Autowired
	private ContaRepository cRepository;

	
	public ResponseEntity<Movimentacoes> deposito(Movimentacoes mov) {
		List<Conta> contaExiste = cRepository.findByConta(mov.getConta());
		Movimentacoes inserir = new Movimentacoes(mov.getConta(), mov.getMovNome(), mov.getValor());
		System.out.println(contaExiste);
		
		System.out.println(inserir);
		if(!contaExiste.isEmpty()) {
			return ResponseEntity.status(201).body(mRepository.save(inserir));
		} else {	
			return ResponseEntity.badRequest().build();
		}
	}
}
