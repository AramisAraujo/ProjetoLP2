package usuario;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Funcionario implements Serializable{
	private String nome;
	private LocalDate dataNascimento;
	private String senha;
	private String matricula;
	private final TipoCargo cargo;
	
	public Funcionario(String nome, LocalDate dataNascimento, String matricula, String senha, TipoCargo cargo) throws Exception {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.matricula = matricula;
		this.senha = senha;
		this.cargo = cargo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public TipoCargo getCargo() {
		return this.cargo;
	}

	public String getSenha() {
		String senha = this.senha;
		return senha;
	}

	public void setSenha(String senhaAntiga, String senhaNova) {
		
		if (this.senha.equals(senhaAntiga)){
			this.senha = senhaNova;
		}
	}

	public String getMatricula() {
		return this.matricula;
	}

}