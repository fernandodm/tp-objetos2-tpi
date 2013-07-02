package ofertaHotelera;

import java.util.Calendar;

import excepciones.ExcepcionLaSubastaAunNoHaIniciado;
import excepciones.ExcepcionLaSubastaYaHaFinalizado;
import excepciones.ExcepcionOfertaInferior;

public class Subasta {

	private Usuario ganadorActual;
	private float valor;
	private Calendar inicioSubasta;
	private Calendar finSubasta;
	private EstadoSubasta estado;
	private Habitacion habitacion;
	private FormaDePago formaDePago;
	
	public Usuario getGanadorActual() {
		return ganadorActual;
	}
	public void setGanadorActual(Usuario ganadorActual) {
		this.ganadorActual = ganadorActual;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public Calendar getInicioSubasta() {
		return inicioSubasta;
	}
	public void setInicioSubasta(Calendar inicioSubasta) {
		this.inicioSubasta = inicioSubasta;
	}
	public Calendar getFinSubasta() {
		return finSubasta;
	}
	public void setFinSubasta(Calendar finSubasta) {
		this.finSubasta = finSubasta;
	}
	public EstadoSubasta getEstado() {
		return estado;
	}
	public void setEstado(EstadoSubasta estado) {
		this.estado = estado;
	}
	public Habitacion getHabitacion() {
		return habitacion;
	}
	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public FormaDePago getFormaDePago() {
		return formaDePago;
	}



	public void setFormaDePago(FormaDePago formaDePago) {
		this.formaDePago = formaDePago;
	}

	/**
	 * Constructor de una subasta, se asume que la fecha inicial será anterior o posterior a la actual, y que la fecha de finalizacion no será anterior a la actual.
	 * @param h, valorInicial, ini, fin
	 * @return 
	 * @throws 
	 */

	public Subasta(Habitacion h, int valorInicial, Calendar ini, Calendar fin){
		
		setHabitacion(h);
		setValor(valorInicial);
		setInicioSubasta(ini);
		setFinSubasta(fin);
		if(ini.after(Calendar.getInstance())){
			setEstado(new SubastaFutura());
		} else {
			setEstado(new EnCurso());
		}
	}
	
	
	/**
	 * Se agrega una oferta a la subasta, se delega esta responsabilidad al estado de la subasta
	 * @param user, oferta
	 * @return boolean
	 * @throws ExcepcionNoHayPrecioEstablecidoParaTalFecha.
	 */
	
	public void agragarOferta(Usuario user, float oferta) throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior{
		
		getEstado().agregarApuesta(user,oferta);
	}
	
	/**
	 * Retorna true si la subasta ya finalizó
	 * @param 
	 * @return termino
	 * @throws 
	 */
	
	public boolean terminoLaSubasta(){
		boolean termino = false;
		if(Calendar.getInstance().after(getFinSubasta())){
			setEstado(new Finalizada());
			termino = true;
		}
		return termino;
	}
	
	
	
}

