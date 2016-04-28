package factory;

import java.util.HashSet;

import categorias.CategoriasDeMedicamentos;
import exceptions.StringException;

import medicamentos.Medicamento;
import medicamentos.MedicamentoGenerico;

public class FactoryDeMedicamentos {

	private Medicamento criaMedicamentoDeReferencia(String nome, double preco,
			int quantidade, HashSet<CategoriasDeMedicamentos> categorias) {
		Medicamento medicamento = new Medicamento(nome, preco, quantidade, categorias);
		return medicamento;
	}
	
	private Medicamento criaMedicamentoGenerico(String nome, double preco,
			int quantidade, HashSet<CategoriasDeMedicamentos> categorias) {
		Medicamento medicamento = new MedicamentoGenerico(nome, preco, quantidade, categorias);
		return medicamento;
	}
	
	public Medicamento criaMedicamento(String nome, double preco,
			int quantidade, HashSet<CategoriasDeMedicamentos> categorias, String tipo) throws StringException{
		if (tipo.equalsIgnoreCase("medicamento generico")){
			return criaMedicamentoGenerico(nome, preco, quantidade, categorias);
		}
		else if (tipo.equalsIgnoreCase("medicamento de referencia")){
			return criaMedicamentoDeReferencia(nome, preco, quantidade, categorias);
		}
		throw new StringException("Esse tipo de medicamento nao existe!");
	}
}