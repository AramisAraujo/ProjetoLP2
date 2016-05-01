package farmacia;
 
import java.util.HashSet;
import java.util.Set;

import exceptions.EntradaException;
import exceptions.StringException;
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
            Set<CategoriasDeMedicamentos> categorias) throws EntradaException {
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