package theaterfy.zona;

import java.util.GregorianCalendar;

/**
 * Clase Deshabilitacion, esta clase gestiona los datos necesarios para deshabilitar una butaca
 * 
 * @author luis.lepore@estudiante.uam.es
 * @author oriol.julian@estudiante.uam.es
 * @author paula.golpe@estudiante.uam.es
 *
 */
public class Deshabilitacion {
	private String deshabilitacion;
	private GregorianCalendar fechaIni, fechaFin;
	
	/**
	 * Constructor, asocia los parámetros que recibe a las variables de la clase
	 * 
	 * @param deshabilitacion, es un string que indica el motivo de la deshabilitacion
	 * @param fechaIni es la fecha en la que comienza a estar deshabilitada
	 * @param fechaFin es la fecha en la que termina la deshabilitacion
	 */
	public Deshabilitacion(String deshabilitacion, GregorianCalendar fechaIni, GregorianCalendar fechaFin) {
		this.deshabilitacion = deshabilitacion;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the fechaIni
	 */
	public GregorianCalendar getFechaIni() {
		return fechaIni;
	}

	/**
	 * @return the fechaFin
	 */
	public GregorianCalendar getFechaFin() {
		return fechaFin;
	}

	/**
	 * @return the deshabilitacion
	 */
	public String getDeshabilitacion() {
		return deshabilitacion;
	}
	
	
}
