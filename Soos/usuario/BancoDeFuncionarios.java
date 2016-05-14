package usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import exceptions.AtualizarInfoException;
import exceptions.CadastroException;
import exceptions.ConsultaException;
import exceptions.ExcluirCadastroException;
import exceptions.ProntuarioException;
import exceptions.VerificaExcecao;
import factories.FactoryFuncionario;

public class BancoDeFuncionarios {
		private Map<String,Funcionario> bancoFuncionarios;
		private FactoryFuncionario factoryFuncionario;
		
		public BancoDeFuncionarios(){
			this.bancoFuncionarios = new HashMap<String,Funcionario>();
			this.factoryFuncionario = new FactoryFuncionario();
		}

		public String cadastraFuncionario(String nome, String cargo, 
				String dataNascimento) throws CadastroException{

			try {
				Funcionario targetUser = this.factoryFuncionario.criarFuncionario(nome, dataNascimento, cargo);
				this.bancoFuncionarios.put(targetUser.getMatricula(), targetUser);
				return targetUser.getMatricula();
			} catch (Exception exp) {
				throw new CadastroException("Erro no cadastro de funcionario.", exp.getMessage());
			}
		}
		
		
		public String getInfoFuncionario(String matricula, String atributo) throws ConsultaException{
			
			if ((Pattern.matches("[a-zA-Z]+", matricula)) || matricula.length() < 7) {
				throw new ConsultaException("funcionario", "A matricula nao segue o padrao.");
			}
			
			Funcionario targetUser = getUsuario(matricula);
			
			if(targetUser == null){
				throw new ConsultaException("funcionario","Funcionario nao cadastrado.");
			}
			
			
			switch (atributo.toUpperCase()) {
			
			case "NOME":
				return targetUser.getNome();
			
			case "CARGO":
				switch (targetUser.getCargo()) {
				
				case DIRETOR:
					return TipoCargo.DIRETOR.toString();
					
				case TECNICOADM:
					return TipoCargo.TECNICOADM.toString();

				case MEDICO:
					return TipoCargo.MEDICO.toString();

				default:
					throw new ConsultaException("funcionario", "Cargo invalido");
				}
			case "DATA":
				String data = targetUser.getDataNascimento().toString();
				return data;

			case "SENHA":
				throw new ConsultaException("funcionario", "A senha do funcionario eh protegida.");
			
			default:
				throw new ConsultaException("funcionario", "Atributo desconhecido.");
			}
			
		}
		
		public void excluiFuncionario(String matricula)throws ExcluirCadastroException{
			//TESTAR SE EH O DIRETOR LOGADO, PEGAR SENHA DO DIRETOR LÃ� NO CONTROLLER.	
			if ((Pattern.matches("[a-zA-Z]+", matricula)) || matricula.length() < 7) {
				throw new ExcluirCadastroException("funcionario", "A matricula nao segue o padrao.");
			}
			
			Funcionario targetUser = getUsuario(matricula);
			
			if(targetUser == null){
				throw new ExcluirCadastroException("funcionario", "Funcionario nao cadastrado.");
			}
			this.bancoFuncionarios.remove(matricula);
			
		}
		
