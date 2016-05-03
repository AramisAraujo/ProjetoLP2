package paciente;

import java.time.LocalDate;

import exceptions.VerificaExcecao;

/**
 * A classe Paciente possui atributos e comportamentos necessarios para a criacao de pacientes
 * e manipulacao de seus dados.
 * 
 * @author Elton Dantas
 */
public class Paciente implements Comparable<Paciente> {
	
	private String nome, sexoBiologico, genero, tipoSanguineo;
	private LocalDate dataNascimento;
	private double peso;
	private int ID;
	
	private final String NOME = "Nome", DATA = "Data", SEXO = "Sexo", GENERO = "Genero",
			TIPO_SANGUINEO =  "TipoSanguineo", PESO = "Peso", IDADE = "Idade";
	
	public Paciente(String nome, LocalDate dataNascimento, double peso, String sexoBiologico,
					String genero, String tipoSanguineo, int ID) throws Exception {
		
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
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento.toString();
	}
	
	public int getIdade() {
		int idade = LocalDate.now().getYear() - this.dataNascimento.getYear();
		return idade;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getSexoBiologico() {
		return sexoBiologico;
	}

	public void setSexoBiologico(String sexoBiologico) {
		this.sexoBiologico = sexoBiologico;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}
	
	public Object getInfoPaciente(String atributo) throws Exception {
		switch (atributo) {
		case NOME:
			this.getNome();
		case DATA:
			this.getDataNascimento();
		case SEXO:
			this.getSexoBiologico();
		case GENERO:
			this.getGenero();
		case TIPO_SANGUINEO:
			this.getTipoSanguineo();
		case PESO:
			this.getPeso();
		case IDADE:
			this.getIdade();
		default:
			throw new Exception("Atributo invalido.");
		}
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
