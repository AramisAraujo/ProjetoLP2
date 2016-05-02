package usuario;

import java.time.LocalDate;

public class Medico extends Usuario{

	public Medico(String nome, LocalDate birthDate, String senha, String matricula) {
		super(nome, birthDate, senha, matricula);
	}

	@Override
	public TipoCargo getCargo() {
		return TipoCargo.MEDICO;
	}

}
