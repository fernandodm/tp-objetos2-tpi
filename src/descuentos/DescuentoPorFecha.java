package descuentos;

import java.util.Calendar;

public class DescuentoPorFecha extends Descuento {

	private Calendar fechaLimite;

	public Calendar getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(Calendar fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	
	public DescuentoPorFecha(Calendar limite, int porcentaje){
		this.setFechaLimite(limite);
		this.setPorcentajeDescontado(porcentaje);
	}
	
	public String descuento() {
		
		Calendar fecha = getFechaLimite();
		int año = fecha.get(fecha.YEAR);
		int mes = fecha.get(fecha.MONTH);
		int dia = fecha.get(fecha.DATE);
		
		String laFechaLimite = dia + "/" + mes + "/" + año;
		
		return "Si venis antes del " + laFechaLimite + "te hacemos un " + 
		getPorcentajeDescontado() + "%" + " de descuento";
	}
	
}
