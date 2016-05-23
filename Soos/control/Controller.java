package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import banco_de_orgaos.BancoDeOrgaos;
import control.filemannager.FileMannager;
import exceptions.AtualizarInfoException;
import exceptions.BancoOrgaoException;
import exceptions.CadastroException;
import exceptions.ConsultaException;
import exceptions.ExcluirCadastroException;
import exceptions.ExportacaoException;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.MedicamentoException;
import exceptions.OpenSystemException;
import exceptions.ProcedimentoException;
import exceptions.ProntuarioException;
import exceptions.SystemCloseException;
import exceptions.VerificaExcecao;
import farmacia.CategoriasDeMedicamentos;
import farmacia.Farmacia;
import farmacia.Medicamento;
import funcionario.BancoFuncionarios;
import funcionario.Funcionario;
import paciente.BancoProntuarios;
import paciente.Prontuario;
import paciente.TipoSanguineo;
import procedimento.CirurgiaBariatrica;
import procedimento.ConsultaClinica;
import procedimento.Procedimento;
import procedimento.RedesignacaoSexual;
import procedimento.TipoProcedimento;
import procedimento.TransplanteDeOrgaos;

/**
 * Classe criada para gerenciar todas as acoes do sistema.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */

public class Controller {

	private boolean sistemaBloqueado;
	private Funcionario usuarioAtual;
	private BancoFuncionarios bancoFuncionarios;
	private BancoProntuarios bancoProntuarios;
	private Farmacia farmacia;
	private BancoDeOrgaos bancoDeOrgaos;
	private Procedimento procedimento;

	private final String NOME = "NOME", PRECO = "PRECO", TIPO = "TIPO",
			QUANTIDADE = "QUANTIDADE", CATEGORIAS = "CATEGORIAS";

	public Controller() {
		this.sistemaBloqueado = true;
		this.bancoFuncionarios = new BancoFuncionarios();
		this.bancoProntuarios = new BancoProntuarios();
		this.farmacia = new Farmacia();
		this.bancoDeOrgaos = new BancoDeOrgaos();
		this.usuarioAtual = null;
	}

	/**
	 * Metodo responsavel por carregar os arquivos guardados.
	 * 
	 * @throws OpenSystemException
	 */
	public void iniciaSistema() throws OpenSystemException {
		// NYI

	}

	/**
	 * Metodo responsavel por salvar e fechar os arquivos do sistema.
	 * 
	 * @throws SystemCloseException
	 */
	public void fechaSistema() throws SystemCloseException {

		if (this.usuarioAtual != null) {
			String errorMsg = "Um funcionario ainda esta logado: "
					+ usuarioAtual.getNome() + ".";
			throw new SystemCloseException(errorMsg);
		}
		
		FileMannager.exportarFuncionarios(this.bancoFuncionarios);
		FileMannager.exportarProntuarios(this.bancoProntuarios);
		FileMannager.exportarFarmacia(this.farmacia);
		FileMannager.exportarBancoOrgaos(this.bancoDeOrgaos);
		// NYI
	}

	/**
	 * Metodo responsavel por fazer o desbloqueio do sistema chamado apenas uma
	 * vez sendo essa a primeira acao. Nesse metodo, logo apos o desbloqueio eh
	 * feito o cadastro do diretor geral.
	 * 
	 * @param chave - Chave de desbloqueio do sistema.
	 * @param nome - Nome do diretor.
	 * @param dataNascimento - Data de nascimento do diretor.
	 * @return - Matricula do funcionario criado.
	 * @throws Exception
	 */
	public String liberaSistema(String chave, String nome, String dataNascimento)
			throws Exception {

		if (sistemaBloqueado == false) {
			throw new OpenSystemException("Sistema liberado anteriormente.");
		}

		if (!(chave.equals("c041ebf8"))) {
			throw new OpenSystemException("Chave invalida.");
		}

		String matriculaDiretor = this.bancoFuncionarios.cadastraFuncionario(nome,
				"Diretor Geral", dataNascimento);

		this.sistemaBloqueado = false;

		return matriculaDiretor;

	}

