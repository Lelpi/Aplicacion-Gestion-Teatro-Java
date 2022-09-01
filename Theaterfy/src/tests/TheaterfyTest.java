package tests;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;

import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.tickets.NonExistentFileException;
import es.uam.eps.padsof.tickets.UnsupportedImageTypeException;
import theaterfy.Theaterfy;
import theaterfy.sucesos.Evento;
import theaterfy.sucesos.EventoDanza;
import theaterfy.sucesos.EventoTeatro;
import theaterfy.sucesos.Precio;
import theaterfy.sucesos.Representacion;
import theaterfy.sucesos.RestriccionAforo;
import theaterfy.usuarios.UsuarioRegistrado;
import theaterfy.zona.Butaca;
import theaterfy.zona.ZonaNoNumerada;
import theaterfy.zona.ZonaNumerada;

public class TheaterfyTest {

	private ZonaNoNumerada znn1;
	private ZonaNumerada zn1;
	private LocalTime t1;
	private ArrayList<Precio> precios = new ArrayList<>();
	ArrayList<String> bailarines = new ArrayList<>();
	private ArrayList<String> actores = new ArrayList<>();
	private Representacion r;
	private GregorianCalendar fecha;

	
	
	@Before
	public void setUp() {
		String bailarin = "Alfredo";
		String actor = "Leonardo DiCaprio";
		znn1 = new ZonaNoNumerada("Palco A", 30, 10);
		zn1 = new ZonaNumerada("Patio de butacas", 18, 25, 30);
		t1 = LocalTime.of(2, 15, 0);
		precios.add(new Precio(30, znn1));
		precios.add(new Precio(18, zn1));
		bailarines.add(bailarin);
		actores.add(actor);
		fecha = new GregorianCalendar();
		fecha.add(GregorianCalendar.MONTH, 1);
		r = new Representacion("Representacion", fecha);
	}
	
	@Test
    public void testAlgunEventoActivo() {
        EventoDanza e = new EventoDanza("El cascanueces", null,"Evento de danza el cascanueces",
                "Alberto", "Paco", t1, precios, bailarines.toString(), "Orquesta Sinfonica", "James T.");
        
        GregorianCalendar fecha2=new GregorianCalendar();
        fecha2.add(GregorianCalendar.DAY_OF_MONTH, -1);
        Representacion r2=new Representacion("evento1",fecha2);
        e.anyadirRepresentacion(r2);
        
        Theaterfy.getTheaterfy().anyadirSuceso(e);

        assertFalse(Theaterfy.getTheaterfy().algunEventoActivo());
        
        e.anyadirRepresentacion(r);
        
        assertTrue(Theaterfy.getTheaterfy().algunEventoActivo());
        
        Theaterfy.getTheaterfy().limpiar();
    }
	
	@Test
	public void testBuscarEventos() {
		ArrayList<Evento> eventosTest;
		boolean flag = false;
		EventoDanza e1 = new EventoDanza("El cascanueces", null,"Evento de danza el cascanueces",
				"Alberto", "Paco", t1, precios, bailarines.toString(), "Orquesta Sinfonica", "James T.");
		EventoTeatro e2 = new EventoTeatro("Mamma mia", null,"Evento de teatro basado en el musical de Mamma mia",
				"Alberto", "Paco", t1, precios, actores.toString());
		e2.anyadirRepresentacion(r);
		//Añadimos dos eventos
		Theaterfy.getTheaterfy().anyadirSuceso(e1);
		Theaterfy.getTheaterfy().anyadirSuceso(e2);
		//Comprobamos con filtro teatro
		eventosTest = Theaterfy.getTheaterfy().buscarEventos("cascanueces", 1);
		assertTrue(eventosTest.isEmpty());
		
		eventosTest = Theaterfy.getTheaterfy().buscarEventos("cascanueces", 0);
		for(Evento e : eventosTest) {
			if(e.getNombre().compareTo(e1.getNombre())==0)
				flag = true;
		}
		assertTrue(flag);
		/*Los casos 0, 1 y 2 son iguales por lo que suponemos que el resultado sera igual*/
		
		/*Probamos el filtro para proximos eventos*/
		eventosTest = Theaterfy.getTheaterfy().buscarEventos("Mamma mia", 3);
		assertFalse(eventosTest.isEmpty());
		
		Theaterfy.getTheaterfy().limpiar();
	}

