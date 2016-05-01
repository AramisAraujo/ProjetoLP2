package exceptions;

/**
 * 
 * @author Elton Dantas
 *
 */
public class NullParameterException extends EntradaException {

	private static final long serialVersionUID = 1L;
	
	public NullParameterException() {
        super("Parametro null.");
    }
      
    public NullParameterException(String mensagem) {
        super("Parametro null. " + mensagem);
    }
}
