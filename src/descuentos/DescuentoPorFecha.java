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
	
}