	@Test
	public void testModificarSuceso() {
		ArrayList<Evento> eventosTest;
		EventoDanza e1 = new EventoDanza("El cascanueces", null,"Evento de danza el cascanueces",
				"Alberto", "Paco", t1, precios, bailarines.toString(), "Orquesta Sinfonica", "James T.");
		EventoTeatro e2 = new EventoTeatro("Mamma mia", null,"Evento de teatro basado en el musical de Mamma mia",
				"Alberto", "Paco", t1, precios, actores.toString());
		EventoTeatro e3 = new EventoTeatro("Mamma mia", null,"Evento de teatro basado en el musical de Mamma mia",
				"Alberto", "Paco", t1, precios, actores.toString());

		e2.anyadirRepresentacion(r);
		//Añadimos dos eventos
		Theaterfy.getTheaterfy().anyadirSuceso(e1);
		Theaterfy.getTheaterfy().anyadirSuceso(e2);
		
		eventosTest = Theaterfy.getTheaterfy().buscarEventos("Mamma mia", 3);
		assertFalse(eventosTest.isEmpty());

		Theaterfy.getTheaterfy().modificarSuceso(e3);
		eventosTest = Theaterfy.getTheaterfy().buscarEventos("Mamma mia", 3);
		
		
		assertTrue(eventosTest.isEmpty());
		
		Theaterfy.getTheaterfy().limpiar();
	}
	
	@Test
    public void testLogin() {
        Theaterfy.getTheaterfy().registrarse("usuario1", "usuario1");
        Theaterfy.getTheaterfy().registrarse("usuario2", "usuario2");
        assertTrue(Theaterfy.getTheaterfy().login("usuario1", "usuario1"));
        assertFalse(Theaterfy.getTheaterfy().login("notusuario1", "usuario1"));
        assertFalse(Theaterfy.getTheaterfy().login("usuario1", "notusuario1"));
        assertFalse(Theaterfy.getTheaterfy().login("usuario1", "usuario2"));
        
        Theaterfy.getTheaterfy().limpiar();
    }

