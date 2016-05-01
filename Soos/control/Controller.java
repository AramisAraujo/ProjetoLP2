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
		
		this.sistemaBloqueado = false;
		
		return false;
		
	}
	private boolean cadstraFuncionario(String nome, String cargo, String dataNascimento){
		return true;
		
	}
	
	public String getinfoFuncionario(int matricula, String info){
		
	}
	private void gerarSenha(int anoNascimento, int matricula){
		
	}
	private Usuario getUsuario(int matricula){
		return true;
		
	}
	private boolean removerUsuario(int matricula){
		return true;
		
	}
	private boolean atualizarInfo(){
		return true;
		
	}
	
	private Date stringToDate(String dateCandidate){
		
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		Date date;
		try {
			date = formato.parse(dateCandidate);
			return date;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	

}
