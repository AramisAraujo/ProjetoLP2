package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import exceptions.CadastroException;
import exceptions.ConsultaException;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.OpenSystemException;
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
		this.usuarioAtual = null;
	}
	
	public void iniciaSistema() throws OpenSystemException{
		
		File usuarios = new File("Usuarios.ser");
		if(usuarios.exists() && !usuarios.isDirectory() && usuarios.canRead()){
			ObjectInputStream objectinputstream = null;
		
			try {
				FileInputStream streamIn = new FileInputStream("Usuarios.ser");
		    
				objectinputstream = new ObjectInputStream(streamIn);
		    
				@SuppressWarnings("unchecked")
				List<Usuario> readCase = (List<Usuario>) objectinputstream.readObject();

				
				for (Usuario usuario : readCase) {
					this.bancoUsuarios.put(usuario.getMatricula(), usuario);

					
				}
		    
			} catch (Exception e) {
			
				e.printStackTrace();
		    
			} finally {
				
				if(objectinputstream != null){
					try {
						objectinputstream .close();
					} catch (IOException e) {
						throw new OpenSystemException(e.getMessage());
					}
				} 
				
			}
		}
		
	}
	
	public void fechaSistema() throws SystemCloseException, IOException{
		if(this.usuarioAtual != null){
			String errorMsg = "Um funcionario ainda esta logado: " + usuarioAtual.getNome()+".";
			throw new SystemCloseException(errorMsg);
		}
		
		ObjectOutputStream oos = null;
		FileOutputStream fout = null;
		try{
		    fout = new FileOutputStream("Usuarios.ser", true);
		    oos = new ObjectOutputStream(fout);
		    
		    List<Usuario> usuarios = new ArrayList<Usuario>();
		    usuarios.addAll(bancoUsuarios.values());
		    
		    oos.writeObject(usuarios);
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    if(oos  != null){
		        oos.close();
		    } 
		}
		
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
		
		//this.usuarioAtual = getUsuario(matriculaDiretor);
		
		return matriculaDiretor;
				
	}
	
	public void login(String matricula, String senha) throws LoginException{
			
		Usuario loginTarget = getUsuario(matricula);
		
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
				
				throw new CadastroException("funcionario", errorMsg);
			}
			else if(!usuarioAtual.getMatricula().startsWith("1")){
			String errorMsg = "O funcionario "+usuarioAtual.getNome()+
  					" nao tem permissao para cadastrar funcionarios.";
			throw new CadastroException("funcionario", errorMsg);
			}
		}
		
		try {
			VerificaExcecao.checkEmptyString(nome, "Nome do funcionario");
		} catch (Exception e) {
			throw new CadastroException("funcionario", e.getMessage());
		}
		try {
			VerificaExcecao.checkEmptyString(cargo, "Nome do cargo");
			targetCargo = this.stringToCargo(cargo);
		} catch (Exception e) {
			throw new CadastroException("funcionario", e.getMessage());
		}
		try {
			birthDate = stringToDate(dataNascimento);
			VerificaExcecao.checarData(birthDate);
		} catch (Exception e) {
			throw new CadastroException("funcionario", "Data invalida.");
		}
		
		try {
			matricula = this.gerarMatricula(birthDate, targetCargo);
		} catch (Exception e) {
			throw new CadastroException("funcionario", e.getMessage());
		}
		//
		if(matricula == null||matricula == ""){
			throw new CadastroException("funcionario", "Oie Aramis");
		}
		//
		senha = this.gerarSenha(birthDate, matricula);
		
		Usuario targetUser = this.factoryUsuarios.criarUsuario(nome, birthDate, senha, matricula, targetCargo);
		
		this.bancoUsuarios.put(matricula, targetUser);
		
		this.cadastrosRealizados = this.cadastrosRealizados + 1;
		
		return matricula;
		
	}
	public String getInfoFuncionario(String matricula, String info) throws ConsultaException{
		
		Usuario targetUser = getUsuario(matricula);
		
		if(targetUser == null){
			throw new ConsultaException("funcionario","funcionario nao encontrado na base de dados.");
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
	public boolean removerUsuario(String matricula){
		return true;
		
	}
	public boolean atualizarInfo(){
		return true;
		
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
	
	
	
	

}
