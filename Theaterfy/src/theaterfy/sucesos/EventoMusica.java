package theaterfy.sucesos;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Esta clase se usa para los eventos musicales del teatro
 * 
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 *
 */
public class EventoMusica extends Evento {
	private static final long serialVersionUID = 5559459461884917302L;
	private String orquesta, solista, programa;

	/**
	 * Constructor, genera un evento de musica
	 * 
	 * @param nombre del suceso
	 * @param ra restriccion de aforo
	 * @param descripcion de la obra
	 * @param director de la obra
	 * @param autor de la obra
	 * @param duracion de la obra
	 * @param precios de la obra
	 * @param orquesta de la obra
	 * @param solista de la obra
	 * @param programa de la obra
	 */
	public EventoMusica(String nombre,RestriccionAforo ra, String descripcion, String director, String autor,
			LocalTime duracion, ArrayList<Precio> precios, String orquesta, String solista, String programa) {
		super(nombre, ra, descripcion, director, autor, duracion, precios);
		this.orquesta = orquesta;
		this.solista = solista;
		this.programa = programa;
	}
	
	@Override
	public String toString() {
		return super.toString()+"\nSolista: "+solista+", orquesta: "+orquesta+", programa: "+programa+"\n";
	}
}
