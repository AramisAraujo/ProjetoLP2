package exceptions;
 
/**
 * Classe implementada para criar Exceptions quando algum medicamento nao
 * existe, ela herda de LogicaException pois esse seria um erro de logica.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class MedicamentoInexistenteException extends LogicaException {
 
    private static final long serialVersionUID = 1L;
 
    public MedicamentoInexistenteException() {
        super("Esse medicamento nao existe!");
    }
 
    public MedicamentoInexistenteException(String mensagem) {
        super(mensagem);
    }
}