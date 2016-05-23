package funcionario;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import exceptions.AtualizarInfoException;
import exceptions.CadastroException;
import exceptions.ConsultaException;
import exceptions.ExcluirCadastroException;
import exceptions.VerificaExcecao;
import factories.FactoryFuncionario;

/**
 * BancoFuncionarios
 * Classe que representa um banco de funcionarios
 * no qual todos os funcionarios do sistema Soos sao armazenados.
 * Eh capaz de delegar a criacao e gerenciar os funcionarios.
 * 
 */

public class BancoFuncionarios implements Serializable{
	
	private Map<String, Funcionario> funcionarios;
	private int cadastrosRealizados;
	private FactoryFuncionario factoryUsuarios;
	
	public BancoFuncionarios(){
		
		this.funcionarios = new HashMap<String,Funcionario>();
		this.cadastrosRealizados = 0;
		this.factoryUsuarios = new FactoryFuncionario();
		
	}
	
	
	public String cadastraFuncionario(String nome, String cargo,
			String dataNascimento) throws CadastroException {

		String matricula;
		String senha;
		LocalDate birthDate;
		TipoCargo targetCargo;

		try {
			VerificaExcecao.checkEmptyString(nome, "Nome do funcionario");
		} catch (Exception e) {
			throw new CadastroException("Erro no cadastro de funcionario.",
					e.getMessage());
		}
		try {
			VerificaExcecao.checkEmptyString(cargo, "Nome do cargo");
			targetCargo = this.stringToCargo(cargo);
		} catch (Exception e) {
			throw new CadastroException("Erro no cadastro de funcionario.",
					e.getMessage());
		}
		try {
			birthDate = stringToDate(dataNascimento);
			VerificaExcecao.checarData(birthDate);
		} catch (Exception e) {

			throw new CadastroException("Erro no cadastro de funcionario.",
					"Data invalida.");
		}

		try {
			matricula = this.gerarMatricula(birthDate, targetCargo);
		} catch (Exception e) {
			throw new CadastroException("Erro no cadastro de funcionario.",
					e.getMessage());
		}
		senha = this.gerarSenha(birthDate, matricula);

		Funcionario targetUser = this.factoryUsuarios.criarFuncionario(nome, birthDate,
				senha, matricula, targetCargo);

		this.funcionarios.put(matricula, targetUser);

		this.cadastrosRealizados = this.cadastrosRealizados + 1;

		return matricula;

	}
	
	public String getInfoFuncionario(String matricula, String info)
			throws ConsultaException {

		if ((Pattern.matches("[a-zA-Z]+", matricula)) || matricula.length() < 7) {
			throw new ConsultaException("funcionario",
					"A matricula nao segue o padrao.");
		}

		Funcionario targetUser = this.getFuncionario(matricula);

		if (targetUser == null) {
			throw new ConsultaException("funcionario",
					"Funcionario nao cadastrado.");
		}

		if (info.equalsIgnoreCase("Nome")) {
			String nome = targetUser.getNome();
			return nome;

		} else if (info.equalsIgnoreCase("Cargo")) {

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

		} else if (info.equalsIgnoreCase("Data")) {
			String data = targetUser.getDataNascimento().toString();
			return data;

		} else if (info.equalsIgnoreCase("Senha")) {
			throw new ConsultaException("funcionario",
					"A senha do funcionario eh protegida.");

		} else {
			throw new ConsultaException("funcionario", "Atributo desconhecido.");
		}

	}
	
	public void excluiFuncionario(String matricula, String senha)
			throws ExcluirCadastroException {
		
		String senhaDiretor = "";
		
		if ((Pattern.matches("[a-zA-Z]+", matricula)) || matricula.length() < 7) {
			throw new ExcluirCadastroException("Erro ao excluir funcionario."
					+ " A matricula nao segue o padrao.");
		}

		Funcionario targetUser = this.getFuncionario(matricula);

		if (targetUser == null) {
			throw new ExcluirCadastroException("Erro ao excluir funcionario. "
					+ "Funcionario nao cadastrado.");
		}

		for (Funcionario funcionario : this.funcionarios.values()) {
			if (funcionario.getMatricula().startsWith("1")) {
				senhaDiretor = funcionario.getSenha();
			}

		}

		if (!senha.equals(senhaDiretor)) {
			throw new ExcluirCadastroException("Erro ao excluir funcionario. "
					+ "Senha invalida.");
		}

		this.funcionarios.remove(matricula);

	}
	
