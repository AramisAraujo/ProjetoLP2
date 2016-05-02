package exceptions;

public class CadastroException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3748305381163668071L;

	public CadastroException(String pessoa, String msg) {
		super("Erro no cadastro de "+pessoa+". "+msg);
	}

}
