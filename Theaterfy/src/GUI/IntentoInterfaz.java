package GUI;

import javax.swing.*;

import GUI.anyadirCiclo.AnyadirCiclo;
import GUI.anyadirCiclo.ControlAnyadirCiclo;
import GUI.anyadirEvento.AnyadirEvento;
import GUI.anyadirEvento.ControlAnyadirEvento;
import GUI.busqueda.Busqueda;
import GUI.busqueda.BusquedaEventoParticular;
import GUI.busqueda.BusquedaGestor;
import GUI.busqueda.BusquedaSinSesion;
import GUI.busqueda.ControlBusqueda;
import GUI.busqueda.ControlBusquedaEventoParticular;
import GUI.busqueda.ControlBusquedaGestor;
import GUI.busqueda.ControlBusquedaSinSesion;
import GUI.comprar.ComprarEntrada;
import GUI.comprar.ControlComprarEntrada;
import GUI.comprarAbono.ComprarAbono;
import GUI.comprarAbono.ControlAbono;
import GUI.gestorPrincipal.ConfiguraZonas;
import GUI.gestorPrincipal.ControlConfiguraZonas;
import GUI.gestorPrincipal.ControlDeshabilitar;
import GUI.gestorPrincipal.ControlGestorPrincipal;
import GUI.gestorPrincipal.DeshabilitarButacas;
import GUI.gestorPrincipal.GestorPrincipal;
import GUI.panelBienvenida.Bienvenida;
import GUI.panelBienvenida.BienvenidaLogin;
import GUI.panelBienvenida.ControlBienvenida;
import GUI.panelBienvenida.ControlBienvenidaLogin;
import GUI.panelConfirmarReserva.ConfirmarReserva;
import GUI.panelConfirmarReserva.ControlConfirmarReserva;
import GUI.panelEstadisticas.ControlEstadisticas;
import GUI.panelEstadisticas.Estadisticas;
import GUI.panelRegistro.ControlRegistro;
import GUI.panelRegistro.Registro;
import theaterfy.sucesos.*;

import java.awt.*;
import java.awt.event.*;

