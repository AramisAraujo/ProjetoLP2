package factories;
 
import java.util.HashSet;
import java.util.Set;

import farmacia.CategoriasDeMedicamentos;
import exceptions.EntradaException;
import exceptions.StringException;
 
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
     * @throws StringException 
     */
    private Medicamento criaMedicamentoDeReferencia(String nome, double preco,
            int quantidade, Set<CategoriasDeMedicamentos> categorias) throws EntradaException {
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
     * @throws EntradaException 
     */
    private Medicamento criaMedicamentoGenerico(String nome, double preco,
            int quantidade, Set<CategoriasDeMedicamentos> categorias) throws EntradaException {
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
     * @throws EntradaException 
     */
    public Medicamento criaMedicamento(String nome, double preco,
            int quantidade, Set categorias,
            String tipo) throws EntradaException {
        if (tipo.equalsIgnoreCase("medicamento generico")) {
            return criaMedicamentoGenerico(nome, preco, quantidade, categorias);
        } else if (tipo.equalsIgnoreCase("medicamento de referencia")) {
            return criaMedicamentoDeReferencia(nome, preco, quantidade,
                    categorias);
        }
        throw new StringException("Esse tipo de medicamento nao existe!");
    }
}