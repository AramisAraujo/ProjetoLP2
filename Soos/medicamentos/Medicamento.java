package medicamentos;

import java.util.HashSet;

import categorias.CategoriasDeMedicamentos;

public class Medicamento {

	private String nome;
	private double preco;
	private int quantidade;
	private HashSet<CategoriasDeMedicamentos> categorias;

	public Medicamento(String nome, double preco, int quantidade,
			HashSet<CategoriasDeMedicamentos> categorias) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categorias = categorias;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public HashSet<CategoriasDeMedicamentos> getCategorias() {
		return categorias;
	}

	public void setCategorias(HashSet<CategoriasDeMedicamentos> categorias) {
		this.categorias = categorias;
	}

	/**
	 * HashCode implementado considerando que um medicamento eh igual a outro se
	 * possuem os mesmos nomes e precos.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Equals implementado considerando que um medicamento eh igual a outro se
	 * possuem os mesmos nomes e precos.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Medicamento) {
			Medicamento outroMedicamento = (Medicamento) obj;
			if (outroMedicamento.nome.equals(this.nome)) {
				if (outroMedicamento.preco == this.preco) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		String formatacao = "Nome: " + this.nome + ". \n";
		formatacao += "Preco: " + this.preco + ". \n";
		formatacao += "Quantidade: " + this.quantidade + ". \n";
		formatacao += "Categorias: \n";
		for (CategoriasDeMedicamentos categoria : categorias) {
			formatacao += "   - " + categoria + "\n";
		}
		return formatacao;
	}
}