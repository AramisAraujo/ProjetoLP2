package paciente;

/**
 * A classe Paciente possui atributos e comportamentos necessarios para o cadastro de pacientes
 * e manipulacao de seus dados.
 * 
 * @author dantaselton
 */
public class Paciente implements Comparable <Paciente> {
	
	private String nome;
	private int dataNascimento;
	private int peso;
	private String sexoBiologico;
	private String genero;
	private int ID;
	private TipoSanguineo tipoSanguineo;
	
	public Paciente(String nome, int dataNascimento, int peso, String sexoBiologico,
					String genero, TipoSanguineo tipoSanguineo, int ID) {
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

	public int getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(int dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getPeso() {
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

	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
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
