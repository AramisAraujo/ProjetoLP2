package usuario;

import java.util.Date;

public abstract class Usuario {
	private String nome;
	private Date dataNascimento;
	private int senha;
	private int matricula;
	
	public Usuario(String nome, Date dataNascimento, int senha, int matricula) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
		this.matricula = matricula;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public abstract TipoCargo getCargo();

	public int getSenha() {
		return this.senha;
	}

	public void setSenha(int senhaAntiga, int senhaNova) {
		
		if (this.senha == senhaAntiga){
			this.senha = senhaNova;
		}
	}

	public int getMatricula() {
		return this.matricula;
	}
	
}
