package theaterfy.sucesos;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import theaterfy.Theaterfy;
import theaterfy.zona.Deshabilitacion;
import theaterfy.zona.Zona;
import theaterfy.zona.ZonaMixta;
import theaterfy.zona.ZonaNumerada;


/**
 * Esta clase abstracta sirve para gestionar los eventos del teatro
 * 
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * 
 */
public abstract class Evento extends Suceso {
	private static final long serialVersionUID = -3356237302656105526L;
	private String descripcion, director, autor;
	private LocalTime duracion;
	private ArrayList <Precio> precios=new ArrayList<>();
	private ArrayList <Representacion> representaciones=new ArrayList<>();
	private RestriccionAforo restriccionAforo;
	
	/**
	 * Constructor, genera un evento segun los parametros que se pasen
	 * 
	 * @param nombre del suceso
	 * @param ra restriccion de aforo
	 * @param descripcion de la obra 
	 * @param director de la obra
	 * @param autor de la obra
	 * @param duracion de la obra
	 * @param precios de la obra
	 */
	public Evento(String nombre, RestriccionAforo ra, String descripcion, String director, String autor, LocalTime duracion, ArrayList<Precio> precios) {
		super(nombre);
		this.descripcion = descripcion;
		this.director = director;
		this.autor = autor;
		this.duracion = duracion;
		this.precios = precios;
		if(ra!=null){
			anyadirRestriccionAforo(ra);
		} else {
			this.restriccionAforo=new RestriccionAforo(1,null);
		}
	}
	
	@Override
	public String toString() {
		return super.getNombre()+". Director: "+director+", autor: "+autor+" ("+duracion+") \n"+descripcion;
	}
	
	/**
	 * Este metodo añade una nueva representacion al array del evento
	 * 
	 * @param r representacion nueva
	 */
	public void anyadirRepresentacion(Representacion r) {
		r.setNombreEvento(this.getNombre());
		this.representaciones.add(r);
	}
	
	/**
	 * 
	 * Metodo que devuelve si el evento tiene alguna representacion activa a partir del momento actual
	 * 
	 * @return true si el evento sigue activo
	 */
	public boolean eventoActivo() {
		GregorianCalendar fecha=new GregorianCalendar();
		for(Representacion r:this.representaciones) {
			if(r.getFecha().compareTo(fecha)>=0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Devuelve los precios por zonas del evento
	 * 
	 * @return los precios
	 */
	public ArrayList<Precio> getPrecios() {
		return this.precios;
	}
	
	/**
	 * @return the duracion
	 */
	public LocalTime getDuracion() {
		return duracion;
	}
	
	/**
	 * Metodo para eliminar una representacion de un evento
	 * 
	 * @param fecha de la representacion a eliminar
	 */
	public void cancelarRepresentacion(GregorianCalendar fecha) {
		int i;
		for(i=0;i<this.representaciones.size();i++) {
			if(this.getRepresentaciones().get(i).getFecha().compareTo(fecha)==0) {
				this.getRepresentaciones().get(i).cancelar();
				break;
			}
		}
		this.representaciones.remove(this.getRepresentaciones().get(i));
	}
	
	/**
	 * Metodo para obtener las representaciones de un evento
	 * 
	 * @return las representaciones
	 */
	public ArrayList<Representacion> getRepresentaciones(){
		return this.representaciones;
	}

	/**
	 * @return the restriccionAforo
	 */
	public RestriccionAforo getRestriccionAforo() {
		return this.restriccionAforo;
	}
	
	/**
	 * añade una restriccion de aforo a todas las zonas
	 * se supone que se aplica para un evento para el que 
	 * aun no se han comprado entradas
	 * @param r restriccion de aforo
	 */
	public void anyadirRestriccionAforo(RestriccionAforo r) {
		this.restriccionAforo=r;
		GregorianCalendar fechaIni, fechaFin;
		
		for(Zona z : Theaterfy.getTheaterfy().getZonas()) {
			for(Representacion representacion : this.representaciones){
				fechaIni=representacion.getFecha();
				fechaIni.add(GregorianCalendar.HOUR_OF_DAY, -1);
				fechaFin=representacion.getFecha();
				fechaFin.add(GregorianCalendar.HOUR_OF_DAY, this.duracion.getHour());
				fechaFin.add(GregorianCalendar.MINUTE, this.duracion.getMinute());
				
				if(z.getClass()==ZonaNumerada.class) {
					((ZonaNumerada)z).anyadirRestriccionZonaNumerada(new Deshabilitacion("restriccion aforo", fechaIni, fechaFin), r);
				} else if(z.getClass()==ZonaMixta.class) {
					((ZonaMixta)z).anyadirRestriccionZonaMixta(new Deshabilitacion("restriccion aforo", fechaIni, fechaFin), r);
				}
			}
		}
	}
	
	/**
	 * @param p precio a añadir
	 */
	public void addPrecio(Precio p) {
		this.precios.add(p);
	}
	
	public String getAutor() {
		return this.autor;
	}
	
	public String getDirector() {
		return this.director;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
}
