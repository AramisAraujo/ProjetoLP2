package exceptions;

/**
 * Classe implementada para criar Exceptions quando algum valor eh invalido, ela
 * herda de EntradaException pois esse seria um erro de entrada.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
@SuppressWarnings("serial")
public class ValorException extends EntradaException{
	
	public ValorException(String mensagem) {
        super(mensagem);
    }
 
    public ValorException() {
        super("Valor invalido");
    }
}
