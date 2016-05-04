package paciente;

import java.time.LocalDate;

import exceptions.PacienteException;
import exceptions.VerificaExcecao;

/**
 * A classe Paciente possui atributos e comportamentos necessarios para a criacao de pacientes
 * e manipulacao de seus dados.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class Paciente implements Comparable<Paciente> {
	
	private String nome, sexoBiologico, genero, tipoSanguineo;
	private LocalDate dataNascimento;
	private double peso;
	private int ID;
	
	public Paciente(String nome, LocalDate dataNascimento, double peso, String sexoBiologico,
					String genero, String tipoSanguineo, int ID) throws PacienteException {
		
		VerificaExcecao.checkEmptyString(nome, "Nome do paciente");
		VerificaExcecao.checkEmptyString(sexoBiologico, "Sexo biologico");
		VerificaExcecao.checkEmptyString(genero, "Genero");
		VerificaExcecao.checkEmptyString(tipoSanguineo, "Tipo sanguineo");
		
		VerificaExcecao.checarData(dataNascimento);
		VerificaExcecao.checarPeso(peso);
		VerificaExcecao.checarSexoBiologico(sexoBiologico);
		VerificaExcecao.checarTipoSanguineo(tipoSanguineo);
		
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.peso = peso;
		this.sexoBiologico = sexoBiologico;
		this.genero = genero;
		this.tipoSanguineo = tipoSanguineo;
		this.ID = ID;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return this.dataNascimento.toString();
	}
	
	public int getIdade() {
		int idade = LocalDate.now().getYear() - this.dataNascimento.getYear();
		return idade;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public double getPeso() {
		return this.peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getSexoBiologico() {
		return this.sexoBiologico;
	}

	public void setSexoBiologico(String sexoBiologico) {
		this.sexoBiologico = sexoBiologico;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTipoSanguineo() {
		return this.tipoSanguineo;
	}
	
	public int getID() {
		return this.ID;
	}
	
	/**
	 * Dois pacientes sao iguais se possuem o mesmo ID.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
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
		Paciente other = (Paciente) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
	/**
	 * Pacientes sao comparaveis pelos seus nomes.
	 */
	@Override
	public int compareTo(Paciente outroPaciente) {
		return this.nome.compareTo(outroPaciente.getNome());
	}
	

	
}
