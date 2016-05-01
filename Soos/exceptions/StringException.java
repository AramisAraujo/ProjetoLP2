package exceptions;

public class StringException extends EntradaException {
 
	private static final long serialVersionUID = 1L;

	public StringException(String mensagem){
        super(mensagem);
    }
     
    public StringException(){
        super("String invalida.");
    }
 
}