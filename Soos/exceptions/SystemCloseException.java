package exceptions;

public class SystemCloseException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2491284169051430698L;

	public SystemCloseException(String msg) {
		super("Nao foi possivel fechar o sistema. "+msg);
	}

}
