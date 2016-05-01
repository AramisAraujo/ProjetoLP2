package paciente;

import java.util.ArrayList;
import java.util.List;

import exceptions.VerificaExcecao;

/**
 * A classe Prontuario é composta de um objeto paciente e um conjunto de procedimentos.
 * 
 * @author Elton Dantas
 */
public class Prontuario implements Comparable<Prontuario> {
	
	private Paciente paciente;
	private List<Procedimento> procedimentos;
	
	public Prontuario(String nome, int dataNascimento, int peso, String sexoBiologico,
						String genero, String tipoSanguineo, int ID) throws Exception {
		
		VerificaExcecao.checharParametroNull(nome,"Nome");
		VerificaExcecao.checharParametroNull(sexoBiologico, "Sexo biologico");
		VerificaExcecao.checharParametroNull(genero, "Genero");
		VerificaExcecao.checharParametroNull(tipoSanguineo, "Tipo sanguineo");
		
		VerificaExcecao.checarNomePaciente(nome);
		VerificaExcecao.checarDataNascimento(dataNascimento);
		VerificaExcecao.checarPeso(peso);
		VerificaExcecao.checarSexoBiologico(sexoBiologico);
		VerificaExcecao.checarTipoSanguineo(tipoSanguineo);
		
		this.paciente = new Paciente(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo, ID);
		this.procedimentos = new ArrayList<Procedimento>();
	}
	
	/**
	 * Prontuarios sao comparados pelos nomes de seus pacientes.
	 */
	@Override
	public int compareTo(Prontuario outroProntuario) {
		return this.paciente.getNome().compareTo(outroProntuario.paciente.getNome());
	}
	
	
	/**
	 * Dois prontuarios sao iguais se tiverem o mesmo paciente.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prontuario other = (Prontuario) obj;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		return true;
	}
	
	
	
	
}
