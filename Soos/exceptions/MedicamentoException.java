package exceptions;
 
/**
 * MedicamentoException
 * Classe que encapsula uma situacao de excecao relacionada  a um Medicamento.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */

@SuppressWarnings("serial")
public class MedicamentoException extends Exception {
  
    public MedicamentoException() {
        super("Esse medicamento nao existe!");
    }
 
    public MedicamentoException(String mensagem) {
        super(mensagem);
    }
}