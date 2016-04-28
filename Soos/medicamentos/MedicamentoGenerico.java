package medicamentos;

import java.util.HashSet;

import categorias.CategoriasDeMedicamentos;

public class MedicamentoGenerico extends Medicamento {

	public MedicamentoGenerico(String nome, double preco, int quantidade,
			HashSet<CategoriasDeMedicamentos> categorias) {
		super(nome, preco, quantidade, categorias);
	}

	/**
	 * Metodo sobrescrito utilizado para calcular o desconto de um medicamento
	 * do tipo generico, que possui 40% de desconto.
	 */
	public double getPreco() {
		double desconto = super.getPreco() * 0.4;
		double precoFinal = super.getPreco() - desconto;
		return precoFinal;
	}
}