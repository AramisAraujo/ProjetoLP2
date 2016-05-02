package exceptions;

public class ConsultaException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8709348199391621035L;

	public ConsultaException(String pessoa,String msg) {
		super("Erro na consulta de "+ pessoa+". "+msg);
	}

}
