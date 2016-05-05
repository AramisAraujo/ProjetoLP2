package farmacia;
 
import static org.junit.Assert.*;
 
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
 
import comparators.NomeComparator;
import farmacia.CategoriasDeMedicamentos;
import farmacia.Medicamento;
 
public class NomeComparatorTest {
 
    private NomeComparator comparator;
    private Set<CategoriasDeMedicamentos> categorias1;
    private Medicamento tylenol;
    private Set<CategoriasDeMedicamentos> categorias2;
    private Medicamento dorflex;
 
    @Before
    public void inicializa() throws Exception {
        comparator = new NomeComparator();
        categorias1 = new HashSet<CategoriasDeMedicamentos>();
        categorias1.add(CategoriasDeMedicamentos.ANALGESICO);
        tylenol = new Medicamento("tylenol", 28.0, 3, categorias1);
        categorias2 = new HashSet<CategoriasDeMedicamentos>();
        categorias1.add(CategoriasDeMedicamentos.ANTIINFLAMATORIO);
        dorflex = new Medicamento("dorflex", 29.0, 4, categorias2);
    }
 
    @Test
    public void testCompare() {
        // primeiro menor que o segundo
        try {
            assertTrue(comparator.compare(tylenol, dorflex) > 0);
        } catch (Exception e) {
            fail();
        }
 
        // nomes iguais
        try {
            assertTrue(comparator.compare(tylenol, tylenol) == 0);
        } catch (Exception e) {
            fail();
        }
 
        // primeiro maior que o segundo
        try {
            assertTrue(comparator.compare(dorflex, tylenol) < 0);
        } catch (Exception e) {
            fail(); // Nao deveria lancar excessao
        }
    }
 
}