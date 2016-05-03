package exceptions;

public class OpenSystemException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3713692031050918629L;

	public OpenSystemException(String msg) {
		super("Erro ao liberar o sistema. "+msg);
	}

}
