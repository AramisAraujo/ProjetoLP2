package control.filemannager;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import banco_de_orgaos.BancoDeOrgaos;
import farmacia.Farmacia;
import funcionario.BancoFuncionarios;
import paciente.BancoProntuarios;

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
	
	public static void exportarFuncionarios(BancoFuncionarios bancoDeFuncionarios) 
			throws FileNotFoundException, IOException {
		
		final String EXTENSION = ".dat";
		
		File systemDir = new File("system_data"+ File.separator);
		File funcionariosBackup = new File(systemDir.getPath()+ File.separator+ "funcionarios" + EXTENSION);
		
		if(! systemDir.exists() || !systemDir.isDirectory()){
			systemDir.mkdir();
		}

		ObjectOutputStream objBufOut = new ObjectOutputStream(new BufferedOutputStream(
				new FileOutputStream(funcionariosBackup)));
		
		
		objBufOut.writeObject(bancoDeFuncionarios);
		
		objBufOut.close();
		
	}
	
	public static void exportarProntuarios(BancoProntuarios bancoProntuarios) 
			throws FileNotFoundException, IOException {
		
		final String EXTENSION = ".dat";
		
		File systemDir = new File("system_data"+ File.separator);
		File prontuariosBackup = new File(systemDir.getPath()+ File.separator+ "prontuarios" + EXTENSION);
		
		if(! systemDir.exists() || !systemDir.isDirectory()){
			systemDir.mkdir();
		}

		ObjectOutputStream objBufOut = new ObjectOutputStream(new BufferedOutputStream(
				new FileOutputStream(prontuariosBackup)));
		
		
		objBufOut.writeObject(bancoProntuarios);
		
		objBufOut.close();
		
	}
	
	public static void exportarFarmacia(Farmacia farmacia) 
			throws FileNotFoundException, IOException {
		
		final String EXTENSION = ".dat";
		
		File systemDir = new File("system_data"+ File.separator);
		File farmaciaBackup = new File(systemDir.getPath()+ File.separator+ "farmacia" + EXTENSION);
		
		if(! systemDir.exists() || !systemDir.isDirectory()){
			systemDir.mkdir();
		}

		ObjectOutputStream objBufOut = new ObjectOutputStream(new BufferedOutputStream(
				new FileOutputStream(farmaciaBackup)));
		
		
		objBufOut.writeObject(farmacia);
		
		objBufOut.close();
		
	}
	
	public static void exportarBancoOrgaos(BancoDeOrgaos bancoOrgaos) 
			throws FileNotFoundException, IOException {
		
		final String EXTENSION = ".dat";
		
		File systemDir = new File("system_data");
		File bancoOrgaosBackup = new File(systemDir.getPath()+ File.separator+ "bancoOrgaos" + EXTENSION);
		
		if(! systemDir.exists() || !systemDir.isDirectory()){
			systemDir.mkdir();
		}

		ObjectOutputStream objBufOut = new ObjectOutputStream(new BufferedOutputStream(
				new FileOutputStream(bancoOrgaosBackup)));
		
		
		objBufOut.writeObject(bancoOrgaosBackup);
		
		objBufOut.close();
		
	}

}
