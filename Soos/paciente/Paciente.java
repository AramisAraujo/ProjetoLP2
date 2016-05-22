package paciente;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import cartao.CartaoFidelidade;
import exceptions.VerificaExcecao;

/**
 * A classe Paciente possui atributos e comportamentos necessarios para a
 * criacao de pacientes e manipulacao de seus dados.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class Paciente implements Comparable<Paciente> {

	private String nome, sexoBiologico, genero;
	private TipoSanguineo tipoSanguineo;
	private LocalDate dataNascimento;
	private double peso;
	private UUID ID;
	private CartaoFidelidade carteirinhaSoos;
	private double gastoTotal;

	private final String NOME = "Nome", DATA = "Data", SEXO = "Sexo",
			GENERO = "Genero", TIPO_SANGUINEO = "TipoSanguineo", PESO = "Peso",
			IDADE = "Idade", GENERO_MASCULINO = "masculino",
			GENERO_FEMININO = "feminino";

	public Paciente(String nome, LocalDate dataNascimento, double peso,
			String sexoBiologico, String genero, TipoSanguineo tipoSanguineo,
			UUID ID) throws Exception {

		validaParametros(nome, dataNascimento, peso, sexoBiologico, genero,
				tipoSanguineo, ID);

		this.ID = ID;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.peso = peso;
		this.sexoBiologico = sexoBiologico;
		this.genero = genero;
		this.tipoSanguineo = tipoSanguineo;
		this.gastoTotal = 0;
		this.carteirinhaSoos = new CartaoFidelidade();
	}

	public UUID getID() {
		UUID iD = this.ID;
		return iD;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento.toString();
	}

	public String getIdade() {
		int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
		return String.valueOf(idade);
	}

	public void setDataNascimento(LocalDate dataNascimento) throws Exception {
		VerificaExcecao.checkEmptyParameter(dataNascimento, "Data");
		this.dataNascimento = dataNascimento;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) throws Exception {
		VerificaExcecao.checarValor(peso, "Peso");
		this.peso = peso;
	}

	public String getSexoBiologico() {
		return sexoBiologico;
	}

	public void setSexoBiologico(String sexoBiologico) throws Exception {
		VerificaExcecao.checkEmptyParameter(sexoBiologico, "Sexo biologico");
		VerificaExcecao.checarSexoBiologico(sexoBiologico);
		this.sexoBiologico = sexoBiologico;
	}

	public String getGenero() {
		return genero;
	}

	/**
	 * Realiza troca de genero
	 */
	public void trocarGenero() {
		if (this.genero.equals(GENERO_FEMININO)) {
			this.genero = GENERO_MASCULINO;
		} else {
			this.genero = GENERO_FEMININO;
		}
	}

	public String getTipoSanguineo() {
		return this.tipoSanguineo.toString();
	}

	public TipoSanguineo consultaTipoSanguineo() {
		return this.tipoSanguineo;
	}

	public double getGastoTotal() {
		return this.gastoTotal;
	}

	public double getGastosPaciente() {
		return this.getGastoTotal();
	}

	/**
	 * Metodo utilizado para somar os gastos do paciente.
	 * 
	 * @param valor
	 *            - valor que sera somado
	 */
	public void somaGastos(double valor) {

		valor = this.carteirinhaSoos.aplicarDesconto(valor);

		this.gastoTotal += valor;
	}

	public void somaPontos(int pontos) {

		this.carteirinhaSoos.addPontos(pontos);

	}

	public int getPontos() {
		return this.carteirinhaSoos.getPontos();
	}

	/**
	 * Metodo utilizado para retornar informacoes relacionadas ao paciente.
	 * 
	 * @param atributo
	 *            - nome do atributo que sera retornado
	 * @return - atributo esperado
	 * @throws Exception
	 *             - excecao lancada caso o atributo seja invalido.
	 */
	public String getInfoPaciente(String atributo) throws Exception {
		if (atributo == null) {
			throw new Exception("Atributo invalido.");
		}
		switch (atributo) {

		case NOME:
			return this.getNome();

		case DATA:
			return this.getDataNascimento();

		case SEXO:
			return this.getSexoBiologico();

		case GENERO:
			return this.getGenero();

		case TIPO_SANGUINEO:
			return this.getTipoSanguineo();

		case PESO:
			String peso = String.valueOf(this.getPeso());
			return peso;

		case IDADE:
			String idade = String.valueOf(this.getIdade());
			return idade;

		default:
			throw new Exception("Atributo invalido.");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	/**
	 * Pacientes sao comparaveis pelos seus nomes.
	 */
	@Override
	public int compareTo(Paciente outroPaciente) {
		return this.nome.compareTo(outroPaciente.getNome());
	}

	/**
	 * Metodo utilizado para validar os dados de um paciente.
	 * 
	 * @param nome
	 *            - nome que sera validado
	 * @param dataNascimento
	 *            - data de nascimento que sera validada
	 * @param peso
	 *            - peso que sera validado
	 * @param sexoBiologico
	 *            - sexo biologico que sera validado
	 * @param genero
	 *            - genero que sera validado
	 * @param tipoSanguineo
	 *            - tipo sanguineo que sera validado
	 * @param ID
	 *            - ID que sera validado
	 * @throws Exception
	 *             - excecao lancada caso ocorra algum erro
	 */
	private void validaParametros(String nome, LocalDate dataNascimento,
			double peso, String sexoBiologico, String genero,
			TipoSanguineo tipoSanguineo, UUID ID) throws Exception {

		VerificaExcecao.checkEmptyParameter(nome, "Nome do paciente");
		VerificaExcecao.checkEmptyParameter(dataNascimento, "Data");
		VerificaExcecao.checkEmptyParameter(sexoBiologico, "Sexo biologico");
		VerificaExcecao.checkEmptyParameter(genero, "Genero");
		VerificaExcecao.checkEmptyParameter(tipoSanguineo, "Tipo sanguineo");
		VerificaExcecao.checkEmptyParameter(ID, "ID");

		VerificaExcecao.checarData(dataNascimento);
		VerificaExcecao.checarValor(peso, "Peso do paciente");
		VerificaExcecao.checarSexoBiologico(sexoBiologico);
		VerificaExcecao.checarGenero(genero);
	}

}
