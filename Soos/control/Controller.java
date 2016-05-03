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
	private Map<String,Usuario> bancoUsuarios;//TODO achei o erro
	private FactoryUsuario factoryUsuarios;
	
	
	public Controller(){
		this.sistemaBloqueado = true;
		this.cadastrosRealizados = 0;
		this.bancoUsuarios = new HashMap<String,Usuario>();
		this.factoryUsuarios = new FactoryUsuario();
		this.usuarioAtual = null;
	}
	
	public void iniciaSistema() throws IOException{
		
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
					objectinputstream .close();
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
		
		this.usuarioAtual = getUsuario(matriculaDiretor);
		
		return matriculaDiretor;
				
	}
	
	public void login(String matricula, String senha) throws LoginException{
		
		if(usuarioAtual != null){
			throw new LoginException("Um funcionario ainda esta logado:" + usuarioAtual.getNome()+".");
		}
		
		Usuario loginTarget = getUsuario(matricula);
		
		if(loginTarget == null){
			throw new LoginException("Funcionario nao cadastrado.");
		}
				
		if (!(loginTarget.getSenha().equals(senha))){
			throw new LoginException("Senha incorreta.");
		}
		
		this.usuarioAtual = loginTarget;
	}
	
	public String cadastraFuncionario(String nome, String cargo, 
			String dataNascimento) throws CadastroException{
		
		if(sistemaBloqueado == false && usuarioAtual == null){ 
			
			String errorMsg = "O usuario nao esta logado.";
			
			throw new CadastroException("funcionario", errorMsg);
		}
		
		if(!(usuarioAtual.getMatricula().startsWith("1"))){
			String errorMsg = "O funcionario "+usuarioAtual.getNome()+
					" nao tem permissao para cadastrar funcionarios.";
			
			throw new CadastroException("funcionario", errorMsg);
		}
		
		try {
			
			VerificaExcecao.checkEmptyString(nome,"Nome do funcionario");
			
		} catch (Exception e) {
			
			throw new CadastroException("funcionario",e.getMessage());
		}
		
		try {
			
			VerificaExcecao.checkEmptyString(cargo, "Nome do cargo");
			
		} catch (Exception e) {
			
			throw new CadastroException("funcionario",e.getMessage());
		}
		
		LocalDate birthDate;
		
		try {
			
			birthDate = stringToDate(dataNascimento);
			
			VerificaExcecao.checarData(birthDate);
			
		} catch (Exception e) {
			
			throw new CadastroException("funcionario","Data invalida.");
		}				
						
		String matricula;
		TipoCargo cargoReal;
		
		try {
			
			switch (cargo) {
			
			case "Medico":
				cargoReal = TipoCargo.MEDICO;
				break;
			case "Diretor Geral":
				cargoReal = TipoCargo.DIRETOR;
				break;

			case "Tecnico Administrativo":
				cargoReal = TipoCargo.TECNICOADM;
				break;

			default:
				throw new Exception("Cargo invalido.");
			}
			
			matricula = this.gerarMatricula(birthDate, cargoReal);
			
		} catch (Exception e) {
			
			throw new CadastroException("funcionario",e.getMessage());
		}
		String senha = this.gerarSenha(birthDate, matricula);
		
		Usuario usuarioCadastrado;
		
		usuarioCadastrado = this.factoryUsuarios.criarUsuario(nome, birthDate, senha, matricula, cargoReal);

		this.cadastrosRealizados = cadastrosRealizados + 1;
		
		this.bancoUsuarios.put(usuarioCadastrado.getMatricula(), usuarioCadastrado);
		
		if(!this.temUsuario(matricula)){
			throw new CadastroException("funcionario","achei o erro");
		}
		
		return matricula;
		
	}
	public String getInfoFuncionario(String matricula, String info) throws ConsultaException{
		
		Usuario targetUser = getUsuario(matricula);
		
		if(targetUser == null){
			throw new ConsultaException("funcionario","funcionario nao encontrado na base de dados.");
		}
		

		if(info.equalsIgnoreCase("Nome")){
			return targetUser.getNome();
			
		}
		else if(info.equalsIgnoreCase("Cargo")){
			switch (targetUser.getCargo()) {
			case DIRETOR:

				return "Diretor Geral";
			case TECNICOADM:

				return "Tecnico Administrativo";
			case MEDICO:

				return "Medico";

			default:
				throw new ConsultaException("funcionario", "Cargo invalido");
			}
			
		}
		else if(info.equalsIgnoreCase("Data")){
			return targetUser.getDataNascimento().toString();
			
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
	private boolean temUsuario(String matricula){
		return this.bancoUsuarios.containsKey(matricula);
	}
	private LocalDate stringToDate(String dateCandidate){
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate data = LocalDate.parse(dateCandidate, formatador);

		return data;
		

	}
	
	
	
	

}
