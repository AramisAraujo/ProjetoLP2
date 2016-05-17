package exceptions;
 
 
/**
 * Classe implementada para criar Exceptions quando existe algum erro
 * relacionado a realizacao do logout.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
@SuppressWarnings("serial")
public class LogoutException extends Exception{
 
    public LogoutException(String msg) {
        super("Nao foi possivel realizar o logout. "+msg);
    }
 
}