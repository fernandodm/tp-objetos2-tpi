package ofertaHotelera;

import java.util.Calendar;
import java.util.List;

public class PeriodoConPrecio extends Periodo {

	private float precio;
	
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public PeriodoConPrecio(Calendar desde, Calendar hasta, int precio) {
		super(desde, hasta);
		this.precio = precio;
	}

	public boolean algunDiaCoincide(List<PeriodoConPrecio> periodo){
		boolean res= false;
		List<Calendar> dias =GeneradorDeCalendar.generarDiasEntre(this.desde, this.hasta);
		for(Calendar each : dias){
			for(PeriodoConPrecio per : periodo){
				if(per.fechaEstaEnElPeriodo(each)){
				res = true;
				break;
			}
			}
		}
		return res;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
