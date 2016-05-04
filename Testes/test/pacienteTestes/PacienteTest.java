package pacienteTestes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import paciente.Paciente;
import exceptions.PacienteException;

public class PacienteTest {
	
	private Paciente paciente;
	private LocalDate dataNascimento;
	private DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	
	@Test
	public void testPaciente() {
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			
			assertEquals("Elton", paciente.getNome());
			assertEquals("2000-12-31", paciente.getDataNascimento());
			assertTrue(paciente.getPeso() == 60.0);
			assertEquals("masculino", paciente.getSexoBiologico());
			assertEquals("masculino", paciente.getGenero());
			assertEquals("O-", paciente.getTipoSanguineo());
			assertTrue(paciente.getID() == 1);
			
		} catch (PacienteException e) {
			fail();
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			fail();
		} catch (PacienteException e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Nome do paciente nao pode ser vazio.", e.getMessage());
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente(" ", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			fail();
		} catch (PacienteException e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Nome do paciente nao pode ser vazio.", e.getMessage());
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente(null, dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			fail();
		} catch (PacienteException e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Nome do paciente nao pode ser vazio.", e.getMessage());
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2050",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			fail();
		} catch (PacienteException e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Data invalida.", e.getMessage());
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, -100.0, "masculino", "masculino", "O-", 1);
			fail();
		} catch (PacienteException e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Peso do paciente nao pode ser negativo.", e.getMessage());
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "", "masculino", "O-", 1);
			fail();
		} catch (PacienteException e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Sexo biologico nao pode ser vazio.", e.getMessage());
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, " ", "masculino", "O-", 1);
			fail();
		} catch (PacienteException e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Sexo biologico nao pode ser vazio.", e.getMessage());
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, null, "masculino", "O-", 1);
			fail();
		} catch (PacienteException e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Sexo biologico nao pode ser vazio.", e.getMessage());
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "", "O-", 1);
			fail();
		} catch (PacienteException e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Genero nao pode ser vazio.", e.getMessage());
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", " ", "O-", 1);
			fail();
		} catch (PacienteException e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Genero nao pode ser vazio.", e.getMessage());
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", null, "O-", 1);
			fail();
		} catch (PacienteException e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Genero nao pode ser vazio.", e.getMessage());
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", "", 1);
			fail();
		} catch (PacienteException e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Tipo sanguineo nao pode ser vazio.", e.getMessage());
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", " ", 1);
			fail();
		} catch (PacienteException e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Tipo sanguineo nao pode ser vazio.", e.getMessage());
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", null, 1);
			fail();
		} catch (PacienteException e) {
			assertEquals("Nao foi possivel cadastrar o paciente. Tipo sanguineo nao pode ser vazio.", e.getMessage());
		}
	}
	
	@Test
	public void testEquals() {
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			Paciente outroPaciente = new Paciente("Helton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			assertTrue(paciente.equals(outroPaciente));
		} catch(Exception e) {
			fail();
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			Paciente outroPaciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 2);
			assertFalse(paciente.equals(outroPaciente));
		} catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testCompareTo() {
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			Paciente outroPaciente = new Paciente("Elton", dataNascimento, 58.0, "masculino", "feminino", "O+", 2);
			assertTrue(paciente.compareTo(outroPaciente) == 0);
		} catch(Exception e) {
			fail();
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			Paciente outroPaciente = new Paciente("Helton", dataNascimento, 58.0, "masculino", "feminino", "O+", 2);
			assertTrue(paciente.compareTo(outroPaciente) < 0);
		} catch(Exception e) {
			fail();
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			Paciente outroPaciente = new Paciente("Eltn", dataNascimento, 58.0, "masculino", "feminino", "O+", 2);
			assertTrue(paciente.compareTo(outroPaciente) > 0);
		} catch(Exception e) {
			fail();
		}
	}

}
