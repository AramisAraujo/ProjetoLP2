package exceptions;


/**
 * A classe LancaExcecao contem metodos que verificam a validade dos parametros passados aos outros
 * metodos do codigo. Caso nao seja o esperado, throws exception.
 * @author Elton Dantas
 */
public class VerificaExcecao {
	
	public VerificaExcecao() {}
	
	public static void checarString(String string) throws Exception {
		if (string.equals("") || string == null) {
			throw new StringException();
		}
	}

}
