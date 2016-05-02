package factories;



import java.time.LocalDate;

import usuario.Diretor;
import usuario.Medico;
import usuario.TecnicoAdm;
import usuario.TipoCargo;
import usuario.Usuario;

public class FactoryUsuario {
	
	public Usuario criarUsuario(String nome, LocalDate birthDate, String senha, 
			String matricula, TipoCargo cargo) throws Exception{
		switch (cargo) {
			case DIRETOR:
				return new Diretor(nome, birthDate, senha, matricula);
			case MEDICO:
				return new Medico(nome, birthDate, senha, matricula);
			case TECNICOADM:
				return new TecnicoAdm(nome, birthDate, senha, matricula);
			default:
				throw new Exception();
		}
	}
}
