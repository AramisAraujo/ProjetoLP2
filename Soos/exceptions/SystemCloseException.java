package exceptions;
 
 
/**
 * SystemCloseException
* Classe que encapsula uma situacao de excecao relacionada  ao processo de fechar o sistema.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */

@SuppressWarnings("serial")
public class SystemCloseException extends Exception{
 
    public SystemCloseException(String msg) {
        super("Nao foi possivel fechar o sistema. "+msg);
    }
 
}