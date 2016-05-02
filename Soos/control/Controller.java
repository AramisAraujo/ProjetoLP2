package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import exceptions.CadastroException;
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
	
	private boolean cadstraFuncionario(String nome, String cargo, 
			String dataNascimento) throws CadastroException{
		
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
	
	public String getinfoFuncionario(String matricula, String info){
		return info;
		
	}
	private String gerarSenha(LocalDate anoNascimento, String matricula){
		return matricula;
		
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
