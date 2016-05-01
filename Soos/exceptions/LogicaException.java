package exceptions;

public class LogicaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public LogicaException() {
	    super("Logica incorreta.");
	}
	
	public LogicaException(String mensagem) {
	    super(mensagem);
	}
}