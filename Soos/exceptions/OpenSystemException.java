package exceptions;
 
 
/**
 * OpenSystemException
* Classe que encapsula uma situacao de excecao relacionada  a inicializacao do sistema.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */

@SuppressWarnings("serial")
public class OpenSystemException extends Exception{
 
    public OpenSystemException(String msg) {
        super("Erro ao liberar o sistema. "+msg);
    }
 
}