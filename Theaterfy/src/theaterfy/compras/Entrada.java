package theaterfy.compras;

import java.util.GregorianCalendar;

import es.uam.eps.padsof.tickets.ITicketInfo;
import theaterfy.Theaterfy;
import theaterfy.sucesos.Evento;
import theaterfy.sucesos.Precio;
import theaterfy.sucesos.Representacion;
import theaterfy.sucesos.Suceso;
import theaterfy.usuarios.UsuarioRegistrado;
import theaterfy.zona.*;

/**
 * Esta clase almacena los campos propios de una 
 * entrada, si la zona es ZonaNoNumerada butaca==null
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class Entrada implements ITicketInfo, java.io.Serializable{

	private static final long serialVersionUID = -7125036616203135642L;
	private static int idGlobal=1;
	private int id;
	private Zona zona;
	private Butaca butaca;
	private UsuarioRegistrado usuario;
	private Representacion representacion;
	
	/**
	 * Constructor, asocia los parámetros a las variables correspondientes
	 * @param representacion representacion a la que pertenece la entrada
	 * @param zona zona asociada a la entrada
	 * @param butaca butaca de la entrada, a null si es no numerada
	 * @param usuario usuario que ha comprado la entrada
	 */
	public Entrada(Representacion representacion, Zona zona, Butaca butaca, UsuarioRegistrado usuario) {
		this.representacion=representacion;
		id=idGlobal;
		idGlobal++;
		this.zona = zona;
		this.butaca=butaca;
		this.usuario=usuario;
	}

	/**
	 * @return the usuario
	 */
	public UsuarioRegistrado getUsuario() {
		return usuario;
	}

	/**
	 * @return the id
	 */
	public int getIdTicket() {
		return id;
	}

	/**
	 * método toString de Entrada
	 */
	@Override
	public String toString() {
		return "Entrada " + id + " de " + usuario.getNick() + " de la obra " + representacion.getNombreEvento();
	}

	/**
	 * @return the representacion
	 */
	public Representacion getRepresentacion() {
		return representacion;
	}

	/**
	 * @return the zona
	 */
	public Zona getZona() {
		return zona;
	}

	/**
	 * @return the butaca
	 */
	public Butaca getButaca() {
		return butaca;
	}
	
	/**
	 * @return el nombre del teatro
	 */
	public String getTheaterName () { 
		return "Teatro de Madriz"; 
	}
	
	/** 
	 * @return el nombre del evento
	 */
    public String getEventName () { 
    	return this.representacion.getNombreEvento(); 
    }
    
    /**
     *@return string con la fecha y hora de la representación 
     */
    public String getEventDate () {
    	return this.representacion.getFecha().get(GregorianCalendar.DAY_OF_MONTH)+"/"+
    			(this.representacion.getFecha().get(GregorianCalendar.MONTH)+1)+"/"+
    			this.representacion.getFecha().get(GregorianCalendar.YEAR)+" "+
    			this.representacion.getFecha().get(GregorianCalendar.HOUR_OF_DAY)+":"+
    			this.representacion.getFecha().get(GregorianCalendar.MINUTE);
    }
    
    /**
     * @return string con información de la localización
     * de la entrada
     */
    public String getSeatNumber () {
    	if(zona.getClass()==ZonaNoNumerada.class) {
    		return "";
    	}
    	return "zona "+zona.getNombre()+", fila "+butaca.getX()+", columna "+butaca.getY();
    }
    
    /**
     * ruta de la localización de la imagen del pdf de la entrada
     */
    public String getPicture () {
    	return "./resources/teatro.jpg"; 
    }
    
    /**
     * 
     * @return el evento de la entrada
     */
    public Evento getEvento() {
    	for(Suceso s : Theaterfy.getTheaterfy().getSucesos()) {
    		if(s.getNombre().equals(this.representacion.getNombreEvento())) {
    			return (Evento)s;
    		}
    	}
    	return null;
    }
    
    /**
     * @return el Precio de la entrada
     */
    public Precio getPrecio() {
    	Evento e=this.getEvento();
    	if(e!=null) {
    		for(Precio p : e.getPrecios()) {
    			if(p.getZona().equals(this.getZona())) {
    				return p;
    			}
    		}
    	}
    	return null;
    }
}
