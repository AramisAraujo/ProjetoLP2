package paciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import exceptions.ProntuarioException;
import exceptions.VerificaExcecao;
import procedimento.Procedimento;

/**
 * A classe Prontuario é composta de um objeto paciente e um conjunto de procedimentos.
 * 
 * @author Elton Dantas
 */
public class Prontuario implements Comparable<Prontuario> {
	
	private Paciente paciente;
	private List<Procedimento> procedimentos;
	
	// TODO
	// Delegar para a controller construir o objeto paciente e receber como parametro nesse construtor.
	public Prontuario(String nome, LocalDate dataNascimento, double peso, String sexoBiologico,
						String genero, TipoSanguineo tipoSanguineo, UUID ID) throws Exception {
		
		validaParametro(nome, dataNascimento, peso, sexoBiologico, genero,
				tipoSanguineo, ID);
		
		this.paciente = new Paciente(nome, dataNascimento, peso, sexoBiologico, genero, tipoSanguineo, ID);
		this.procedimentos = new ArrayList<Procedimento>();
	}

	public String getID(){
		return this.paciente.getID().toString();
	}
	
	public String getInfoPaciente(String atributo) throws Exception{
		
		return this.paciente.getInfoPaciente(atributo);
	}
	
	public int getQntProcedimentos() {
		return this.procedimentos.size();
	}

	public double getPeso() {
		return this.paciente.getPeso();
	}
	
	public int getPontos(){
		return this.paciente.getPontos();
	}
	
	public double getGastoTotal() {
		return this.paciente.getGastoTotal();
	}
	
	public TipoSanguineo consultaTipoSanguineo() {
		return this.paciente.consultaTipoSanguineo();
	}
	
	public void somaGastos(double valor){
		this.paciente.somaGastos(valor);
	}
	
	public void somaPontos(int pontos){
		this.paciente.somaPontos(pontos);
	}
	
	public void setNome(String nome) {
		this.paciente.setNome(nome);
	}
	
	//TODO
	// Verificar nome do metodo, melhorar legibilidade.
	public void setDataNascimento(LocalDate dataNascimento) throws Exception {
		VerificaExcecao.checkEmptyParameter(dataNascimento, "Data");
		this.paciente.setDataNascimento(dataNascimento);
	}
	
	public void setPeso(double peso) throws Exception {
		VerificaExcecao.checarValor(peso, "Peso");
		this.paciente.setPeso(peso);
	}
	
	public void setSexoBiologico(String sexoBiologico) throws Exception {
		VerificaExcecao.checkEmptyParameter(sexoBiologico, "Sexo biologico");
		this.paciente.setSexoBiologico(sexoBiologico);
	}
	
	public void trocarGenero() {
		this.paciente.trocarGenero();
	}

	
	public boolean registraProcedimento(Procedimento procedimento) {
		return this.procedimentos.add(procedimento);
	}
	
	public int getTotalProcedimento(){
		
		return this.procedimentos.size();
	}
	
	/**
	 * Prontuarios sao comparados pelos nomes de seus pacientes.
	 */
	@Override
	public int compareTo(Prontuario outroProntuario) {
		return this.paciente.getNome().compareTo(outroProntuario.paciente.getNome());
	}
	
	
	/**
	 * Dois prontuarios sao iguais se tiverem o mesmo paciente.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
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
		Prontuario other = (Prontuario) obj;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		return true;
	}

	private void validaParametro(String nome, LocalDate dataNascimento,
			double peso, String sexoBiologico, String genero,
			TipoSanguineo tipoSanguineo, UUID ID) throws Exception,
			ProntuarioException {
		VerificaExcecao.checkEmptyParameter(nome, "Nome do paciente");
		VerificaExcecao.checkEmptyParameter(dataNascimento, "Data");
		VerificaExcecao.checkEmptyParameter(sexoBiologico, "Sexo biologico");
		VerificaExcecao.checkEmptyParameter(genero, "Genero");
		VerificaExcecao.checkEmptyParameter(tipoSanguineo, "Tipo sanguineo");
		VerificaExcecao.checkEmptyParameter(ID, "ID");
		
		VerificaExcecao.checarData(dataNascimento);
		VerificaExcecao.checarValor(peso, "Peso do paciente");
		VerificaExcecao.checarSexoBiologico(sexoBiologico);
	}

}
