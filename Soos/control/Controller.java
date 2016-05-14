package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import paciente.Prontuario;
import paciente.TipoSanguineo;
import usuario.BancoDeFuncionarios;
import usuario.Funcionario;
import exceptions.AtualizarInfoException;
import exceptions.CadastroException;
import exceptions.ConsultaException;
import exceptions.ExcluirCadastroException;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.MedicamentoException;
import exceptions.OpenSystemException;
import exceptions.ProntuarioException;
import exceptions.SystemCloseException;
import exceptions.VerificaExcecao;
import farmacia.CategoriasDeMedicamentos;
import farmacia.Farmacia;
import farmacia.Medicamento;

public class Controller {
	
	private boolean sistemaBloqueado;
	private Funcionario usuarioAtual;
	private Map<String,Funcionario> bancoFuncionarios;
	private List<Prontuario> bancoProntuarios;
	private Farmacia farmacia;
	private BancoDeFuncionarios bancoDeFuncionarios;
	
	
	public Controller(){
		this.sistemaBloqueado = true;
		this.bancoFuncionarios = new HashMap<String,Funcionario>();
		this.bancoProntuarios = new ArrayList<Prontuario>();
		this.farmacia = new Farmacia();
		this.usuarioAtual = null;
		this.bancoDeFuncionarios = new BancoDeFuncionarios();
	}
	
	public void iniciaSistema() throws OpenSystemException{
		//NYI
		
	}
	
	public void fechaSistema() throws SystemCloseException{
		
		if(this.usuarioAtual != null){
			String errorMsg = "Um funcionario ainda esta logado: " + usuarioAtual.getNome()+".";
			throw new SystemCloseException(errorMsg);
		}
		//NYI
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
				
		return matriculaDiretor;
				
	}
	
	public void login(String matricula, String senha) throws LoginException{
			
		Funcionario loginTarget = this.getFuncionario(matricula);
		
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
	
	public String cadastraMedicamento(String nome, String tipo, double preco, 
			int quantidade, String categorias) throws CadastroException{
		
		String[] categoriasSplit;
		Set<CategoriasDeMedicamentos> categoriasMed = new HashSet<CategoriasDeMedicamentos>();		

		if(!usuarioAtual.getMatricula().startsWith("3")){
			String errorMsg = "O funcionario "+usuarioAtual.getNome()+
  					" nao tem permissao para cadastrar medicamentos.";
			throw new CadastroException("Erro no cadastro de medicamento.", errorMsg);
		}
				
		try {
			VerificaExcecao.checkEmptyString(nome, "Nome do medicamento");
		} catch (Exception e) {
			throw new CadastroException("Erro no cadastro de medicamento.", e.getMessage());
		}
		
		try {
			VerificaExcecao.checkEmptyString(tipo, "Tipo do medicamento");
		} catch (Exception e) {
			throw new CadastroException("Erro no cadastro de medicamento.", e.getMessage());
		}
		
		try {
			VerificaExcecao.checarValor(preco, "Preco do medicamento");
		} catch (ProntuarioException e) {
			throw new CadastroException("Erro no cadastro de medicamento.", e.getMessage());
		}
		
		
		try {
			VerificaExcecao.checarValor(quantidade, "Quantidade do medicamento");
		} catch (ProntuarioException e) {
			throw new CadastroException("Erro no cadastro de medicamento.", e.getMessage());
		}
		
		if(categorias.contains(",")){
			
			categoriasSplit = categorias.split(",");
			
			for (String categoria : categoriasSplit) {
				try {
					categoriasMed.add(CategoriasDeMedicamentos.valueOf(categoria.toUpperCase()));

				} catch (Exception e) {
					throw new CadastroException("Erro no cadastro do medicamento.", "Categoria invalida."+
				categoriasMed);
				}
			
			}
		}
		
		try{
			CategoriasDeMedicamentos tipoMed = CategoriasDeMedicamentos.valueOf(categorias.toUpperCase());
			categoriasMed.add(tipoMed);
		}catch(Exception e){
			
		}
		

		
		
		try {
			this.farmacia.criaMedicamento(nome, preco, quantidade, categoriasMed, tipo);
		} catch (MedicamentoException e) {
			throw new CadastroException("Erro no cadastro do medicamento.", e.getMessage());
		}
		
		return nome;
			
	}
	
	public String cadastraFuncionario(String nome, String cargo, 
			String dataNascimento) throws CadastroException{
		
		if(sistemaBloqueado == false){ 
			if(usuarioAtual == null){
				String errorMsg = "Nenhum funcionario estah logado.";
				
				throw new CadastroException("Erro no cadastro de funcionario.", errorMsg);
			}
			else if(!usuarioAtual.getMatricula().startsWith("1")){
			String errorMsg = "O funcionario "+usuarioAtual.getNome()+
  					" nao tem permissao para cadastrar funcionarios.";
			throw new CadastroException("Erro no cadastro de funcionario.", errorMsg);
			}
		}
		
		return bancoDeFuncionarios.cadastraFuncionario(nome, cargo, dataNascimento);
	}
	
	public String cadastraPaciente(String nome, String Data, double Peso
			, String sexoBio, String genero, String tipoSanguineo) throws CadastroException{
		
		if(!this.usuarioAtual.getMatricula().startsWith("3")){
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", 
					"O funcionario " + usuarioAtual.getNome()+" nao tem permissao para cadastrar pacientes.");
		}
		
		for (Prontuario prontuario : this.bancoProntuarios) {
			
			try {
				if(prontuario.getInfoPaciente("Nome").equals(nome)){
					throw new Exception("Paciente ja cadastrado.");
				}
			} catch (Exception e) {
				throw new CadastroException("Nao foi possivel cadastrar o paciente.", e.getMessage());
			}
			
		}
		
		try {
			VerificaExcecao.checkEmptyString(nome, "Nome");
		} catch (Exception e) {
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", "Nome do paciente "
					+ "nao pode ser vazio.");
		}
		
