package exceptions;

public class ProcedimentoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ProcedimentoException() {
		super("Erro na realizacao de procedimentos.");
	}

	public ProcedimentoException(String mensagem) {
		super("Erro na realizacao de procedimentos. "+mensagem);
	}

}
