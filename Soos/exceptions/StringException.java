package exceptions;
 
/**
 * Classe implementada para criar Exceptions quando alguma String eh invalida,
 * ela herda de EntradaException pois esse seria um erro de entrada.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
@SuppressWarnings("serial")
public class StringException extends EntradaException {
 
    public StringException(String mensagem) {
        super(mensagem);
    }
 
    public StringException() {
        super("String inválida");
    }
 
}