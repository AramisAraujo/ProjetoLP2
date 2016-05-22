package exceptions;
 
/**
 * AtualizarInfoException
 * Classe que encapsula uma situacao de excecao relacionada  a atualizacao de 
 * alguma informacao de um objeto.
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