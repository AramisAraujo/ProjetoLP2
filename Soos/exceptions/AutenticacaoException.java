package exceptions;
 
 
/**
 * AutenticacaoException
 *Classe que encapsula uma situacao de excecao relacionada  a autenticacao de um funcionario.
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