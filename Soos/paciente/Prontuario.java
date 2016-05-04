package paciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exceptions.PacienteException;
import exceptions.VerificaExcecao;

/**
 * A classe Prontuario é composta de um objeto paciente e um conjunto de procedimentos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class Prontuario implements Comparable<Prontuario> {
	
	private Paciente paciente;
	private List<Procedimento> procedimentos;
	
	private final String NOME = "Nome", DATA = "Data", SEXO = "Sexo", GENERO = "Genero",
			TIPO_SANGUINEO =  "TipoSanguineo", PESO = "Peso", IDADE = "Idade";
	
	public Prontuario(String nome, LocalDate dataNascimento, double peso, String sexoBiologico,
						String genero, String tipoSanguineo, int ID) throws PacienteException {
		
		VerificaExcecao.checkEmptyString(nome, "Nome do paciente");
		VerificaExcecao.checkEmptyString(sexoBiologico, "Sexo biologico");
		VerificaExcecao.checkEmptyString(genero, "Genero");
		VerificaExcecao.checkEmptyString(tipoSanguineo, "Tipo sanguineo");
		
		VerificaExcecao.checarData(dataNascimento);
		VerificaExcecao.checarPeso(peso);
		VerificaExcecao.checarSexoBiologico(sexoBiologico);
		VerificaExcecao.checarTipoSanguineo(tipoSanguineo);
		
		this.paciente = new Paciente(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo, ID);
		this.procedimentos = new ArrayList<Procedimento>();
	}
	
	/**
	 * Fornce uma informacao do paciente conforme o atributo desejado
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getInfoPaciente(String atributo) throws Exception {
		if (atributo == null) {
			throw new Exception ("Atributo nulo.");
		}
		switch (atributo) {
		case NOME:
			return this.paciente.getNome();
		case DATA:
			return this.paciente.getDataNascimento();
		case PESO:
			return String.valueOf(this.paciente.getPeso());
		case SEXO:
			return this.paciente.getSexoBiologico();
		case GENERO:
			return this.paciente.getGenero();
		case TIPO_SANGUINEO:
			return this.paciente.getTipoSanguineo();
		case IDADE:
			return String.valueOf(this.paciente.getIdade());
		default:
			throw new Exception("Atributo invalido.");
		}
	}
	
	public List<Procedimento> getProcedimentos() {
		return this.procedimentos;
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
