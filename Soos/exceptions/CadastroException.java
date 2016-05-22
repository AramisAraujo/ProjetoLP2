package exceptions;
 
 
/**
 * CadastroException
 * Classe que encapsula uma situacao de excecao relacionada  ao cadastro de um funcionario, medicamento 
 * ou paciente.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */

@SuppressWarnings("serial")
public class CadastroException extends Exception{
 
    public CadastroException(String pessoa,String msg) {
        super(pessoa+" "+msg);
    }
 
}