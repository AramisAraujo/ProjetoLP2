package farmacia;
 
import java.util.HashSet;
import java.util.Set;

import exceptions.EntradaException;
import exceptions.StringException;
import exceptions.ValorException;
import farmacia.CategoriasDeMedicamentos;
 
/**
 * Classe responsavel por criar Medicamentos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class Medicamento implements Comparable<Medicamento> {
 
    private String nome;
    private double preco;
    private int quantidade;
    private Set<CategoriasDeMedicamentos> categorias;
 
    public Medicamento(String nome, double preco, int quantidade,
            Set<CategoriasDeMedicamentos> categorias) throws EntradaException {
    	verificaString(nome);
    	verificaPreco(preco);
    	verificaQuantidade(quantidade);
    	verificaCategorias(categorias);
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categorias = categorias;
    }
 
    public void verificaString(String string) throws StringException{
    	if (string == null){
    		throw new StringException("O nome do medicamento nao pode ser nulo.");
    	}
    	else if (string.equals("".trim())){
    		throw new StringException("O nome do medicamento nao pode ser vazio.");
    	}
    }
    
    public void verificaPreco(double preco) throws ValorException{
    	if (preco < 0){
    		throw new ValorException("O preco do medicamento nao pode ser negativo.");
    	}
    }
    
    public void verificaQuantidade(int quantidade) throws ValorException{
    	if (quantidade == 0){
    		throw new ValorException("A quantidade do medicamento nao pode ser zero.");
    	}
    	else if (quantidade < 0){
    		throw new ValorException("A quantidade do medicamento nao pode ser negativa.");
    	}
    }
    
    public void verificaCategorias(Set categorias) throws ValorException{
    	if (categorias == null){
    		throw new ValorException("A categoria do medicamento nao pode ser nula.");
    	}
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
 
    public Set<CategoriasDeMedicamentos> getCategorias() {
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
 
    /**
     * CompareTo implementado considerando que os medicamentos sao comparaveis
     * atraves do preco.
     */
    @Override
    public int compareTo(Medicamento outroMedicamento) {
        if (this.preco > outroMedicamento.getPreco()) {
            return 1;
        } else if (this.preco < outroMedicamento.getPreco()) {
            return -1;
        } else {
            return 0;
        }
 
    }
}