package ofertaHotelera;

import java.util.Calendar;

import excepciones.ExcepcionLaSubastaAunNoHaIniciado;
import excepciones.ExcepcionLaSubastaYaHaFinalizado;

public class Subasta {

	private Usuario ganadorActual;
	private float valor;
	private Calendar inicioSubasta;
	private Calendar finSubasta;
	private EstadoSubasta estado;
	
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



	public Subasta(int valorInicial, Calendar ini, Calendar fin){
		
		setValor(valorInicial);
		setInicioSubasta(ini);
		setFinSubasta(fin);
		if(ini.after(Calendar.getInstance())){
			setEstado(new SubastaFutura());
		} else {
			setEstado(new EnCurso());
		}
	}
	
	public void agragarOferta(Usuario user, float oferta) throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado{
		
		getEstado().agregarApuesta(user,oferta);
		
		
	}
	
	
}

