package theaterfy.sucesos;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Esta clase se usa para los eventos de danza del teatro
 * 
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 *
 */
public class EventoDanza extends Evento {
	private static final long serialVersionUID = -6893212199447482635L;
	private String bailarines;
	private String orquesta, directorOrquesta;
	
	/**
	 * Constructor, genera un evento de danza
	 * 
	 * @param nombre del suceso
	 * @param ra restriccion de aforo
	 * @param descripcion de la obra
	 * @param director de la obra
	 * @param autor de la obra
	 * @param duracion de la obra
	 * @param precios de la obra
	 * @param bailarines de la obra
	 * @param orquesta de la obra
	 * @param directorOrquesta de la obra
	 */
	public EventoDanza(String nombre, RestriccionAforo ra, String descripcion, String director, String autor, LocalTime duracion, ArrayList<Precio> precios, 
			String bailarines, String orquesta, String directorOrquesta) {
		super(nombre,ra, descripcion, director, autor, duracion, precios);
		this.bailarines = bailarines;
		this.orquesta = orquesta;
		this.directorOrquesta = directorOrquesta;
	}
	
	@Override
	public String toString() {
		return super.toString()+"\nBailarines: "+bailarines+", orquesta: "+orquesta+" (director: "+directorOrquesta+")\n";
	}
	
}
