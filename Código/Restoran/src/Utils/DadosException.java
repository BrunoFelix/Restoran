package Utils;

public class DadosException extends Exception {


	public DadosException(){
		super();
	}

    public DadosException(Exception e){
		super(e.getMessage());
	}
    
    public DadosException(String mensagem){
		super(mensagem);
	}
}
