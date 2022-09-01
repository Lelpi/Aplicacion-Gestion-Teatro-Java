package theaterfy.sucesos;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Esta clase se usa para los eventos teatrales del teatro
 * 
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 *
 */

public class EventoTeatro extends Evento {
	
	private static final long serialVersionUID = -4762216763423913452L;
	private String actores;

	/**
	 * Constructor, genera un evento de teatro
	 * 
	 * @param nombre del suceso
	 * @param ra restriccion de aforo
	 * @param descripcion titulo de la obra
	 * @param director titulo de la obra
	 * @param autor titulo de la obra
	 * @param duracion titulo de la obra
	 * @param precios titulo de la obra
	 * @param actores titulo de la obra
	 */
	public EventoTeatro(String nombre, RestriccionAforo ra, String descripcion, String director, String autor, LocalTime duracion,
			ArrayList<Precio> precios, String actores) {
		super(nombre, ra, descripcion, director, autor, duracion, precios);
		this.actores = actores;
	}

	@Override
	public String toString() {
		return super.toString()+"\nActores: "+actores+"\n";
	}
}