	/**
	 * Metodo responsavel por fazer o login de um funcionario no sistema.
	 * 
	 * @param matricula - Matricula de um funcionario.
	 * @param senha - Senha do funcionario da respectiva matricula.
	 * @throws LoginException
	 */
	public void login(String matricula, String senha) throws LoginException {

		Funcionario loginTarget = this.bancoFuncionarios.getFuncionario(matricula);

		if (loginTarget == null) {
			throw new LoginException("Funcionario nao cadastrado.");
		}

		if (!(loginTarget.getSenha().equals(senha))) {
			throw new LoginException("Senha incorreta.");
		}

		if (usuarioAtual != null) {
			throw new LoginException("Um funcionario ainda esta logado: "
					+ usuarioAtual.getNome() + ".");
		}

		this.usuarioAtual = loginTarget;
	}

	/**
	 * Metodo responsavel por fazer o logout de um funcionario no sistema.
	 * @throws LogoutException
	 */
	public void logout() throws LogoutException {

		if (this.usuarioAtual == null) {
			throw new LogoutException("Nao ha um funcionario logado.");
		}

		this.usuarioAtual = null;

	}
	
	/**
	 * Metodo responsavel por cadastrar um novo medicamento no sistema.
	 * @param nome - Nome do medicamento.
	 * @param tipo - Tipo do medicamento(Generico/Referencia).
	 * @param preco - Preco do medicamento.
	 * @param quantidade - Medicamento em estoque.
	 * @param categorias - Categoria do medicamento.
	 * @return - Nome do medicamento criado.
	 * @throws CadastroException
	 */
	public String cadastraMedicamento(String nome, String tipo, double preco,
			int quantidade, String categorias) throws CadastroException {

		String[] categoriasSplit;
		List<CategoriasDeMedicamentos> categoriasMed = new ArrayList<CategoriasDeMedicamentos>();

		if (!usuarioAtual.getMatricula().startsWith("3")) {

			String errorMsg = "O funcionario " + usuarioAtual.getNome()
					+ " nao tem permissao para cadastrar medicamentos.";

			throw new CadastroException("Erro no cadastro de medicamento.",
					errorMsg);
		}

		if (categorias.contains(",")) {

			categoriasSplit = categorias.split(",");

			for (String categoria : categoriasSplit) {
				try {
					categoriasMed.add(CategoriasDeMedicamentos
							.valueOf(categoria.toUpperCase()));

				} catch (Exception e) {
					throw new CadastroException(
							"Erro no cadastro do medicamento.",
							"Categoria invalida.");
				}

			}
		} else {
			try {
				CategoriasDeMedicamentos tipoMed = CategoriasDeMedicamentos
						.valueOf(categorias.toUpperCase());
				categoriasMed.add(tipoMed);
			} catch (Exception e) {
				throw new CadastroException("Erro no cadastro do medicamento.",
						"Categoria invalida.");
			}
		}
		try {
			this.farmacia.cadastraMedicamento(nome, tipo, preco, quantidade,
					categoriasMed);

		} catch (Exception e) {
			throw new CadastroException("Erro no cadastro de medicamento.",
					e.getMessage());
		}

		return nome;

	}

	/**
	 * Metodo responsavel por cadastrar um novo funcionario no sistema.
	 * @param nome - Nome do funcionario.
	 * @param cargo - Cargo do funcionario.
	 * @param dataNascimento - Data de nascimento do funcionario.
	 * @return - Matricula do funcionario criado.
	 * @throws CadastroException
	 */
	public String cadastraFuncionario(String nome, String cargo,
			String dataNascimento) throws CadastroException {

		if (sistemaBloqueado == false) {
			if (usuarioAtual == null) {
				String errorMsg = "Nenhum funcionario estah logado.";

				throw new CadastroException("Erro no cadastro de funcionario.",
						errorMsg);
			} else if (!usuarioAtual.getMatricula().startsWith("1")) {
				String errorMsg = "O funcionario " + usuarioAtual.getNome()
						+ " nao tem permissao para cadastrar funcionarios.";
				throw new CadastroException("Erro no cadastro de funcionario.",
						errorMsg);
			}
		}

		String matriculaNovoFuncionario  = this.bancoFuncionarios.cadastraFuncionario(nome,
				cargo, dataNascimento);

		return matriculaNovoFuncionario;

	}

