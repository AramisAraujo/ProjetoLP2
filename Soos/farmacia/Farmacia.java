package farmacia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import comparator.NomeComparator;
import categorias.CategoriasDeMedicamentos;
import medicamentos.Medicamento;
import exceptions.MedicamentoInexistenteException;
import exceptions.MedicamentoJaCadastradoException;
import exceptions.StringException;
import factory.FactoryDeMedicamentos;

public class Farmacia {

	private FactoryDeMedicamentos factoryDeMedicamentos;
	private List<Medicamento> medicamentos;
	private NomeComparator comparator;

	public Farmacia() {
		this.factoryDeMedicamentos = new FactoryDeMedicamentos();
		this.medicamentos = new ArrayList<Medicamento>();
	}

	public boolean criaMedicamento(String nome, double preco, int quantidade,
			HashSet<CategoriasDeMedicamentos> categorias, String tipo)
			throws MedicamentoJaCadastradoException, StringException {
		if (existeMedicamento(nome, preco)) {
			throw new MedicamentoJaCadastradoException();
		}
		Medicamento medicamento = factoryDeMedicamentos.criaMedicamento(nome,
				preco, quantidade, categorias, tipo);
		medicamentos.add(medicamento);
		return true;
	}

	private boolean existeMedicamento(String nome, double preco) {
		for (Medicamento medicamento : medicamentos) {
			if (medicamento.getNome().equals(nome)
					&& medicamento.getPreco() == preco) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Medicamento> buscaMedicamentos(CategoriasDeMedicamentos categoria){
		ArrayList<Medicamento> medicamentos = new ArrayList<>();
		for (Medicamento medicamento : this.medicamentos) {
			if (medicamento.getCategorias().contains(categoria)){
				medicamentos.add(medicamento);
			}
		}
		Collections.sort(medicamentos);
		return medicamentos;
	}

	public Medicamento buscaMedicamento(String nome) throws MedicamentoInexistenteException{
		for (Medicamento medicamento : medicamentos) {
			if (medicamento.getNome().equals(nome)){
				return medicamento;
			}
		}
		throw new MedicamentoInexistenteException();
	}
	
	public List<Medicamento> getMedicamentosPreco(){
		Collections.sort(this.medicamentos);
		return this.medicamentos;
	}
	
	public List<Medicamento> getMedicamentosNome(){
		Collections.sort(this.medicamentos, comparator);
		return this.medicamentos;
	}
}