package usuario;

import java.time.LocalDate;

public class TecnicoAdm extends Usuario{

	public TecnicoAdm(String nome, LocalDate birthDate, String senha, String matricula) {
		super(nome, birthDate, senha, matricula);
	}

	@Override
	public TipoCargo getCargo() {
		return TipoCargo.TECNICOADM;
	}
}