	/**
	 * Metodo responsavel por criar um novo paciente.
	 * @param nome - Nome do paciente.
	 * @param Data - Data de nascimento do paciente.
	 * @param Peso - Peso do paciente.
	 * @param sexoBio - Sexo biologico do paciente.
	 * @param genero - Genero do paciente.
	 * @param tipoSanguineo - Tipo sanguineo do funcionario.
	 * @return - O ID do paciente.
	 * @throws CadastroException
	 */
	
public String cadastraPaciente(String nome, String data, double peso,
		String sexoBio, String genero, String tipoSanguineo)throws CadastroException {

		if (!this.usuarioAtual.getMatricula().startsWith("3")) {
			throw new CadastroException(
					"Nao foi possivel cadastrar o paciente.", "O funcionario "
							+ usuarioAtual.getNome()
							+ " nao tem permissao para cadastrar pacientes.");
		}

		for (Prontuario prontuario : this.bancoProntuarios) {

			try {
				if (prontuario.getInfoPaciente("Nome").equals(nome)) {
					throw new Exception("Paciente ja cadastrado.");
				}
			} catch (Exception e) {
				throw new CadastroException(
						"Nao foi possivel cadastrar o paciente.",
						e.getMessage());
			}

		}

		try {
			VerificaExcecao.checkEmptyString(nome, "Nome do paciente");
		} catch (Exception e) {

			throw new CadastroException(
					"Nao foi possivel cadastrar o paciente.", e.getMessage());
		}

		LocalDate birthDate;
		try {
			birthDate = this.stringToDate(data);
			VerificaExcecao.checarData(birthDate);
		} catch (Exception e) {

			throw new CadastroException(
					"Nao foi possivel cadastrar o paciente.", "Data invalida.");
		}

		try {
			
		
			VerificaExcecao.checarValor(peso,"Peso do paciente");
			
		} catch (Exception e) {
			throw new CadastroException(
					"Nao foi possivel cadastrar o paciente.", e.getMessage());

		}

		try {
			VerificaExcecao.checarSexoBiologico(sexoBio);

		} catch (Exception e) {
			throw new CadastroException(
					"Nao foi possivel cadastrar o paciente.", e.getMessage());
		}

		try {
			VerificaExcecao.checkEmptyString(genero, "Genero");
		} catch (Exception e) {
			throw new CadastroException(
					"Nao foi possivel cadastrar o paciente.", e.getMessage());
		}

		UUID novoID = UUID.randomUUID();
		TipoSanguineo tipoSangue;
		Prontuario novoProntuario;

		try {
			tipoSangue = this.stringToSanguineo(tipoSanguineo);
		} catch (Exception e) {
			throw new CadastroException(
					"Nao foi possivel cadastrar o paciente.", e.getMessage());
		}

		try {
			novoProntuario = new Prontuario(nome, birthDate, peso, sexoBio, genero, tipoSangue, novoID);
		} catch (Exception e) {
			throw new CadastroException(
					"Nao foi possivel cadastrar o paciente.", e.getMessage());
		}

		this.bancoProntuarios.add(novoProntuario);

		Collections.sort(this.bancoProntuarios);

		return novoID.toString();

	}

	/**
	 * Metodo responsavel por cadastrar um novo orgao.
	 * @param nome - Nome do orgao.
	 * @param tipoSanguineo - Tipo sanguineo do doador.
	 * @throws BancoOrgaoException
	 */
	public void cadastraOrgao(String nome, String tipoSanguineo)
			throws BancoOrgaoException {

		TipoSanguineo sanguineo;

		try {
			sanguineo = this.stringToSanguineo(tipoSanguineo);
		} catch (Exception e) {
			throw new BancoOrgaoException(e.getMessage());
		}

		this.bancoDeOrgaos.addOrgao(nome, sanguineo);

	}

	/**
	 * Metodo responsavel por pegar uma informacao especifica do funcionario.
	 * @param matricula - Matricula do funcionario da informacao.
	 * @param info - Atributo do funcionario que se deseja.
	 * @return - Atributo do funcionario que se deseja.
	 * @throws ConsultaException
	 */
	public String getInfoFuncionario(String matricula, String info)
			throws ConsultaException {

		return this.bancoFuncionarios.getInfoFuncionario(matricula, info);

	}

	/**
	 * Obtem informacoes do paciente conforme o atributo especificado.
	 * 
	 * @param pacienteID - ID do paciente que se deseja a informacao.
	 * @param atributo - Atributo do paciente que se deseja.
	 * @return - Atributo do paciente que se deseja.
	 * @throws ConsultaException
	 */
	public String getInfoPaciente(String pacienteID, String atributo)
			throws ConsultaException {

		Prontuario targetProntuario = null;

		for (Prontuario prontuario : this.bancoProntuarios) {

			if (prontuario.getID().equals(pacienteID)) {
				targetProntuario = prontuario;
			}

		}

		if (targetProntuario == null) {
			throw new ConsultaException("paciente", "Paciente nao cadastrado.");
		}

		try {
			return targetProntuario.getInfoPaciente(atributo);
		} catch (Exception e) {
			throw new ConsultaException("paciente", e.getMessage());
		}

	}

