
package theaterfy.zona;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Clase Butaca, esta clase almacena todos los datos que se necesitan para gestionar una butaca
 * 
 * @author luis.lepore@estudiante.uam.es
 * @author oriol.julian@estudiante.uam.es
 * @author paula.golpe@estudiante.uam.es
 *
 */
public class Butaca  implements java.io.Serializable{
	private static final long serialVersionUID = 8454455965310441023L;
	private int x, y;
	private Zona zona;
	private ArrayList<Deshabilitacion> deshabilitadas = new ArrayList<>();
	
	/**
	 * Constructor, asocia los parámetros a las variables de la clase
	 * 
	 * @param x es la posicion x de la butaca
	 * @param y es la posicion y de la butaca
	 * @param z es la zona a la que esta asociada la butaca
	 * @param d es un array de las deshabilitaciones de una butaca
	 */
	public Butaca(int x, int y, Zona z, Deshabilitacion d) {
		this.x = x;
		this.y = y;
		this.zona = z;
		if(d!=null)
			addDeshabilitada(d);
	}
	
	/**
	 * Constructor, llama al constructor de butaca, pasándole null 
	 * como parámetro en el array de deshabilitadas
	 * 
	 * @param x es la posicion x de la butaca
	 * @param y es la posicion y de la butaca
	 * @param z es la zona a la que esta asociada la butaca
	 */
	public Butaca(int x, int y, Zona z) {
		this(x, y, z, null);
	}
	
	/**
	 * Método que añade una nueva deshabilitacion al array de deshabilitaciones de una butaca
	 * 
	 * @param d es la nueva deshabilitacion a añadir
	 */
	public void addDeshabilitada(Deshabilitacion d) {
		this.deshabilitadas.add(d);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * comprueba si se encuentra deshabilitada en un momento dado
	 * @param fechaIni fecha de comienzo
	 * @param fechaFin fecha de fin
	 * @return true si la butaca se encuentra deshabilitada en el tiempo marcado por las fechas 
	 */
	public boolean isDeshabilitada(GregorianCalendar fechaIni, GregorianCalendar fechaFin) {
		for(Deshabilitacion d: deshabilitadas) {
			if(d.getFechaIni().compareTo(fechaIni)<=0 && d.getFechaFin().compareTo(fechaFin)>=0) {
				return true;
			}
		}
		return false;
	}
	
	
}
