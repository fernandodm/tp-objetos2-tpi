package ofertaHotelera;

import java.util.Calendar;

public class Periodo {
	
	private Calendar desde;
	private Calendar hasta;
	
	
	public Periodo(Calendar desde, Calendar hasta) {

		this.desde = desde;
		this.hasta = hasta;
	}


	public Calendar getDesde() {
		return desde;
	}


	public void setDesde(Calendar desde) {
		this.desde = desde;
	}


	public Calendar getHasta() {
		return hasta;
	}


	public void setHasta(Calendar hasta) {
		this.hasta = hasta;
	}

	public boolean estaEntre(Calendar desde2, Calendar hasta2) {

		return (desde2.after(getDesde()) && desde2.before(getHasta())) || 
			(hasta2.after(getDesde()) && hasta2.before(getHasta()))
			|| seSuperPone(desde2,hasta2);
	}

	private boolean seSuperPone(Calendar desde2, Calendar hasta2) {
		
		String fechaInicio = "" + desde2.get(desde2.YEAR) + desde2.get(desde2.MONTH) + desde2.get(desde2.DATE);
		String fechaFin = "" + hasta2.get(hasta2.YEAR) + hasta2.get(hasta2.MONTH) + hasta2.get(hasta2.DATE);
		String fechaPeriodoDesde = "" + getDesde().get(getDesde().YEAR) + getDesde().get(getDesde().MONTH) + getDesde().get(desde2.DATE);
		String fechaPeriodoHasta = "" + getHasta().get(getHasta().YEAR) + getHasta().get(getHasta().MONTH) + getHasta().get(getHasta().DATE);
		
		return fechaInicio.equals(fechaPeriodoDesde) || fechaInicio.equals(fechaPeriodoHasta)
			|| fechaFin.equals(fechaPeriodoDesde) || fechaFin.equals(fechaPeriodoHasta);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