	/**
	 * Metodo responsavel por pegar uma informacao especifica do medicamento.
	 * @param atributo - Atributo do medicamento que se deseja.
	 * @param medicamento - Medicamento que se deseja obter a informacao.
	 * @return - Atributo do funcionario que se deseja.
	 * @throws ConsultaException
	 * @throws MedicamentoException
	 */
	public String getInfoMedicamento(String atributo, String medicamento)
			throws ConsultaException, MedicamentoException {

		if (this.farmacia.buscaMedicamento(medicamento) == null) {
			throw new ConsultaException("medicamento",
					"Medicamento nao existe.");

		}

		switch (atributo.toUpperCase()) {

		case NOME:
			farmacia.existeMedicamento(medicamento);
			return medicamento;

		case PRECO:

			String preco;
			try {
				preco = String.valueOf(this.farmacia.getPreco(medicamento));
			} catch (Exception e) {
				throw new ConsultaException("medicamento", e.getMessage());
			}
			return preco;

		case QUANTIDADE:

			String quantidade;
			try {
				quantidade = String.valueOf(this.farmacia
						.getQuantidade(medicamento));
			} catch (Exception e) {
				throw new ConsultaException("medicamento", e.getMessage());
			}
			return quantidade;

		case CATEGORIAS:

			String categorias = this.farmacia
					.getCategoriasMedicamento(medicamento);
			return categorias;

		case TIPO:

			String tipo;
			try {
				tipo = this.farmacia.getTipoMedicamento(medicamento);
			} catch (Exception e) {
				throw new ConsultaException("medicamento", e.getMessage());
			}
			return tipo;

		default:
			throw new ConsultaException("medicamento", "Atributo invalido.");
		}

	}

	/**
	 * Acessa um prontuario a partir de sua posicao na colecao.
	 * 
	 * @param posicao - Posicao do prontuario.
	 * @return - O ID.
	 * @throws ProntuarioException
	 */
	public String getProntuario(int posicao) throws ProntuarioException {

		if (posicao < 0) {
			throw new ProntuarioException(
					"Erro ao consultar prontuario. Indice do prontuario nao pode"
							+ " ser negativo.");
		}
		if (posicao > this.bancoProntuarios.size()) {

			throw new ProntuarioException("Erro ao consultar prontuario. "
					+ "Nao ha prontuarios suficientes (max = "
					+ this.bancoProntuarios.size() + ").");
		}

		return this.bancoProntuarios.get(posicao).getID();

	}

	/**
	 * Metodo responsavel por buscar os orgaos de determinado tipo sanguineo.
	 * @param tipoSanguineo - Tipo sanguineo dos orgaos.
	 * @return - Nome dos orgao com o tipo sanguineo.
	 * @throws BancoOrgaoException
	 */
	public String buscaOrgPorSangue(String tipoSanguineo)
			throws BancoOrgaoException {

		TipoSanguineo sanguineo;

		String nomesOrgaos = "";

		try {
			sanguineo = this.stringToSanguineo(tipoSanguineo);

		} catch (Exception e) {

			throw new BancoOrgaoException(e.getMessage());
		}

		List<String> orgaosEncontrados = this.bancoDeOrgaos
				.getOrgaoPorSangue(sanguineo);

		if (orgaosEncontrados.isEmpty()) {
			throw new BancoOrgaoException(
					"Nao ha orgaos cadastrados para esse tipo sanguineo.");
		}

		for (String nomeOrgao : orgaosEncontrados) {

			nomesOrgaos = nomesOrgaos + nomeOrgao + ",";

		}

		nomesOrgaos = nomesOrgaos.substring(0, nomesOrgaos.length() - 1);

		return nomesOrgaos;

	}

