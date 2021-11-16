package api.spring.bluebank.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence. *;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="conta")
public class Conta{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private int agencia;
	
	@NotNull
	private Long conta;
	
	@OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Movimentacoes> movimentacoes;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public List<Movimentacoes> getMovimentacoes() {
//		return movimentacoes;
//	}
//
//	public void setMovimentacoes(List<Movimentacoes> movimentacoes) {
//		this.movimentacoes = movimentacoes;
//	}

	private double saldo;

	private static int total; //esse atributo serve para sabermos quantas contas foram abertas
	
	//enum tipo de conta

	public Conta() {
	}
	
	public Conta(@NotNull Long conta) {
		super();
		this.conta = conta;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}


	public Long getConta() {
		return conta;
	}


	public void setConta(@NotNull Long conta) {
		this.conta = conta;
	}




	public double getSaldo() {
		return saldo;
	}


	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}


	@Override
	public String toString() {
		return "Conta [agencia=" + agencia + ", conta=" + conta + ", saldo=" + saldo + "]";
	}
	
	public void deposita(double valor) {
		this.saldo += valor;
	}
	
	public boolean saca(double valor) {
		if(this.saldo >= valor) {
			this.saldo -= valor;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean tranfere(double valor, Conta destino) {
		if(this.saldo >= valor) {
			this.saldo -= valor;
			destino.deposita(valor);
			return true;
		} else {
			return false;
		}
	}	
}
