package exceptions;

public class LogoutException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3688890372021201110L;

	public LogoutException(String msg) {
		super("Nao foi possivel realizar o logout. "+msg);
	}

}
