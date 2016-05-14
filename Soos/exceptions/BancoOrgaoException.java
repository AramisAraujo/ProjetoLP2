package exceptions;

public class BancoOrgaoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8615245003115898401L;

	public BancoOrgaoException(String msg) {
		super("O banco de orgaos apresentou um erro. " + msg);
	}

}
