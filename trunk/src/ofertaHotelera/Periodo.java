package ofertaHotelera;

import java.util.Calendar;
import java.util.List;

public class Periodo {
	
	protected Calendar desde;
	protected Calendar hasta;
	
	
	public Periodo(Calendar desde, Calendar hasta) {

		this.setDesde(desde);
		this.setHasta(hasta);
	}


	public Periodo() {
		// TODO Auto-generated constructor stub
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
	
	/**
	 * Retorna true si la fecha está dentro del período
	 * @param fecha
	 * @return boolean
	 * @throws 
	 */
	
	public boolean fechaEstaEnElPeriodo(Calendar fecha){
		
		return((fecha.equals(getDesde()) || fecha.equals(getHasta())) || (fecha.after(getDesde())) && fecha.before(getHasta()));
	}
	
	/**
	 * Retorna el período al que pertenece una fecha de una lista de periodos.
	 * @param fecha, periodos
	 * @return p
	 * @throws 
	 */
	
	public Periodo periodoDeLaFecha(Calendar fecha, List<Periodo> periodos){
		
		Periodo p = new Periodo();
		for(Periodo each : periodos){
			if(each.fechaEstaEnElPeriodo(fecha)){
				p = each;
			}
		}
		return p;
	}

	/**
	 * Retorna true si las fecha q se paso como parametro esta entre el periodo
	 * @param desde2
	 * @param hasta2
	 * @return
	 */
	public boolean estaEntre(Calendar desde2, Calendar hasta2) {

		return (desde2.after(getDesde()) && desde2.before(getHasta())) || 
			(hasta2.after(getDesde()) && hasta2.before(getHasta()))
			|| seSuperPone(desde2,hasta2);
	}

	/**
	 * Retorna true si una de las fechas de parametro se superpone
	 * con alguna del periodo
	 * @param desde2
	 * @param hasta2
	 * @return
	 */
	public boolean seSuperPone(Calendar desde2, Calendar hasta2) {
		
		return desde2.equals(getDesde()) || desde2.equals(getHasta())
			|| hasta2.equals(getDesde()) || hasta2.equals(getHasta());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
