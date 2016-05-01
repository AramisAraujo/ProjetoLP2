package usuario;

import java.util.Date;

public class Medico extends Usuario{

	public Medico(String nome, Date dataNascimento, int senha, int matricula) {
		super(nome, dataNascimento, senha, matricula);
	}

	@Override
	public TipoCargo getCargo() {
		return TipoCargo.MEDICO;
	}

}
