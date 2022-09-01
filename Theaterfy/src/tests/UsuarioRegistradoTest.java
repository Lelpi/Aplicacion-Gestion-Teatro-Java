package tests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Test;

import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.tickets.NonExistentFileException;
import es.uam.eps.padsof.tickets.UnsupportedImageTypeException;

import org.junit.Before;

import theaterfy.Theaterfy;
import theaterfy.compras.AbonoAnual;
import theaterfy.compras.AbonoDeCiclo;
import theaterfy.compras.Entrada;
import theaterfy.compras.EntradaReservada;
import theaterfy.usuarios.UsuarioRegistrado;
import theaterfy.zona.*;
import theaterfy.sucesos.Ciclo;
import theaterfy.sucesos.EventoDanza;
import theaterfy.sucesos.Precio;
import theaterfy.sucesos.Representacion;
import theaterfy.sucesos.Suceso;

public class UsuarioRegistradoTest {
	private UsuarioRegistrado u1;
	private ZonaNoNumerada zonaNoNumerada;
	
	@Before
	public void inicio() {
		u1=new UsuarioRegistrado("usuario1", "usuario1");
		zonaNoNumerada=new ZonaNoNumerada("noNumerada", 10, 5*5);
	}
	/*
	@Test
	public void testComprarEntrada() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testReservarEntrada() {
		fail("Not yet implemented");
	}
	*/
	@Test
	public void testComprarAbono() throws InvalidCardNumberException, 
	FailedInternetConnectionException, 
	OrderRejectedException{
		//comprar abono anual
		u1.comprarAbono(zonaNoNumerada, "1234567890123456");
		AbonoAnual abonoAnual=(AbonoAnual)u1.getAbonosComprados().get(0);
		assertEquals(10f,abonoAnual.getPrecio(),0);
		assertEquals(10f,Theaterfy.getTheaterfy().getPreciosAbonoAnual().get(0).getPrecio(),0);
		
		//comprar AbonoCiclo
		ArrayList <Suceso> subciclos=new ArrayList<>();
		ArrayList <Precio> precios=new ArrayList<>();
		precios.add(new Precio(12,zonaNoNumerada));
		EventoDanza e1 = new EventoDanza("El cascanueces", null,"Evento de danza el cascanueces",
				"Alberto", "Paco", LocalTime.of(1,30), precios, null, "Orquesta Sinfonica", "James T.");
		subciclos.add((Suceso)e1);
		Ciclo ciclo=new Ciclo("ciclo", subciclos);
		Theaterfy.getTheaterfy().anyadirPrecioAbonoCiclo(ciclo, (float)0.7);
		u1.comprarAbono(zonaNoNumerada, ciclo, "1234567890123456");
		AbonoDeCiclo abonoDeCiclo=(AbonoDeCiclo)u1.getAbonosComprados().get(1);
		assertEquals(12*0.7f,abonoDeCiclo.getPrecio(),0.1);
		Theaterfy.getTheaterfy().limpiar();
	}
	
	@Test
	public void testConfirmarReserva() throws InvalidCardNumberException, 
	FailedInternetConnectionException, 
	OrderRejectedException, NonExistentFileException, UnsupportedImageTypeException{
		ArrayList <Entrada> entradasCompradas;
		
		GregorianCalendar fecha_actuacion=new GregorianCalendar();
		GregorianCalendar fecha_reserva=new GregorianCalendar();
		fecha_actuacion.add(GregorianCalendar.HOUR_OF_DAY,Theaterfy.getTheaterfy().getHorasSePermiteReserva()+1);
		fecha_reserva.add(GregorianCalendar.HOUR_OF_DAY, -Theaterfy.getTheaterfy().getHorasConfirmarReserva()-1);//se reservó hace más del tiempo permitido
		
		Representacion r1=new Representacion("actuacion", fecha_actuacion);
		u1.reservarEntrada(1, zonaNoNumerada, r1);
		entradasCompradas=u1.getEntradas();
		((EntradaReservada)entradasCompradas.get(0)).setFechaReserva(fecha_reserva);
		assertFalse(u1.confirmarReserva(entradasCompradas, "1234567890123456"));
		u1.reservarEntrada(1,zonaNoNumerada,r1);
		entradasCompradas=u1.getEntradas();
		assertTrue(u1.confirmarReserva(entradasCompradas, "1234567890123456"));
		Theaterfy.getTheaterfy().limpiar();
	}
	
	@Test
	public void testCancelarReserva() {
		ArrayList <Entrada> entradasCompradas;
		GregorianCalendar fecha_actuacion=new GregorianCalendar();
		Representacion r1=new Representacion("actuacion", fecha_actuacion);
		u1.reservarEntrada(1, zonaNoNumerada, r1);
		entradasCompradas=u1.getEntradas();
		u1.cancelarReserva(entradasCompradas);
		assertTrue(u1.getEntradas().isEmpty());
		Theaterfy.getTheaterfy().limpiar();
	}

}
