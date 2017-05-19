package Negocio;

import Basica.Usuario;
import Utils.NegocioException;

public class RNUsuario {
	   
	private void ValidarPreenchimentoDados(Usuario u) throws NegocioException {
           if (u.getNome() == null || u.getNome() == "")
           {
               throw new NegocioException("O campo \"Nome\" precisa ser preenchido!");
           }
           if (u.getCpf() == null || u.getCpf() == "")
           {
               throw new NegocioException("O campo \"CPF\" precisa ser preenchido!");
           }
           if (u.getLogin() == null || u.getLogin() == "")
           {
               throw new NegocioException("O campo \"Login\" precisa ser preenchido!");
           }
           if (u.getSenha()== null || u.getSenha() == "")
           {
               throw new NegocioException("O campo \"Senha\" precisa ser preenchido!");
           }
           
       }
   
}

