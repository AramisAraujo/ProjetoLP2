package exceptions;
 
 
/**
 * Classe implementada para criar Exceptions quando existe algum erro
 * relacionado a realizacao do login.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
@SuppressWarnings("serial")
public class LoginException extends Exception{
 
    public LoginException(String msg) {
        super("Nao foi possivel realizar o login. "+msg);
    }
     
 
}