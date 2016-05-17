package exceptions;
 
 
/**
 * Classe implementada para criar Exceptions quando existe algum erro
 * relacionado a liberacao do sistema.
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