	public void atualizaInfoFuncionario(String matricula, String atributo,
			String novoValor) throws AtualizarInfoException {

		if ((Pattern.matches("[a-zA-Z]+", matricula)) || matricula.length() < 7) {
			throw new AtualizarInfoException("funcionario",
					"A matricula nao segue o padrao.");
		}

		Funcionario targetUser = this.getFuncionario(matricula);

		switch (atributo.toUpperCase()) {

		case "NOME":

			try {
				VerificaExcecao.checkEmptyString(novoValor, "Nome");
			} catch (Exception e) {
				throw new AtualizarInfoException("funcionario",
						"Nome do funcionario nao pode ser vazio.");
			}

			if ((Pattern.matches("[a-zA-Z0-9]+", novoValor))
					|| novoValor.length() > 50) {
				throw new AtualizarInfoException("funcionario",
						"Nome invalido.");
			}

			targetUser.setNome(novoValor);

			break;

		case "DATA":

			LocalDate novaData;
			try {

				novaData = this.stringToDate(novoValor);

			} catch (Exception e) {
				throw new AtualizarInfoException("funcionario",
						"Data invalida.");
			}

			try {
				VerificaExcecao.checarData(novaData);
			} catch (Exception e) {
				throw new AtualizarInfoException("funcionario", e.getMessage());
			}

			targetUser.setDataNascimento(novaData);

			break;

		default:
			throw new AtualizarInfoException("funcionario",
					"Atributo invalido.");
		}

	}


	public void atualizaSenha(String matricula, String senhaAntiga, String novaSenha)
			throws AtualizarInfoException {

		Funcionario funcionarioBuscado = this.getFuncionario(matricula);
		
		if (!funcionarioBuscado.getSenha().equals(senhaAntiga)) {
			throw new AtualizarInfoException("funcionario", "Senha invalida.");
		}

		try {
			VerificaExcecao.checkEmptyString(novaSenha, "Senha");
		} catch (Exception e) {
			throw new AtualizarInfoException("funcionario", e.getMessage());
		}

		if ((Pattern.matches("[a-zA-Z0-9]+", novaSenha) == false)
				|| novaSenha.length() < 8 || novaSenha.length() > 12) {

			throw new AtualizarInfoException("funcionario",
					"A nova senha deve "
							+ "ter entre 8 - 12 caracteres alfanumericos.");
		}

		funcionarioBuscado.setSenha(senhaAntiga, novaSenha);

	}
	
	private String gerarSenha(LocalDate anoNascimento, String matricula) {

		String senha = "";
		int ano = anoNascimento.getYear();

		senha = senha + String.valueOf(ano);
		senha = senha + matricula.substring(0, 4);

		return senha;

	}

	private String gerarMatricula(LocalDate dataNascimento, TipoCargo cargo)
			throws Exception {

		String matricula = "";
		int esteAno = LocalDate.now().getYear();
		String parteCadastros = String.format("%03d",
				(this.cadastrosRealizados + 1));

		switch (cargo) {

		case DIRETOR:

			for (Funcionario usuario : this.funcionarios.values()) {
				if (usuario.getMatricula().startsWith("1")) {
					throw new Exception(
							"Nao eh possivel criar mais de um Diretor Geral.");
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

	public Funcionario getFuncionario(String matricula) {

		for (Funcionario usuario : this.funcionarios.values()) {

			if (usuario.getMatricula().equals(matricula)) {
				return usuario;
			}

		}
		return null;

	}

	private LocalDate stringToDate(String dateCandidate) {

		DateTimeFormatter formatador = DateTimeFormatter
				.ofPattern("dd/MM/yyyy");

		LocalDate data = LocalDate.parse(dateCandidate, formatador);

		return data;

	}

	private TipoCargo stringToCargo(String cargo) throws Exception {

		switch (cargo.toUpperCase()) {

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
