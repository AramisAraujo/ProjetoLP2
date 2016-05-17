package exceptions;
 
 
/**
 * Classe implementada para criar Exceptions quando existe algum erro
 * relacionado a autenticacao de alguma informacao.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
@SuppressWarnings("serial")
public class AutenticacaoException extends Exception{
 
    public AutenticacaoException(String msg) {
        super(msg);
    }
 
}