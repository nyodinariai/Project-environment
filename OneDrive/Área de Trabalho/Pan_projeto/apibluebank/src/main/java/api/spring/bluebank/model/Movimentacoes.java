package api.spring.bluebank.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="movimentacoes")
public class Movimentacoes{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	private Conta conta;

//	@Column(name = "conta_id")
//	private Long conta;
	
	//Saque/Deposito/Pagamento
	@Column(name = "movNome", nullable = false, length = 60)
	private String movNome;
	
	@Column(name = "valor", nullable = false, length = 60)
	private Double valor;
	
	@Column(name = "data")
	private Date data;

	public Movimentacoes() {
		this(new Date());
	}
	
	public Movimentacoes(Conta conta, String movNome, Double valor) {
		super();
		this.setConta(conta);
		this.setMovNome(movNome);
		this.setValor(valor);
	}

	public Movimentacoes(Date data) {
		super();
		this.data = data;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMovNome() {
		return movNome;
	}

	public void setMovNome(String movNome) {
		this.movNome = movNome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

//	public Long getConta() {
//		return conta;
//	}
//
//
//	public void setConta(Long conta) {
//		this.conta = conta;
//	}
	
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
}
