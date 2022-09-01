package tests;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Test;

import theaterfy.Theaterfy;
import theaterfy.sucesos.EventoDanza;
import theaterfy.sucesos.Representacion;

public class EventoTest {

	@Test
	public void testEventoActivo() {
		
		EventoDanza danza=new EventoDanza("evento1", null,
				"Este es un evento de danza", "director", "autor",  LocalTime.of(2,30), 
				null, null, "orquesta", "director de oprquesta");
		
		GregorianCalendar fecha1=new GregorianCalendar();
		fecha1.add(GregorianCalendar.DAY_OF_MONTH, -2);
		Representacion r1=new Representacion("evento1",fecha1);
		danza.anyadirRepresentacion(r1);
		
		assertFalse(danza.eventoActivo());
		
		GregorianCalendar fecha2=new GregorianCalendar();
		fecha2.add(GregorianCalendar.DAY_OF_MONTH, 1);
		Representacion r2=new Representacion("evento1",fecha2);
		danza.anyadirRepresentacion(r2);
		
		assertTrue(danza.eventoActivo());
		
		Theaterfy.getTheaterfy().limpiar();
	}
	
	@Test
	public void testCancelarRepresentacion() {
		
		EventoDanza danza=new EventoDanza("evento1", null,
				"Este es un evento de danza", "director", "autor",  LocalTime.of(2,30), 
				null, null, "orquesta", "director de oprquesta");
		
		GregorianCalendar fecha1=new GregorianCalendar();
		fecha1.add(GregorianCalendar.DAY_OF_MONTH, 1);
		Representacion r1=new Representacion("evento1",fecha1);
		danza.anyadirRepresentacion(r1);
		
		GregorianCalendar fecha2=new GregorianCalendar();
		Representacion r2=new Representacion("evento1",fecha2);
		danza.anyadirRepresentacion(r2);
		
		ArrayList<Representacion> r=danza.getRepresentaciones();
		assertTrue(r.contains(r2));
		assertTrue(r.contains(r1));
		
		danza.cancelarRepresentacion(fecha2);
		assertFalse(r.contains(r2));
		assertTrue(r.contains(r1));
		
		Theaterfy.getTheaterfy().limpiar();
	}

}