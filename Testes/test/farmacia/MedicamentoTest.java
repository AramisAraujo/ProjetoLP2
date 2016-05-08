package farmacia;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
 
import java.util.ArrayList;
import java.util.List;
 
import org.junit.Before;
import org.junit.Test;

import exceptions.MedicamentoException;
import farmacia.CategoriasDeMedicamentos;
import farmacia.Farmacia;
import farmacia.Medicamento;
 
public class MedicamentoTest {
 
    private Farmacia farmacia;
    private List<CategoriasDeMedicamentos> categorias;
 
    @Before
    public void inicializa() {
        farmacia = new Farmacia();
        categorias = new ArrayList<CategoriasDeMedicamentos>();
        categorias.add(CategoriasDeMedicamentos.ANTIBIOTICO);
    }
     
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
            fail("Nao deveria criar um medicamento com o nome null.");
        } catch (MedicamentoException exp) {
            assertEquals("O nome do medicamento nao pode ser nulo." , exp.getMessage());
        } catch (Exception exp) {
            fail();
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
        } catch (MedicamentoException exp) {
            assertEquals("Erro no cadastro de medicamento. Preco do medicamento nao pode ser negativo." , exp.getMessage());
        } catch (Exception exp) {
            fail();
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