	@Test
    public void testgenerarEstadisticasOcupacionRepresentacion() throws NonExistentFileException, InvalidCardNumberException, FailedInternetConnectionException, UnsupportedImageTypeException, OrderRejectedException {
		Butaca b[] = new Butaca[1];
		ArrayList<Representacion> representaciones = new ArrayList<>();
		ArrayList<Precio> precios = new ArrayList<>();
		EventoDanza e = new EventoDanza("El cascanueces", new RestriccionAforo(1, null),"Evento de danza el cascanueces",
				"Alberto", "Paco", t1, precios, bailarines.toString(), "Orquesta Sinfonica", "James T.");
		GregorianCalendar fecha1=new GregorianCalendar();
		fecha1.add(GregorianCalendar.DAY_OF_MONTH, 2);
		GregorianCalendar fecha2=new GregorianCalendar();
		fecha2.add(GregorianCalendar.DAY_OF_MONTH, 3);
		GregorianCalendar fecha3=new GregorianCalendar();
		fecha3.add(GregorianCalendar.DAY_OF_MONTH, 4);
		UsuarioRegistrado u1 = new UsuarioRegistrado("Nick", "contraseña");
		ZonaNumerada z1 = new ZonaNumerada("A1", 104, 2, 3);
		ZonaNoNumerada z2 = new ZonaNoNumerada("A2", 104, 7);
		ZonaNoNumerada z3 = new ZonaNoNumerada("A3", 104, 10);
		LinkedHashMap<String, Float> datos=new LinkedHashMap<>();
		
		precios.add(new Precio(21, z1));
		precios.add(new Precio(19, z2));
		precios.add(new Precio(17, z3));
		
		representaciones.add(new Representacion("Representacion1", fecha1));
		representaciones.add(new Representacion("Representacion2", fecha2));
		representaciones.add(new Representacion("Representacion3", fecha3));
		
		for(int i=0; i<3; i++) {
			e.anyadirRepresentacion(representaciones.get(i));
		}
		Theaterfy.getTheaterfy().anyadirSuceso(e);
		Theaterfy.getTheaterfy().anyadirZona(z1);
		Theaterfy.getTheaterfy().anyadirZona(z2);
		Theaterfy.getTheaterfy().anyadirZona(z3);
		
		b[0] = z1.getButaca(2, 1);
		
		u1.comprarEntrada(5, z3, representaciones.get(1), "1234567890123456");
		u1.comprarEntrada(3, z2, representaciones.get(2), "1234567890123456");
		u1.comprarEntrada(b, z1, representaciones.get(0), "1234567890123456");
		
		fecha1.add(GregorianCalendar.MONTH, -5);
		fecha2.add(GregorianCalendar.MONTH, -5);
		fecha3.add(GregorianCalendar.MONTH, -5);
		
		String str;
		/*Se ha comprobado que la salida del programa debe ser la que se compara*/
		datos=Theaterfy.getTheaterfy().generarEstadisticasOcupacionRepresentacion(e);
		System.out.println("EstadisticasOcupacionRepresentacion: "+datos);
		
		str=fecha1.get(GregorianCalendar.DATE)+"/"+(fecha1.get(GregorianCalendar.MONTH)+1)+"/"+fecha1.get(GregorianCalendar.YEAR)+" "
	            + fecha1.get(GregorianCalendar.HOUR_OF_DAY)+":"+fecha1.get(GregorianCalendar.MINUTE);
		assertTrue(datos.get(str).equals((float)0.04347826));
		
		str=fecha2.get(GregorianCalendar.DATE)+"/"+(fecha2.get(GregorianCalendar.MONTH)+1)+"/"+fecha2.get(GregorianCalendar.YEAR)+" "
	            + fecha2.get(GregorianCalendar.HOUR_OF_DAY)+":"+fecha2.get(GregorianCalendar.MINUTE);
		assertTrue(datos.get(str).equals((float)0.2173913));
		
		str=fecha3.get(GregorianCalendar.DATE)+"/"+(fecha3.get(GregorianCalendar.MONTH)+1)+"/"+fecha3.get(GregorianCalendar.YEAR)+" "
	            + fecha3.get(GregorianCalendar.HOUR_OF_DAY)+":"+fecha3.get(GregorianCalendar.MINUTE);
		assertTrue(datos.get(str).equals((float)0.13043478));
		
        Theaterfy.getTheaterfy().limpiar();
    }
	
