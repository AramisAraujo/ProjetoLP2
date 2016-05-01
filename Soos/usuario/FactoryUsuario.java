package usuario;

import java.util.Date;

public class FactoryUsuario {
	
	public Usuario criarUsuario(String nome, Date dataNascimento, int senha, int matricula, TipoCargo cargo) throws Exception {
		switch (cargo) {
			case DIRETOR:
				return new Diretor(nome, dataNascimento, senha, matricula);
			case MEDICO:
				return new Medico(nome, dataNascimento, senha, matricula);
			case TECNICOADM:
				return new TecnicoAdm(nome, dataNascimento, senha, matricula);
			default:
				throw new Exception();
		}
	}
}