	/**
	 * Metodo responsavel por buscar os orgaos com determinado nome.
	 * @param nome - Nome do orgao.
	 * @return - Orgao e tipo sanguineo do doador.
	 * @throws BancoOrgaoException
	 */
	public String buscaOrgPorNome(String nome) throws BancoOrgaoException {

		String sangueOrgaos = "";

		if (!this.bancoDeOrgaos.existeOrgao(nome)) {
			throw new BancoOrgaoException("Orgao nao cadastrado.");
		}

		List<String> tiposDisp = this.bancoDeOrgaos.getOrgaoPorNome(nome);

		for (String tipoSangue : tiposDisp) {

			sangueOrgaos = sangueOrgaos + tipoSangue + ",";

		}

		sangueOrgaos = sangueOrgaos.substring(0, sangueOrgaos.length() - 1);

		return sangueOrgaos;

	}

	/**
	 * Metodo responsavel por buscar orgaos com determinado nome e tipo sanguineo.
	 * @param nome - Nome do orgao.
	 * @param tipoSanguineo - Tipo sanguineo do doador.
	 * @return - Se o orgao existe ou nao.
	 * @throws BancoOrgaoException
	 */
	public boolean buscaOrgao(String nome, String tipoSanguineo)
			throws BancoOrgaoException {

		TipoSanguineo sangue;
		try {
			sangue = stringToSanguineo(tipoSanguineo);
		} catch (Exception e) {
			throw new BancoOrgaoException("Tipo sanguineo invalido.");
		}

		boolean orgaoExiste;

		orgaoExiste = this.bancoDeOrgaos.existeOrgao(nome, sangue);

		return orgaoExiste;
	}

	/**
	 * Metodo responsavel por retirar um orgao do banco de orgaos.
	 * @param nome - Nome do orgao.
	 * @param tipoSanguineo - Tipo sanguineo do doador.
	 * @throws ExcluirCadastroException
	 */
	public void retiraOrgao(String nome, String tipoSanguineo)
			throws ExcluirCadastroException {

		TipoSanguineo sangue;

		try {
			sangue = stringToSanguineo(tipoSanguineo);
		} catch (Exception e) {
			throw new ExcluirCadastroException("Erro na retirada de orgaos. "
					+ e.getMessage());
		}

		try {
			this.bancoDeOrgaos.removeOrgao(nome, sangue);
		} catch (Exception e) {
			throw new ExcluirCadastroException("Erro na retirada de orgaos. "
					+ e.getMessage());
		}

	}

	/**
	 * Acessa um prontuario a partir do ID do paciente.
	 * 
	 * @param ID - ID do paciente.
	 * @return - ID.
	 * @throws Exception
	 */
	public Prontuario getProntuario(String ID) throws Exception {

		Prontuario prontuarioProcurado = null;
		for (Prontuario prontuario : this.bancoProntuarios) {
			if (prontuario.getID().equals(ID)) {
				prontuarioProcurado = prontuario;
				return prontuarioProcurado;
			}
		}

		throw new Exception("Prontuario nao cadastrado.");
	}

	/**
	 * 
	 * @param matricula
	 * @param senha
	 * @throws ExcluirCadastroException
	 */
	public void excluiFuncionario(String matricula, String senha)
			throws ExcluirCadastroException {

		if (!usuarioAtual.getMatricula().startsWith("1")) {

			String erroMsg = " O funcionario " + usuarioAtual.getNome()
					+ " nao tem permissao para excluir funcionarios.";

			throw new ExcluirCadastroException("Erro ao excluir funcionario."
					+ erroMsg);
		}
		
		this.bancoFuncionarios.excluiFuncionario(matricula, senha);

	}

	public void atualizaInfoFuncionario(String matricula, String atributo,
			String novoValor) throws AtualizarInfoException {

		if (!usuarioAtual.getMatricula().startsWith("1")) {
			throw new AtualizarInfoException("funcionario",
					"O usuario atual nao tem permissao para"
							+ "alterar informacoes.");
		}

		this.bancoFuncionarios.atualizaInfoFuncionario(matricula, atributo, novoValor);

	}

	public void atualizaInfoFuncionario(String atributo, String novoValor)
			throws AtualizarInfoException {

		String matricula = this.usuarioAtual.getMatricula();
		
		this.bancoFuncionarios.atualizaInfoFuncionario(matricula, atributo, novoValor);

	}

	public void atualizaSenha(String senhaAntiga, String novaSenha)
			throws AtualizarInfoException {

		String matricula = this.usuarioAtual.getMatricula();

		this.bancoFuncionarios.atualizaSenha(matricula, senhaAntiga, novaSenha);

	}

