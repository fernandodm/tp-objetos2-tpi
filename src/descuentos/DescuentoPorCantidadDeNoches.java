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
		this.setCantidadDeNochesParaDescuento(noches);
		this.setPorcentajeDescontado(porcentaje);
	}
	
	public String descuento() {
		
		return "si pasas " + getCantidadDeNochesParaDescuento() +
		" te hacemos un " + getPorcentajeDescontado() + "%" + " de descuento.";
	}
	
}
