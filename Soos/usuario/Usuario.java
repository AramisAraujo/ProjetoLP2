package usuario;

import java.util.Date;

public abstract class Usuario {
	private String nome;
	private Date dataNascimento;
	private String senha;
	private int matricula;
	
	public Usuario(String nome, Date dataNascimento, String senha, int matricula) {
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

	public String getSenha() {
		String senha = this.senha;
		return senha;
	}

	public void setSenha(String senhaAntiga, String senhaNova) {
		
		if (this.senha.equals(senhaAntiga)){
			this.senha = senhaNova;
		}
	}

	public int getMatricula() {
		return this.matricula;
	}
	
}
