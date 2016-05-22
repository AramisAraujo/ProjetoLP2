package exceptions;
 
 
/**
 * ConsultaException
 *Classe que encapsula uma situacao de excecao relacionada  a consulta de alguma informacao de
 *um funcionario, medicamento ou de um paciente.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */

@SuppressWarnings("serial")
public class ConsultaException extends Exception{
     
    public ConsultaException(String pessoa,String msg) {
        super("Erro na consulta de "+ pessoa+". "+msg);
    }
 
}