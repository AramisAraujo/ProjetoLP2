package exceptions;

public class LoginException extends Exception{
	public LoginException(String msg) {
		super("Nao foi possivel realizar o login. "+msg);
	}
	

}