	public void atualizaMedicamento(String nome, String atributo,
			String novoValor) throws AtualizarInfoException {

		try {
			this.farmacia.atualizaMedicamento(nome, atributo, novoValor);
		} catch (Exception e) {
			throw new AtualizarInfoException("medicamento", e.getMessage());
		}

	}

	public String consultaMedCategoria(String categoria)
			throws ConsultaException {

		try {
			return farmacia.consultaMedCategoria(categoria);
		} catch (Exception e) {
			throw new ConsultaException("medicamentos", e.getMessage());
		}

	}

	public Medicamento consultaMedNome(String nome) throws ConsultaException {

		try {
			return farmacia.consultaMedNome(nome);
		} catch (Exception e) {
			throw new ConsultaException("medicamentos", e.getMessage());
		}
	}

	public String getEstoqueFarmacia(String ordenacao)
			throws MedicamentoException, ConsultaException {
		try {
			return farmacia.getEstoqueFarmacia(ordenacao);
		} catch (Exception e) {
			throw new ConsultaException("medicamentos", e.getMessage());
		}

	}

	public int qtdOrgaos(String nome) throws BancoOrgaoException {

		return this.bancoDeOrgaos.qntOrgao(nome);

	}

	public int totalOrgaosDisponiveis() {

		return this.bancoDeOrgaos.qntTotalOrgaos();
	}

	private LocalDate stringToDate(String dateCandidate) {

		DateTimeFormatter formatador = DateTimeFormatter
				.ofPattern("dd/MM/yyyy");

		LocalDate data = LocalDate.parse(dateCandidate, formatador);

		return data;

	}



	private TipoSanguineo stringToSanguineo(String tipoSanguineo)
			throws Exception {

		for (TipoSanguineo sangue : TipoSanguineo.values()) {
			if (sangue.toString().equalsIgnoreCase(tipoSanguineo)) {
				return sangue;
			}

		}
		throw new Exception("Tipo sanguineo invalido.");

	}

	private TipoProcedimento stringToProcedure(String procedimento)
			throws Exception {

		for (TipoProcedimento procedure : TipoProcedimento.values()) {
			if (procedure.toString().equalsIgnoreCase(procedimento)) {
				return procedure;
			}

		}
		throw new Exception("Procedimento invalido.");

	}

	/**
	 * Acessa o id de um paciente pelo seu nome
	 * 
	 * @param nomePaciente
	 * @return
	 * @throws Exception
	 */
	public String getPacienteID(String nomePaciente) throws ProntuarioException {
		try {
			VerificaExcecao.checkEmptyParameter(nomePaciente,
					"Nome do paciente");
		} catch (Exception e) {
			throw new ProntuarioException(e.getMessage());
		}

		try {
			String idProcurado = null;
			for (Prontuario prontuario : this.bancoProntuarios) {
				if (prontuario.getInfoPaciente("Nome").equals(nomePaciente)) {
					idProcurado = prontuario.getID();
					return idProcurado;
				}
			}
			throw new ProntuarioException("Paciente nao encontrado.");
		} catch (Exception e) {
			throw new ProntuarioException(e.getMessage());
		}
	}

	/**
	 * Realiza procedimento sem orgao
	 * 
	 * @param nomeProcedimento
	 * @param nomePaciente
	 * @param medicamentos
	 * @throws Exception
	 */

