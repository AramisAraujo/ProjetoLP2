package farmacia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import exceptions.MedicamentoException;
import factories.FactoryDeMedicamentos;
import farmacia.CategoriasDeMedicamentos;
import farmacia.Farmacia;
import farmacia.Medicamento;

public class FarmaciaTest {
	private Farmacia farmacia;
	private HashSet<CategoriasDeMedicamentos> categorias;
	private FactoryDeMedicamentos factory;

	@Before
	public void inicializa() {
		farmacia = new Farmacia();
		categorias = new HashSet<CategoriasDeMedicamentos>();
		categorias.add(CategoriasDeMedicamentos.ANTIBIOTICO);
		factory = new FactoryDeMedicamentos();
	}

	@Test
	public void testCriaMedicamento() {
		// caso normal
		try {
			farmacia.criaMedicamento("dorflex", 34.0, 5, categorias, "medicamento de referencia");
			assertTrue(farmacia.existeMedicamento("dorflex", 34.0));
		} catch (Exception e) {
			fail();
		}

		// medicamento ja existente
		try {
			farmacia.criaMedicamento("dorflex", 34.0, 5, categorias, "medicamento de referencia");
			farmacia.criaMedicamento("dorflex", 34.0, 5, categorias, "medicamento de referencia");
			fail();
		} catch (MedicamentoException e) {
			assertEquals("Esse medicamento ja foi cadastrado.", e.getMessage());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testExisteMedicamento() {
		// medicamento existe
		try {
			farmacia.criaMedicamento("dorflex", 34.0, 5, categorias, "medicamento de referencia");
			assertTrue(farmacia.existeMedicamento("dorflex", 34.0));
		} catch (Exception e) {
			fail();
		}

		// medicamento nao existe
		try {
			assertFalse(farmacia.existeMedicamento("tylenol", 50.0));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testBuscaMedicamentos() {
		try {
			Set<CategoriasDeMedicamentos> categoria = new HashSet<CategoriasDeMedicamentos>();
			categoria.add(CategoriasDeMedicamentos.ANTI_INFLAMATORIO);
			factory = new FactoryDeMedicamentos();
			Medicamento telorac = factory.criaMedicamento("terolac", 36.87, 10, categoria, "medicamento generico");
			Medicamento biofenac = factory.criaMedicamento("biofenac", 8.95, 10, categoria, "medicamento generico");
			farmacia.criaMedicamento("terolac", 36.87, 10, categoria, "medicamento generico");
			farmacia.criaMedicamento("biofenac", 8.95, 10, categoria, "medicamento generico");
			List<Medicamento> medicamentos = new ArrayList<Medicamento>();
			medicamentos.add(biofenac);
			medicamentos.add(telorac);
			assertEquals(farmacia.buscaMedicamentos(CategoriasDeMedicamentos.ANTI_INFLAMATORIO), medicamentos);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testBuscaMedicamento() {
		// medicamento existente
		try {
			factory = new FactoryDeMedicamentos();
			Medicamento telorac = factory.criaMedicamento("terolac", 36.87, 10, categorias, "medicamento generico");
			Medicamento biofenac = factory.criaMedicamento("biofenac", 8.95, 10, categorias, "medicamento generico");
			farmacia.criaMedicamento("terolac", 36.87, 10, categorias, "medicamento generico");
			farmacia.criaMedicamento("biofenac", 8.95, 10, categorias, "medicamento generico");
			assertEquals(farmacia.buscaMedicamento("terolac"), telorac);
			assertEquals(farmacia.buscaMedicamento("biofenac"), biofenac);
		} catch (Exception e) {
			fail();
		}

		// medicamento inexistente
		try {
			farmacia.buscaMedicamento("buscopam");
			fail();
		} catch (Exception e) {
			assertEquals("Esse medicamento nao existe!", e.getMessage());
		}

	}

	@Test
	public void testGetMedicamentosPreco() {
		try {
			factory = new FactoryDeMedicamentos();
			Medicamento telorac = factory.criaMedicamento("terolac", 36.87, 10, categorias, "medicamento generico");
			Medicamento biofenac = factory.criaMedicamento("biofenac", 8.95, 10, categorias, "medicamento generico");
			Medicamento ponstan = factory.criaMedicamento("ponstan", 20.0, 2, categorias, "medicamento de referencia");
			farmacia.criaMedicamento("terolac", 36.87, 10, categorias, "medicamento generico");
			farmacia.criaMedicamento("biofenac", 8.95, 10, categorias, "medicamento generico");
			farmacia.criaMedicamento("ponstan", 20.0, 2, categorias, "medicamento de referencia");
			List<Medicamento> medicamentos = new ArrayList<Medicamento>();
			medicamentos.add(biofenac);
			medicamentos.add(ponstan);
			medicamentos.add(telorac);
			assertEquals(farmacia.getMedicamentosPreco(), medicamentos);
		} catch (Exception e) {
			fail();
		}
	}

	
	@Test 
	public void testGetMedicamentosNome() { 
		try {
			factory = new FactoryDeMedicamentos();
			Medicamento telorac = factory.criaMedicamento("terolac", 36.87, 10, categorias, "medicamento generico");
			Medicamento biofenac = factory.criaMedicamento("biofenac", 20.0, 10, categorias, "medicamento generico");
			Medicamento ponstan = factory.criaMedicamento("ponstan", 8.95, 2, categorias, "medicamento de referencia");
			farmacia.criaMedicamento("terolac", 36.87, 10, categorias, "medicamento generico");
			farmacia.criaMedicamento("biofenac", 20.0, 10, categorias, "medicamento generico");
			farmacia.criaMedicamento("ponstan", 8.95, 2, categorias, "medicamento de referencia");
			List<Medicamento> medicamentos = new ArrayList<Medicamento>();
			medicamentos.add(biofenac);
			medicamentos.add(ponstan);
			medicamentos.add(telorac);
			assertEquals(farmacia.getMedicamentosNome(), medicamentos);
		} catch (Exception e) {
			fail();
		}
	}
	 
}
