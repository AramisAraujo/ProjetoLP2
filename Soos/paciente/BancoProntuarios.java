package paciente;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import exceptions.CadastroException;
import exceptions.ConsultaException;
import exceptions.ProntuarioException;
import exceptions.VerificaExcecao;

public class BancoProntuarios implements Serializable{

	
	private List<Prontuario> prontuarios;
	
	
	public BancoProntuarios(){
		
		this.prontuarios = new ArrayList<Prontuario> ();
	}
	
	
	public String cadastraPaciente(String nome, String data, double peso,
			String sexoBio, String genero, String tipoSanguineo)throws CadastroException {

			for (Prontuario prontuario : this.prontuarios) {

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

			this.prontuarios.add(novoProntuario);

			Collections.sort(this.prontuarios);

			return novoID.toString();

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

		for (Prontuario prontuario : this.prontuarios) {

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
		if (posicao > this.prontuarios.size()) {

			throw new ProntuarioException("Erro ao consultar prontuario. "
					+ "Nao ha prontuarios suficientes (max = "
					+ this.prontuarios.size() + ").");
		}

		return this.prontuarios.get(posicao).getID();

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
		for (Prontuario prontuario : this.prontuarios) {
			if (prontuario.getID().equals(ID)) {
				prontuarioProcurado = prontuario;
				return prontuarioProcurado;
			}
		}

		throw new Exception("Prontuario nao cadastrado.");
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
			for (Prontuario prontuario : this.prontuarios) {
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

private LocalDate stringToDate(String dateCandidate) {

	DateTimeFormatter formatador = DateTimeFormatter
			.ofPattern("dd/MM/yyyy");

	LocalDate data = LocalDate.parse(dateCandidate, formatador);

	return data;

}
	
}
