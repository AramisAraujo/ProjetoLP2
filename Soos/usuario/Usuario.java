package usuario;

public abstract class Usuario {
	private String nome;
	private int dataNascimento;
	private int senha;
	private int matricula;
	
	public Usuario(String nome, int dataNascimento, int senha, int matricula) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
		this.matricula = matricula;
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

	public abstract TipoCargo getCargo();

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	public int getMatricula() {
		return matricula;
	}
	
}
