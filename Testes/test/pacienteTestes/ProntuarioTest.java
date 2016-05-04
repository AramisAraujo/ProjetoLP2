package pacienteTestes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import paciente.Prontuario;
import exceptions.PacienteException;

public class ProntuarioTest {
	
	private LocalDate dataNascimento;
	private DateTimeFormatter formatador;
	private Prontuario prontuario;
	
	@Before
	public void inicializa() {
		formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dataNascimento = LocalDate.parse("31/12/2000",formatador);
	}
	
	@Test
	public void testProntuario() {
		
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			assertTrue(prontuario.getProcedimentos().isEmpty());
		} catch (PacienteException e) {
			fail();
		}
	}
	
	@Test
	public void testGetInfoPaciente() {
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			assertEquals("Elton", prontuario.getInfoPaciente("Nome"));
			assertEquals("2000-12-31", prontuario.getInfoPaciente("Data"));
			assertEquals("60.0", prontuario.getInfoPaciente("Peso"));
			assertEquals("masculino", prontuario.getInfoPaciente("Sexo"));
			assertEquals("masculino", prontuario.getInfoPaciente("Genero"));
			assertEquals("16", prontuario.getInfoPaciente("Idade"));
		} catch (Exception e) {
			fail();
		}
		
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			String nome = prontuario.getInfoPaciente("");
			fail();
		} catch (Exception e) {
			assertEquals("Atributo invalido.", e.getMessage());
		}
		
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			String nome = prontuario.getInfoPaciente(" ");
			fail();
		} catch (Exception e) {
			assertEquals("Atributo invalido.", e.getMessage());
		}
		
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			String nome = prontuario.getInfoPaciente(null);
			fail();
		} catch (Exception e) {
			assertEquals("Atributo nulo.", e.getMessage());
		}
		
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			String nome = prontuario.getInfoPaciente("Endereco");
			fail();
		} catch (Exception e) {
			assertEquals("Atributo invalido.", e.getMessage());
		}
	}
	
	@Test
	public void testCompareTo() {
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			Prontuario outroProntuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			assertTrue(prontuario.compareTo(outroProntuario) == 0);
		} catch (Exception e) {
			fail();
		}
		
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			Prontuario outroProntuario = new Prontuario("Helton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			assertTrue(prontuario.compareTo(outroProntuario) < 0);
		} catch (Exception e) {
			fail();
		}
		
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			Prontuario outroProntuario = new Prontuario("Eltn", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			assertTrue(prontuario.compareTo(outroProntuario) > 0);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testEquals() {
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			Prontuario outroProntuario = new Prontuario("Helton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			assertTrue(prontuario.equals(outroProntuario));
		} catch (Exception e) {
			fail();
		}
		
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			Prontuario outroProntuario = new Prontuario("Helton", dataNascimento, 60.0, "masculino", "masculino", "O-", 2);
			assertFalse(prontuario.equals(outroProntuario));
		} catch (Exception e) {
			fail();
		}
	}
}
