package exceptions;
 
/**
 * Classe implementada para criar Exceptions quando algum medicamento ja esta
 * cadastrado, ela herda de LogicaException pois esse seria um erro de logica.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class MedicamentoJaCadastradoException extends LogicaException {
<<<<<<< HEAD

	 private static final long serialVersionUID = 1L;
     
	    public MedicamentoJaCadastradoException() {
	        super("Esse medicamento ja foi cadastrado.");
	    }
	      
	    public MedicamentoJaCadastradoException(String mensagem) {
	        super(mensagem);
	    }
=======
 
    private static final long serialVersionUID = 1L;
 
    public MedicamentoJaCadastradoException() {
        super("Esse medicamento ja foi cadastrado!");
    }
 
    public MedicamentoJaCadastradoException(String mensagem) {
        super(mensagem);
    }
>>>>>>> master
}