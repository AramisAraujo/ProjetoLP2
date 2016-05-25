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

import bancoorgaos.BancoDeOrgaos;
import farmacia.Farmacia;
import funcionario.BancoFuncionarios;
import paciente.BancoProntuarios;

/**
 * FileMannager
 * Classe que representa um gerenciador de arquivos do sistema.
 * Eh responsavel por todas as operacoes de salvar e carregar
 * de arquivos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */

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
	
	/**
	 * ExportarFichaPaciente
	 * Metodo responsavel por escrever em um arquivo de texto
	 * uma string que representa a ficha de um paciente do sistema
	 * Soos.
	 * 
	 * @param nomePaciente
	 * @param fichaPaciente
	 * @param dataHoje
	 * @throws IOException
	 */
	
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
	
	/**
	 * ExportarFuncionarios
	 * Metodo responsavel por exportar o
	 * Banco de Funcionarios do sistema para ser 
	 * recarregado posteriormente.
	 * 
	 * @param bancoDeFuncionarios
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
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
	
	/**
	 * ExportarProntuarios
	 * Metodo responsavel por exportar o
	 * Banco de Prontuarios do sistema para ser 
	 * recarregado posteriormente.
	 * 
	 * @param bancoDeFuncionarios
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
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
	
	/**
	 * ExportarFarmacia
	 * Metodo responsavel por exportar a
	 * Farmacia do sistema para ser 
	 * recarregado posteriormente.
	 * 
	 * @param bancoDeFuncionarios
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
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
	
	/**
	 * ExportarBancoOrgaos
	 * Metodo responsavel por exportar o
	 * Banco de orgaos do sistema para ser 
	 * recarregado posteriormente.
	 * 
	 * @param bancoDeFuncionarios
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
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
	
	/**
	 * ImportarFuncionarios
	 * Metodo responsavel por importar o
	 * Banco de Funcionarios do sistema a
	 * partir de um arquivo.
	 * 
	 * @param bancoDeFuncionarios
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
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
	
	
	/**
	 * ImportarProntuarios
	 * Metodo responsavel por importar o
	 * Banco de Prontuarios do sistema a
	 * partir de um arquivo.
	 * 
	 * @param bancoDeFuncionarios
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
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
	
	/**
	 * ImportarOrgaos
	 * Metodo responsavel por importar o
	 * Banco de Orgaos do sistema a
	 * partir de um arquivo.
	 * 
	 * @param bancoDeFuncionarios
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
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
	
	
	/**
	 * ImportarFarmacia
	 * Metodo responsavel por importar a
	 * Farmacia do sistema a
	 * partir de um arquivo.
	 * 
	 * @param bancoDeFuncionarios
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
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
	
	/**
	 * ExisteBackup
	 * Metodo que verifica se existe um arquivo
	 * de backup do sistema que deve ser carregado
	 * em sua inicializacao.
	 * 
	 * @param atributo
	 * @return
	 */
	
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
