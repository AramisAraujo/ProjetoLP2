package farmacia;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
 
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
 
import farmacia.CategoriasDeMedicamentos;
import farmacia.Farmacia;
import farmacia.Medicamento;
 
public class MedicamentoTest {
 
    @SuppressWarnings("unused")
	private Farmacia farmacia;
    private Set<CategoriasDeMedicamentos> categorias;
 
    @Before
    public void inicializa() {
        farmacia = new Farmacia();
        categorias = new HashSet<CategoriasDeMedicamentos>();
        categorias.add(CategoriasDeMedicamentos.ANTIBIOTICO);
    }
     
    @SuppressWarnings("unused")
	@Test
    public void testMedicamento() {
        // caso normal
        try {
            Medicamento tylenol = new Medicamento("tylenol", 49, 4, categorias);
            assertEquals(tylenol.getNome(), "tylenol");
            assertEquals(tylenol.getPreco(), 49, 0.0);
            assertEquals(tylenol.getQuantidade(), 4);
            assertEquals(tylenol.getCategorias(), categorias);
        } catch (Exception e) {
            fail();
        }
         
        // nome nulo
        try {
            Medicamento morfina = new Medicamento(null, 49, 4, categorias);
            fail("Nao deveria criar um medicamento com o nome nulo.");
        } catch (Exception exp) {
            assertEquals("O nome do medicamento nao pode ser nulo." , exp.getMessage());
        }
         
        // nome vazio
        try {
            Medicamento morfina = new Medicamento("", 49, 4, categorias);
            fail("Nao deveria criar um medicamento com o nome vazio.");
        } catch (Exception exp) {
            assertEquals("Erro no cadastro de medicamento. Nome do medicamento nao pode ser vazio." , exp.getMessage());
        }
         
        // preco negativo.
        try {
            Medicamento morfina = new Medicamento("Morfina", -49, 4, categorias);
            fail("Nao deveria criar um medicamento com o preco negativo.");
        } catch (Exception exp) {
            assertEquals("Erro no cadastro de medicamento. Preco do medicamento nao pode ser negativo." , exp.getMessage());
        }
         
        // quantidade vazia.
        try {
            Medicamento morfina = new Medicamento("Morfina", 49, 0, categorias);
            fail("Nao deveria criar um medicamento com a quantidade vazia.");
        } catch (Exception exp) {
            assertEquals("A quantidade do medicamento nao pode ser zero." , exp.getMessage());
        }
         
        // quantidade negativa.
        try {
            Medicamento morfina = new Medicamento("Morfina", 49, -4, categorias);
            fail("Nao deveria criar um medicamento com a quantidade negativa.");
        } catch (Exception exp) {
            assertEquals("Erro no cadastro de medicamento. Quantidade do medicamento nao pode ser negativo." , exp.getMessage());
        }
         
        // categorias null.
        try {
            Medicamento morfina = new Medicamento("Morfina", 49, 4, null);
            fail("Nao deveria criar um medicamento com a categoria null.");
        } catch (Exception exp) {
            assertEquals("A categoria do medicamento nao pode ser nula." , exp.getMessage());
        }
    }
 
}