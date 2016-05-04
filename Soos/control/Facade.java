package control;

import java.io.IOException;

import exceptions.AtualizarInfoException;
import exceptions.CadastroException;
import exceptions.ConsultaException;
import exceptions.ExcluirCadastroException;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.OpenSystemException;
import exceptions.ProntuarioException;
import exceptions.SystemCloseException;

public class Facade {
	
	Controller controle;
	
	public Facade(){
		
		this.controle = new Controller();
		
	}
	
	public void iniciaSistema() throws OpenSystemException{
		this.controle.iniciaSistema();
	
		
	}
	
	public void fechaSistema() throws SystemCloseException, IOException{
		this.controle.fechaSistema();
		
	}
	
	public String liberaSistema(String chave, String nome, 
			String dataNascimento) throws Exception{
		
		return this.controle.liberaSistema(chave, nome, dataNascimento);
		
	}
	
	public void login(String matricula, String senha) throws LoginException{
		
		this.controle.login(matricula, senha);
	}
	
	public void logout()throws LogoutException{
		this.controle.logout();
	}
	
	public String cadastraFuncionario(String nome, String cargo, 
			String dataNascimento) throws CadastroException{
		
		return this.controle.cadastraFuncionario(nome, cargo, dataNascimento);
		
	}
	
	public String cadastraPaciente(String nome, String Data, double Peso
			, String sexoBio, String genero, String tipoSanguineo) throws CadastroException{
		
		return this.controle.cadastraPaciente(nome, Data, Peso, sexoBio, genero, tipoSanguineo);
	}
	
	public String getProntuario(int posicao) throws ProntuarioException{
		return this.controle.getProntuario(posicao);
	}
	
	public String getInfoPaciente(String paciente, String atributo) throws ConsultaException{
		return this.controle.getInfoPaciente(paciente, atributo);
	}
	
	public String getInfoFuncionario(String matricula, String info) throws ConsultaException{
		
		return this.controle.getInfoFuncionario(matricula, info);
	}
	
	public void excluiFuncionario(String matricula, String senha) throws ExcluirCadastroException{
		this.controle.excluiFuncionario(matricula, senha);
		
	}
	
	public void atualizaInfoFuncionario(String matricula, 
			String atributo, String novoValor) throws AtualizarInfoException{
		
		this.controle.atualizaInfoFuncionario(matricula, atributo, novoValor);
		
	}
	
	public void atualizaInfoFuncionario(String atributo, String novoValor) throws AtualizarInfoException{
		
		this.controle.atualizaInfoFuncionario(atributo, novoValor);
		
	}
	
	public void atualizaSenha(String senhaAntiga, String novaSenha) throws AtualizarInfoException{
		this.controle.atualizaSenha(senhaAntiga, novaSenha);
	}

	
	
}
