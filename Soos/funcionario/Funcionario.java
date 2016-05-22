package funcionario;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Funcionario
 * Classe que representa um funcionario do sistema Soos.
 * Cada funcionario possui dados como nome, data de nascimento,
 * senha uma matricula e um cargo que ocupa.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */

public  class Funcionario implements Serializable{
	
	private String nome;
	private LocalDate dataNascimento;
	private String senha;
	private String matricula;
	private final TipoCargo cargo;
	
	public Funcionario(String nome, LocalDate birthDate, String senha, String matricula, TipoCargo cargo) {
		
		this.nome = nome;
		this.dataNascimento = birthDate;
		this.senha = senha;
		this.matricula = matricula;
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

	public  TipoCargo getCargo(){
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
