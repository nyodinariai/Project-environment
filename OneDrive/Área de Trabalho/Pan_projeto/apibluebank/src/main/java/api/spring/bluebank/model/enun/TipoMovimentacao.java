package api.spring.bluebank.model.enun;

public enum TipoMovimentacao {

	DEPOSITO("Deposito"),
	SAQUE("Saque"),
	TRANSFERENCIA("Transferencia");

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	TipoMovimentacao(String descricao) {
		this.descricao = descricao;
	}


}
