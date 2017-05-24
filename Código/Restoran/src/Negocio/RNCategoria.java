package Negocio;

public class RNCategoria {
	
	CategoriaDAO categoriaDAO;

	public RNCategoria() {

	  categoriaDAO = new CategoriaDAO();
	}
	
    /*##################################
              FUNCIONALIDADES
    ##################################*/
    
    public void salvar(Categoria c) throws NegocioException, DadosException{
    	
         salvar(c);
       
    }
    
    public void alterar(Categoria c) throws ControladorException{
    	
        alterar(c);

    }
    
    public void excluir(Categoria c) throws ControladorException{
       
        excluir(c);
    }
    /*##################################
              VALIDAÇÕES
    ##################################*/	

}
