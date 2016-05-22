package exceptions;
 
/**
 * ProntuarioException
* Classe que encapsula uma situacao de excecao relacionada  a um prontuario.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */

@SuppressWarnings("serial")
public class ProntuarioException extends Exception {
       
    public ProntuarioException(String mensagem) {
        super(mensagem);
    }
}