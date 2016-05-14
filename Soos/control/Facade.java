package control;

import java.io.IOException;

import exceptions.AtualizarInfoException;
import exceptions.BancoOrgaoException;
import exceptions.CadastroException;
import exceptions.ConsultaException;
import exceptions.ExcluirCadastroException;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.MedicamentoException;
import exceptions.OpenSystemException;
import exceptions.ProntuarioException;
import exceptions.SystemCloseException;
import farmacia.Medicamento;

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
	public String cadastraMedicamento(String nome, String tipo, double preco, 
			int quantidade, String categorias) throws CadastroException{
		
		return this.controle.cadastraMedicamento(nome, tipo, preco, quantidade, categorias);
	}
	
	public void cadastraOrgao (String nome, String tipoSanguineo) throws BancoOrgaoException{
		
		this.controle.cadastraOrgao(nome, tipoSanguineo);
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
	
	public String getInfoMedicamento(String atributo, String medicamento) throws ConsultaException, MedicamentoException{
		return this.controle.getInfoMedicamento(atributo, medicamento);
	}
	
	public String buscaOrgPorSangue(String tipoSanguineo) throws BancoOrgaoException{
		
		return this.controle.buscaOrgPorSangue(tipoSanguineo);
	}
	
	public String buscaOrgPorNome(String nome) throws BancoOrgaoException{
		
		return this.controle.buscaOrgPorNome(nome);
	}
	
	public boolean buscaOrgao(String nome, String tipoSanguineo) throws BancoOrgaoException{
		
		return this.controle.buscaOrgao(nome, tipoSanguineo);
		
	}
	
	public void excluiFuncionario(String matricula, String senha) throws ExcluirCadastroException{
		this.controle.excluiFuncionario(matricula, senha);
		
	}
	
	public void retiraOrgao(String nome, String tipoSanguineo) throws ExcluirCadastroException{
		
		this.controle.retiraOrgao(nome, tipoSanguineo);
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

	public void atualizaMedicamento(String nome, String atributo,
			String novoValor) throws AtualizarInfoException{
		this.controle.atualizaMedicamento(nome, atributo, novoValor);
	}
	
	public String consultaMedCategoria(String categoria) throws ConsultaException{
		return this.controle.consultaMedCategoria(categoria);
	}
	
	public Medicamento consultaMedNome(String nome) throws ConsultaException{
		return this.controle.consultaMedNome(nome);
	}
	
	public String getEstoqueFarmacia(String ordenacao) throws ConsultaException, MedicamentoException{
		return this.controle.getEstoqueFarmacia(ordenacao);
	}
	
	public int qtdOrgaos(String nome) throws BancoOrgaoException{
		
		return this.controle.qtdOrgaos(nome);
	}
	
	public int totalOrgaosDisponiveis(){
		
		return this.controle.totalOrgaosDisponiveis();
	}
	
}
