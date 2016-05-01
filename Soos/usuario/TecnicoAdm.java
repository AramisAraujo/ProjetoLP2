package usuario;

import java.util.Date;

public class TecnicoAdm extends Usuario{

	public TecnicoAdm(String nome, Date dataNascimento, int senha, int matricula) {
		super(nome, dataNascimento, senha, matricula);
	}

	@Override
	public TipoCargo getCargo() {
		return TipoCargo.TECNICOADM;
	}
}
