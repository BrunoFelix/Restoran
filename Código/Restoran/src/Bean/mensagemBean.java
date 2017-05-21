package Bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.faces.bean.ManagedBean; 

@ManagedBean(name="mensagemBean")//@ManagedBean(name=“msg”)
public class mensagemBean {
	private String mensagem; 
	public String getMensagem() {
		Calendar calendar = Calendar.getInstance();
		int	hora = calendar.get(Calendar.HOUR_OF_DAY);
		SimpleDateFormat sdf = new SimpleDateFormat("EEEEE - dd-MMM-yyyy");
		if(hora < 12){
			mensagem ="teste1";
		} else
			if(hora < 18){
				mensagem ="teste2";
			} else	 {
				mensagem ="teste3";
			} 
		return mensagem; 
	   } 

}
