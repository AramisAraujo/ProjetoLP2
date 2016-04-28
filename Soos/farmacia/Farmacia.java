package farmacia;

import java.util.ArrayList;
import java.util.HashSet;

import categorias.CategoriasDeMedicamentos;

import medicamentos.Medicamento;

import exceptions.MedicamentoInexistenteException;
import exceptions.MedicamentoJaCadastradoException;
import exceptions.StringException;
import factory.FactoryDeMedicamentos;

public class Farmacia {

	private FactoryDeMedicamentos factoryDeMedicamentos;
	private ArrayList<Medicamento> medicamentos;

	public Farmacia() {
		this.factoryDeMedicamentos = new FactoryDeMedicamentos();
		this.medicamentos = new ArrayList<>();
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
	
	public ArrayList<Medicamento> getMedicamentos(){
		return this.medicamentos;
	}
}