package exceptions;
 
/**
 * Classe implementada para criar todas as Exceptions que ocorrerem por erro
 * de logica.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class LogicaException extends Exception {
 
    private static final long serialVersionUID = 1L;
     
    public LogicaException() {
        super("Logica incorreta!");
    }
     
    public LogicaException(String mensagem) {
        super(mensagem);
    }
}