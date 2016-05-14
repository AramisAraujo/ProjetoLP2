package factories;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import usuario.Funcionario;
import usuario.TipoCargo;
import exceptions.VerificaExcecao;

public class FactoryFuncionario {
	private static int cadastrosRealizados = 0;
	private TipoCargo cargo;
	private String matricula;
	private String senha;
	private LocalDate dataNascimento;
	
	public Funcionario criarFuncionario(String nome, String dataNascimentoString, String cargoString) throws Exception {
		VerificaExcecao.checkEmptyString(nome, "Nome do funcionario");
		VerificaExcecao.checkEmptyString(dataNascimentoString, "Data invalida.");
		this.dataNascimento = stringToDate(dataNascimentoString);
		VerificaExcecao.checarData(this.dataNascimento);
		this.cargo = stringToCargo(cargoString);
		this.matricula = gerarMatricula(cargo); 
		this.senha = gerarSenha(dataNascimento, matricula);
		Funcionario funcionarioCriado = new Funcionario(nome, dataNascimento, matricula, senha, cargo);
		cadastrosRealizados++;
		
		return funcionarioCriado;
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
	
private LocalDate stringToDate(String dateCandidate){
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate data = LocalDate.parse(dateCandidate, formatador);
		
		return data;
	}
	
	private String gerarSenha(LocalDate anoNascimento, String matricula){
		
		String senha = "";
		int ano = anoNascimento.getYear();
		
		senha = senha + String.valueOf(ano);
		senha = senha + matricula.substring(0, 4);
		
		return senha;
		
	}
	
	private String gerarMatricula(TipoCargo cargo) throws Exception{
		
		String matricula = "";
		int esteAno = LocalDate.now().getYear();
		String parteCadastros = String.format("%03d", cadastrosRealizados + 1);

				
		switch (cargo) {
		
		case DIRETOR:

			if (cadastrosRealizados + 1 > 1) {
				throw new Exception("Nao eh possivel criar mais de um Diretor Geral.");
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
}