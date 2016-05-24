package control.filemannager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import banco_de_orgaos.BancoDeOrgaos;
import farmacia.Farmacia;
import funcionario.BancoFuncionarios;
import paciente.BancoProntuarios;

public class FileMannager {
	
	
	private final String EXTENSION_TXT = ".txt";
	private final String EXTENSION_DAT = ".dat";
	
	private File diretorioFichas;
	private File systemDir;
	
	private File funcionariosBackup;
	
	private File prontuariosBackup;
	
	private File farmaciaBackup;
	
	private File orgaosBackup;
	
	
	public FileMannager(){
		
		this.diretorioFichas =  new File("fichas_pacientes" +  File.separator);
		this.systemDir = new File("system_data"+ File.separator);
		
		this.funcionariosBackup = new File(systemDir.getPath()
				+File.separator+"funcionarios"+EXTENSION_DAT);
		
		this.prontuariosBackup = new File(systemDir.getPath()+ File.separator
				+ "prontuarios" + EXTENSION_DAT);
		
		this.farmaciaBackup  = new File(systemDir.getPath()+ File.separator
				+ "farmacia" + EXTENSION_DAT);
		
		this.orgaosBackup = new File(systemDir.getPath()+ File.separator
				+ "orgaos" + EXTENSION_DAT);
		
	}
	
	public void exportarFichaPaciente(String nomePaciente, String fichaPaciente
			, String dataHoje) throws IOException{
		
		if(! diretorioFichas.exists() || ! diretorioFichas.isDirectory()){
			diretorioFichas.mkdir();
		}
		
		String fileName = "";
		
		String patientName = nomePaciente.replaceAll(" ", "_");
		String date = dataHoje.replaceAll("-", "_");
		
		fileName = patientName +"_"+ date;
		fileName = fileName + EXTENSION_TXT;
		fileName = diretorioFichas.getPath()+  File.separator +fileName;
		
		File ficha = new File(fileName);
		
		BufferedWriter	writer = new BufferedWriter( new FileWriter(ficha) );

		writer.write(fichaPaciente);
		
		writer.close();
		
	}
	
	public void exportarFuncionarios(BancoFuncionarios bancoDeFuncionarios) 
			throws FileNotFoundException, IOException {
				
		if(! systemDir.exists() || !systemDir.isDirectory()){
			systemDir.mkdir();
		}

		ObjectOutputStream objBufOut = new ObjectOutputStream(new BufferedOutputStream(
				new FileOutputStream(funcionariosBackup)));
		
		
		objBufOut.writeObject(bancoDeFuncionarios);
		
		objBufOut.close();
		
	}
	
	public void exportarProntuarios(BancoProntuarios bancoProntuarios) 
			throws FileNotFoundException, IOException {		
		
		if(! systemDir.exists() || !systemDir.isDirectory()){
			systemDir.mkdir();
		}

		ObjectOutputStream objBufOut = new ObjectOutputStream(new BufferedOutputStream(
				new FileOutputStream(prontuariosBackup)));
		
		
		objBufOut.writeObject(bancoProntuarios);
		
		objBufOut.close();
		
	}
	
	public void exportarFarmacia(Farmacia farmacia) 
			throws FileNotFoundException, IOException {
		
		if(! systemDir.exists() || !systemDir.isDirectory()){
			systemDir.mkdir();
		}

		ObjectOutputStream objBufOut = new ObjectOutputStream(new BufferedOutputStream(
				new FileOutputStream(farmaciaBackup)));
		
		
		objBufOut.writeObject(farmacia);
		
		objBufOut.close();
		
	}
	
	public void exportarBancoOrgaos(BancoDeOrgaos bancoOrgaos) 
			throws FileNotFoundException, IOException {
			
		if(! systemDir.exists() || !systemDir.isDirectory()){
			systemDir.mkdir();
		}

		ObjectOutputStream objBufOut = new ObjectOutputStream(new BufferedOutputStream(
				new FileOutputStream(orgaosBackup)));
		
		
		objBufOut.writeObject(bancoOrgaos);
		
		objBufOut.close();
		
	}
	
	
	public BancoFuncionarios importarFuncionarios() throws Exception{

		if(! systemDir.exists() || !systemDir.isDirectory()){
			throw new Exception("Diretorio do sistema inexistente.");
		}
		
		if(! funcionariosBackup.exists()){
			throw new Exception("Arquivo nao encontrado.");
		}
		
		ObjectInputStream objIN = new ObjectInputStream(new BufferedInputStream(
				new FileInputStream(funcionariosBackup)));
		
		BancoFuncionarios doArquivo = (BancoFuncionarios)  objIN.readObject();
		
		objIN.close();
		
		if(doArquivo == null){
			throw new Exception("Falha na leitura do arquivo.");
		}
		
		return doArquivo;
		
	}
	
	
	public BancoProntuarios importarProntuarios() throws Exception{

		if(! systemDir.exists() || !systemDir.isDirectory()){
			throw new Exception("Diretorio do sistema inexistente.");
		}
		
		if(! prontuariosBackup.exists()){
			throw new Exception("Arquivo nao encontrado.");
		}
		
		ObjectInputStream objIN = new ObjectInputStream(new BufferedInputStream(
				new FileInputStream(prontuariosBackup)));
		
		BancoProntuarios doArquivo = (BancoProntuarios)  objIN.readObject();
		
		objIN.close();
		
		if(doArquivo == null){
			throw new Exception("Falha na leitura do arquivo.");
		}
		
		return doArquivo;
		
	}
	
	
	public BancoDeOrgaos importarOrgaos() throws Exception{

		if(! systemDir.exists() || !systemDir.isDirectory()){
			throw new Exception("Diretorio do sistema inexistente.");
		}
		
		if(! orgaosBackup.exists()){
			throw new Exception("Arquivo nao encontrado.");
		}
		
		ObjectInputStream objIN = new ObjectInputStream(new BufferedInputStream(
				new FileInputStream(orgaosBackup)));
		
		BancoDeOrgaos doArquivo = (BancoDeOrgaos) objIN.readObject();
				
		objIN.close();
		
		if(doArquivo == null){
			throw new Exception("Falha na leitura do arquivo.");
		}
		
		return doArquivo;
		
	}
	
	
	public Farmacia importarFarmacia() throws Exception{

		if(! systemDir.exists() || !systemDir.isDirectory()){
			throw new Exception("Diretorio do sistema inexistente.");
		}
		
		if(! farmaciaBackup.exists()){
			throw new Exception("Arquivo nao encontrado.");
		}
		
		ObjectInputStream objIN = new ObjectInputStream(new BufferedInputStream(
				new FileInputStream(farmaciaBackup)));
		
		Farmacia doArquivo = (Farmacia)  objIN.readObject();
		
		objIN.close();
		
		if(doArquivo == null){
			throw new Exception("Falha na leitura do arquivo.");
		}
		
		return doArquivo;
		
	}
	
	public boolean existeBackup(String atributo){
		
		switch (atributo) {
		
		case "Funcionarios":
			
			return funcionariosBackup.exists();
			
		case "Prontuarios":
			
			return prontuariosBackup.exists();
		
		case "Orgaos":
	
			return orgaosBackup.exists();

		case "Farmacia":
			
			return farmaciaBackup.exists();

		default:
			
			return false;
		}
		
		
	}

}
