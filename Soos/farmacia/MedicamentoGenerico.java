package farmacia;
 
import java.io.Serializable;
import java.util.List;

import farmacia.CategoriasDeMedicamentos;
 
/**
 * MedicamentoGenerico
 * Classe que representa um medicamento do tipo Generico.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class MedicamentoGenerico extends Medicamento implements Serializable {
 
    public MedicamentoGenerico(String nome, double preco, int quantidade,
            List<CategoriasDeMedicamentos> categorias) throws Exception {
        super(nome, preco, quantidade, categorias);
        this.tipo = "Generico";
    }
 
    /**
     * GetPreco
     * Metodo sobrescrito utilizado para calcular o desconto de um medicamento
     * do tipo generico, que possui 40% de desconto.
     */
    public double getPreco() {
        double desconto = super.getPreco() * 0.4;
        double precoFinal = super.getPreco() - desconto;
        return precoFinal;
    }
    
    @Override
    public String toString() {
    	
    	String categoriaString = super.getCategorias();	
    	
        String formatacao = String.format("Medicamento Generico: %s - ", this.getNome());
        formatacao = formatacao + String.format("Preco: R$ %.2f - ",this.getPreco());
        formatacao = formatacao + String.format("Disponivel: %d - ", this.getQuantidade());
        formatacao = formatacao + String.format("Categorias: %s", categoriaString);
        
        return formatacao;
    }
}