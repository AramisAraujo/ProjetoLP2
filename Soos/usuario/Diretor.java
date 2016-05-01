package usuario;

import java.util.Date;

public class Diretor extends Usuario{

	public Diretor(String nome, Date dataNascimento, int senha, int matricula) {
		super(nome, dataNascimento, senha, matricula);
	}

	@Override
	public TipoCargo getCargo() {
		return TipoCargo.DIRETOR;
	}
	
}
