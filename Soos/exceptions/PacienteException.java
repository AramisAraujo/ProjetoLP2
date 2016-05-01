package exceptions;

/**
 * 
 * @author Elton Dantas
 *
 */
public class PacienteException extends EntradaException {

	private static final long serialVersionUID = 1L;
	
	public PacienteException() {
        super("Nao foi possivel cadastrar o paciente.");
    }
      
    public PacienteException(String mensagem) {
        super("Nao foi possivel cadastrar o paciente. " + mensagem);
    }
}
