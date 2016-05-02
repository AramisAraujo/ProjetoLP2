package control;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import usuario.Usuario;
import usuario.TipoCargo;

public class Controller {
	
	private boolean sistemaBloqueado;
	private Usuario usuarioAtual;
	private Map bancoUsuarios;
	
	public Controller(){
		this.sistemaBloqueado = true;
		this.bancoUsuarios = new HashMap<String,Usuario>();
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
		if(!temUsuario(matricula)){
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
		return true;
		
	}
	
	public String getinfoFuncionario(String matricula, String info){
		
	}
	private void gerarSenha(Date anoNascimento, String matricula){
		
	}
	private Usuario getUsuario(String matricula){
		return true;
		
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
	private Date stringToDate(String dateCandidate){
		
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		Date date;
		try {
			date = formato.parse(dateCandidate);
			return date;

		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	

}
