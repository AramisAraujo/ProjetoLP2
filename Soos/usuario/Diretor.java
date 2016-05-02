package usuario;

import java.time.LocalDate;

public class Diretor extends Usuario{

	public Diretor(String nome, LocalDate birthDate, String senha, String matricula) {
		super(nome, birthDate, senha, matricula);
	}

	@Override
	public TipoCargo getCargo() {
		return TipoCargo.DIRETOR;
	}
	
}
