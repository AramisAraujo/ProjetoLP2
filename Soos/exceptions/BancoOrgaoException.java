package exceptions;
 
 
/**
 * BancoOrgaoException
* Classe que encapsula uma situacao de excecao relacionada  ao banco de orgaos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */

@SuppressWarnings("serial")
public class BancoOrgaoException extends Exception{
 
    public BancoOrgaoException(String msg) {
        super("O banco de orgaos apresentou um erro. " + msg);
    }
 
}