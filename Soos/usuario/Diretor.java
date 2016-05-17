package usuario;

import java.time.LocalDate;

/**
 * Classe implesmentada para criar um Diretor.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
public class Diretor extends Usuario{

	public Diretor(String nome, LocalDate birthDate, String senha, String matricula) {
		super(nome, birthDate, senha, matricula);
	}

	@Override
	public TipoCargo getCargo() {
		return TipoCargo.DIRETOR;
	}
	
}
