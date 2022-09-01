package theaterfy.sucesos;

import java.util.ArrayList;
import theaterfy.zona.Zona;


/**
 * Esta clase almacena los ciclos actuales del teatro
 * 
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 *
 */
public class Ciclo extends Suceso {
	private static final long serialVersionUID = -4265028250088942719L;
	private ArrayList <Suceso> subciclos=new ArrayList<>();
	
	
	/**
	 * Constructor, llama al constructor de Suceso y luego asigna los subciclos correspondientes
	 * 
	 * @param nombre del ciclo
	 * @param subciclos eventos/ciclos que forman al ciclo
	 */
	public Ciclo(String nombre, ArrayList<Suceso> subciclos) {
		super(nombre);
		this.subciclos = subciclos;
	}
	
	/**
	 * Metodo para añadir un subciclo al ciclo
	 * 
	 * @param suceso el subciclo/evento a añadir
	 */
	public void anyadirSubciclo(Suceso suceso) {
		this.subciclos.add(suceso);
	}
	
	/**
	 * Metodo que devuelve la suma de los precios de una zona de un ciclo
	 * @param zona zona objetivo
	 * @return la suma de todos los precios de una zona de todos los eventos del ciclo
	 */
	public double getPrecioTotal(Zona zona) {
		
		return getPrecioTotalRec(zona, this);
	}
	
	/**
	 * Metodo recursivo para calcular el total de precios de un ciclo
	 * 
	 * @return precio total del suceso en cierta zona
	 */
	private double getPrecioTotalRec(Zona zona, Suceso s) {
		double precio=0;
		if(s.getClass()!=Ciclo.class) {
			for(Precio p : ((Evento)s).getPrecios()) {
				if(p.getZona().getNombre().compareTo(zona.getNombre())==0) {
					return p.getPrecio();
				}
			}
		} else {
			for(Suceso subciclo : ((Ciclo)s).getSubciclos()) {
				precio+=getPrecioTotalRec(zona, subciclo);
			}
		}
		
		return precio;
	}

	/**
	 * Metodo para obtener los subciclos de un ciclo
	 * 
	 * @return los subciclos
	 */
	public ArrayList<Suceso> getSubciclos() {
		return subciclos;
	}
	
	/**
	 * Metodo que verifica que una representacion pertenezca a un ciclo
	 * 
	 * @param r representacion que pertenece (o no) al ciclo
	 * @return true si r pertenece al ciclo this
	 */
	public boolean perteneceRepresentacion(Representacion r) {
		for(Suceso s : subciclos) {
			if(s.getClass()!=Ciclo.class) {
				if(s.getNombre().compareTo(r.getNombreEvento())==0) {
					return true;
				}
			} else {
				if(((Ciclo)s).perteneceRepresentacion(r)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
