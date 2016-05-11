package farmacia;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.MedicamentoException;
import exceptions.VerificaExcecao;
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
    private List<CategoriasDeMedicamentos> categorias;
    protected String tipo;
 
    public Medicamento(String nome, double preco, int quantidade, List<CategoriasDeMedicamentos> categorias) throws Exception{
        VerificaExcecao.checkEmptyString(nome, "Nome do medicamento");
        VerificaExcecao.checarValor(preco, "Preco do medicamento");
        VerificaExcecao.checarValor(quantidade, "Quantidade do medicamento");
        VerificaExcecao.checarValor(quantidade, "A quantidade do medicamento");
        verificaCategorias(categorias);
    	this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categorias = categorias;
        this.tipo = "de Referencia";
    }
    
	   /**
     * Metodo utilizado para verificar se a(s) categoria(s) eh(sao) nula(s),
     * caso ela(s) seja(m), uma excessao eh lancada.
     * 
     * @param categorias
     *            - categoria(s) que sera(o) verificada(s)
     * @throws ValorException
     *             - excessao lancada caso a(s) categoria(s) eh(sejam) nula(s)
     */
    public static void verificaCategorias(List<CategoriasDeMedicamentos> categorias) throws MedicamentoException {
        if (categorias == null) {
            throw new MedicamentoException("A categoria do medicamento nao pode ser nula.");
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
 
    public String getCategorias() {
    	Collections.sort(categorias);
    	String toStringCategorias = "";
		int cont = 0;
		for (CategoriasDeMedicamentos categoria : this.categorias) {
			cont++;
			toStringCategorias += categoria.name().toLowerCase();
			if (cont != categorias.size()) {
				toStringCategorias += ",";
			}
		}
		return toStringCategorias;
    }
 
    public void setCategorias(List<CategoriasDeMedicamentos> categorias) {
        this.categorias = categorias;
    }
 
    public String getTipo() {
        return this.tipo;
    }
    
    /**
     * HashCode implementado considerando que um medicamento eh igual a outro se
     * possuem os mesmos nomes.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }
 
    /**
     * Equals implementado considerando que um medicamento eh igual a outro se
     * possuem os mesmos nomes.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Medicamento) {
            Medicamento outroMedicamento = (Medicamento) obj;
            if (outroMedicamento.nome.equals(this.nome)) {
                return true;
            }
        }
        return false;
    }
 
    @Override
    public String toString() {
    	
    	List<CategoriasDeMedicamentos> categorias = new ArrayList<CategoriasDeMedicamentos>();
    	String categoriaString = "";
    	
    	categorias.addAll(this.categorias);
    	
    	Collections.sort(categorias);
    	
    	for(int i = 0; i < categorias.size(); i++ ){
    		if(i == categorias.size() -1){
    			categoriaString =  categoriaString + categorias.get(i).toString();
    		}
    		else{
    			categoriaString = categoriaString + categorias.get(i).toString()+",";
    		}
    	}
    	
    	
    	
        String formatacao = String.format("Medicamento de Referencia: %s - ", this.getNome());
        formatacao = formatacao + String.format("Preco: R$ %.2f - ",this.getPreco());
        formatacao = formatacao + String.format("Disponivel: %d - ", this.getQuantidade());
        formatacao = formatacao + String.format("Categorias: %s", categoriaString);
        
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