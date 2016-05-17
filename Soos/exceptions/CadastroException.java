package exceptions;
 
 
/**
 * Classe implementada para criar Exceptions quando existe algum erro
 * relacionado ao cadastro de algo.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
@SuppressWarnings("serial")
public class CadastroException extends Exception{
 
    public CadastroException(String pessoa,String msg) {
        super(pessoa+" "+msg);
    }
 
}