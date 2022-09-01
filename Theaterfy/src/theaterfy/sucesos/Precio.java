package theaterfy.sucesos;

import theaterfy.zona.Zona;

/**
 * Esta clase contiene el precio de una zona en específico
 * 
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * 
 */
public class Precio implements java.io.Serializable{
	private static final long serialVersionUID = 1635678618655237165L;
	private float precio;
	private Zona zona;
	
	/**
	 * Constructor, asigna el precio a una zona
	 * 
	 * @param precio dinero que cuesta cada entrada
	 * @param zona lugar de la entrada
	 */
	public Precio(float precio, Zona zona) {
		this.precio = precio;
		this.zona = zona;
	}

	/**
	 * Metodo para obtener un precio
	 * 
	 * @return el  precio
	 */
	public float getPrecio() {
		return this.precio;
	}

	/**
	 * Metodo para obtener una zona 
	 * 
	 * @return la zona
	 */
	public Zona getZona() {
		return this.zona;
	}
	
	
	
}
