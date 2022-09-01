package theaterfy.compras;

import java.util.ArrayList;

import theaterfy.sucesos.Representacion;
import theaterfy.zona.Zona;

/**
 * Esta clase almacena un precio y un identificador del abono, 
 * además de una zona asociada
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public abstract class Abono implements java.io.Serializable{
	private static final long serialVersionUID = -871075129876647902L;
	
	private static int idGlobal=1;
	private static String pref="AB";
	private String id;
	private double precio;
	private Zona zona;
	private ArrayList<Representacion> representacionesUsadas=new ArrayList<>();
	
	/**
	 * Constructor, asigna los parámetros a las variables correspondientes
	 * @param precio precio del abono
	 * @param zona zona del abono
	 */
	public Abono(double precio, Zona zona) {
		id=pref+idGlobal;
		idGlobal++;
		this.precio = precio;
		this.zona=zona;
	}

	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}
	
	/**
	 * se indica que el abono se ha usado en una representacion ya
	 * @param r representacion a añadir
	 */
	public void anyadirRepresentacionUsada(Representacion r) {
		this.representacionesUsadas.add(r);
	}
	
	/**
	 * comprueba si se ha utilizado el abono en un evento concreto anteriormente
	 * @param r representacion a comprobar
	 * @return true si el nombre del evento de r no coincide con ninguno de representacionesUsadas
	 */
	public boolean abonoUsado(Representacion r) {
		for(Representacion representacionUsada : this.representacionesUsadas) {
			if(representacionUsada.getNombreEvento().compareTo(r.getNombreEvento())==0) {
				return true;
			}
		}
		return false;
	}
}
