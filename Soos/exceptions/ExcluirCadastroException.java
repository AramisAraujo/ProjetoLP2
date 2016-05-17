package exceptions;
 
/**
 * Classe implementada para criar Exceptions quando existe algum erro
 * relacionado a exclusao de cadastros.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
@SuppressWarnings("serial")
public class ExcluirCadastroException extends Exception{
 
    public ExcluirCadastroException(String msg) {
        super(msg);
    }
 
}