	public void realizaProcedimento(String nomeProcedimento, String nomePaciente,
									String medicamentos) throws ProcedimentoException {
		
		if (!usuarioAtual.getMatricula().startsWith("2")) {
			
			String errorMsg = "O funcionario " + usuarioAtual.getNome()
					+ " nao tem permissao para realizar procedimentos.";
			
			throw new ProcedimentoException(errorMsg);
		}
		
		String[] medicamentosUsados = medicamentos.split(",");

		try {

			VerificaExcecao.checkEmptyString(nomeProcedimento,
					"Nome do procedimento");
			VerificaExcecao.checkEmptyString(nomePaciente, "ID do paciente");
			VerificaExcecao.checkEmptyString(medicamentos,
					"Nome do medicamento");

			for (String nome : medicamentosUsados) {
				VerificaExcecao.checkEmptyString(nome, "Nome do medicamento");
				if (this.farmacia.buscaMedicamento(nome) == null) {
					throw new Exception("Medicamento nao cadastrado.");
				}

			}
		} catch (Exception e) {
			throw new ProcedimentoException(e.getMessage());
		}

		try {

			VerificaExcecao.checarProcedimento(nomeProcedimento);
		} catch (Exception e) {
			throw new ProcedimentoException(e.getMessage());
		}

		Prontuario prontuario;

		try {
			prontuario = this.getProntuario(nomePaciente);
		} catch (Exception e) {
			throw new ProcedimentoException(e.getMessage());
		}

		try {

			double custoMedicamentos = 0;
			for (String medicamento : medicamentosUsados) {
				custoMedicamentos += this.farmacia.getPreco(medicamento);
			}
			prontuario.somaGastos(custoMedicamentos);

		} catch (Exception e) {
			throw new ProcedimentoException(e.getMessage());
		}

		TipoProcedimento procedure;

		try {

			procedure = this.stringToProcedure(nomeProcedimento);
		} catch (Exception e) {

			throw new ProcedimentoException(e.getMessage());
		}
		
		String nomeMedico = this.usuarioAtual.getNome();
		LocalDate dataRealizacao = LocalDate.now();
		
		try {	

			switch (procedure) {

			case CONSULTACLINICA:
				
				this.procedimento = new ConsultaClinica(nomeMedico, dataRealizacao);

				this.procedimento.realizaProcedimento(prontuario);
				prontuario.registraProcedimento(procedimento);
				break;

			case CIRURGIABARIATRICA:
				
				this.
				procedimento = new CirurgiaBariatrica(nomeMedico, dataRealizacao);

				this.procedimento.realizaProcedimento(prontuario);
				prontuario.registraProcedimento(procedimento);
				break;

			case REDESIGNACAOSEXUAL:
				
				procedimento = new RedesignacaoSexual(nomeMedico, dataRealizacao);
				procedimento.realizaProcedimento(prontuario);
				prontuario.registraProcedimento(procedimento);
				break;

			default:
				throw new Exception("Procedimento invalido.");
			}

		} catch (Exception e) {
			throw new ProcedimentoException(e.getMessage());
		}
	}

	/**
	 * Realiza procedimento com orgao
	 * 
	 * @param nomeProcedimento
	 * @param nomeOrgao
	 * @param nomePaciente
	 * @param medicamentos
	 * @throws Exception
	 */

	public void realizaProcedimento(String nomeProcedimento, String nomePaciente,
			String nomeOrgao, String medicamentos) throws ProcedimentoException {
		
		if (!usuarioAtual.getMatricula().startsWith("2")) {
			
			String errorMsg = "O funcionario " + usuarioAtual.getNome()
					+ " nao tem permissao para realizar procedimentos.";
			
			throw new ProcedimentoException(errorMsg);
		}
		
		String[] medicamentosUsados = medicamentos.split(",");

		try {

			VerificaExcecao.checkEmptyString(nomeProcedimento,
					"Nome do procedimento");
			VerificaExcecao.checkEmptyString(nomeOrgao, "Nome do orgao");

			if (!this.bancoDeOrgaos.existeOrgao(nomeOrgao)) {
				throw new Exception("Banco nao possui o orgao especificado.");
			}

			VerificaExcecao.checkEmptyString(nomePaciente, "ID do paciente");
			VerificaExcecao.checkEmptyString(medicamentos,
					"Nome do medicamento");

			for (String nome : medicamentosUsados) {

				VerificaExcecao.checkEmptyString(nome, "Nome do medicamento");
				if (this.farmacia.buscaMedicamento(nome) == null) {
					throw new Exception("Medicamento nao cadastrado.");
				}

			}

		} catch (Exception e) {
			throw new ProcedimentoException(e.getMessage());
		}

		try {
			VerificaExcecao.checarProcedimento(nomeProcedimento);
		} catch (Exception e) {
			throw new ProcedimentoException(e.getMessage());
		}

		Prontuario prontuario;

		try {
			prontuario = this.getProntuario(nomePaciente);
		} catch (Exception e) {
			throw new ProcedimentoException(e.getMessage());
		}

		TipoSanguineo sangue = prontuario.consultaTipoSanguineo();

		try {
			this.bancoDeOrgaos.checarOrgaoCompativel(nomeOrgao, sangue);

		} catch (Exception e) {
			throw new ProcedimentoException(e.getMessage());
		}

		// registra o custo com medicamentos, somando aos gastos do paciente
		try {

			double custoMedicamentos = 0;
			for (String medicamento : medicamentosUsados) {
				custoMedicamentos += this.farmacia.getPreco(medicamento);
			}
			prontuario.somaGastos(custoMedicamentos);

		} catch (Exception e) {
			throw new ProcedimentoException(e.getMessage());
		}

		TipoProcedimento procedure;

		try {
			procedure = this.stringToProcedure(nomeProcedimento);
		} catch (Exception e) {
			throw new ProcedimentoException(e.getMessage());
		}
		
		String nomeMedico = this.usuarioAtual.getNome();
		LocalDate dataRealizacao = LocalDate.now();
		
		try {
			if (procedure.equals(TipoProcedimento.TRANSPLANTEDEORGAOS)) {

				this.bancoDeOrgaos.removeOrgao(nomeOrgao, sangue);
				procedimento = new TransplanteDeOrgaos(nomeMedico, dataRealizacao, nomeOrgao);
				this.procedimento.realizaProcedimento(prontuario);
				prontuario.registraProcedimento(procedimento);

			} else {
				throw new ProcedimentoException("Procedimento invalido.");
			}

		} catch (Exception e) {
			throw new ProcedimentoException(e.getMessage());
		}
	}
	
