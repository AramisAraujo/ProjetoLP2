package control.filemannager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileMannager {
	
	
	public static void exportarFichaPaciente(String nomePaciente, String fichaPaciente, String dataHoje) throws IOException{
		
		final String EXTENSION = ".txt";
		
		File diretorioPadrao = new File("fichas_pacientes" +  File.separator);
		
		if(! diretorioPadrao.exists() || ! diretorioPadrao.isDirectory()){
			diretorioPadrao.mkdir();
		}
		
		String fileName = "";
		
		String patientName = nomePaciente.replaceAll(" ", "_");
		String date = dataHoje.replaceAll("-", "_");
		
		fileName = patientName +"_"+ date;
		fileName = fileName + EXTENSION;
		fileName = diretorioPadrao.getPath()+  File.separator +fileName;
		
		File ficha = new File(fileName);
		
		BufferedWriter	writer = new BufferedWriter( new FileWriter(ficha) );

		writer.write(fichaPaciente);
		
		writer.close();
		
	}

}
