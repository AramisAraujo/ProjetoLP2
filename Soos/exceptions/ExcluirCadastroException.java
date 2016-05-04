package exceptions;

public class ExcluirCadastroException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8181065410649863401L;

	public ExcluirCadastroException(String pessoa, String msg) {
		super("Erro ao excluir "+pessoa+". "+msg);
	}

}