	@Test
    public void testGenerarEstadisticasRemuneracionEvento() throws NonExistentFileException, InvalidCardNumberException, FailedInternetConnectionException, UnsupportedImageTypeException, OrderRejectedException {
		Butaca b[] = new Butaca[1];
		ArrayList<Representacion> representaciones = new ArrayList<>();
		ArrayList<Precio> precios = new ArrayList<>();
		EventoDanza e1 = new EventoDanza("El cascanueces", new RestriccionAforo(1, null),"Evento de danza el cascanueces",
				"Alberto", "Paco", t1, precios, bailarines.toString(), "Orquesta Sinfonica", "James T.");
		EventoTeatro e2 = new EventoTeatro("Mamma mia", new RestriccionAforo(1, null),"Evento de teatro basado en el musical de Mamma mia",
				"Alberto", "Paco", t1, precios, actores.toString());
		GregorianCalendar fecha1=new GregorianCalendar();
		fecha1.add(GregorianCalendar.DAY_OF_MONTH, 2);
		GregorianCalendar fecha2=new GregorianCalendar();
		fecha2.add(GregorianCalendar.DAY_OF_MONTH, 3);
		UsuarioRegistrado u1 = new UsuarioRegistrado("Nick", "contraseña");
		ZonaNumerada z1 = new ZonaNumerada("A1", 104, 2, 3);
		ZonaNoNumerada z2 = new ZonaNoNumerada("A2", 104, 7);
		ZonaNoNumerada z3 = new ZonaNoNumerada("A3", 104, 10);
		LinkedHashMap<String, Float> datos=new LinkedHashMap<>();
		
		precios.add(new Precio(21, z1));
		precios.add(new Precio(19, z2));
		precios.add(new Precio(17, z3));
		
		representaciones.add(new Representacion("Repre", fecha1));
		representaciones.add(new Representacion("Repre", fecha2));
		
		
		e1.anyadirRepresentacion(representaciones.get(0));
		e2.anyadirRepresentacion(representaciones.get(1));

		Theaterfy.getTheaterfy().anyadirSuceso(e1);
		Theaterfy.getTheaterfy().anyadirSuceso(e2);
		Theaterfy.getTheaterfy().anyadirZona(z1);
		Theaterfy.getTheaterfy().anyadirZona(z2);
		Theaterfy.getTheaterfy().anyadirZona(z3);
		
		b[0] = z1.getButaca(2, 1);
		
		u1.comprarEntrada(5, z3, e1.getRepresentaciones().get(0), "1234567890123456");
		u1.comprarEntrada(b, z1, e1.getRepresentaciones().get(0), "1234567890123456");
		u1.comprarEntrada(3, z2, e1.getRepresentaciones().get(0), "1234567890123456");
		
		u1.comprarEntrada(2, z3, e2.getRepresentaciones().get(0), "1234567890123456");
		u1.comprarEntrada(b, z1, e2.getRepresentaciones().get(0), "1234567890123456");
		
		fecha1.add(GregorianCalendar.MONTH, -1);
		fecha2.add(GregorianCalendar.MONTH, -3);
		
		
		/*Se ha comprobado que la salida del programa debe ser la que se compara*/
		datos=Theaterfy.getTheaterfy().generarEstadisticasRemuneracionEvento();
		System.out.println("EstadisticasRemuneracionEvento: "+datos);
		
		assertTrue(datos.get(e1.getNombre())==163 && datos.get(e2.getNombre())==55);
	
        Theaterfy.getTheaterfy().limpiar();
    }
	
