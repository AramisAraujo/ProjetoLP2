package exceptions;
 
/**
 * Classe implementada para criar Exceptions quando existe algum erro
 * relacionado a atualizacao de alguma informacao.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
@SuppressWarnings("serial")
public class AtualizarInfoException extends Exception {
 
    public AtualizarInfoException(String pessoa, String msg) {
        super("Erro ao atualizar " + pessoa + ". " + msg);
    }
 
}