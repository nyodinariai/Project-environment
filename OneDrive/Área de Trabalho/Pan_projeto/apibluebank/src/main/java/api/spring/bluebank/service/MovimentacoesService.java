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

	
	public ResponseEntity<Movimentacoes> deposito(Movimentacoes mov, Long id) {
	    Optional<Conta>	contaExistente = cRepository.findById(id);
		List<Conta> contaExiste = cRepository.findByConta(mov.getConta());
		Movimentacoes inserir = new Movimentacoes(mov.getConta(), mov.getMovNome(), mov.getValor());
		System.out.println(contaExiste);
		
		System.out.println(inserir);
		if(!contaExistente.isEmpty()) {
		double saldoAtual = ((Conta) contaExiste).getSaldo();
		double valor = mov.getValor();
		
		double saldo = saldoAtual + valor;
		//contaExistente.setSaldo(saldoAtual);
		//double novosaldo = ((Conta) contaExiste).setSaldo(saldo);
			return ResponseEntity.status(201).body(mRepository.save(inserir));
		} else {	
			return ResponseEntity.badRequest().build();
	
		}
	}
}
