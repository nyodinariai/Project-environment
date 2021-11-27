package api.spring.bluebank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.spring.bluebank.model.Conta;
import api.spring.bluebank.model.Movimentacoes;
import api.spring.bluebank.repository.ContaRepository;
import api.spring.bluebank.repository.MovimentacoesRepository;

@Service
public class MovimentacoesService {

	@Autowired
	private MovimentacoesRepository mRepository;

	@Autowired
	private ContaRepository cRepository;

	public ResponseEntity<Movimentacoes> depositar(Movimentacoes mov) {
		List<Conta> contaExiste = cRepository.findByConta(mov.getConta());	
		mov.setSaldoInicial(contaExiste.get(0).getSaldo());
		mov.setSaldoFinal(mov.getSaldoInicial()+ mov.getValor());
		contaExiste.get(0).setSaldo(mov.getSaldoFinal());
		mov.getConta().getSaldo();
		Movimentacoes inserir = new Movimentacoes(mov.getConta(), mov.getTipoMovimentacao(), mov.getValor(), mov.getSaldoInicial(), mov.getSaldoFinal());

		if (!contaExiste.isEmpty()) {
			System.out.println(contaExiste.get(0).getSaldo());

			return ResponseEntity.status(201).body(mRepository.save(inserir));
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	public ResponseEntity<Movimentacoes> sacar(Movimentacoes mov) {
		List<Conta> contaExiste = cRepository.findByConta(mov.getConta());	
		mov.setSaldoInicial(contaExiste.get(0).getSaldo());
		mov.setSaldoFinal(mov.getSaldoInicial()- mov.getValor());
		contaExiste.get(0).setSaldo(mov.getSaldoFinal());
		Movimentacoes inserir = new Movimentacoes(mov.getConta(), mov.getTipoMovimentacao(), mov.getValor(), mov.getSaldoInicial(), mov.getSaldoFinal());
		if (!contaExiste.isEmpty() && mov.getSaldoInicial() >= mov.getValor()) {
			System.out.println(contaExiste.get(0).getSaldo());
			return ResponseEntity.status(201).body(mRepository.save(inserir));
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Movimentacoes> tranferir(Movimentacoes valor, Movimentacoes destino) {
		List<Conta> contaExiste = cRepository.findByConta(valor.getConta());
		valor.setSaldoInicial(contaExiste.get(0).getSaldo());
		valor.setSaldoFinal(valor.getSaldoInicial() - valor.getValor());
		contaExiste.get(0).setSaldo(valor.getSaldoFinal());
		Movimentacoes inserir = new Movimentacoes(valor.getConta(), valor.getTipoMovimentacao(), valor.getValor(), valor.getSaldoInicial(), valor.getSaldoFinal());
		
		List<Conta> contaDestino = cRepository.findByConta(destino.getConta());
		destino.setSaldoInicial(contaDestino.get(0).getSaldo());
		destino.setSaldoFinal(destino.getSaldoInicial() + valor.getValor());
		contaDestino.get(0).setSaldo(destino.getSaldoFinal());
		Movimentacoes destino1 = new Movimentacoes(destino.getConta(), destino.getTipoMovimentacao(), destino.getValor(), destino.getSaldoInicial(), destino.getSaldoFinal());
		if (!contaExiste.isEmpty() && !contaDestino.isEmpty() && valor.getSaldoInicial() >= valor.getValor()) {
			Movimentacoes save = mRepository.save(destino1);
			return ResponseEntity.status(201).body(mRepository.save(inserir));
			
		} else {
			return ResponseEntity.badRequest().build();
		}
		
	}
}
