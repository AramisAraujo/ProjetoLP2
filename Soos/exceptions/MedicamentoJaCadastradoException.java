package exceptions;

public class MedicamentoJaCadastradoException extends LogicaException {

	 private static final long serialVersionUID = 1L;
     
	    public MedicamentoJaCadastradoException() {
	        super("Esse medicamento ja foi cadastrado.");
	    }
	      
	    public MedicamentoJaCadastradoException(String mensagem) {
	        super(mensagem);
	    }
}