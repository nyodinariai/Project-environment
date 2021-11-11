package api.spring.bluebank.model;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name="documento")
public class ValidarCPF {
	Cliente cliente = new Cliente();
	
	public void cpfValido() {
		String cpf = cliente.getCpf();
		
		
		
	}
}
