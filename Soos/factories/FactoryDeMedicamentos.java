package factories;
  
import java.util.List;

 
import farmacia.CategoriasDeMedicamentos;
import exceptions.MedicamentoException;

import farmacia.Medicamento;
import farmacia.MedicamentoGenerico;
  
/**
 * Classe responsavel por criar todos os medicamentos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class FactoryDeMedicamentos {
  
    /**
     * Metodo utilizado para criar medicamentos de referencia.
     * 
     * @param nome
     *            - nome do medicamento que sera criado
     * @param preco
     *            - preco do medicamento que sera criado
     * @param quantidade
     *            - quantidade do medicamento que sera criado
     * @param categorias
     *            - categorias as quais o medicamento que sera criado pertence
     * @return - o medicamento criado
     * @throws Exception - excessao lancada caso ocorra algum erro
     */
    private Medicamento criaMedicamentoDeReferencia(String nome, double preco,
            int quantidade, List<CategoriasDeMedicamentos> categorias) throws Exception {
        Medicamento medicamento = new Medicamento(nome, preco, quantidade,
                categorias);
        return medicamento;
    }
  
    /**
     * Metodo utilizado para criar medicamentos genericos.
     * 
     * @param nome
     *            - nome do medicamento que sera criado
     * @param preco
     *            - preco do medicamento que sera criado
     * @param quantidade
     *            - quantidade do medicamento que sera criado
     * @param categorias
     *            - categorias as quais o medicamento que sera criado pertence
     * @return - o medicamento criado
     * @throws Exception - excessao lancada caso ocorra algum erro
     */
    private Medicamento criaMedicamentoGenerico(String nome, double preco,
            int quantidade, List<CategoriasDeMedicamentos> categorias) throws Exception {
        Medicamento medicamento = new MedicamentoGenerico(nome, preco,
                quantidade, categorias);
 
        return medicamento;
    }
  
    /**
     * Metodo utilizado para criar medicamentos de acordo com o seu tipo, que
     * pode ser "medicamento generico" ou "medicamento de referencia".
     * 
     * @param nome
     *            - nome do medicamento que sera criado
     * @param preco
     *            - preco do medicamento que sera criado
     * @param quantidade
     *            - quantidade do medicamento que sera criado
     * @param categorias
     *            - categorias as quais o medicamento que sera criado pertence
     * @param tipo
     *            - tipo do medicamento que sera criado (medicamento generico ou
     *            medicamento de refenrencia)
     * @return - medicamento criado
     * @throws Exception 
     * @throws EntradaException 
     */
    public Medicamento criaMedicamento(String nome, double preco,
            int quantidade, List<CategoriasDeMedicamentos> categorias,
            String tipo) throws Exception {
        if (tipo.equalsIgnoreCase("generico")) {
            return criaMedicamentoGenerico(nome, preco, quantidade, categorias);
        } else if (tipo.equalsIgnoreCase("referencia")) {
            return criaMedicamentoDeReferencia(nome, preco, quantidade,
                    categorias);
        }
        throw new MedicamentoException("Esse tipo de medicamento nao existe!");
    }
}