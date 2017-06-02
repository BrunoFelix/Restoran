package TestesUnitario;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import Basica.Mesa;
import Basica.Usuario;
import Fachada.Fachada;
import Negocio.RNMesa;
import Utils.NegocioException;

public class MesaTest {
	
	EntityManagerFactory emf;
	Fachada fachada;
	RNMesa rnMesa;
	Mesa mesa;
	
	@Before
	public void IniciarHibernate(){
		emf = Persistence.createEntityManagerFactory("projetorestoran");
		fachada = Fachada.getInstance();
		rnMesa = new RNMesa(emf);
	}
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo NUMEROMESA
	 * Mesa 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteNumeroMesaZero() throws NegocioException{
		mesa = new Mesa();
		mesa.setNumeroMesa(0);
		rnMesa.validarCampos(mesa);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteNumeroMesaNegativo() throws NegocioException{
		mesa = new Mesa();
		mesa.setNumeroMesa(-1);
		rnMesa.validarCampos(mesa);

	}
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo CAPACIDADEMESA
	 * Mesa 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteCapacidadeMesaZero() throws NegocioException{
		mesa = new Mesa();
		mesa.setNumeroMesa(1);
		mesa.setCapacidadeMesa(0);
		rnMesa.validarCampos(mesa);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteCapacidadeMesaNegativo() throws NegocioException{
		mesa = new Mesa();
		mesa.setNumeroMesa(1);
		mesa.setCapacidadeMesa(-1);
		rnMesa.validarCampos(mesa);

	}
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo STATUS
	 * Mesa 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteStatusVazio() throws NegocioException{
		mesa = new Mesa();
		mesa.setNumeroMesa(1);
		mesa.setCapacidadeMesa(1);
		mesa.setStatus("");
		rnMesa.validarCampos(mesa);

	}
	
	@Test(expected=NegocioException.class)
	public void TesteStatusNull() throws NegocioException{
		mesa = new Mesa();
		mesa.setNumeroMesa(1);
		mesa.setCapacidadeMesa(1);
		mesa.setStatus(null);
		rnMesa.validarCampos(mesa);

	}
	
	@Test(expected=NegocioException.class)
	public void TesteStatusMaiorQueOPermitido() throws NegocioException{	
		mesa = new Mesa();
		mesa.setNumeroMesa(1);
		mesa.setCapacidadeMesa(1);
		mesa.setStatus("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		rnMesa.validarCampos(mesa);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteStatusMenorQueOPermitido() throws NegocioException{	
		mesa = new Mesa();
		mesa.setNumeroMesa(1);
		mesa.setCapacidadeMesa(1);
		mesa.setStatus("a");
		rnMesa.validarCampos(mesa);
		
	}
	
	@Test(expected=NegocioException.class)
	public void TesteStatusSomenteComEspacos() throws NegocioException{	
		mesa = new Mesa();
		mesa.setNumeroMesa(1);
		mesa.setCapacidadeMesa(1);
		mesa.setStatus("      ");
		rnMesa.validarCampos(mesa);
	}
	
	
	/***********************************
	 * 
	 * Testes da regra de negocio com função que verifica se o objeto está vazio
	 * Mesa 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteObjetoExite() throws NegocioException{	
		mesa = null;
		rnMesa.verificarObjeto(mesa);
	}
}
