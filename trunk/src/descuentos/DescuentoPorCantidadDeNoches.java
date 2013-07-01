package descuentos;

public class DescuentoPorCantidadDeNoches extends Descuento {
	
	private int cantidadDeNochesParaDescuento;

	public int getCantidadDeNochesParaDescuento() {
		return cantidadDeNochesParaDescuento;
	}
	public void setCantidadDeNochesParaDescuento(int cantidadDeNochesParaDescuento) {
		this.cantidadDeNochesParaDescuento = cantidadDeNochesParaDescuento;
	}

	public DescuentoPorCantidadDeNoches(int noches, int porcentaje){
		this.cantidadDeNochesParaDescuento = noches;
		this.porcentajeDescontado = porcentaje;
	}
	
	public String descuento() {
		
		return "Si pasas " + getCantidadDeNochesParaDescuento() +
		" dias te hacemos un " + getPorcentajeDescontado() + "%" + " de descuento. ";
	}
	
}
