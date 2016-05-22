package exceptions;
 
/**
 * ExcluirCadastroException
* Classe que encapsula uma situacao de excecao relacionada  a exclusao do cadastro.
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