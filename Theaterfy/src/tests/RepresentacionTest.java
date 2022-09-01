package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Test;

import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.tickets.NonExistentFileException;
import es.uam.eps.padsof.tickets.UnsupportedImageTypeException;
import theaterfy.Theaterfy;
import theaterfy.sucesos.Representacion;
import theaterfy.usuarios.Notificacion;
import theaterfy.usuarios.UsuarioRegistrado;
import theaterfy.zona.Butaca;
import theaterfy.zona.ZonaNoNumerada;
import theaterfy.zona.ZonaNumerada;

public class RepresentacionTest {

	@Test
	public void testCancelar() throws InvalidCardNumberException, 
	FailedInternetConnectionException, 
	OrderRejectedException, NonExistentFileException, UnsupportedImageTypeException{
		Representacion r1=new Representacion("evento", new GregorianCalendar());

		UsuarioRegistrado usuarioEspera=new UsuarioRegistrado("nick1", "contra1");
		r1.anyadirListaEspera(usuarioEspera);

		ZonaNoNumerada zona=new ZonaNoNumerada("zona", 40, 100);
		UsuarioRegistrado usuarioCompra=new UsuarioRegistrado("nick2","contra2");
		usuarioCompra.comprarEntrada(1, zona, r1, "1234567890123456");
		
		r1.cancelar();
		
		ArrayList<Notificacion> notificacionEspera=usuarioEspera.getNotificaciones();
		for(Notificacion n:notificacionEspera) {
			if(n.getMensaje().compareTo("Cancelada una representacion del evento "+r1.getNombreEvento())!=0) {
				fail("No, deberian ser iguales");
			}
		}
		
		ArrayList<Notificacion> notificacionCompra=usuarioCompra.getNotificaciones();
		for(Notificacion n:notificacionCompra) {
			if(n.getMensaje().compareTo("Cancelada una representacion del evento "+r1.getNombreEvento())!=0) {
				fail("No, deberian ser iguales");
			}
		}
		
		assertTrue(r1.getEntradaDeRepresentacion().isEmpty());
		assertTrue(r1.getListaEspera().isEmpty());

		Theaterfy.getTheaterfy().limpiar();
	}

	@Test
	public void testPosponer() throws InvalidCardNumberException, 
	FailedInternetConnectionException, 
	OrderRejectedException, NonExistentFileException, UnsupportedImageTypeException{
		Representacion r1=new Representacion("evento", new GregorianCalendar());
		
		UsuarioRegistrado usuarioEspera=new UsuarioRegistrado("nick1", "contra1");
		r1.anyadirListaEspera(usuarioEspera);
		
		ZonaNoNumerada zona=new ZonaNoNumerada("zona", 40, 100);
		UsuarioRegistrado usuarioCompra=new UsuarioRegistrado("nick2","contra2");
		usuarioCompra.comprarEntrada(1, zona, r1, "1234567890123456");
		
		GregorianCalendar fechaNueva=new GregorianCalendar();
		fechaNueva.add(GregorianCalendar.DAY_OF_MONTH, 1);
		r1.posponer(fechaNueva);
		
		ArrayList<Notificacion> notificacionEspera=usuarioEspera.getNotificaciones();
		for(Notificacion n:notificacionEspera) {
			assertTrue(n.getMensaje().compareTo("Pospuesta una representacion del evento "+r1.getNombreEvento()+
					" a "+r1.getFecha().get(GregorianCalendar.DAY_OF_MONTH)+"/"+
					+r1.getFecha().get(GregorianCalendar.MONTH)+"/"+
					+r1.getFecha().get(GregorianCalendar.YEAR)+" ("
					+r1.getFecha().get(GregorianCalendar.HOUR)+":"
					+r1.getFecha().get(GregorianCalendar.MINUTE)+")")==0);
		}
		
		ArrayList<Notificacion> notificacionCompra=usuarioCompra.getNotificaciones();
		for(Notificacion n:notificacionCompra) {
			assertTrue(n.getMensaje().compareTo("Pospuesta una representacion del evento "+r1.getNombreEvento()+
					" a "+r1.getFecha().get(GregorianCalendar.DAY_OF_MONTH)+"/"+
					+r1.getFecha().get(GregorianCalendar.MONTH)+"/"+
					+r1.getFecha().get(GregorianCalendar.YEAR)+" ("
					+r1.getFecha().get(GregorianCalendar.HOUR)+":"
					+r1.getFecha().get(GregorianCalendar.MINUTE)+")")==0);
		}
		
		assertFalse(r1.getEntradaDeRepresentacion().isEmpty());
		assertFalse(r1.getListaEspera().isEmpty());
		
		Theaterfy.getTheaterfy().limpiar();
	}
	
	@Test
	public void testCancelarEntrada() {
		GregorianCalendar fecha=new GregorianCalendar();
		fecha.add(GregorianCalendar.DAY_OF_MONTH, 2);
		Representacion r1=new Representacion("evento", fecha);
		
		UsuarioRegistrado usuarioEspera=new UsuarioRegistrado("nick1", "contra1");
		r1.anyadirListaEspera(usuarioEspera);
		
		ZonaNoNumerada zona=new ZonaNoNumerada("zona", 40, 100);
		UsuarioRegistrado usuarioReserva=new UsuarioRegistrado("nick2","contra2");
		usuarioReserva.reservarEntrada(1, zona, r1);
		
		assertTrue(r1.getEntradaDeRepresentacion().contains(usuarioReserva.getEntradas().get(0)));
		r1.cancelarEntrada(usuarioReserva.getEntradas().get(0));
		assertFalse(r1.getEntradaDeRepresentacion().contains(usuarioReserva.getEntradas().get(0)));
		
		ArrayList<Notificacion> notificacionEspera=usuarioEspera.getNotificaciones();
		for(Notificacion n:notificacionEspera) {
			if(n.getMensaje().compareTo("Se liberó una representacion del evento "+r1.getNombreEvento())!=0) {
				fail("No, deberian ser iguales");
			}
		}
		
		ZonaNumerada zonaN=new ZonaNumerada("zonaN",100,5,5);
		UsuarioRegistrado usuarioReserva2=new UsuarioRegistrado("nick3","contra3");
		Butaca b[]= {new Butaca(0,0,null), new Butaca(0,1,null)};
		usuarioReserva2.reservarEntrada(b, zonaN, r1);
		
		assertTrue(r1.getButacasCompradas().contains(usuarioReserva2.getEntradas().get(0).getButaca()));
		assertTrue(r1.getButacasCompradas().contains(usuarioReserva2.getEntradas().get(1).getButaca()));
		r1.cancelarEntrada(usuarioReserva2.getEntradas().get(0));
		assertFalse(r1.getButacasCompradas().contains(usuarioReserva2.getEntradas().get(0).getButaca()));
		assertTrue(r1.getButacasCompradas().contains(usuarioReserva2.getEntradas().get(1).getButaca()));
		
		Theaterfy.getTheaterfy().limpiar();
	}
}