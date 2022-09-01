package theaterfy.sucesos;

import theaterfy.compras.*;
import theaterfy.usuarios.*;
import theaterfy.zona.Butaca;
import theaterfy.zona.ZonaNumerada;
import java.util.ArrayList;
import java.util.GregorianCalendar;


/**
 * Esta clase sirve para almacenar cada una de las representaciones de un evento
 * 
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * 
 */
public class Representacion  implements java.io.Serializable{
	
	private static final long serialVersionUID = 3422667752580937921L;
	
	private GregorianCalendar fecha;
	private String nombreEvento;
	private ArrayList <Entrada> entradaDeRepresentacion=new ArrayList<>();
	private ArrayList <UsuarioRegistrado> listaEspera=new ArrayList<>();
	private ArrayList <Butaca> butacasCompradas=new ArrayList<>();

	/**
	 * Constructor, crea una representacion de un evento segun su fecha
	 * @param nombreEvento nombre del evento de la representación
	 * @param fecha fecha de la representacion
	 */
	public Representacion(String nombreEvento, GregorianCalendar fecha) {
		this.nombreEvento=nombreEvento;
		this.fecha = fecha;
	}
	
	/**
	 * Esta funcion cancela un evento, se vacian los arrays de lista de espera y de las entradas
	 * y se envia una notificacion
	 *
	 */
	public void cancelar() {
		for(UsuarioRegistrado u:this.listaEspera) {
			u.addNotificacion(new Notificacion("Cancelada una representacion del evento "+this.nombreEvento));
		}
		this.listaEspera.clear();
		for(Entrada e:this.entradaDeRepresentacion) {
			e.getUsuario().addNotificacion(new Notificacion("Cancelada una representacion del evento "+this.nombreEvento));
		}
		this.entradaDeRepresentacion.clear();
	}
	
	/**
	 * Esta funcion comprueba que se ingresa una fecha posterior a la original y pospone el evento 
	 * avisando a los usuarios
	 *
	 * @param fecha nueva de la representacion
	 */
	public void posponer(GregorianCalendar fecha) {
		if(this.fecha.compareTo(fecha)<0) {
			this.fecha=fecha;
			for(UsuarioRegistrado u:this.listaEspera) {
				u.addNotificacion(new Notificacion("Pospuesta una representacion del evento "+this.nombreEvento+
						" a "+this.fecha.get(GregorianCalendar.DAY_OF_MONTH)+"/"+
						+this.fecha.get(GregorianCalendar.MONTH)+"/"+
						+this.fecha.get(GregorianCalendar.YEAR)+" ("
						+this.fecha.get(GregorianCalendar.HOUR)+":"
						+this.fecha.get(GregorianCalendar.MINUTE)+")"));
			}
			for(Entrada e:this.entradaDeRepresentacion) {
				e.getUsuario().addNotificacion(new Notificacion("Pospuesta una representacion del evento "+this.nombreEvento+
						" a "+this.fecha.get(GregorianCalendar.DAY_OF_MONTH)+"/"+
						+this.fecha.get(GregorianCalendar.MONTH)+"/"+
						+this.fecha.get(GregorianCalendar.YEAR)+" ("
						+this.fecha.get(GregorianCalendar.HOUR)+":"
						+this.fecha.get(GregorianCalendar.MINUTE)+")"));
			}
		}
	}

	/**
	 * Metodo para obtener el nombre de una representacion
	 * 
	 * @return el nombreEvento
	 */
	public String getNombreEvento() {
		return nombreEvento;
	}

	/**
	 * Metodo para obtener la fecha de una representacion
	 * 
	 * @return la fecha
	 */
	public GregorianCalendar getFecha() {
		return fecha;
	}
	
	/**
	 * Metodo para añadir un usuario a la lista de espera
	 * 
	 * @param u el usuario a añadir a la lista de espera
	 */
	public void anyadirListaEspera(UsuarioRegistrado u) {
		this.listaEspera.add(u);
	}
	
	/**
	 * Metodo para añadir una entrada al array de entradas
	 * 
	 * @param e entrada a añadir
	 */
	public void anyadirEntradaDeRepresentacion(Entrada e) {
		this.entradaDeRepresentacion.add(e);
	}

	/**
	 * Metodo para obtener las entradas compradas
	 * 
	 * @return las entradaDeRepresentacion
	 */
	public ArrayList<Entrada> getEntradaDeRepresentacion() {
		return entradaDeRepresentacion;
	}

	/**
	 * Metodo para obtener el array de usuarios en lista de espera
	 * 
	 * @return la listaEspera
	 */
	public ArrayList<UsuarioRegistrado> getListaEspera() {
		return listaEspera;
	}
	
	/**
	 * Metodo para obtener el array de butacas compradas
	 * 
	 * @return las butacas compradas
	 */
	public ArrayList<Butaca> getButacasCompradas() {
		return this.butacasCompradas;
	}
	/**
	 * Metodo para añadir una butaca al array de butaca
	 * 
	 * @param b butaca a añadir
	 */
	public void anyadirButacasCompradas(Butaca b) {
		this.butacasCompradas.add(b);
	}

	/**
	 * Metodo para cancelar la reserva de una entrada
	 * 
	 * @param e entrada cuya reserva se cancela
	 */
	public void cancelarEntrada(Entrada e) {
		if(e.getZona().getClass()==ZonaNumerada.class) {
			this.butacasCompradas.remove(e.getButaca());
		}
		this.entradaDeRepresentacion.remove(e);
		for(UsuarioRegistrado u:this.listaEspera) {
			u.addNotificacion(new Notificacion("Se liberó una representacion del evento "+this.nombreEvento));
		}
	}

	/**
	 * Metodo para asignar el nombre a un evento
	 * 
	 * @param nombreEvento el nombre del evento a asignar
	 */
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	
}
