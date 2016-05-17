package exceptions;
 
 
/**
 * Classe implementada para criar Exceptions quando existe algum erro
 * relacionado a consulta de algo.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
@SuppressWarnings("serial")
public class ConsultaException extends Exception{
     
    public ConsultaException(String pessoa,String msg) {
        super("Erro na consulta de "+ pessoa+". "+msg);
    }
 
}