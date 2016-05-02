package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

import exceptions.CadastroException;
import exceptions.ConsultaException;
import exceptions.StringException;
import exceptions.SystemCloseException;
import exceptions.VerificaExcecao;
import factories.FactoryUsuario;
import usuario.Usuario;
import usuario.TipoCargo;

public class Controller {
	
	private boolean sistemaBloqueado;
	private int cadastrosRealizados;
	private Usuario usuarioAtual;
	private Map<String,Usuario> bancoUsuarios;
	private FactoryUsuario factoryUsuarios;
	
	
	public Controller(){
		this.sistemaBloqueado = true;
		this.cadastrosRealizados = 0;
		this.bancoUsuarios = new HashMap<String,Usuario>();
		this.factoryUsuarios = new FactoryUsuario();
	}
	
	public void iniciaSistema(){
		
	}
	public void fechaSistema() throws SystemCloseException{
		if(this.usuarioAtual != null){
			String errorMsg = "Um funcionario ainda esta logado: " + usuarioAtual.getNome()+".";
			throw new SystemCloseException(errorMsg);
		}
		
	}
	
	public boolean liberaSistema(String chave, String nome, String dataNascimento) throws CadastroException{
		if(sistemaBloqueado == false){
			//throw exception sistema jah liberado
		}
		if(!chave.equals("c041ebf8")){
			//throw exception chave errada
		}
		//cadastrar diretor
		this.cadstraFuncionario(nome, usuario.TipoCargo.DIRETOR.name(),
				dataNascimento);
		this.sistemaBloqueado = false;
		
		return false;
		
	}
	
	public void login(String matricula, String senha) throws LoginException{
		
		if(usuarioAtual != null){
			throw new LoginException("Um funcionario ainda esta logado:" + usuarioAtual.getNome()+".");
		}
		
		if(!this.temUsuario(matricula)){
			throw new LoginException("Funcionario nao cadastrado.");
		}
		
		Usuario loginTarget = getUsuario(matricula);
		
		if (!loginTarget.getSenha().equals(senha)){
			throw new LoginException("Senha incorreta.");
		}
		
		this.usuarioAtual = loginTarget;
	}
	
	private boolean cadstraFuncionario(String nome, String cargo, 
			String dataNascimento) throws CadastroException{
		
		if(!usuarioAtual.getMatricula().startsWith("1")){
			String errorMsg = "O funcionario "+usuarioAtual.getNome()+
					" nao tem permissao para cadastrar funcionarios.";
			throw new CadastroException("Funcionario", errorMsg);
		}
		
		try {
			VerificaExcecao.checkEmptyString(nome,"Nome do Funcionario");
		} catch (Exception e) {
			throw new CadastroException("Funcionario",e.getMessage());
		}
		
		LocalDate birthDate;
		
		try {
			birthDate = stringToDate(dataNascimento);
			VerificaExcecao.checarData(birthDate);
		} catch (Exception e) {
			throw new CadastroException("Funcionario","Data invalida.");
		}				
			
		try {
			VerificaExcecao.checkEmptyString(cargo, "Nome do Cargo");
		} catch (Exception e) {
			throw new CadastroException("Funcionario",e.getMessage());
		}
				
		String matricula;
		
		try {
			matricula = this.gerarMatricula(birthDate, TipoCargo.valueOf(cargo));
		} catch (Exception e) {
			throw new CadastroException("Funcionario",e.getMessage());
		}
		String senha = this.gerarSenha(birthDate, matricula);
		
		try {
			this.factoryUsuarios.criarUsuario(nome, birthDate, senha, matricula, TipoCargo.valueOf(cargo));
		} catch (Exception e) {
			throw new CadastroException("Funcionario", "Erro impossivel.");
		}
		
		return true;
		
	}
	
	public String getinfoFuncionario(String matricula, String info) throws ConsultaException{
		
		Usuario targetUser = getUsuario(matricula);
		
		if(targetUser == null){
			throw new ConsultaException("Funcionario","Funcionario nao encontrado na base de dados.");
		}
		

		if(info.equalsIgnoreCase("Nome")){
			return targetUser.getNome();
			
		}
		else if(info.equalsIgnoreCase("Cargo")){
			return targetUser.getCargo().name();
			
		}
		else if(info.equalsIgnoreCase("Data")){
			return targetUser.getDataNascimento().toString();
			
		}
		else if(info.equalsIgnoreCase("Senha")){
			throw new ConsultaException("Funcionario", "A senha do funcionario eh protegida.");
	
		}
		else{
			throw new ConsultaException("Funcionario", "Atributo desconhecido.");
		}
		
		
		
		
		return info;
		
	}
	private String gerarSenha(LocalDate anoNascimento, String matricula){
		
		String senha = "";
		int ano = anoNascimento.getYear();
		
		senha = senha + String.valueOf(ano);
		senha = senha + matricula.substring(0, 3);
		
		return senha;
		
	}
	private String gerarMatricula(LocalDate dataNascimento, TipoCargo cargo) throws Exception{
		
		String matricula = "";
		int anoNascimento = dataNascimento.getYear();
				
		switch (cargo) {
		
		case DIRETOR:

			for (Usuario usuario : this.bancoUsuarios.values()) {
				if(usuario.getMatricula().startsWith("1")){
					throw new Exception("Nao eh possivel criar mais de um Diretor Geral.");
				}
			}
			
			matricula = matricula + "1";
			matricula = matricula + String.valueOf(anoNascimento);
			matricula = matricula + this.cadastrosRealizados;	
			
			break;
		
		case MEDICO:
			
			matricula = matricula + "2";
			matricula = matricula + String.valueOf(anoNascimento);
			matricula = matricula + this.cadastrosRealizados;
			
			break;
			
			
		case TECNICOADM:
			matricula = matricula + "3";
			matricula = matricula + String.valueOf(anoNascimento);
			matricula = matricula + this.cadastrosRealizados;	
			
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
	private boolean removerUsuario(String matricula){
		return true;
		
	}
	private boolean atualizarInfo(){
		return true;
		
	}
	private boolean temUsuario(String matricula){
		return this.bancoUsuarios.containsKey(matricula);
	}
	private LocalDate stringToDate(String dateCandidate){
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		

		return LocalDate.parse(dateCandidate, formatador);
		

	}
	
	
	
	

}