		public void atualizaInfoFuncionario(String matricula, 
				String atributo, String novoValor) throws AtualizarInfoException{
			
			//TESTAR SE EH O DIRETOR QUE ESTA LOGADO.
			if ((Pattern.matches("[a-zA-Z]+", matricula)) || matricula.length() < 7) {
				throw new AtualizarInfoException("funcionario", "A matricula nao segue o padrao.");
			}
			
			Funcionario targetUser = this.getUsuario(matricula);
			
			//ADICIONEI ESSA PARTE.
			if(targetUser == null){
				throw new AtualizarInfoException("funcionario", "Funcionario nao cadastrado.");
			}
			
			switch (atributo.toUpperCase()) {
			
			case "NOME":
				
				try {
					VerificaExcecao.checkEmptyString(novoValor, "Nome");
				} catch (Exception e) {
					throw new AtualizarInfoException("funcionario", "Nome do funcionario nao pode ser vazio.");
				}
				
				if ((Pattern.matches("[a-zA-Z0-9]+", novoValor)) || novoValor.length() > 50) {
					throw new AtualizarInfoException("funcionario", "Nome invalido.");
				}
				
				targetUser.setNome(novoValor);
				
				break;

			case "DATA":
				
				LocalDate novaData;
				try{
					novaData = this.stringToDate(novoValor);
				}catch(Exception exp){
					throw new AtualizarInfoException("funcionario", "Data invalida.");
				}
				
				try {
					VerificaExcecao.checarData(novaData);
				} catch (ProntuarioException exp) {
					throw new AtualizarInfoException("funcionario", exp.getMessage());
				}
				
				targetUser.setDataNascimento(novaData);
				
				break;
				
			default:
				throw new AtualizarInfoException("funcionario", "Atributo invalido.");
			}
					
		}
		
		public void atualizaInfoFuncionario(Funcionario usuarioAtual, String atributo, String novoValor) throws AtualizarInfoException{
			
			//PENSAR EM UM JEITO DE COLOCAR MAIS NA RAIZ.
			switch (atributo.toUpperCase()) {
			
			case "NOME":
				
				try {
					VerificaExcecao.checkEmptyString(novoValor, "Nome");
				} catch (Exception exp) {
					throw new AtualizarInfoException("funcionario", "Nome do funcionario nao pode ser vazio.");
				}
				
				if ((Pattern.matches("[a-zA-Z0-9]+", novoValor)) || novoValor.length() > 50) {
					throw new AtualizarInfoException("funcionario", "Nome invalido.");
				}
				
				usuarioAtual.setNome(novoValor);
				
				break;

			case "DATA":
				
				LocalDate novaData;
				try{
					
				novaData = this.stringToDate(novoValor);
				
				}catch(Exception exp){
					throw new AtualizarInfoException("funcionario", "Data invalida.");
				}
				
				try {
					VerificaExcecao.checarData(novaData);
				} catch (ProntuarioException exp) {
					throw new AtualizarInfoException("funcionario", exp.getMessage());
				}
				
				usuarioAtual.setDataNascimento(novaData);
				
				break;
				
			default:
				throw new AtualizarInfoException("Erro no cadastro de funcionario.", "Atributo invalido.");
			}
					
		}
		
		public void atualizaSenha(Funcionario usuarioAtual, String senhaAntiga, String novaSenha) throws AtualizarInfoException{
			
			if(!usuarioAtual.getSenha().equals(senhaAntiga)){
				throw new AtualizarInfoException("funcionario", "Senha invalida.");
			}
			
			try {
				VerificaExcecao.checkEmptyString(novaSenha, "Senha");
			} catch (Exception exp) {
				throw new AtualizarInfoException("funcionario", exp.getMessage());
			}
			
			if ((Pattern.matches("[a-zA-Z0-9]+", novaSenha) == false) ||
					novaSenha.length() < 8 || novaSenha.length() > 12) {
				
				throw new AtualizarInfoException("funcionario", "A nova senha deve "
						+ "ter entre 8 - 12 caracteres alfanumericos.");
			}
			
			String matricula = usuarioAtual.getMatricula();
			
			Funcionario targetUser = this.getUsuario(matricula);
			
			targetUser.setSenha(senhaAntiga, novaSenha);
			
			usuarioAtual = targetUser;
			
		}
		
		private Funcionario getUsuario(String matricula){
			
			for (Funcionario usuario : this.bancoFuncionarios.values()) {
				
				if(usuario.getMatricula().equals(matricula)){
					return usuario;
				}
				
			}
			return null;
			
		}

		private LocalDate stringToDate(String dateCandidate){
			
			DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			LocalDate data = LocalDate.parse(dateCandidate, formatador);

			return data;
			

		}
}