package Negocio;

import Basica.Usuario;
import Utils.ControladorException;

public class RNUsuario {
	   
    private void verificarPrenchimento(Usuario u) throws ControladorException {
        
    	
    	if(u == null)
             throw new ControladorException("Objeto Usuario n�o preenchido");
    }

    private void validarCampos(Usuario u) throws ControladorException {
        if((u.getNome().isEmpty()== true) || (u.getNome()).length() < 5)
            throw new ControladorException("Nome Inv�lido");
        if((u.getCpf().isEmpty()== true) || (u.getCpf().length() < 10))
            throw new ControladorException("Cpf Inv�lido");
        if((u.getTelefone().isEmpty()== true) || (u.getTelefone().length() < 8))
            throw new ControladorException("Telefone Inv�lido");
        if((u.getLogin().isEmpty())== true || u.getLogin()== "")
            throw new ControladorException("Login inv�lido");
        if(u.getSenha().isEmpty()== true || u.getSenha() == "")
            throw new ControladorException("senha inv�lida");
    }
   
}

