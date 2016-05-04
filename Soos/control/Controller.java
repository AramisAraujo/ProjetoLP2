package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import exceptions.AtualizarInfoException;
import exceptions.CadastroException;
import exceptions.ConsultaException;
import exceptions.ExcluirCadastroException;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.OpenSystemException;
import exceptions.ProntuarioException;
import exceptions.SystemCloseException;
import exceptions.VerificaExcecao;
import factories.FactoryUsuario;
import paciente.Prontuario;
import paciente.TipoSanguineo;
import usuario.Usuario;
import usuario.TipoCargo;

public class Controller {
	
	private boolean sistemaBloqueado;
	private int cadastrosRealizados;
	private Usuario usuarioAtual;
	private Map<String,Usuario> bancoUsuarios;
	private List<Prontuario> bancoProntuarios;
	private FactoryUsuario factoryUsuarios;
	
	
	public Controller(){
		this.sistemaBloqueado = true;
		this.cadastrosRealizados = 0;
		this.bancoUsuarios = new HashMap<String,Usuario>();
		this.bancoProntuarios = new ArrayList<Prontuario>();
		this.factoryUsuarios = new FactoryUsuario();
		this.usuarioAtual = null;
	}
	
	public void iniciaSistema() throws OpenSystemException{
		//NYI
		
	}
	
	public void fechaSistema() throws SystemCloseException{
		
		if(this.usuarioAtual != null){
			String errorMsg = "Um funcionario ainda esta logado: " + usuarioAtual.getNome()+".";
			throw new SystemCloseException(errorMsg);
		}
		//NYI
	}
	
	public String liberaSistema(String chave, String nome, 
			String dataNascimento) throws Exception{
		
		if(sistemaBloqueado == false){
			throw new OpenSystemException("Sistema liberado anteriormente.");
		}
		
		if(!(chave.equals("c041ebf8"))){
			throw new OpenSystemException("Chave invalida.");
		}

		String matriculaDiretor = this.cadastraFuncionario(nome, "Diretor Geral", dataNascimento);

		this.sistemaBloqueado = false;
				
		return matriculaDiretor;
				
	}
	
	public void login(String matricula, String senha) throws LoginException{
			
		Usuario loginTarget = this.getUsuario(matricula);
		
		if(loginTarget == null){
			throw new LoginException("Funcionario nao cadastrado.");
		}
				
		if (!(loginTarget.getSenha().equals(senha))){
			throw new LoginException("Senha incorreta.");
		}
		
		if(usuarioAtual != null){
			throw new LoginException("Um funcionario ainda esta logado: " + usuarioAtual.getNome()+".");
		}
		
		this.usuarioAtual = loginTarget;
	}
	
	public void logout() throws LogoutException{
		
		if(this.usuarioAtual == null){
			throw new LogoutException("Nao ha um funcionario logado.");
		}
		
		this.usuarioAtual = null;
		
	}
	
	public String cadastraFuncionario(String nome, String cargo, 
			String dataNascimento) throws CadastroException{
		
		String matricula;
		String senha;
		LocalDate birthDate;
		TipoCargo targetCargo;
		
		if(sistemaBloqueado == false){ 
			if(usuarioAtual == null){
				String errorMsg = "Nenhum funcionario estah logado.";
				
				throw new CadastroException("Erro no cadastro de funcionario.", errorMsg);
			}
			else if(!usuarioAtual.getMatricula().startsWith("1")){
			String errorMsg = "O funcionario "+usuarioAtual.getNome()+
  					" nao tem permissao para cadastrar funcionarios.";
			throw new CadastroException("Erro no cadastro de funcionario.", errorMsg);
			}
		}
		
		try {
			VerificaExcecao.checkEmptyString(nome, "Nome do funcionario");
		} catch (Exception e) {
			throw new CadastroException("Erro no cadastro de funcionario.", e.getMessage());
		}
		try {
			VerificaExcecao.checkEmptyString(cargo, "Nome do cargo");
			targetCargo = this.stringToCargo(cargo);
		} catch (Exception e) {
			throw new CadastroException("Erro no cadastro de funcionario.", e.getMessage());
		}
		try {
			birthDate = stringToDate(dataNascimento);
			VerificaExcecao.checarData(birthDate);
		} catch (Exception e) {
			throw new CadastroException("Erro no cadastro de funcionario.", "Data invalida.");
		}
		
		try {
			matricula = this.gerarMatricula(birthDate, targetCargo);
		} catch (Exception e) {
			throw new CadastroException("Erro no cadastro de funcionario.", e.getMessage());
		}
		senha = this.gerarSenha(birthDate, matricula);
		
		Usuario targetUser = this.factoryUsuarios.criarUsuario(nome, birthDate, senha, matricula, targetCargo);
		
		this.bancoUsuarios.put(matricula, targetUser);
		
		this.cadastrosRealizados = this.cadastrosRealizados + 1;
		
		return matricula;
		
	}
	