	public void realizaProcedimento(String nomeProcedimento, String ID) throws Exception {
		
		if (!usuarioAtual.getMatricula().startsWith("2")) {
			
			String errorMsg = "O funcionario " + usuarioAtual.getNome()
					+ " nao tem permissao para realizar procedimentos.";
			
			throw new ProcedimentoException(errorMsg);
		}
		
		VerificaExcecao.checkEmptyParameter(nomeProcedimento, "Nome do procedimento");
		VerificaExcecao.checkEmptyParameter(ID, "ID");

		TipoProcedimento procedure;

		try {
			procedure = this.stringToProcedure(nomeProcedimento);
		} catch (Exception e) {
			throw new ProcedimentoException(e.getMessage());
		}

		Prontuario prontuario;

		try {
			prontuario = this.getProntuario(ID);
		} catch (Exception e) {
			throw new ProcedimentoException(e.getMessage());
		}
		
		String nomeMedico = this.usuarioAtual.getNome();
		LocalDate dataRealizacao = LocalDate.now();
		
		try {
			if (procedure.equals(TipoProcedimento.CONSULTACLINICA)) {
				
				procedimento = new ConsultaClinica(nomeMedico, dataRealizacao);

				this.procedimento.realizaProcedimento(prontuario);
				prontuario.registraProcedimento(procedimento);

			} else {
				throw new ProcedimentoException("Procedimento invalido.");
			}

		} catch (Exception e) {
			throw new ProcedimentoException(e.getMessage());
		}
	}

	public int getTotalProcedimento(String ID) throws Exception {

		VerificaExcecao.checkEmptyParameter(ID, "ID");

		Prontuario prontuario = this.getProntuario(ID);

		return prontuario.getTotalProcedimento();

	}

	public int getPontosFidelidade(String ID) throws Exception {

		VerificaExcecao.checkEmptyParameter(ID, "ID");

		Prontuario prontuario = this.getProntuario(ID);

		return prontuario.getPontos();
	}
	
	public String getGastosPaciente(String ID) throws Exception {
		
		VerificaExcecao.checkEmptyParameter(ID, "ID");

		Prontuario prontuario = this.getProntuario(ID);
		
		String gastos = String.format(Locale.US,"%.2f", prontuario.getGastosPaciente());
		
		return gastos;
	}
	
	public String getFichaPaciente(String ID) throws Exception {
		
		VerificaExcecao.checkEmptyParameter(ID, "ID");
		
		Prontuario prontuario = this.getProntuario(ID);
		
		return prontuario.getFichaPaciente();
		
	}
	
	public void exportaFichaPaciente(String idPaciente) throws ExportacaoException{
		
		Prontuario prontuarioDoPaciente;
		
		try {
			prontuarioDoPaciente = this.getProntuario(idPaciente);
		} catch (Exception e) {
			throw new 	ExportacaoException("Erro ao exportar ficha do paciente. "+ e.getMessage());
		}
		
		String nomePaciente = prontuarioDoPaciente.getNome();
		String fichaDoPaciente = prontuarioDoPaciente.getFichaPaciente();
		String dataHoje = LocalDate.now().toString();
		
		try {
			FileMannager.exportarFichaPaciente(nomePaciente, fichaDoPaciente, dataHoje);
		} catch (Exception e) {
			throw new ExportacaoException("Erro ao exportar ficha do paciente. " +e.getMessage());
		}
		
	}

}
