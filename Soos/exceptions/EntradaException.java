package exceptions;

public class EntradaException extends Exception {
  
    private static final long serialVersionUID = 1L;
      
    public EntradaException() {
        super("Entrada invalida.");
    }
      
    public EntradaException(String mensagem) {
        super(mensagem);
    }
      
}