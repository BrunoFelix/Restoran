package Utils;

public class ControladorException extends Exception{
    
    public ControladorException(){
        super();
    }
    
    public ControladorException(String mensagem){
        super(mensagem);
    }
}