	@Test
    public void testGenerarEstadisticasRemuneracionZona() throws NonExistentFileException, InvalidCardNumberException, FailedInternetConnectionException, UnsupportedImageTypeException, OrderRejectedException {
		
		Butaca b[] = new Butaca[1];
		ArrayList<Representacion> representaciones = new ArrayList<>();
		ArrayList<Precio> precios1 = new ArrayList<>();
		ArrayList<Precio> precios2= new ArrayList<>();
		LinkedHashMap<String, Float> datos=new LinkedHashMap<>();

		EventoDanza e1 = new EventoDanza("El cascanueces", new RestriccionAforo(1, null),"Evento de danza el cascanueces",
				"Alberto", "Paco", t1, precios1, bailarines.toString(), "Orquesta Sinfonica", "James T.");
		EventoTeatro e2 = new EventoTeatro("Mamma mia", new RestriccionAforo(1, null),"Evento de teatro basado en el musical de Mamma mia",
				"Alberto", "Paco", t1, precios2, actores.toString());
		
		GregorianCalendar fecha1=new GregorianCalendar();
		fecha1.add(GregorianCalendar.DAY_OF_MONTH, 2);
		GregorianCalendar fecha2=new GregorianCalendar();
		fecha2.add(GregorianCalendar.DAY_OF_MONTH, 3);
		GregorianCalendar fecha3=new GregorianCalendar();
		fecha3.add(GregorianCalendar.DAY_OF_MONTH, 4);
		
		UsuarioRegistrado u1 = new UsuarioRegistrado("Nick", "contraseña");
		
		ZonaNumerada z1 = new ZonaNumerada("A1", 104, 2, 3);
		ZonaNoNumerada z2 = new ZonaNoNumerada("A2", 104, 7);
		ZonaNoNumerada z3 = new ZonaNoNumerada("A3", 104, 10);
		
		precios1.add(new Precio(21, z1));
		precios1.add(new Precio(19, z2));
		precios1.add(new Precio(17, z3));
		
		precios2.add(new Precio(20, z1));
		precios2.add(new Precio(18, z2));
		precios2.add(new Precio(16, z3));
		
		representaciones.add(new Representacion("Repre", fecha1));
		representaciones.add(new Representacion("Repre", fecha2));
		representaciones.add(new Representacion("Repre", fecha3));
		
		e1.anyadirRepresentacion(representaciones.get(0));
		e2.anyadirRepresentacion(representaciones.get(1));
		e2.anyadirRepresentacion(representaciones.get(2));
		
		Theaterfy.getTheaterfy().anyadirSuceso(e1);
		Theaterfy.getTheaterfy().anyadirSuceso(e2);
		Theaterfy.getTheaterfy().anyadirZona(z1);
		Theaterfy.getTheaterfy().anyadirZona(z2);
		Theaterfy.getTheaterfy().anyadirZona(z3);
		
		b[0] = z1.getButaca(2, 1);
		
		u1.comprarEntrada(5, z3, e1.getRepresentaciones().get(0), "1234567890123456");
		u1.comprarEntrada(b, z1, e1.getRepresentaciones().get(0), "1234567890123456");
		u1.comprarEntrada(3, z2, e1.getRepresentaciones().get(0), "1234567890123456");
		
		u1.comprarEntrada(2, z3, e2.getRepresentaciones().get(0), "1234567890123456");
		u1.comprarEntrada(b, z1, e2.getRepresentaciones().get(1), "1234567890123456");
		
		fecha1.add(GregorianCalendar.MONTH, -1);
		fecha2.add(GregorianCalendar.MONTH, -3);
		fecha3.add(GregorianCalendar.MONTH, -4);
		
		datos=Theaterfy.getTheaterfy().generarEstadisticasRemuneracionZona();
		System.out.println("EstadisticasRemuneracionZona: "+datos);
		/*Se ha comprobado que la salida del programa debe ser la que se compara*/
		assertTrue(datos.get(z1.getNombre())==41.0);
		assertTrue(datos.get(z2.getNombre())==57.0);
		assertTrue(datos.get(z3.getNombre())==117.0);
		
        Theaterfy.getTheaterfy().limpiar();
	}

