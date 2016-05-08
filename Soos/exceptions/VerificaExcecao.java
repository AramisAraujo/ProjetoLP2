package exceptions;


import java.time.LocalDate;
import java.util.List;

import farmacia.CategoriasDeMedicamentos;

/**
 * A classe LancaExcecao contem metodos que verificam a validade dos parametros passados aos outros
 * metodos do codigo. Caso nao seja o esperado, throws exception.
 * 
 * @author Elton Dantas
 */
public class VerificaExcecao {
	
	public static void checkEmptyString(String umaString,String atributo) throws Exception {
		if (umaString == null || umaString.trim().isEmpty()) {
			throw new Exception(atributo+" nao pode ser vazio.");

		}
	}
	
	public static boolean checarData(LocalDate birthDate) throws ProntuarioException {
		int thisYear = LocalDate.now().getYear();
		
		if(birthDate.getYear() > thisYear){
			throw new ProntuarioException("Data invalida.");
		}
		return true;
	}
	
	public static void checarValor(double valor,String atributo) throws ProntuarioException {
		if (valor < 0) {
			throw new ProntuarioException(atributo+" nao pode ser negativo.");
		}
		
		
		
	}
	
	public static void checarValor(int valor,String atributo) throws ProntuarioException {
		if (valor < 0) {
			throw new ProntuarioException(atributo+" nao pode ser negativo.");
		}
		
		
		
	}
	
	public static boolean checarSexoBiologico(String sexoBiologico) throws ProntuarioException {
		if ((!sexoBiologico.equalsIgnoreCase("masculino")) && (!sexoBiologico.equalsIgnoreCase("feminino"))) {
			throw new ProntuarioException("Sexo biologico nao identificado.");
		}
		return true;
	}
	
	/**
     * Metodo utilizado para verificar se determinado nome eh nulo ou vazio,
     * caso ele seja, uma excessao eh lancada.
     * 
     * @param string
     *            - nome que sera verificado
     * @throws StringException
     *             - excessao lancada caso o nome seja nulo ou vazio
     */
    public static void verificaString(String string) throws MedicamentoException {
        if (string == null) {
            throw new MedicamentoException("O nome do medicamento nao pode ser nulo.");
        } else if (string.equals("".trim())) {
            throw new MedicamentoException("Erro no cadastro de medicamento. Nome do medicamento nao pode ser vazio.");
        }
    }
 
    /**
     * Metodo utilizado para verificar se um determinado double eh negativo,
     * caso ele seja, uma excessao eh lancada.
     * 
     * @param preco
     *            - preco que sera verificado
     * @throws ValorException
     *             - excessao lancada caso o double seja negativo
     */
    public static void verificaPreco(double preco) throws MedicamentoException {
        if (preco < 0) {
            throw new MedicamentoException("Erro no cadastro de medicamento. Preco do medicamento nao pode ser negativo.");
        }
    }
 
    /**
     * Metodo utlizado para verificar se um determinado valor eh igual ou
     * inferior a 0, caso ele seja, uma excessao eh lancada.
     * 
     * @param quantidade
     *            - quantidade que sera verificada
     * @throws ValorException
     *             - excessao lancada caso o valor seja igual ou inferior a 0
     */
    public static void verificaQuantidade(int quantidade) throws MedicamentoException {
        if (quantidade == 0) {
            throw new MedicamentoException("A quantidade do medicamento nao pode ser zero.");
        } else if (quantidade < 0) {
            throw new MedicamentoException(
                    "Erro no cadastro de medicamento. Quantidade do medicamento nao pode ser negativo.");
        }
    }
 
    /**
     * Metodo utilizado para verificar se a(s) categoria(s) eh(sao) nula(s),
     * caso ela(s) seja(m), uma excessao eh lancada.
     * 
     * @param categorias
     *            - categoria(s) que sera(o) verificada(s)
     * @throws ValorException
     *             - excessao lancada caso a(s) categoria(s) eh(sejam) nula(s)
     */
    public static void verificaCategorias(List<CategoriasDeMedicamentos> categorias) throws MedicamentoException {
        if (categorias == null) {
            throw new MedicamentoException("A categoria do medicamento nao pode ser nula.");
        }
    }

}
