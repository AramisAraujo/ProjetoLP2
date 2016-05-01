package exceptions;

public class MedicamentoInexistenteException extends LogicaException{

	private static final long serialVersionUID = 1L;
    
    public MedicamentoInexistenteException() {
        super("Esse medicamento nao existe.");
    }
      
    public MedicamentoInexistenteException(String mensagem) {
        super(mensagem);
    }
}