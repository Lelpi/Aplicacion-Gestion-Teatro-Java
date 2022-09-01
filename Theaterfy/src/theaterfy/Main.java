package theaterfy;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.tickets.NonExistentFileException;
import es.uam.eps.padsof.tickets.UnsupportedImageTypeException;
import theaterfy.compras.Entrada;
import theaterfy.sucesos.Ciclo;
import theaterfy.sucesos.EventoDanza;
import theaterfy.sucesos.EventoTeatro;
import theaterfy.sucesos.Precio;
import theaterfy.sucesos.Representacion;
import theaterfy.sucesos.Suceso;
import theaterfy.zona.*;

/**
 * Una clase Main para probar el funcionamiento
 * 
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * 
 */
public class Main {

	/**
	 * Main de prueba de funcionamiento de la aplicacion
	 * 
	 * @param args argumentos del main (ninguno)
	 * @throws InvalidCardNumberException excepcion
	 * @throws FailedInternetConnectionException excepcion
	 * @throws OrderRejectedException excepcion 
	 * @throws NonExistentFileException excepcion
	 * @throws UnsupportedImageTypeException excepcion
	 */
	public static void main(String[] args) throws InvalidCardNumberException, FailedInternetConnectionException, OrderRejectedException, NonExistentFileException, UnsupportedImageTypeException {

		System.out.println(Theaterfy.getTheaterfy().registrarse("usuario1","usuario1contr"));
		System.out.println(Theaterfy.getTheaterfy().registrarse("usuario2","usuario2contr"));
		System.out.println(Theaterfy.getTheaterfy().registrarse("usuario2","usuario2contraseña")); //devuelve false porque ya existe ese usuario
		System.out.println(Theaterfy.getTheaterfy().login("usuario1", "usuario1contr"));
		System.out.println(Theaterfy.getTheaterfy().login("usuario2", "usuario2contr"));
		
		Zona[] subzonas=new Zona[2];
		subzonas[0] = new ZonaNumerada("A1", 104, 2, 3);
		subzonas[1] = new ZonaNoNumerada("A2", 104, 7);
		ZonaMixta z = new ZonaMixta("A3", subzonas);
		ZonaNoNumerada z2 = new ZonaNoNumerada("A4", 104, 10);
		
		Theaterfy.getTheaterfy().anyadirZona(z);
		Theaterfy.getTheaterfy().anyadirZona(z2);

		ArrayList<Precio> precios1 = new ArrayList<>();
		ArrayList<Precio> precios2 = new ArrayList<>();

		precios1.add(new Precio(21, subzonas[0]));
		precios1.add(new Precio(25, subzonas[1]));
		precios1.add(new Precio(17, z2));
		
		precios2.add(new Precio(42, subzonas[0]));
		precios2.add(new Precio(50, subzonas[1]));
		precios2.add(new Precio(34, z2));
		
		EventoDanza e1 = new EventoDanza("El cascanueces", null,"Evento de danza el cascanueces",
				"Alberto", "Paco", LocalTime.of(2, 45, 30), precios1, null, "Orquesta Sinfonica", "James T.");
		EventoTeatro e2 = new EventoTeatro("Mamma mia", null,"Evento de teatro basado en el musical de Mamma mia",
				"Alberto", "Paco", LocalTime.of(2, 15, 0), precios2, null);
		
		GregorianCalendar fecha1 = new GregorianCalendar();
		fecha1.add(GregorianCalendar.DAY_OF_MONTH, 2);
		GregorianCalendar fecha2 = new GregorianCalendar();
		fecha2.add(GregorianCalendar.DAY_OF_MONTH, 3);
		GregorianCalendar fecha3 = new GregorianCalendar();
		fecha3.add(GregorianCalendar.DAY_OF_MONTH, 4);

		e1.anyadirRepresentacion(new Representacion("Representacion1", fecha1));
		e1.anyadirRepresentacion(new Representacion("Representacion2", fecha2));
		e2.anyadirRepresentacion(new Representacion("Representacion3", fecha3));
		
		ArrayList<Suceso> subciclos = new ArrayList<>();
		subciclos.add(e1);
		subciclos.add(e2);

		Ciclo c= new Ciclo("Ciclo", subciclos);
		
		Theaterfy.getTheaterfy().anyadirSuceso(c);
		
		Theaterfy.getTheaterfy().getUsuarios().get(0).comprarAbono(z2, "1234567891011121");
		
		Theaterfy.getTheaterfy().anyadirPrecioAbonoCiclo(c, (float)0.5);
		Theaterfy.getTheaterfy().getUsuarios().get(1).comprarAbono(subzonas[0], c, "1234567891011121");
		
		Theaterfy.getTheaterfy().getUsuarios().get(0).comprarEntrada(2, z2, e1.getRepresentaciones().get(0), "1234567891011121"); //paga 17 por tener un abono anual
		Theaterfy.getTheaterfy().getUsuarios().get(0).comprarEntrada(1, z2, e1.getRepresentaciones().get(0), "1234567891011121"); //paga 17 porque ya gasto el abono
		//Theaterfy.getTheaterfy().getUsuarios().get(0).comprarEntrada(1, z2, e1.getRepresentaciones().get(0), "1000000"); //tarjeta invalida
		
		Theaterfy.getTheaterfy().getUsuarios().get(0).mostrarEntradas();
		
		Butaca b[] = new Butaca[2];
		b[0] = ((ZonaNumerada)subzonas[0]).getButaca(0, 0);
		b[1] = ((ZonaNumerada)subzonas[0]).getButaca(0, 1);
		
		Theaterfy.getTheaterfy().getUsuarios().get(1).reservarEntrada(b,  ((ZonaNumerada)subzonas[0]), e2.getRepresentaciones().get(0));
		
		ArrayList<Entrada> entradaConfirmar = new ArrayList<>();
		entradaConfirmar.add(Theaterfy.getTheaterfy().getUsuarios().get(1).getEntradas().get(0));
		
		Theaterfy.getTheaterfy().getUsuarios().get(1).confirmarReserva(entradaConfirmar, "1234567891011142");
				
		Theaterfy.getTheaterfy().getUsuarios().get(1).mostrarEntradas();

		
		ArrayList<Entrada> entradaCancelar = new ArrayList<>();
		entradaCancelar.add(Theaterfy.getTheaterfy().getUsuarios().get(1).getEntradas().get(0));
		
		Theaterfy.getTheaterfy().getUsuarios().get(1).cancelarReserva(entradaCancelar);

		Theaterfy.getTheaterfy().getUsuarios().get(1).mostrarEntradas();

		GregorianCalendar fecha4 = new GregorianCalendar();
		fecha4.add(GregorianCalendar.DAY_OF_MONTH, 5);

		e2.getRepresentaciones().get(0).posponer(fecha4);
		
		e2.getRepresentaciones().get(0).cancelar();
		System.out.println(e2.getRepresentaciones().get(0).getEntradaDeRepresentacion()); //Se muestra vacio porque se cancela el evento y se eliminan las entradas

		Theaterfy.getTheaterfy().getUsuarios().get(1).mostrarNotificaciones();
		
		System.out.println(Theaterfy.getTheaterfy().buscarEventos("Mamma", -1));
		
		System.out.println(Theaterfy.getTheaterfy().buscarEventos("Mamma", 0)); //No devuelve nada porque no se cumplen las condiciones
		
		System.out.println(Theaterfy.getTheaterfy().buscarEventos("", 3));
		
		fecha1.add(GregorianCalendar.DAY_OF_MONTH, -10);
		fecha2.add(GregorianCalendar.DAY_OF_MONTH, -10);
		fecha4.add(GregorianCalendar.DAY_OF_MONTH, -10);
		

		System.out.println(Theaterfy.getTheaterfy().generarEstadisticasRemuneracionZona());
		System.out.println(Theaterfy.getTheaterfy().generarEstadisticasRemuneracionEvento());
		System.out.println(Theaterfy.getTheaterfy().generarEstadisticasOcupacionRepresentacion(e1));
		System.out.println(Theaterfy.getTheaterfy().generarEstadisticasOcupacionZona());
		
		Theaterfy.getTheaterfy().guardar();
		Theaterfy.getTheaterfy().limpiar();
		System.out.println(Theaterfy.getTheaterfy().getSucesos());
		Theaterfy.getTheaterfy().cargar();
		System.out.println(Theaterfy.getTheaterfy().getSucesos());
		
		Theaterfy.getTheaterfy().limpiar();
	}

}