	public String cadastraPaciente(String nome, String Data, double Peso
			, String sexoBio, String genero, String tipoSanguineo) throws CadastroException{
		
		if(!this.usuarioAtual.getMatricula().startsWith("3")){
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", 
					"O funcionario " + usuarioAtual.getNome()+" nao tem permissao para cadastrar pacientes.");
		}
		
		for (Prontuario prontuario : this.bancoProntuarios) {
			
			try {
				if(prontuario.getInfoPaciente("Nome").equals(nome)){
					throw new Exception("Paciente ja cadastrado.");
				}
			} catch (Exception e) {
				throw new CadastroException("Nao foi possivel cadastrar o paciente.", e.getMessage());
			}
			
		}
		
		try {
			VerificaExcecao.checkEmptyString(nome, "Nome");
		} catch (Exception e) {
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", "Nome do paciente "
					+ "nao pode ser vazio.");
		}
		
		LocalDate birthDate;
		try {
			birthDate = this.stringToDate(Data);
			VerificaExcecao.checarData(birthDate);
		} catch (Exception e) {
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", "Data invalida.");
		}
		
		try {
			VerificaExcecao.checarPeso(Peso);
		} catch (ProntuarioException e) {
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", "Peso do paciente "
					+ "nao pode ser negativo.");
		}
		
		try {
			VerificaExcecao.checarSexoBiologico(sexoBio);
		} catch (ProntuarioException e) {
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", "Sexo biologico "
					+ "nao identificado.");
		}
		
		try {
			VerificaExcecao.checkEmptyString(genero, "Genero");
		} catch (Exception e) {
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", e.getMessage());
		}
		
		
		
		UUID novoID = UUID.randomUUID();
		TipoSanguineo tipoSangue;
		Prontuario novoProntuario;
		
		try {
			tipoSangue = this.stringToSanguineo(tipoSanguineo);
		} catch (Exception e) {
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", e.getMessage());
		}
		
		try {
			novoProntuario = new Prontuario(nome, birthDate, Peso, 
					sexoBio, genero, tipoSangue, novoID);
		} catch (Exception e) {
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", e.getMessage());
		}
		
		this.bancoProntuarios.add(novoProntuario);
		
		Collections.sort(this.bancoProntuarios);
		
		return novoID.toString();
		
		
	}

	public String getInfoPaciente(String paciente, String atributo) throws ConsultaException{
		
		Prontuario targetProntuario = null;
		
		for (Prontuario prontuario : this.bancoProntuarios) {
			
			if(prontuario.getID().equals(paciente)){
				targetProntuario = prontuario;
			}
			
		}
		
		if(targetProntuario == null){
			throw new ConsultaException("paciente", "Paciente nao cadastrado.");
		}
		
		try {
			return targetProntuario.getInfoPaciente(atributo);
		} catch (Exception e) {
			throw new ConsultaException("paciente", e.getMessage());
		}
		
	}
	
	public String getProntuario(int posicao) throws ProntuarioException{
		
		if(posicao < 0){
			throw new ProntuarioException("Erro ao consultar prontuario. Indice do prontuario nao pode"
					+ " ser negativo.");
		}
		if(posicao > this.bancoProntuarios.size()){
		
			throw new ProntuarioException("Erro ao consultar prontuario. "+
					"Nao ha prontuarios suficientes (max = "+this.bancoProntuarios.size()+").");
		}
		
		return this.bancoProntuarios.get(posicao).getID();
		
	}
	
	public String getInfoFuncionario(String matricula, String info) throws ConsultaException{
		
		if ((Pattern.matches("[a-zA-Z]+", matricula)) || matricula.length() < 7) {
			throw new ConsultaException("funcionario", "A matricula nao segue o padrao.");
		}
		
		Usuario targetUser = getUsuario(matricula);
		
		if(targetUser == null){
			throw new ConsultaException("funcionario","Funcionario nao cadastrado.");
		}
		
		
		if(info.equalsIgnoreCase("Nome")){
			String nome = targetUser.getNome();
			return nome;
			
		}
		else if(info.equalsIgnoreCase("Cargo")){
			
			String cargo;
			
			switch (targetUser.getCargo()) {
			
			case DIRETOR:
				cargo = "Diretor Geral";
				return cargo;
				
			case TECNICOADM:

				cargo = "Tecnico Administrativo";
				return cargo;

			case MEDICO:

				cargo = "Medico";
				return cargo;

			default:
				throw new ConsultaException("funcionario", "Cargo invalido");
			}
			
		}
		else if(info.equalsIgnoreCase("Data")){
			String data = targetUser.getDataNascimento().toString();
			return data;
			
		}
		else if(info.equalsIgnoreCase("Senha")){
			throw new ConsultaException("funcionario", "A senha do funcionario eh protegida.");
	
		}
		else{
			throw new ConsultaException("funcionario", "Atributo desconhecido.");
		}
		

		
	}
	
	public void excluiFuncionario(String matricula, String senha)throws ExcluirCadastroException{
		
		String senhaDiretor = "";
		
		if(!usuarioAtual.getMatricula().startsWith("1")){
			
			String erroMsg = "O funcionario "+usuarioAtual.getNome()
			+" nao tem permissao para excluir funcionarios.";
			
			throw new ExcluirCadastroException("funcionario", erroMsg);
		}
		
		if ((Pattern.matches("[a-zA-Z]+", matricula)) || matricula.length() < 7) {
			throw new ExcluirCadastroException("funcionario", "A matricula nao segue o padrao.");
		}
		
		Usuario targetUser = getUsuario(matricula);
		
		if(targetUser == null){
			throw new ExcluirCadastroException("funcionario", "Funcionario nao cadastrado.");
		}
		
		for (Usuario funcionario : this.bancoUsuarios.values()) {
			if(funcionario.getMatricula().startsWith("1")){
				senhaDiretor = funcionario.getSenha();
			}
			
		}
		
		if(!senha.equals(senhaDiretor)){
			throw new ExcluirCadastroException("funcionario","Senha invalida.");
		}
		
		this.bancoUsuarios.remove(matricula);
		
	}
	
	public void atualizaInfoFuncionario(String matricula, 
			String atributo, String novoValor) throws AtualizarInfoException{
		
		if(!usuarioAtual.getMatricula().startsWith("1")){
			throw new AtualizarInfoException("funcionario", "O usuario atual nao tem permissao para"
					+ "alterar informacoes.");
		}
		
		if ((Pattern.matches("[a-zA-Z]+", matricula)) || matricula.length() < 7) {
			throw new AtualizarInfoException("funcionario", "A matricula nao segue o padrao.");
		}
		
		Usuario targetUser = this.getUsuario(matricula);
		
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
			
			}catch(Exception e){
				throw new AtualizarInfoException("funcionario", "Data invalida.");
			}
			
			try {
				VerificaExcecao.checarData(novaData);
			} catch (ProntuarioException e) {
				throw new AtualizarInfoException("funcionario", e.getMessage());
			}
			
			targetUser.setDataNascimento(novaData);
			
			break;
			
		default:
			throw new AtualizarInfoException("funcionario", "Atributo invalido.");
		}
				
	}
	
	public void atualizaInfoFuncionario(String atributo, String novoValor) throws AtualizarInfoException{
		
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
			
			usuarioAtual.setNome(novoValor);
			
			break;

		case "DATA":
			
			LocalDate novaData;
			try{
				
			novaData = this.stringToDate(novoValor);
			
			}catch(Exception e){
				throw new AtualizarInfoException("funcionario", "Data invalida.");
			}
			
			try {
				VerificaExcecao.checarData(novaData);
			} catch (ProntuarioException e) {
				throw new AtualizarInfoException("funcionario", e.getMessage());
			}
			
			usuarioAtual.setDataNascimento(novaData);
			
			break;
			
		default:
			throw new AtualizarInfoException("Erro no cadastro de funcionario.", "Atributo invalido.");
		}
				
	}
	
	public void atualizaSenha(String senhaAntiga,String novaSenha) throws AtualizarInfoException{
		
		if(!usuarioAtual.getSenha().equals(senhaAntiga)){
			throw new AtualizarInfoException("funcionario", "Senha invalida.");
		}
		
		try {
			VerificaExcecao.checkEmptyString(novaSenha, "Senha");
		} catch (Exception e) {
			throw new AtualizarInfoException("funcionario", e.getMessage());
		}
		
		if ((Pattern.matches("[a-zA-Z0-9]+", novaSenha) == false) ||
				novaSenha.length() < 8 || novaSenha.length() > 12) {
			
			throw new AtualizarInfoException("funcionario", "A nova senha deve "
					+ "ter entre 8 - 12 caracteres alfanumericos.");
		}
		
		String matricula = this.usuarioAtual.getMatricula();
		
		Usuario targetUser = this.getUsuario(matricula);
		
		targetUser.setSenha(senhaAntiga, novaSenha);
		
		this.usuarioAtual = targetUser;
		
	}
	
	private String gerarSenha(LocalDate anoNascimento, String matricula){
		
		String senha = "";
		int ano = anoNascimento.getYear();
		
		senha = senha + String.valueOf(ano);
		senha = senha + matricula.substring(0, 4);
		
		return senha;
		
	}
	
	private String gerarMatricula(LocalDate dataNascimento, TipoCargo cargo) throws Exception{
		
		String matricula = "";
		int esteAno = LocalDate.now().getYear();
		String parteCadastros = String.format("%03d", (this.cadastrosRealizados + 1));

				
		switch (cargo) {
		
		case DIRETOR:

			for (Usuario usuario : this.bancoUsuarios.values()) {
				if(usuario.getMatricula().startsWith("1")){
					throw new Exception("Nao eh possivel criar mais de um Diretor Geral.");
				}
			}
			
			matricula = matricula + "1";
			matricula = matricula + String.valueOf(esteAno);
			matricula = matricula + parteCadastros;	
			
			break;
		
		case MEDICO:
			
			matricula = matricula + "2";
			matricula = matricula + String.valueOf(esteAno);
			matricula = matricula + parteCadastros;	
			
			break;
			
			
		case TECNICOADM:
			matricula = matricula + "3";
			matricula = matricula + String.valueOf(esteAno);
			matricula = matricula + parteCadastros;	
			
			break;
		
		default:
			throw new Exception("Cargo invalido.");
			
		}
		return matricula;
		
	}
	
	private Usuario getUsuario(String matricula){
		
		for (Usuario usuario : this.bancoUsuarios.values()) {
			
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
	
	private TipoCargo stringToCargo(String cargo) throws Exception{
		
		switch (cargo.toUpperCase()){
		
		case "DIRETOR GERAL":
			return TipoCargo.DIRETOR;
			
		case "MEDICO":
			return TipoCargo.MEDICO;
			
		case "TECNICO ADMINISTRATIVO":
			return TipoCargo.TECNICOADM;
		
		default:
			throw new Exception("Cargo invalido.");
		}
		
	}
	
	private TipoSanguineo stringToSanguineo(String tipoSanguineo) throws Exception{
		
		for (TipoSanguineo sangue : TipoSanguineo.values()) {
			if(sangue.toString().equalsIgnoreCase(tipoSanguineo)){
				return sangue;
			}
				
		}
		throw new Exception("Tipo sanguineo invalido.");
			
		
	}
	
}
