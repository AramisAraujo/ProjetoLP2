package exceptions;
 
/**
 * Classe implementada para criar todas as Exceptions que ocorrerem por erro
 * de entrada (dado informado pelo usuario).
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class EntradaException extends Exception {
 
    private static final long serialVersionUID = 1L;
 
    public EntradaException() {
        super("Entrada invalida.");
    }
 
    public EntradaException(String mensagem) {
        super(mensagem);
    }
 
}