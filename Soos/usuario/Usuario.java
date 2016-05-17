package usuario;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe implementada para criar um usuario e obter ou mudar informacoes do
 * mesmo.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
public abstract class Usuario implements Serializable{

	private static final long serialVersionUID = -1015234438916494007L;
	private String nome;
	private LocalDate dataNascimento;
	private String senha;
	private String matricula;
	
	public Usuario(String nome, LocalDate birthDate, String senha, String matricula) {
		this.nome = nome;
		this.dataNascimento = birthDate;
		this.senha = senha;
		this.matricula = matricula;
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

	public String getMatricula() {
		return this.matricula;
	}
	
}
