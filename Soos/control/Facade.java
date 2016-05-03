package control;

import java.io.IOException;

import exceptions.CadastroException;
import exceptions.ConsultaException;
import exceptions.LoginException;
import exceptions.OpenSystemException;
import exceptions.SystemCloseException;

public class Facade {
	
	Controller controle;
	
	public Facade(){
		
		this.controle = new Controller();
		
	}
	
	public void iniciaSistema() throws IOException{
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
	
	public void cadastraFuncionario(String nome, String cargo, 
			String dataNascimento) throws CadastroException{
		
		this.controle.cadastraFuncionario(nome, cargo, dataNascimento);
		
	}
	
	public String getInfoFuncionario(String matricula, String info) throws ConsultaException{
		
		return this.controle.getInfoFuncionario(matricula, info);
	}
	
	public void removerUsuario(String matricula){
		this.controle.removerUsuario(matricula);
	}
	public void atualizarInfo(){
		this.controle.atualizarInfo();
		
	}

}
