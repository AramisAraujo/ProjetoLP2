package pacienteTestes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import paciente.Paciente;
import exceptions.PacienteException;

public class PacienteTest {
	
	private Paciente paciente;
	
	@Test
	public void testPaciente() {
		
		try {
			
			LocalDate dataNascimento;
			DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dataNascimento = LocalDate.parse("01/01/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", "O-", 1);
			
			assertEquals("Elton", paciente.getNome());
			assertEquals("01-01-2000", paciente.getDataNascimento());
			assertTrue(paciente.getIdade() == 60.0);
			assertEquals("masculino", paciente.getSexoBiologico());
			assertEquals("masculino", paciente.getGenero());
			assertEquals("O-", paciente.getTipoSanguineo());
			assertTrue(paciente.getID() == 1);
			
		} catch (Exception e) {
			fail();
		}
		
	}

}
