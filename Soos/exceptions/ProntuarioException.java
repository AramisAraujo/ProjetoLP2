package exceptions;
 
/**
 * Classe implementada para criar Exceptions quando existe algum erro
 * relacionado ao prontuario.
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