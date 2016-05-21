package farmacia;
 
import java.util.List;

import farmacia.CategoriasDeMedicamentos;
 
/**
 * Classe responsavel por criar medicamentos genericos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class MedicamentoGenerico extends Medicamento {
 
    public MedicamentoGenerico(String nome, double preco, int quantidade,
            List<CategoriasDeMedicamentos> categorias) throws Exception {
        super(nome, preco, quantidade, categorias);
        this.tipo = "Generico";
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