package exceptions;

public class AtualizarInfoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5136997774514822956L;

	public AtualizarInfoException(String pessoa, String msg) {
		super("Erro ao atualizar "+pessoa+". "+msg);
	}

}
