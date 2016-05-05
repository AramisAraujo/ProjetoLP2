package farmacia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import comparators.NomeComparator;
import farmacia.CategoriasDeMedicamentos;
import farmacia.Medicamento;
import exceptions.MedicamentoException;
import factories.FactoryDeMedicamentos;

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
	 * @throws EntradaException 
	 * @throws MedicamentoJaCadastradoException
	 *             - excessao lancada caso ja exista algum medicamento com o
	 *             mesmo nome e o mesmo preco
	 */
	public boolean criaMedicamento(String nome, double preco, int quantidade, Set<CategoriasDeMedicamentos> categorias,
			String tipo) throws MedicamentoException, MedicamentoException {
		if (existeMedicamento(nome, preco)) {
			throw new MedicamentoException("Esse medicamento ja foi cadastrado.");
		}
		Medicamento medicamento = factoryDeMedicamentos.criaMedicamento(nome, preco, quantidade, categorias, tipo);
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
			if (medicamento.getNome().equals(nome) && medicamento.getPreco() == preco) {
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
	public ArrayList<Medicamento> buscaMedicamentos(CategoriasDeMedicamentos categoria) {
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
	 * @return - medicamento com o nome especificado ou null se tal nao existir.
	 */
	public Medicamento buscaMedicamento(String nome){
		for (Medicamento medicamento : medicamentos) {
			if (medicamento.getNome().equals(nome)) {
				return medicamento;
			}
		}
		return null;
	}

    /**
     * Metodo utilizado para retornar o tipo do medicamento que tem o nome
     * passado como parametro.
     * 
     * @param nomeMedicamento
     *            - nome do medicamento
     * @return - tipo do medicamento
     * @throws LogicaException
     *             - excessao lancada caso nao exista nenhum medicamento com o
     *             nome especificado
     */
    public String getTipoMedicamento(String nomeMedicamento) throws Exception {
    	
        Medicamento medicamento = buscaMedicamento(nomeMedicamento);
        return medicamento.getTipo();
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
		Collections.sort(this.medicamentos, new NomeComparator());
		return this.medicamentos;
	}
	
	public String getNome(String medicamento) throws Exception {
		
        for (Medicamento med : medicamentos) {
			if(med.getNome().equals(medicamento)){
				return med.getNome();
			}
		}
        
        throw new Exception("Medicamento nao encontrado.");
    }
 
    public double getPreco(String medicamento) throws Exception {
    	for (Medicamento med : medicamentos) {
			if(med.getNome().equals(medicamento)){
				return med.getPreco();
			}
		}
    	throw new Exception("Medicamento nao encontrado.");
    }
 
    public int getQuantidade(String medicamento) throws Exception {
    	
    	for (Medicamento med : medicamentos) {
			if(med.getNome().equals(medicamento)){
				return med.getQuantidade();
			}
		}
        
        throw new Exception("Medicamento nao encontrado.");
    }
 
    /**		
     * Metodo utilizado para retornar as categorias que o medicamento que tem o		
     * nome passado como parametro pertence.		
     * 		
     * @param nomeMedicamento		
     *            - nome do medicamento		
     * @return - String representando categorias as quais o medicamento pertence
     * 		
     */
    public String getCategorias(String medicamento){
    	
    	List<CategoriasDeMedicamentos> categorias = new ArrayList<CategoriasDeMedicamentos>();
    	String categoriaString = "";
    	
    	for (Medicamento med : medicamentos) {
			if(med.getNome().equals(medicamento)){
				categorias.addAll(med.getCategorias());
			}
		}
    	
    	Collections.sort(categorias);
    	
    	for(int i = 0; i < categorias.size(); i++ ){
    		if(i == categorias.size() -1){
    			categoriaString =  categoriaString + categorias.get(i).toString();
    		}
    		else{
    			categoriaString = categoriaString + categorias.get(i).toString()+",";
    		}
    	}
    	
    	return categoriaString;
    }
    
    /**		
     * Metodo utilizado para atualizar determinado atributo de um medicamento.		
     * 		
     * @param nome		
     *            - nome do medicamento que sera atualizado		
     * @param atributo		
     *            - atributo que sera atualizado		
     * @param novoValor		
     *            - novo valor do atributo que sera atualizado
     *            
     * @throws Exception representando um caso de erro.	
     */		
    
    public void atualizaMedicamento(String nome, String atributo,		
            String novoValor) throws Exception {	
    	
        if (buscaMedicamento(nome) != null) {	
        	
            switch (atributo.toUpperCase()) {	
            
            case "PRECO":		
            	
                double preco = Double.valueOf(novoValor).doubleValue();		
                buscaMedicamento(nome).setPreco(preco);		
                
                break;		
                
            case "QUANTIDADE":	
            	
                int quantidade = Integer.parseInt(novoValor);		
                buscaMedicamento(nome).setQuantidade(quantidade);
                
                break;	
                
            case "NOME":
            	
                throw new MedicamentoException("Nome do medicamento nao pode ser alterado.");	
                
            case "TIPO":
            	
                throw new MedicamentoException("Tipo do medicamento nao pode ser alterado.");
                
            default:		
                throw new MedicamentoException("Esse atributo nao existe.");		
            }	
            
        } else {		
            throw new MedicamentoException("Medicamento nao cadastrado.");		
        }		
    }

    public String getMedicamentoDesc(String nome) throws Exception{
    	
    	for (Medicamento med : this.medicamentos) {
			if(med.getNome().equals(nome)){
				return med.toString();
			}
		}
    	
    	throw new Exception("Medicamento nao cadastrado.");

    }
    
}