package theaterfy.compras;

import java.util.GregorianCalendar;

import theaterfy.sucesos.Representacion;
import theaterfy.usuarios.UsuarioRegistrado;
import theaterfy.zona.Butaca;
import theaterfy.zona.Zona;

/**
 * Esta clase almacena las variables propias de Entrada, 
 * adem�s de una fecha en la que se reserv� la entrada
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Juli�n oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class EntradaReservada extends Entrada {
	private static final long serialVersionUID = 8475424402582106952L;
	
	private GregorianCalendar fechaReserva;

	/**
	 * Constructor, asocia los par�metros a las variables correspondientes
	 * @param representacion representaci�n a la que pertenece la entrada
	 * @param zona zona asociada a la entrada
	 * @param butaca butaca de la entrada, a null si es no numerada
	 * @param usuario usuario que ha comprado la entrada
	 * @param fechaReserva fecha en la que la entrada fue reservada
	 */
	public EntradaReservada(Representacion representacion, Zona zona, Butaca butaca, UsuarioRegistrado usuario, GregorianCalendar fechaReserva) {
		super(representacion, zona, butaca, usuario);
		this.fechaReserva = fechaReserva;
	}

	/**
	 * @return the fechaReserva
	 */
	public GregorianCalendar getFechaReserva() {
		return fechaReserva;
	}

	/**
	 * @param fechaReserva the fechaReserva to set
	 */
	public void setFechaReserva(GregorianCalendar fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	
	/**
	 * m�todo toString de Entrada
	 */
	@Override
	public String toString() {
		return "Entrada Reservada " + this.getIdTicket() + " de " + this.getUsuario().getNick() + " de la obra " + this.getRepresentacion().getNombreEvento();
	}

}
