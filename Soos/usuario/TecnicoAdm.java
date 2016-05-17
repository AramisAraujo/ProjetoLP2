package usuario;

import java.time.LocalDate;

/**
 * Classe implesmentada para criar um Tecnico Administrativo.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
public class TecnicoAdm extends Usuario{

	public TecnicoAdm(String nome, LocalDate birthDate, String senha, String matricula) {
		super(nome, birthDate, senha, matricula);
	}

	@Override
	public TipoCargo getCargo() {
		return TipoCargo.TECNICOADM;
	}
}
