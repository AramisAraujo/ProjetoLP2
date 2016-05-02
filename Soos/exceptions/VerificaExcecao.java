package exceptions;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A classe LancaExcecao contem metodos que verificam a validade dos parametros passados aos outros
 * metodos do codigo. Caso nao seja o esperado, throws exception.
 * 
 * @author Elton Dantas
 */
public class VerificaExcecao {
	
	private static List<String> tiposDeSangue;
	
	public VerificaExcecao() {
		tiposDeSangue = new ArrayList<String>(Arrays.asList("A+","A-","B+","B-","AB+","AB-","O+","O-"));
	}
	
	public static void checarString(String string) throws StringException {
		if (string == null || string.trim().isEmpty()) {
			throw new StringException();
		}
	}
	
	public static void checharParametroNull(Object obj, String atributo) throws NullParameterException {
		if (obj == null) {
			throw new NullParameterException(atributo + " nao pode ser null.");
		}
	}
	
	public static void checarNomePaciente(String nome) throws PacienteException {
		if (nome == "") {
			throw new PacienteException("Nome do paciente nao pode ser vazio.");
		}
	}
	
	public static void checarDataNascimento(int dataNascimento) {
	}
	
	public static void checarPeso(int peso) throws PacienteException {
		if (peso < 0) {
			throw new PacienteException("Peso do paciente nao pode ser negativo.");
		}
	}
	
	public static void checarSexoBiologico(String sexoBiologico) throws PacienteException {
		if (sexoBiologico != "feminino" || sexoBiologico != "masculino") {
			throw new PacienteException("Sexo biologico nao identificado.");
		}
	}
	
	public static void checarTipoSanguineo(String tipoSanguineo) throws PacienteException {
		if (!tiposDeSangue.contains(tipoSanguineo)) {
			throw new PacienteException();
		}
	}
	
	public static void checharPacienteJaCadastrado() {
		
	}

}
