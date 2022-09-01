package theaterfy.usuarios;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import theaterfy.Theaterfy;
import theaterfy.compras.*;
import theaterfy.sucesos.Ciclo;
import theaterfy.sucesos.Evento;
import theaterfy.sucesos.Representacion;
import theaterfy.sucesos.Suceso;
import theaterfy.sucesos.Precio;
import theaterfy.zona.*;

import es.uam.eps.padsof.telecard.*;
import es.uam.eps.padsof.tickets.NonExistentFileException;
import es.uam.eps.padsof.tickets.TicketSystem;
import es.uam.eps.padsof.tickets.UnsupportedImageTypeException;

/**
 * Esta clase implementa las variables y funciones 
 * propias de un usuario registrado
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class UsuarioRegistrado extends Usuario {

	private static final long serialVersionUID = -7957522787585501042L;
	private String nick;
	private String contraseña;
	private ArrayList <Notificacion> notificaciones=new ArrayList<>();
	private ArrayList <Entrada> entradas=new ArrayList<>();
	private ArrayList <Abono> abonosComprados=new ArrayList<>();
	
	
	/**
	 * Constructor, asigna los parámetros a las variables correspondientes
	 * @param nick el nombre del usuario
	 * @param contraseña la contraseña del usuario
	 */
	public UsuarioRegistrado(String nick, String contraseña) {
		this.nick = nick;
		this.contraseña = contraseña;
	}

	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * @param nick the nick to set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * @return the contraseña
	 */
	public String getContraseña() {
		return contraseña;
	}

	/**
	 * @param contraseña the contraseña to set
	 */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	/**
	 * @return the notificaciones
	 */
	public ArrayList<Notificacion> getNotificaciones() {
		return notificaciones;
	}
	
	/**
	 * @param n notificacion a añadir
	 */
	public void addNotificacion(Notificacion n) {
		notificaciones.add(n);
	}
	
	
	/**
	 * @return the entradas
	 */
	public ArrayList<Entrada> getEntradas() {
		return entradas;
	}

	/**
	 * @return the abonosComprados
	 */
	public ArrayList<Abono> getAbonosComprados() {
		return abonosComprados;
	}

	/**
	 * efectua pago con tarjeta
	 * @param tarjeta número de la tarjeta que compra
	 * @param motivo qué se compra
	 * @param precio valor monetario
	 * @param cantidad cantidad de unidades a pagar
	 */
	private void pago(String tarjeta, String motivo, float precio, int cantidad)  throws InvalidCardNumberException, 
																						FailedInternetConnectionException, 
																						OrderRejectedException{
		for(int i=0;i<cantidad;i++) {
			TeleChargeAndPaySystem.charge(tarjeta, motivo, precio, true);
		}
	}
	
	/**
	 * genera num entradas para una zona no numerada, genera pdf y efectúa el pago
	 * @param num numero de entradas no numeradas
	 * @param zona zona donde se compra
	 * @param representacion obra de la entrada
	 * @param tarjeta número de la tarjeta que compra
	 * @return true si se ejecutó con éxito
	 */
	public boolean comprarEntrada(int num, ZonaNoNumerada zona, Representacion representacion, String tarjeta) throws NonExistentFileException, 
																														UnsupportedImageTypeException, 
																														InvalidCardNumberException, 
																														FailedInternetConnectionException, 
																														OrderRejectedException{
		int i=0;
		ArrayList <Entrada> compras=new ArrayList<>();
		GregorianCalendar fecha=new GregorianCalendar();
		if(fecha.compareTo(representacion.getFecha())>0) {
			System.out.println("Error de compra. La representación ya se ha realizado");
			return false;
		}
		
		for(Entrada e : representacion.getEntradaDeRepresentacion()) {
			if(e.getZona().getNombre().compareTo(zona.getNombre())==0) {
				i++;
			}
		}
		for(Suceso s : Theaterfy.getTheaterfy().getSucesos()) {
			if(s.getClass()!=Ciclo.class && s.getNombre().compareTo(representacion.getNombreEvento())==0) {
				if(num+i>Math.floor(zona.getAforo()*((Evento)s).getRestriccionAforo().getPorcentaje())) {
					System.out.println("Error de compra. La representación no posee entradas disponibles, añadiendo a la lista de espera."
							+ "se le avisará si queda alguna plaza libre");
					representacion.anyadirListaEspera(this);
					return false;
				}
			}
		}
		
		if(TeleChargeAndPaySystem.isValidCardNumber(tarjeta)==false) {
			throw new InvalidCardNumberException(tarjeta);
		}
		
		if(num>Theaterfy.getTheaterfy().getMaxCompraEntradas()) {
			System.out.println("Error de compra. Se intentan comprar más de "+Theaterfy.getTheaterfy().getMaxCompraEntradas()+" entradas");
			return false;
		}
		
		for(i=0;i<num;i++) {
			compras.add(new Entrada(representacion, zona, null, this));
			entradas.add(compras.get(i));
			representacion.anyadirEntradaDeRepresentacion(compras.get(i));
			TicketSystem.createTicket( compras.get(i),"./tmp/");
		}
		
		for(Abono a : this.abonosComprados) {
			if(a.getClass()==AbonoAnual.class && a.abonoUsado(representacion)==false) {
				fecha=(GregorianCalendar)((AbonoAnual)a).getInicio().clone();
				fecha.add(GregorianCalendar.YEAR, 1);
				if(representacion.getFecha().compareTo(((AbonoAnual)a).getInicio())>0 && representacion.getFecha().compareTo(fecha)<0) {
					num--;
					a.anyadirRepresentacionUsada(representacion);
					System.out.println(this.getNick()+" ha usado un abono anual");
				}
			} else if(a.getClass()==AbonoDeCiclo.class && a.abonoUsado(representacion)==false){
				if(((AbonoDeCiclo)a).getCiclo().perteneceRepresentacion(representacion)) {
					num--;
					a.anyadirRepresentacionUsada(representacion);
					System.out.println(this.getNick()+" ha usado un abono de ciclo");
				}
			}
		}
		
		for(Suceso s : Theaterfy.getTheaterfy().getSucesos()) {
			if(s.getClass()!=Ciclo.class && s.getNombre().compareTo(representacion.getNombreEvento())==0) {
				for(Precio p : ((Evento)s).getPrecios()) {
					if(p.getZona().getNombre().compareTo(zona.getNombre())==0) {
						this.pago(tarjeta, "Entrada de "+representacion.getNombreEvento(), p.getPrecio(), num);
					}
				}
			}
		}
		
		return true;
	}
	
	/**
	 * genera una entrada por butaca para una zona numerada, genera pdf y efectúa el pago
	 * @param butacas butacas de las entradas
	 * @param zona zona donde se compra
	 * @param representacion obra de la entrada
	 * @param tarjeta número de la tarjeta que compra
	 * @return true si se ejecutó con éxito
	 */
	public boolean comprarEntrada(Butaca[] butacas, ZonaNumerada zona, Representacion representacion, String tarjeta) throws NonExistentFileException, 
																																UnsupportedImageTypeException, 
																																InvalidCardNumberException, 
																																FailedInternetConnectionException, 
																																OrderRejectedException{
		int i;
		GregorianCalendar fecha, fechaIni=null, fechaFin=null;
		ArrayList <Entrada> compras=new ArrayList<>();
		GregorianCalendar fecha_actual=new GregorianCalendar();
		if(fecha_actual.compareTo(representacion.getFecha())>0 ) {
			System.out.println("Error de compra. La representación ya se ha realizado");
			return false;
		}
		if(TeleChargeAndPaySystem.isValidCardNumber(tarjeta)==false) {
			throw new InvalidCardNumberException(tarjeta);
		}
		
		if(butacas.length>Theaterfy.getTheaterfy().getMaxCompraEntradas()) {
			System.out.println("Error de compra. Se intentan comprar más de "+Theaterfy.getTheaterfy().getMaxCompraEntradas()+" entradas");
			return false;
		}
		
		for(Suceso s : Theaterfy.getTheaterfy().getSucesos()) {
			if(s.getClass()!=Ciclo.class && s.getNombre().compareTo(representacion.getNombreEvento())==0) {
				fechaIni=representacion.getFecha();
				fechaIni.add(GregorianCalendar.HOUR_OF_DAY, -1);
				fechaFin=representacion.getFecha();
				fechaFin.add(GregorianCalendar.HOUR_OF_DAY, ((Evento)s).getDuracion().getHour());
				fechaFin.add(GregorianCalendar.MINUTE, ((Evento)s).getDuracion().getMinute());
				
				if(butacas.length+representacion.getEntradaDeRepresentacion().size()>Math.floor(zona.getAforo()*((Evento)s).getRestriccionAforo().getPorcentaje())) {
					System.out.println("Error de compra. La representación no posee entradas disponibles, añadiendo a la lista de espera."
							+ "se le avisará si queda alguna plaza libre");
					representacion.anyadirListaEspera(this);
					return false;
				}
			}
		}
		
		for(Butaca b : butacas) {
			if(b.isDeshabilitada(fechaIni, fechaFin)) {
				return false;
			}
		}
		
		i=0;
		for(Butaca b : butacas) {
			compras.add(new Entrada(representacion, zona, b, this));
			entradas.add(compras.get(i));
			representacion.anyadirEntradaDeRepresentacion(compras.get(i));
			representacion.anyadirButacasCompradas(b);
			TicketSystem.createTicket( compras.get(i),"./tmp/");
			i++;
		}
		
		for(Abono a : this.abonosComprados) {
			if(a.getClass()==AbonoAnual.class && a.abonoUsado(representacion)==false) {
				fecha=(GregorianCalendar)((AbonoAnual)a).getInicio().clone();
				fecha.add(GregorianCalendar.YEAR, 1);
				
				if(representacion.getFecha().compareTo(((AbonoAnual)a).getInicio())>0 && representacion.getFecha().compareTo(fecha)<0) {
					i--;
					a.anyadirRepresentacionUsada(representacion);
					System.out.println(this.getNick()+" ha usado un abono anual");
				}
			} else if(a.getClass()==AbonoDeCiclo.class && a.abonoUsado(representacion)==false){
				if(((AbonoDeCiclo)a).getCiclo().perteneceRepresentacion(representacion)) {
					i--;
					a.anyadirRepresentacionUsada(representacion);
					System.out.println(this.getNick()+" ha usado un abono de ciclo");
				}
			}
		}
		
		for(Suceso s : Theaterfy.getTheaterfy().getSucesos()) {
			if(s.getClass()!=Ciclo.class && s.getNombre().compareTo(representacion.getNombreEvento())==0) {
				for(Precio p : ((Evento)s).getPrecios()) {
					if(p.getZona().getNombre().compareTo(zona.getNombre())==0) {
						this.pago(tarjeta, "Entrada de "+representacion.getNombreEvento(), p.getPrecio(), i);
					}
				}
			}
		}
		
		return true;
	}
	
	/**
	 * genera num entradas reservadas para una zona no numerada
	 * @param num numero de entradas no numeradas reservadas
	 * @param zona zona donde se reserva
	 * @param representacion obra de la entradas
	 * @return true si se ejecutó con éxito
	 */
	public boolean reservarEntrada(int num, ZonaNoNumerada zona, Representacion representacion) {
		int i=0;
		ArrayList <EntradaReservada> reservas=new ArrayList<>();
		GregorianCalendar fecha=new GregorianCalendar();
		fecha.add(GregorianCalendar.HOUR_OF_DAY, Theaterfy.getTheaterfy().getHorasSePermiteReserva());
		if(fecha.compareTo(representacion.getFecha())>0) {
			System.out.println("Error de reserva. La representación ya se ha realizado");
			return false;
		}
		
		for(Entrada e : representacion.getEntradaDeRepresentacion()) {
			if(e.getZona().getNombre().compareTo(zona.getNombre())==0) {
				i++;
			}
		}
		for(Suceso s : Theaterfy.getTheaterfy().getSucesos()) {
			if(s.getClass()!=Ciclo.class && s.getNombre().compareTo(representacion.getNombreEvento())==0) {
				if(num+i>Math.floor(zona.getAforo()*((Evento)s).getRestriccionAforo().getPorcentaje())) {
					System.out.println("Error de reserva. La representación no posee entradas disponibles, añadiendo a la lista de espera."
							+ "se le avisará si queda alguna plaza libre");
					representacion.anyadirListaEspera(this);
					return false;
				}
			}
		}
		
		if(num>Theaterfy.getTheaterfy().getMaxReservaEntradas()) {
			System.out.println("Error de compra. Se intentan comprar más de "+Theaterfy.getTheaterfy().getMaxCompraEntradas()+" entradas");
			return false;
		}

		for(i=0;i<num;i++) {
			reservas.add(new EntradaReservada(representacion, zona, null, this, new GregorianCalendar()));
			entradas.add(reservas.get(i));
			representacion.anyadirEntradaDeRepresentacion(reservas.get(i));
		}
		
		return true;
	}
	
	/**
	 * genera una entrada reservada por butaca para una zona numerada
	 * @param butacas butacas de las reservas
	 * @param zona zona donde se reserva
	 * @param representacion obra de la entrada
	 * @return true si se ejecutó con éxito
	 */
	public boolean reservarEntrada(Butaca[] butacas, ZonaNumerada zona, Representacion representacion){
		int i=0;
		ArrayList <EntradaReservada> reservas=new ArrayList<>();
		GregorianCalendar fechaIni=null, fechaFin=null;
		GregorianCalendar fecha=new GregorianCalendar();
		fecha.add(GregorianCalendar.HOUR_OF_DAY, Theaterfy.getTheaterfy().getHorasSePermiteReserva());
		if(fecha.compareTo(representacion.getFecha())>0) {
			System.out.println("Error de reserva. La representación ya se ha realizado");
			return false;
		}
		
		if(butacas.length>Theaterfy.getTheaterfy().getMaxReservaEntradas()) {
			System.out.println("Error de compra. Se intentan comprar más de "+Theaterfy.getTheaterfy().getMaxCompraEntradas()+" entradas");
			return false;
		}
		
		for(Suceso s : Theaterfy.getTheaterfy().getSucesos()) {
			if(s.getClass()!=Ciclo.class && s.getNombre().compareTo(representacion.getNombreEvento())==0) {
				fechaIni=representacion.getFecha();
				fechaIni.add(GregorianCalendar.HOUR_OF_DAY, -1);
				fechaFin=representacion.getFecha();
				fechaFin.add(GregorianCalendar.HOUR_OF_DAY, ((Evento)s).getDuracion().getHour());
				fechaFin.add(GregorianCalendar.MINUTE, ((Evento)s).getDuracion().getMinute());
				if(butacas.length+representacion.getEntradaDeRepresentacion().size()>Math.floor(zona.getAforo()*((Evento)s).getRestriccionAforo().getPorcentaje())) {
					System.out.println("Error de reserva. La representación no posee entradas disponibles, añadiendo a la lista de espera."
							+ "se le avisará si queda alguna plaza libre");
					representacion.anyadirListaEspera(this);
					return false;
				}
			}
		}
		
		for(Butaca b : butacas) {
			if(b.isDeshabilitada(fechaIni, fechaFin)) {
				return false;
			}
		}
		
		i=0;
		for(Butaca b : butacas) {
			reservas.add(new EntradaReservada(representacion, zona, b, this, new GregorianCalendar()));
			entradas.add(reservas.get(i));
			representacion.anyadirEntradaDeRepresentacion(reservas.get(i));
			representacion.anyadirButacasCompradas(b);
			i++;
		}
		
		return true;
	}
	
	/**
	 * genera un abono de ciclo y efectúa el pago
	 * @param zona zona asociada al abono
	 * @param ciclo ciclo asociado al AbonoDeCiclo
	 * @param tarjeta número de la tarjeta que compra
	 */
	public void comprarAbono(Zona zona, Ciclo ciclo, String tarjeta) throws InvalidCardNumberException, 
																								FailedInternetConnectionException, 
																								OrderRejectedException{
		if(TeleChargeAndPaySystem.isValidCardNumber(tarjeta)==false) {
			throw new InvalidCardNumberException(tarjeta);
		}
		
		abonosComprados.add(new AbonoDeCiclo(ciclo.getPrecioTotal(zona),zona,Theaterfy.getTheaterfy().getPrecioAbonoCiclo().get(ciclo),ciclo));
		this.pago(tarjeta, "Abono de ciclo", (float)abonosComprados.get(abonosComprados.size()-1).getPrecio(), 1);
		
	}
	
	/**
	 * genera un abono anual y efectúa el pago
	 * @param zona zona asociada al AbonoAnual
	 * @param tarjeta número de la tarjeta que compra
	 */
	public void comprarAbono(Zona zona, String tarjeta) throws InvalidCardNumberException, 
																FailedInternetConnectionException, 
																OrderRejectedException{
		
		if(TeleChargeAndPaySystem.isValidCardNumber(tarjeta)==false) {
			throw new InvalidCardNumberException(tarjeta);
		}
		for(Precio p : Theaterfy.getTheaterfy().getPreciosAbonoAnual()) {
			if(p.getZona().getNombre().compareTo(zona.getNombre())==0) {
				abonosComprados.add(new AbonoAnual(p.getPrecio(),zona,new GregorianCalendar()));
				this.pago(tarjeta, "Abono anual", (float)p.getPrecio(), 1);
				return;
			}
		}	
	}
	
	/**
	 * confirma un array de entradas reservadas para una misma representación
	 * @param entradasReservadas entradas a confirmar
	 * @param tarjeta número de tarjeta del pago
	 * @return false si se pasó el plazo de confirmación de la reserva
	 */
	public boolean confirmarReserva(ArrayList<Entrada> entradasReservadas, String tarjeta) throws NonExistentFileException, 
																									UnsupportedImageTypeException, 
																									InvalidCardNumberException, 
																									FailedInternetConnectionException, 
																									OrderRejectedException{
		int i=entradasReservadas.size();
		Representacion representacion=entradasReservadas.get(0).getRepresentacion();
		Zona zona=entradasReservadas.get(0).getZona();
		GregorianCalendar fecha=((EntradaReservada)entradasReservadas.get(0)).getFechaReserva();
		fecha.add(GregorianCalendar.HOUR_OF_DAY, Theaterfy.getTheaterfy().getHorasConfirmarReserva());
		
		for(Entrada entrada : entradasReservadas) {		//si alguna entrada no es de la misma representación no se puede confirmar la reserva
			if(entrada.getRepresentacion()!=entradasReservadas.get(0).getRepresentacion()) {
				return false;
			}
		}
		
		if(TeleChargeAndPaySystem.isValidCardNumber(tarjeta)==false) {
			throw new InvalidCardNumberException(tarjeta);
		}
		if(fecha.compareTo(new GregorianCalendar())<0) {
			for(Entrada entrada : entradasReservadas) {
				entrada.getRepresentacion().cancelarEntrada(entrada);
			}
			this.entradas.removeAll(entradasReservadas);
			return false;
		}
		
		entradas.removeAll(entradasReservadas);
		for(Entrada entrada: entradasReservadas) {
			entrada.getRepresentacion().cancelarEntrada(entrada);
			entradas.add(new Entrada(entrada.getRepresentacion(),entrada.getZona(), entrada.getButaca(), this));
			entradas.get(entradas.size()-1).getRepresentacion().anyadirEntradaDeRepresentacion(entradas.get(entradas.size()-1));
			if(entradas.get(entradas.size()-1).getZona().getClass()==ZonaNumerada.class) {
				entradas.get(entradas.size()-1).getRepresentacion().anyadirButacasCompradas(entradas.get(entradas.size()-1).getButaca());
			}
			System.out.println("Confirmada entrada de "+entradas.get(entradas.size()-1).getEventName());
			TicketSystem.createTicket( entradas.get(entradas.size()-1),"./tmp/");
		}
		
		for(Abono a : this.abonosComprados) {
			if(a.getClass()==AbonoAnual.class && a.abonoUsado(representacion)==false) {
				fecha=(GregorianCalendar)((AbonoAnual)a).getInicio().clone();
				fecha.add(GregorianCalendar.YEAR, 1);
				if(representacion.getFecha().compareTo(((AbonoAnual)a).getInicio())>0 && representacion.getFecha().compareTo(fecha)<0) {
					i--;
					a.anyadirRepresentacionUsada(representacion);
					System.out.println(this.getNick()+" ha usado un abono anual");
				}
			} else if(a.getClass()==AbonoDeCiclo.class && a.abonoUsado(representacion)==false){
				if(((AbonoDeCiclo)a).getCiclo().perteneceRepresentacion(representacion)) {
					i--;
					a.anyadirRepresentacionUsada(representacion);
					System.out.println(this.getNick()+" ha usado un abono de ciclo");
				}
			}
		}
		
		for(Suceso s : Theaterfy.getTheaterfy().getSucesos()) {
			if(s.getClass()!=Ciclo.class && s.getNombre().compareTo(representacion.getNombreEvento())==0) {
				for(Precio p : ((Evento)s).getPrecios()) {
					if(p.getZona().getNombre().compareTo(zona.getNombre())==0) {
						this.pago(tarjeta, "Entrada de "+representacion.getNombreEvento(), p.getPrecio(), i);
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * cancela un array de reservas, eliminándolas de UsuarioRegistrado y de Representación
	 * @param entradas a cancelar
	 */
	public void cancelarReserva(ArrayList <Entrada> entradas) {
		for(Entrada entrada : entradas) {
			entrada.getRepresentacion().cancelarEntrada(entrada);
			this.entradas.remove(entrada);
			System.out.println("Cancelada entrada de "+entrada.getEventName());
		}
	}
	
	/**
	 * Elimina todas las reservas de representaciones que ya se hayan realizado
	 */
	public void limpiarReservas() {
		ArrayList<Entrada> entradas=new ArrayList<>();
		GregorianCalendar fechaHoy=new GregorianCalendar();
		for(Entrada e : this.entradas) {
			if(e.getClass()==EntradaReservada.class && e.getRepresentacion().getFecha().compareTo(fechaHoy)<0) {
				entradas.add(e);
			}
		}
		this.cancelarReserva(entradas);
	}
	
	/**
	 * muestra las notificaciones del usuario
	 */
	public void mostrarNotificaciones() {
		int i=0;
		System.out.println("Notificaciones de " + nick);
		for(Notificacion n : notificaciones) {
			i++;
			System.out.println("\n------------------\n" + i + "- "+n);
		}
	}
	
	/**
	 * muestra las entradas del usuario
	 */
	public void mostrarEntradas() {
		int i=1;
		System.out.println("Entradas de " + nick);
		for(Entrada e : entradas) {
			if(e.getClass()==EntradaReservada.class){
				System.out.println("\n------------------\n" + i + "- "+e);
				i++;
			}
		}
		for(Entrada e : entradas) {
			if(e.getClass()==Entrada.class){
				System.out.println("\n------------------\n" + i + "- "+e);
				i++;
			}
		}
	}
	
	/**
	 * 
	 * @param n notificacion a ser añadida
	 */
	public void anyadirNotificacion(Notificacion n) {
		notificaciones.add(n);
	}
}