/**
 * Ventana del proyecto
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class IntentoInterfaz extends JFrame{

	public static String BIENVENIDA = "Bienvenida";
	public static String COMPRARENTRADA = "Compra Entrada";
	public static String GESTOR = "Gestor";
	public static String BUSQUEDA = "Busqueda";
	public static String BUSQUEDAUNEVENTO = "Buscar un evento";
	public static String BUSQUEDAGESTOR = "Buscar para gestor";
	public static String COMPRARABONO = "Comprar Abono";
	public static String BUSQUEDASINSESION = "Busqueda para usuarios sin registrar";
	public static String REGISTRO = "Registro";
	public static String ESTADISTICAS = "Estadisticas";
	public static String BIENVENIDALOGIN = "Bienvenida con sesion iniciada";
	public static String CONFIRMARRESERVA="Confirmar reserva";
	public static String ANYADIREVENTO = "Añadir evento";
    public static String CONFIGURAZONAS = "Configura zonas";
	public static String ANYADIRCICLO = "Añadir ciclo";
	public static String DESHABILITARBUTACAS = "Deshabilitar buatcas";
	
	private BusquedaEventoParticular busquedaUnEvento;
	private Busqueda busqueda;
	private ComprarAbono comprarAbono;
	private BusquedaGestor busquedaGestor;
	private BusquedaSinSesion busquedaSinSesion;
	private BienvenidaLogin bienvenidaLogin;
	private ComprarEntrada comprarEntrada;
	private ConfirmarReserva confirmarReserva;
	private AnyadirEvento anyadirEvento;
    private GestorPrincipal gestor;
    private ConfiguraZonas configZonas;
	private AnyadirCiclo anyadirCiclo;
	private DeshabilitarButacas deshabilitarButacas;
	private Estadisticas estadisticas;

	/**
	 * Constructor, construye todos los paneles
	 */
	public IntentoInterfaz() {
		
		Container contenedor = this.getContentPane();
		contenedor.setLayout(new CardLayout());
		
		Bienvenida bienvenida=new Bienvenida();
		comprarEntrada = new ComprarEntrada();
		busqueda=new Busqueda();
		busquedaUnEvento = new BusquedaEventoParticular();
		busquedaGestor = new BusquedaGestor();
		comprarAbono = new ComprarAbono();
		busquedaSinSesion= new BusquedaSinSesion();
		Registro registro = new Registro();
		estadisticas = new Estadisticas();
		bienvenidaLogin = new BienvenidaLogin();
		confirmarReserva=new ConfirmarReserva();
		anyadirEvento = new AnyadirEvento();
		gestor = new GestorPrincipal();
		configZonas = new ConfiguraZonas();
		anyadirCiclo = new AnyadirCiclo();
		deshabilitarButacas=new DeshabilitarButacas();
		
		ActionListener controlBusqueda = new ControlBusqueda(busqueda, this);
		busqueda.setControlador(controlBusqueda);
		
		ActionListener controlBusquedaEventoParticular = new ControlBusquedaEventoParticular(busquedaUnEvento, this);
		busquedaUnEvento.setControlador(controlBusquedaEventoParticular);
		
		ActionListener controlAbono = new ControlAbono(comprarAbono, this);
		comprarAbono.setControlador(controlAbono);
		
		ActionListener controlBusquedaGestor = new ControlBusquedaGestor(busquedaGestor, this);
		busquedaGestor.setControlador(controlBusquedaGestor);
		
		ActionListener controlBusquedaSinSesion = new ControlBusquedaSinSesion(busquedaSinSesion, this);
		busquedaSinSesion.setControlador(controlBusquedaSinSesion);
		
		ActionListener controlBienvenida = new ControlBienvenida(bienvenida, this);
		bienvenida.setControlador(controlBienvenida);
		
		ActionListener controlRegistro = new ControlRegistro(registro, this);
		registro.setControlador(controlRegistro);
		
		ActionListener controlEstadisticas = new ControlEstadisticas(estadisticas, this);
		estadisticas.setControlador(controlEstadisticas);
		
		ActionListener controlBienvenidaLogin = new ControlBienvenidaLogin(bienvenidaLogin, this);
		bienvenidaLogin.setControlador(controlBienvenidaLogin);
		
		ActionListener controlComprar = new ControlComprarEntrada(comprarEntrada, this);
        comprarEntrada.setControladores(controlComprar);
        
        ActionListener controlConfirmarReserva = new ControlConfirmarReserva(confirmarReserva, this);
        confirmarReserva.setControlador(controlConfirmarReserva);
        
        ActionListener controlConfiguraZonas = new ControlConfiguraZonas(configZonas, this);
        configZonas.setControladores(controlConfiguraZonas);
        
        ActionListener controlGestorPrincipal = new ControlGestorPrincipal(gestor, this);
        gestor.setControladores(controlGestorPrincipal);
        
        ActionListener controlAnyadirEvento = new ControlAnyadirEvento(anyadirEvento, this);
        anyadirEvento.setControladores(controlAnyadirEvento);
        
        ActionListener controlAnyadirCiclo = new ControlAnyadirCiclo(anyadirCiclo, this);
        anyadirCiclo.setControlador(controlAnyadirCiclo);
        
        ActionListener controlDeshabilitarButacas=new ControlDeshabilitar(deshabilitarButacas, this);
        deshabilitarButacas.setControlador(controlDeshabilitarButacas);
        
		contenedor.add(bienvenida, BIENVENIDA);
		contenedor.add(comprarEntrada, COMPRARENTRADA);
		contenedor.add(gestor, GESTOR);
		contenedor.add(busqueda, BUSQUEDA);
		contenedor.add(busquedaUnEvento, BUSQUEDAUNEVENTO);
		contenedor.add(busquedaGestor, BUSQUEDAGESTOR);
		contenedor.add(comprarAbono, COMPRARABONO);		
		contenedor.add(busquedaSinSesion, BUSQUEDASINSESION);
		contenedor.add(registro, REGISTRO);
		contenedor.add(estadisticas, ESTADISTICAS);
		contenedor.add(bienvenidaLogin, BIENVENIDALOGIN);
		contenedor.add(confirmarReserva, CONFIRMARRESERVA);
		contenedor.add(anyadirEvento, ANYADIREVENTO);
		contenedor.add(configZonas, CONFIGURAZONAS);
		contenedor.add(anyadirCiclo, ANYADIRCICLO);
		contenedor.add(deshabilitarButacas, DESHABILITARBUTACAS);

		this.setSize(1000,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		this.addWindowListener(new Guardado());
		
	}
	

	public void mostrarPanel(String s) {
		((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(), s); 
	}

	/**
	 * main de prueba para comprobar funcionamiento de la app
	 * @param args
	 */
	public static void main(String[] args){
		
		IntentoInterfaz prueba =new IntentoInterfaz();
		prueba.mostrarPanel(BIENVENIDA);
	}
	
	/**
	 * actualiza la busqueda de un evento particular
	 * @param e Evento para actualziar los datos
	 */
	public void actualizarBusquedaUnEvento(Evento e) {
		this.busquedaUnEvento.actualizar(e);
	}
	
	/**
	 * actualiza la busqueda sin sesion iniciada
	 * @param e Evento para actualziar los datos
	 */
	public void actualizarBusquedaSinSesion(Evento e) {
		this.busquedaSinSesion.actualizar(e);
	}
	
	/**
	 * actualiza la busqueda del gestor
	 * @param e Evento para actualziar los datos
	 */
	public void actualizarBusquedaGestor(Evento e) {
		this.busquedaGestor.actualizar(e);
	}
	
	/**
	 * devuelve el panel de busqueda para poder modificar la tabla
	 * @return el panel de busqueda
	 */
	public Busqueda getPanelBusqueda() {
		return this.busqueda;
	}
	
	/**
	 * Actualiza el panel de bienvenida con sesion iniciada
	 */
	public void actualizarBienvenidaLogin() {
		this.bienvenidaLogin.actualizar();
	}
	
	/**
	 * actualiza el panel de comprar entrada en base al evento accedido
	 * @param e Evento para actualziar los datos
	 */
	public void actualizarComprarEntrada(Evento e) {
		this.comprarEntrada.actualizar(e);
	}
	
	/**
	 * Actualiza el panel con las reservas del usuario
	 */
	public void actualizarConfirmarReserva() {
		this.confirmarReserva.actualizar();
	}
	
	/**
	 * Actualiza la bienvenida del gestor
	 */
	public void actualizarGestorPrincipal() {
		this.gestor.setActualizarTabla();
	}
	
	/**
	 * Actualiza las zonas
	 */
	public void actualizarZonas() {
        this.configZonas.getSubzonas().setActualizarTabla();
    }
	
	/**
	 * Actualiza el panel de deshabilitacion de butacas
	 */
	public void actualizarDeshabilitarButacas() {
        this.deshabilitarButacas.actualizar();
    }

	/**
	 * Actualiza el panel para añadir ciclos
	 */
	public void actualizarAnyadirCiclos() {
		this.anyadirCiclo.actualizarTabla();
	}
	
	/**
	 * Actualiza el panel para añadir evento
	 */
	public void actualizarAnyadirEvento() {
		this.anyadirEvento.actualizar();
	}
	
	/**
	 * Actualiza el panel para estadisticas
	 */
	public void actualizarEstadisticas() {
		this.estadisticas.actualizar();
	}
	
	public void actualizarComprarAbono() {
		this.comprarAbono.actualizar();
	}
}