		LocalDate birthDate;
		try {
			birthDate = this.stringToDate(Data);
			VerificaExcecao.checarData(birthDate);
		} catch (Exception e) {
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", "Data invalida.");
		}
		
		try {
			VerificaExcecao.checarValor(Peso,"Peso do paciente");
		} catch (ProntuarioException e) {
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", e.getMessage());
		}
		
		try {
			VerificaExcecao.checarSexoBiologico(sexoBio);
		} catch (ProntuarioException e) {
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", "Sexo biologico "
					+ "nao identificado.");
		}
		
		try {
			VerificaExcecao.checkEmptyString(genero, "Genero");
		} catch (Exception e) {
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", e.getMessage());
		}
		
		
		
		UUID novoID = UUID.randomUUID();
		TipoSanguineo tipoSangue;
		Prontuario novoProntuario;
		
		try {
			tipoSangue = this.stringToSanguineo(tipoSanguineo);
		} catch (Exception e) {
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", e.getMessage());
		}
		
		try {
			novoProntuario = new Prontuario(nome, birthDate, Peso, 
					sexoBio, genero, tipoSangue, novoID);
		} catch (Exception e) {
			throw new CadastroException("Nao foi possivel cadastrar o paciente.", e.getMessage());
		}
		
		this.bancoProntuarios.add(novoProntuario);
		
		Collections.sort(this.bancoProntuarios);
		
		return novoID.toString();
		
		
	}

	public String getInfoFuncionario(String matricula, String atributo) throws ConsultaException{
		return bancoDeFuncionarios.getInfoFuncionario(matricula, atributo);
	}
	
	public String getInfoPaciente(String paciente, String atributo) throws ConsultaException{
		
		Prontuario targetProntuario = null;
		
		for (Prontuario prontuario : this.bancoProntuarios) {
			
			if(prontuario.getID().equals(paciente)){
				targetProntuario = prontuario;
			}
			
		}
		
		if(targetProntuario == null){
			throw new ConsultaException("paciente", "Paciente nao cadastrado.");
		}
		
		try {
			return targetProntuario.getInfoPaciente(atributo);
		} catch (Exception e) {
			throw new ConsultaException("paciente", e.getMessage());
		}
		
	}
	
	public String getInfoMedicamento(String atributo, String medicamento) throws ConsultaException{
		
		if(this.farmacia.buscaMedicamento(medicamento) == null){
			throw new ConsultaException("medicamento", "Medicamento nao existe.");
			
		}
		
		switch (atributo.toUpperCase()) {
		
		case "NOME":
			
			String nome;
			try {
				nome = this.farmacia.getNome(medicamento);
			} catch (Exception e) {
				throw new ConsultaException("medicamento", e.getMessage());
			}
			return nome;

		case "PRECO":
			
			String preco;
			try {
				preco = String.valueOf(this.farmacia.getPreco(medicamento));
			} catch (Exception e) {
				throw new ConsultaException("medicamento", e.getMessage());
			}
			return preco;
			
		case "QUANTIDADE":
			
			String quantidade;
			try {
				quantidade = String.valueOf(this.farmacia.getQuantidade(medicamento));
			} catch (Exception e) {
				throw new ConsultaException("medicamento", e.getMessage());
			}
			return quantidade;			
			
		case "CATEGORIAS":
			
			String categorias = this.farmacia.getCategorias(medicamento);
			return categorias;
			
			
		case "TIPO":
			
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
	
	public String getProntuario(int posicao) throws ProntuarioException{
		
		if(posicao < 0){
			throw new ProntuarioException("Erro ao consultar prontuario. Indice do prontuario nao pode"
					+ " ser negativo.");
		}
		if(posicao > this.bancoProntuarios.size()){
		
			throw new ProntuarioException("Erro ao consultar prontuario. "+
					"Nao ha prontuarios suficientes (max = "+this.bancoProntuarios.size()+").");
		}
		
		return this.bancoProntuarios.get(posicao).getID();
		
	}
	
	public void excluiFuncionario(String matricula, String senha)throws ExcluirCadastroException{
		
		String senhaDiretor = "";
		
		if(!usuarioAtual.getMatricula().startsWith("1")){
			
			String erroMsg = "O funcionario "+usuarioAtual.getNome()
			+" nao tem permissao para excluir funcionarios.";
			
			throw new ExcluirCadastroException("funcionario", erroMsg);
		}
		
		for (Funcionario funcionario : this.bancoFuncionarios.values()) {
			if(funcionario.getMatricula().startsWith("1")){
				senhaDiretor = funcionario.getSenha();
			}
			
		}
		
		if(!senha.equals(senhaDiretor)){
			throw new ExcluirCadastroException("funcionario","Senha invalida.");
		}
		
		bancoDeFuncionarios.excluiFuncionario(matricula);
		
	}
	
	public void atualizaInfoFuncionario(String matricula, 
			String atributo, String novoValor) throws AtualizarInfoException{
		
		if(!usuarioAtual.getMatricula().startsWith("1")){
			throw new AtualizarInfoException("funcionario", "O usuario atual nao tem permissao para"
					+ "alterar informacoes.");
		}
		bancoDeFuncionarios.atualizaInfoFuncionario(matricula, atributo, novoValor);
	}
	
	public void atualizaInfoFuncionario(String atributo, String novoValor) throws AtualizarInfoException{
		
		bancoDeFuncionarios.atualizaInfoFuncionario(usuarioAtual, atributo, novoValor);
	}
	
	public void atualizaSenha(String senhaAntiga,String novaSenha) throws AtualizarInfoException{
		
		if(!usuarioAtual.getSenha().equals(senhaAntiga)){
			throw new AtualizarInfoException("funcionario", "Senha invalida.");
		}
		
		try {
			VerificaExcecao.checkEmptyString(novaSenha, "Senha");
		} catch (Exception e) {
			throw new AtualizarInfoException("funcionario", e.getMessage());
		}
		
		if ((Pattern.matches("[a-zA-Z0-9]+", novaSenha) == false) ||
				novaSenha.length() < 8 || novaSenha.length() > 12) {
			
			throw new AtualizarInfoException("funcionario", "A nova senha deve "
					+ "ter entre 8 - 12 caracteres alfanumericos.");
		}
		
		String matricula = this.usuarioAtual.getMatricula();
		
		Funcionario targetUser = this.getFuncionario(matricula);
		
		targetUser.setSenha(senhaAntiga, novaSenha);
		
		this.usuarioAtual = targetUser;
		
	}
	
	public void atualizaMedicamento(String nome, String atributo,
			String novoValor) throws AtualizarInfoException{
		
		try {
			this.farmacia.atualizaMedicamento(nome, atributo, novoValor);
		} catch (Exception e) {
			throw new AtualizarInfoException("medicamento", e.getMessage());
		}

	}
	
	public String consultaMedCategoria(String categoria) throws ConsultaException{
		
		CategoriasDeMedicamentos category;
		ArrayList<Medicamento> medicamentos;
		
		try {
			category = CategoriasDeMedicamentos.valueOf(categoria.toUpperCase());
		} catch (Exception e) {
			throw new ConsultaException("medicamentos", "Categoria invalida.");
		}
		
		medicamentos = this.farmacia.buscaMedicamentos(category);
		
		if (medicamentos.isEmpty()){
			throw new ConsultaException("medicamentos", "Nao ha remedios cadastrados nessa categoria.");
		}
		
		String resultado = "";
		
		for(int i = 0; i < medicamentos.size(); i++ ){
			
    		if(i == medicamentos.size() -1){
    			
    			resultado =  resultado + medicamentos.get(i).getNome();
    		}
    		else{
    			resultado = resultado + medicamentos.get(i).getNome()+",";
    		}
    	}
			
		return resultado;
		
	}

	public String consultaMedNome(String nome) throws ConsultaException{
		
		String medicamentoDesc;
		
		try {
			medicamentoDesc = this.farmacia.getMedicamentoDesc(nome);
		} catch (Exception e) {
			throw new ConsultaException("medicamentos", e.getMessage());
		}
		
		return medicamentoDesc;
	}

	public String getEstoqueFarmacia(String ordenacao) throws ConsultaException{
		
		String medicamentos = "";
		
		List<Medicamento> meds;
		switch (ordenacao.toUpperCase()) {
		
		case "ALFABETICA":
			meds = this.farmacia.getMedicamentosNome();
			
			break;

		case "PRECO":
			meds = this.farmacia.getMedicamentosPreco();
			break;
			
		default:
			
			throw new ConsultaException("medicamentos", "Tipo de ordenacao invalida.");

		}
		
		for(int i = 0; i < meds.size(); i++ ){
			
    		if(i == meds.size() -1){
    			
    			medicamentos =  medicamentos + meds.get(i).getNome();
    		}
    		else{
    			medicamentos = medicamentos + meds.get(i).getNome()+",";
    		}
    	}
		
		return medicamentos;
		
	}
	
	private Funcionario getFuncionario(String matricula){
		
		for (Funcionario usuario : this.bancoFuncionarios.values()) {
			
			if(usuario.getMatricula().equals(matricula)){
				return usuario;
			}
			
		}
		return null;
		
	}

	private LocalDate stringToDate(String dateCandidate){
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate data = LocalDate.parse(dateCandidate, formatador);

		return data;
		

	}
	
	private TipoSanguineo stringToSanguineo(String tipoSanguineo) throws Exception{
		
		for (TipoSanguineo sangue : TipoSanguineo.values()) {
			if(sangue.toString().equalsIgnoreCase(tipoSanguineo)){
				return sangue;
			}
				
		}
		throw new Exception("Tipo sanguineo invalido.");
			
		
	}
	
}