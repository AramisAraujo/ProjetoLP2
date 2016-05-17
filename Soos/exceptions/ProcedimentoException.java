package exceptions;


/**
 * Classe implementada para criar Exceptions quando existe algum erro
 * relacionado ao procedimento.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
@SuppressWarnings("serial")
public class ProcedimentoException extends Exception {
	
	public ProcedimentoException() {
		super("Erro na realizacao de procedimentos.");
	}

	public ProcedimentoException(String mensagem) {
		super("Erro na realizacao de procedimentos. "+mensagem);
	}

}
