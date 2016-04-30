package farmacia;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
 
import comparator.NomeComparator;
import categorias.CategoriasDeMedicamentos;
import medicamentos.Medicamento;
import exceptions.MedicamentoInexistenteException;
import exceptions.MedicamentoJaCadastradoException;
import exceptions.StringException;
import factory.FactoryDeMedicamentos;
 
/**
 * Classe responsavel por gerenciar todos os medicamentos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class Farmacia {
 
    private FactoryDeMedicamentos factoryDeMedicamentos;
    private List<Medicamento> medicamentos;
    private NomeComparator comparator;
 
    public Farmacia() {
        this.factoryDeMedicamentos = new FactoryDeMedicamentos();
        this.medicamentos = new ArrayList<Medicamento>();
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
     * @throws MedicamentoJaCadastradoException
     *             - excessao lancada caso ja exista algum medicamento com o
     *             mesmo nome e o mesmo preco
     * @throws StringException
     *             - excecao lancada caso o tipo de medicamento informado nao
     *             exista
     */
    public boolean criaMedicamento(String nome, double preco, int quantidade,
            HashSet<CategoriasDeMedicamentos> categorias, String tipo)
            throws MedicamentoJaCadastradoException, StringException {
        if (existeMedicamento(nome, preco)) {
            throw new MedicamentoJaCadastradoException();
        }
        Medicamento medicamento = factoryDeMedicamentos.criaMedicamento(nome,
                preco, quantidade, categorias, tipo);
        medicamentos.add(medicamento);
        return true;
    }
 
    /**
     * Metodo utilizado para verificar se um determinado medicamento existe
     * informandando seu nome e preco
     * 
     * @param nome
     *            - nome do medicamento que sera verificado
     * @param preco
     *            - preco do medicamento que sera verificado
     * @return - true, se o medicamento existe ou false, se o medicamento nao
     *         existe
     */
    public boolean existeMedicamento(String nome, double preco) {
        for (Medicamento medicamento : medicamentos) {
            if (medicamento.getNome().equals(nome)
                    && medicamento.getPreco() == preco) {
                return true;
            }
        }
        return false;
    }
 
    /**
     * Metodo utilizado para buscar medicamentos que pertencem a uma determinada
     * categoria.
     * 
     * @param categoria
     *            - categoria a ser pesquisada
     * @return - lista ordenada pelo menor preco dos medicamentos que pertencem
     *         a categoria especificada
     */
    public ArrayList<Medicamento> buscaMedicamentos(
            CategoriasDeMedicamentos categoria) {
        ArrayList<Medicamento> medicamentos = new ArrayList<>();
        for (Medicamento medicamento : this.medicamentos) {
            if (medicamento.getCategorias().contains(categoria)) {
                medicamentos.add(medicamento);
            }
        }
        Collections.sort(medicamentos);
        return medicamentos;
    }
 
    /**
     * Metodo utilizado para buscar um determinado medicamento pelo nome.
     * 
     * @param nome
     *            - nome do medicamento a ser procurado
     * @return - medicamento com o nome especificado
     * @throws MedicamentoInexistenteException
     *             - excessao lancada caso nao exista nenhum medicamento com
     *             esse nome
     */
    public Medicamento buscaMedicamento(String nome)
            throws MedicamentoInexistenteException {
        for (Medicamento medicamento : medicamentos) {
            if (medicamento.getNome().equals(nome)) {
                return medicamento;
            }
        }
        throw new MedicamentoInexistenteException();
    }
 
    /**
     * Metodo utilizado para retornar todos os medicamentos presentes no estoque
     * da farmacia.
     * 
     * @return - lista de medicamentos ordenada pelo menor preco
     */
    public List<Medicamento> getMedicamentosPreco() {
        Collections.sort(this.medicamentos);
        return this.medicamentos;
    }
 
    /**
     * Metodo utilizado para retornar todos os medicamentos presentes no estoque
     * da farmacia.
     * 
     * @return - lista de medicamentos ordenada por ordem alfabetica
     */
    public List<Medicamento> getMedicamentosNome() {
        Collections.sort(this.medicamentos, comparator);
        return this.medicamentos;
    }
}