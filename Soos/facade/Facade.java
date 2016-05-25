package facade;

import java.io.IOException;

import control.Controller;
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
import farmacia.Medicamento;

/****
 * Classe criada para delegar a Controller os dados
 * necessarios para operar o sistema
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class Facade {
	
	private Controller controle;
	
	public Facade(){
		
		this.controle = new Controller();
		
	}
	
	/**
	 * Metodo responsavel por carregar os arquivos guardados.
	 * 
	 * @throws OpenSystemException
	 */
	public void iniciaSistema() throws OpenSystemException{
		this.controle.iniciaSistema();
	
		
	}
	
	/**
	 * Metodo responsavel por salvar e fechar os arquivos do sistema.
	 * 
	 * @throws SystemCloseException
	 */
	public void fechaSistema() throws SystemCloseException, IOException{
		this.controle.fechaSistema();
		
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
	public String liberaSistema(String chave, String nome, 
			String dataNascimento) throws Exception{
		
		return this.controle.liberaSistema(chave, nome, dataNascimento);
		
	}
	
	/**
	 * Metodo responsavel por fazer o login de um funcionario no sistema.
	 * 
	 * @param matricula - Matricula de um funcionario.
	 * @param senha - Senha do funcionario da respectiva matricula.
	 * @throws LoginException
	 */
	public void login(String matricula, String senha) throws LoginException{
		
		this.controle.login(matricula, senha);
	}
	
	/**
	 * Metodo responsavel por fazer o logout de um funcionario no sistema.
	 * @throws LogoutException
	 */
	public void logout()throws LogoutException{
		this.controle.logout();
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
			String dataNascimento) throws CadastroException{
		
		return this.controle.cadastraFuncionario(nome, cargo, dataNascimento);
		
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
	public String cadastraPaciente(String nome, String data, double peso, String sexoBio,
			String genero, String tipoSanguineo) throws CadastroException{
		
		return this.controle.cadastraPaciente(nome, data, peso, sexoBio, genero, tipoSanguineo);
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
			int quantidade, String categorias) throws CadastroException{
		
		return this.controle.cadastraMedicamento(nome, tipo, preco, quantidade, categorias);
	}
	
	/**
	 * Metodo responsavel por cadastrar um novo orgao.
	 * @param nome - Nome do orgao.
	 * @param tipoSanguineo - Tipo sanguineo do doador.
	 * @throws BancoOrgaoException
	 */
	public void cadastraOrgao (String nome, String tipoSanguineo) throws BancoOrgaoException{
		
		this.controle.cadastraOrgao(nome, tipoSanguineo);
	}
	
	/**
	 * Acessa um prontuario a partir de sua posicao na colecao.
	 * 
	 * @param posicao - Posicao do prontuario.
	 * @return - O ID.
	 * @throws ProntuarioException
	 */
	public String getProntuario(int posicao) throws ProntuarioException{
		return this.controle.getProntuario(posicao);
	}
	
	/**
	 * Acessa um prontuario a partir do ID do paciente.
	 * 
	 * @param ID - ID do paciente.
	 * @return - ID.
	 * @throws Exception
	 */
	public String getPacienteID(String nome) throws ProntuarioException{
		
		return this.controle.getPacienteID(nome);
	}
	
	/**
	 * Obtem informacoes do paciente conforme o atributo especificado.
	 * 
	 * @param pacienteID - ID do paciente que se deseja a informacao.
	 * @param atributo - Atributo do paciente que se deseja.
	 * @return - Atributo do paciente que se deseja.
	 * @throws ConsultaException
	 */
	public String getInfoPaciente(String pacienteID, String atributo) throws ConsultaException{
		return this.controle.getInfoPaciente(pacienteID, atributo);
	}
	
	/**
	 * Metodo responsavel por pegar uma informacao especifica do funcionario.
	 * @param matricula - Matricula do funcionario da informacao.
	 * @param info - Atributo do funcionario que se deseja.
	 * @return - Atributo do funcionario que se deseja.
	 * @throws ConsultaException
	 */
	public String getInfoFuncionario(String matricula, String info) throws ConsultaException{
		
		return this.controle.getInfoFuncionario(matricula, info);
	}
	
	/**
	 * Metodo responsavel por pegar uma informacao especifica do medicamento.
	 * @param atributo - Atributo do medicamento que se deseja.
	 * @param medicamento - Medicamento que se deseja obter a informacao.
	 * @return - Atributo do funcionario que se deseja.
	 * @throws ConsultaException
	 * @throws MedicamentoException
	 */
	public String getInfoMedicamento(String atributo, String medicamento) throws ConsultaException, MedicamentoException{
		return this.controle.getInfoMedicamento(atributo, medicamento);
	}
	
	/**
	 * Metodo responsavel por buscar os orgaos de determinado tipo sanguineo.
	 * @param tipoSanguineo - Tipo sanguineo dos orgaos.
	 * @return - Nome dos orgao com o tipo sanguineo.
	 * @throws BancoOrgaoException
	 */
	public String buscaOrgPorSangue(String tipoSanguineo) throws BancoOrgaoException{
		
		return this.controle.buscaOrgPorSangue(tipoSanguineo);
	}
	
	/**
	 * Metodo responsavel por buscar os orgaos com determinado nome.
	 * @param nome - Nome do orgao.
	 * @return - Orgao e tipo sanguineo do doador.
	 * @throws BancoOrgaoException
	 */
	public String buscaOrgPorNome(String nome) throws BancoOrgaoException{
		
		return this.controle.buscaOrgPorNome(nome);
	}
	
	/**
	 * Metodo responsavel por buscar orgaos com determinado nome e tipo sanguineo.
	 * @param nome - Nome do orgao.
	 * @param tipoSanguineo - Tipo sanguineo do doador.
	 * @return - Se o orgao existe ou nao.
	 * @throws BancoOrgaoException
	 */
	public boolean buscaOrgao(String nome, String tipoSanguineo) throws BancoOrgaoException{
		
		return this.controle.buscaOrgao(nome, tipoSanguineo);
		
	}
	
	/**
	 * Exclui um funcionario do banco de funcionarios
	 * @param matricula
	 * @param senha
	 * @throws ExcluirCadastroException
	 */
	public void excluiFuncionario(String matricula, String senha) throws ExcluirCadastroException{
		this.controle.excluiFuncionario(matricula, senha);
		
	}
	
	/**
	 * Metodo responsavel por retirar um orgao do banco de orgaos.
	 * @param nome - Nome do orgao.
	 * @param tipoSanguineo - Tipo sanguineo do doador.
	 * @throws ExcluirCadastroException
	 */
	public void retiraOrgao(String nome, String tipoSanguineo) throws ExcluirCadastroException{
		
		this.controle.retiraOrgao(nome, tipoSanguineo);
	}
	
	/**
	 * Atualiza uma informacao de um funcionario a partir de seu numero de matricula
	 * @param matricula
	 * @param atributo
	 * @param novoValor
	 * @throws AtualizarInfoException
	 */
	public void atualizaInfoFuncionario(String matricula, 
			String atributo, String novoValor) throws AtualizarInfoException{
		
		this.controle.atualizaInfoFuncionario(matricula, atributo, novoValor);
		
	}
	
	/**
	 * Atualiza uma informacao do usuario logado
	 * @param atributo
	 * @param novoValor
	 * @throws AtualizarInfoException
	 */
	public void atualizaInfoFuncionario(String atributo, String novoValor) throws AtualizarInfoException{
		
		this.controle.atualizaInfoFuncionario(atributo, novoValor);
		
	}
	
	/**
	 * Atualiza a senha de um funcionario
	 * @param senhaAntiga
	 * @param novaSenha
	 * @throws AtualizarInfoException
	 */
	public void atualizaSenha(String senhaAntiga, String novaSenha) throws AtualizarInfoException{
		this.controle.atualizaSenha(senhaAntiga, novaSenha);
	}

	/**
	 * Atualiza uma informacao de um medicamento
	 * @param nome
	 * @param atributo
	 * @param novoValor
	 * @throws AtualizarInfoException
	 */
	public void atualizaMedicamento(String nome, String atributo,
			String novoValor) throws AtualizarInfoException{
		this.controle.atualizaMedicamento(nome, atributo, novoValor);
	}
	
	/**
	 * Retorna todos os medicamentos pertencentes a uma categoria
	 * @param categoria
	 * @return
	 * @throws ConsultaException
	 */
	public String consultaMedCategoria(String categoria) throws ConsultaException{
		return this.controle.consultaMedCategoria(categoria);
	}
	
	/**
	 * Acessa um medicamento a partir de seu nome
	 * @param nome
	 * @return
	 * @throws ConsultaException
	 */
	public Medicamento consultaMedNome(String nome) throws ConsultaException{
		return this.controle.consultaMedNome(nome);
	}
	
	/**
	 * Acessa o estoque da farmacia
	 * @param ordenacao
	 * @return
	 * @throws MedicamentoException
	 * @throws ConsultaException
	 */
	public String getEstoqueFarmacia(String ordenacao) throws ConsultaException, MedicamentoException{
		return this.controle.getEstoqueFarmacia(ordenacao);
	}
	
	/**
	 * Acessa a quantidade de um orgao no banco de orgaos
	 * @param nome
	 * @return
	 * @throws BancoOrgaoException
	 */
	public int qtdOrgaos(String nome) throws BancoOrgaoException{
		
		return this.controle.qtdOrgaos(nome);
	}
	
	/**
	 * Acessa o total de orgaos disponivel no banco de orgaos
	 * @return
	 */
	public int totalOrgaosDisponiveis(){
		
		return this.controle.totalOrgaosDisponiveis();
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
		
		this.controle.realizaProcedimento(nomeProcedimento, nomePaciente, medicamentos);
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
	public void realizaProcedimento(String nomeProcedimento, String nomePaciente, String orgao,
			String medicamentos) throws ProcedimentoException {
		
		this.controle.realizaProcedimento(nomeProcedimento, nomePaciente, orgao, medicamentos);;
	}
	
	/**
	 * Realiza o procedimento apenas por nome do procediemnto e id do paciente
	 * @param nomeProcedimento
	 * @param ID
	 * @throws Exception
	 */
	public void realizaProcedimento(String nomeProcedimento, String ID) throws Exception {
		this.controle.realizaProcedimento(nomeProcedimento, ID);
	}
	
	/**
	 * Acessa o numero de procedimentos feitos por um paciente.
	 * @param ID
	 * @return
	 * @throws Exception
	 */
	public int getTotalProcedimento(String ID) throws Exception{
		
		return this.controle.getTotalProcedimento(ID);
	}
	
	/**
	 * Acessa os pontos do cartao fidelidade de um paciente
	 * @param ID
	 * @return
	 * @throws Exception
	 */
	public int getPontosFidelidade(String ID) throws Exception{
		
		return this.controle.getPontosFidelidade(ID);
	}
	
	/**
	 * Acessa os gastos do paciente com servicos.
	 * @param ID
	 * @return
	 * @throws Exception
	 */
	public String getGastosPaciente(String ID) throws Exception {
		
		return this.controle.getGastosPaciente(ID);
	}
	
	/**
	 * Acessa a ficha de um paciente
	 * @param ID
	 * @return
	 * @throws Exception
	 */
	public String getFichaPaciente(String ID) throws Exception {
		
		return this.controle.getFichaPaciente(ID);
	}
	
	/**
	 * Exporta a ficha de um paciente a partir de seu ID
	 * @param idPaciente
	 * @throws ExportacaoException
	 */
	public void exportaFichaPaciente(String ID) throws ExportacaoException{
		
		this.controle.exportaFichaPaciente(ID);
	}
}
