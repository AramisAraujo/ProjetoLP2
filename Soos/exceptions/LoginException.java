package exceptions;
 
 
/**
 * LoginException
 * Classe que encapsula uma situacao de excecao relacionada  ao Login no sistema.
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