package ofertaHotelera;

public class Servicio {
	private String nombre;
	private float precio;
	private String descripcion;
	
	public Servicio(String nombre, float precio, String descripcion) {

		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
