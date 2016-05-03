package exceptions;

public class LoginException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1195597153228578649L;

	public LoginException(String msg) {
		super("Nao foi possivel realizar o login. "+msg);
	}
	

}
