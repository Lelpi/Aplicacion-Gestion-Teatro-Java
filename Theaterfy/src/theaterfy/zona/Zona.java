package theaterfy.zona;


import theaterfy.Theaterfy;
import theaterfy.sucesos.Precio;

/**
 * Clase abstracta Zona, guarda el nombre de las zonas
 * 
 * @author luis.lepore@estudiante.uam.es
 * @author oriol.julian@estudiante.uam.es
 * @author paula.golpe@estudiante.uam.es
 *
 */
public abstract class Zona  implements java.io.Serializable{
	private static final long serialVersionUID = -3929478209024967975L;
	private String nombre;	
	/**
	 * Constructor, asocia los parámetros a las variables de la clase
	 * 
	 * @param nombre es el nombre de la zona
	 * @param precioAbonoAnual el el precio del abono anual para una zona
	 */
	public Zona(String nombre, float precioAbonoAnual) {
		this.nombre = nombre;
		Theaterfy.getTheaterfy().anyadirPreciosAbonoAnual(new Precio(precioAbonoAnual, this));
	}

	/**
	 * Método getter, devuelve el nombre de la zona
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @return devuelve el aforo máximo de la zona
	 */
	public abstract int getAforo();
}
