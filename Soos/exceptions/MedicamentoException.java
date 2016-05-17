package exceptions;
 
/**
 * Classe implementada para criar Exceptions quando existe algum erro
 * relacionado ao medicamento.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class MedicamentoException extends Exception {
 
    private static final long serialVersionUID = 1L;
 
    public MedicamentoException() {
        super("Esse medicamento nao existe!");
    }
 
    public MedicamentoException(String mensagem) {
        super(mensagem);
    }
}