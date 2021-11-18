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
		double saldoAtual = contaExiste.get(0).getSaldo();
		
		if(!contaExiste.isEmpty()) {
			
			contaExiste.get(0).setSaldo(saldoAtual + mov.getValor());
			System.out.println(contaExiste.get(0).getSaldo());

			return ResponseEntity.status(201).body(mRepository.save(inserir));
		} else {	
			return ResponseEntity.badRequest().build();
		}	
	}
	
	public ResponseEntity<Movimentacoes> sacar(Movimentacoes mov) {
		List<Conta> contaExiste = cRepository.findByConta(mov.getConta());
		Movimentacoes inserir = new Movimentacoes(mov.getConta(), mov.getMovNome(), mov.getValor());
		double saldoAtual = contaExiste.get(0).getSaldo();
		
		if(!contaExiste.isEmpty() && saldoAtual >= mov.getValor()) {
			
			contaExiste.get(0).setSaldo(saldoAtual - mov.getValor());
			System.out.println(contaExiste.get(0).getSaldo());

			return ResponseEntity.status(201).body(mRepository.save(inserir));
		} else {	
			return ResponseEntity.badRequest().build();
		}	
	}
}