	@Test
    public void generarEstadisticasOcupacionZona() throws NonExistentFileException, InvalidCardNumberException, FailedInternetConnectionException, UnsupportedImageTypeException, OrderRejectedException {
		Butaca b[] = new Butaca[1];
		ArrayList<Representacion> representaciones = new ArrayList<>();
		ArrayList<Precio> precios = new ArrayList<>();
		EventoDanza e1 = new EventoDanza("El cascanueces", new RestriccionAforo(1, null),"Evento de danza el cascanueces",
				"Alberto", "Paco", t1, precios, bailarines.toString(), "Orquesta Sinfonica", "James T.");
		EventoTeatro e2 = new EventoTeatro("Mamma mia", new RestriccionAforo(1, null),"Evento de teatro basado en el musical de Mamma mia",
				"Alberto", "Paco", t1, precios, actores.toString());
		GregorianCalendar fecha1=new GregorianCalendar();
		fecha1.add(GregorianCalendar.DAY_OF_MONTH, 2);
		GregorianCalendar fecha2=new GregorianCalendar();
		fecha2.add(GregorianCalendar.DAY_OF_MONTH, 3);
		UsuarioRegistrado u1 = new UsuarioRegistrado("Nick", "contraseÃ±a");
		ZonaNumerada z1 = new ZonaNumerada("A1", 104, 2, 3);
		ZonaNoNumerada z2 = new ZonaNoNumerada("A2", 104, 7);
		ZonaNoNumerada z3 = new ZonaNoNumerada("A3", 104, 10);
		LinkedHashMap<String, Float> datos=new LinkedHashMap<>();
		
		precios.add(new Precio(21, z1));
		precios.add(new Precio(19, z2));
		precios.add(new Precio(17, z3));
		
		representaciones.add(new Representacion("Repre", fecha1));
		representaciones.add(new Representacion("Repre", fecha2));
		
		
		e1.anyadirRepresentacion(representaciones.get(0));
		e2.anyadirRepresentacion(representaciones.get(1));

		Theaterfy.getTheaterfy().anyadirSuceso(e1);
		Theaterfy.getTheaterfy().anyadirSuceso(e2);
		Theaterfy.getTheaterfy().anyadirZona(z1);
		Theaterfy.getTheaterfy().anyadirZona(z2);
		Theaterfy.getTheaterfy().anyadirZona(z3);
		
		b[0] = z1.getButaca(2, 1);
		
		u1.comprarEntrada(5, z3, e1.getRepresentaciones().get(0), "1234567890123456");
		u1.comprarEntrada(b, z1, e1.getRepresentaciones().get(0), "1234567890123456");
		u1.comprarEntrada(3, z2, e1.getRepresentaciones().get(0), "1234567890123456");
		
		assertTrue(u1.comprarEntrada(2, z3, e2.getRepresentaciones().get(0), "1234567890123456"));
		assertTrue(u1.comprarEntrada(b, z1, e2.getRepresentaciones().get(0), "1234567890123456"));
		
		fecha1.add(GregorianCalendar.MONTH, -1);
		fecha2.add(GregorianCalendar.MONTH, -3);
		
		
		
		/*Se ha comprobado que la salida del programa debe ser la que se compara*/
		datos=Theaterfy.getTheaterfy().generarEstadisticasOcupacionZona();
		System.out.println("EstadisticasOcupacionZona: "+datos);
		assertTrue(datos.get(z1.getNombre())==(float)0.083333336 && datos.get(z2.getNombre())==(float)0.10714286 && datos.get(z3.getNombre())==(float)0.175);
	
        Theaterfy.getTheaterfy().limpiar();
    }
	
	@Test
	public void testGuardaryCargar() {

		Theaterfy.getTheaterfy().limpiar();	//anula el @Before
		Theaterfy.getTheaterfy().registrarse("usuario1","usuario1contr");
		Theaterfy.getTheaterfy().registrarse("usuario2","usuario2contr");
		Theaterfy.getTheaterfy().anyadirZona(new ZonaNumerada("Patio de butacas",18,25,30));
		Theaterfy.getTheaterfy().guardar();
		
		Theaterfy.getTheaterfy().limpiar();
		assertTrue(Theaterfy.getTheaterfy().getUsuarios().isEmpty());
		
		Theaterfy.getTheaterfy().cargar();
		assertTrue(Theaterfy.getTheaterfy().getUsuarios().get(0).getNick().compareTo("usuario1")==0);
		assertTrue(Theaterfy.getTheaterfy().getUsuarios().get(1).getNick().compareTo("usuario2")==0);
		assertTrue(Theaterfy.getTheaterfy().getUsuarios().get(0).getContraseña().compareTo("usuario1contr")==0);
		assertTrue(Theaterfy.getTheaterfy().getUsuarios().get(1).getContraseña().compareTo("usuario2contr")==0);
		
		assertEquals(18f,Theaterfy.getTheaterfy().getPreciosAbonoAnual().get(0).getPrecio(),0);
		assertTrue(Theaterfy.getTheaterfy().getZonas().get(0).getNombre().compareTo("Patio de butacas")==0);
		Theaterfy.getTheaterfy().limpiar();
	}
}
