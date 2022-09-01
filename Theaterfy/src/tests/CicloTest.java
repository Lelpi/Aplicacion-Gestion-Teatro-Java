package tests;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Test;

import theaterfy.Theaterfy;
import theaterfy.sucesos.Ciclo;
import theaterfy.sucesos.Suceso;
import theaterfy.zona.ZonaNoNumerada;
import theaterfy.sucesos.EventoDanza;
import theaterfy.sucesos.EventoTeatro;
import theaterfy.sucesos.Precio;
import theaterfy.sucesos.Representacion;

public class CicloTest {

	@Test
	public void testGetPrecioTotal() {
		
		ZonaNoNumerada zona=new ZonaNoNumerada("zona", 1, 5);
		ArrayList<Precio> precios=new ArrayList<>();
		precios.add(new Precio(15,zona));
		
		EventoDanza danza=new EventoDanza("evento1", null,
				"Este es un evento de danza", "director", "autor",  LocalTime.of(2,30), 
				precios, null, "orquesta", "director de oprquesta");
		
		ArrayList<Suceso> evento=new ArrayList<>();
		evento.add(danza);
		Ciclo c2= new Ciclo("ciclo2",evento);
		
		EventoTeatro teatro = new EventoTeatro("evento2", null,
				"Este es un evento de teatro", "director", "autor", LocalTime.of(2,30),
				precios, null);
		
		ArrayList<Suceso> subciclos=new ArrayList<>();
		subciclos.add(teatro);
		subciclos.add(c2);
		Ciclo c1= new Ciclo("ciclo1", subciclos);
		
		assertEquals(30f,c1.getPrecioTotal(zona),0);
		Theaterfy.getTheaterfy().limpiar();
		
	}
	
	@Test
	public void testPerteneceRepresentacion() {
		
		Representacion r1=new Representacion("actuacion", new GregorianCalendar());
		Representacion r2=new Representacion("noactuacion", new GregorianCalendar());
		
		EventoDanza danza=new EventoDanza("evento1", null,
				"Este es un evento de danza", "director", "autor",  LocalTime.of(2,30), 
				null, null, "orquesta", "director de oprquesta");
		
		ArrayList<Suceso> evento=new ArrayList<>();
		evento.add(danza);
		Ciclo c2= new Ciclo("ciclo2",evento);
		
		EventoTeatro teatro = new EventoTeatro("evento2", null,
				"Este es un evento de teatro", "director", "autor", LocalTime.of(2,30),
				null, null);
		
		ArrayList<Suceso> subciclos=new ArrayList<>();
		subciclos.add(teatro);
		subciclos.add(c2);
		Ciclo c1= new Ciclo("ciclo1", subciclos);
		
		assertFalse(c1.perteneceRepresentacion(r1));
		danza.anyadirRepresentacion(r1);
		assertTrue(c1.perteneceRepresentacion(r1));
		assertFalse(c1.perteneceRepresentacion(r2));
		Theaterfy.getTheaterfy().limpiar();
	}

}
