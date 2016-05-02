package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import exceptions.StringException;
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
	public void fechaSistema(){
		
	}
	
	public boolean liberaSistema(String chave, String nome, String dataNascimento){
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
	
	public boolean login(String matricula, String senha){
		if(!this.temUsuario(matricula)){
			//throw new loginException("Nao foi possivel realizar o login. Funcionario nao cadastrado.")
			return false;
		}
		
		Usuario loginTarget = (Usuario) bancoUsuarios.get(matricula);
		
		if (!loginTarget.getSenha().equals(senha)){
			//throw new loginException("Nao foi possivel realizar o login. Senha incorreta.")
			return false;
		}
		
		this.usuarioAtual = loginTarget;
		return true;
	}
	
	private boolean cadstraFuncionario(String nome, String cargo, String dataNascimento){
		
		try {
			VerificaExcecao.checarString(nome);
		} catch (StringException e) {
			//throw new cadastroException(nome invalido);
		}
		
		LocalDate birthDate = stringToDate(dataNascimento);
		
		try {
			VerificaExcecao.checarData(birthDate);
		} catch (Exception e) {
			//throw new cadastroException(data nao valida);
		}				
		
		String matricula;
		try {
			matricula = this.gerarMatricula(birthDate, TipoCargo.valueOf(cargo));
		} catch (Exception e) {
			//throw new cadastroException(cargo nao existe);
		}
		String senha = this.gerarSenha(birthDate, matricula);
		
		try {
			this.factoryUsuarios.criarUsuario(nome, birthDate, senha, matricula, TipoCargo.valueOf(cargo));
		} catch (Exception e) {
			//throw new cadastroException(cargo nao existe);
		}
		
		return true;
		
	}
	
	public String getinfoFuncionario(String matricula, String info){
		
	}
	private String gerarSenha(LocalDate anoNascimento, String matricula){
		
	}
	private String gerarMatricula(LocalDate dataNascimento, TipoCargo cargo) throws Exception{
		
		String matricula = "";
		int anoNascimento = dataNascimento.getYear();
		
		switch (cargo) {
		
		case DIRETOR:

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
			throw new Exception();
			
		}
		return matricula;
		
	}
	private Usuario getUsuario(String matricula){
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
