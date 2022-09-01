package GUI.panelBienvenida;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;

import theaterfy.Theaterfy;
import theaterfy.compras.Entrada;
import theaterfy.compras.EntradaReservada;
import theaterfy.usuarios.Notificacion;

/**
 * JPanel, bienvenida para un usuario que ha iniciado sesión
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class BienvenidaLogin extends JPanel{
	
	private BienvenidaBuscar bienvenidaBuscar;
	private DefaultListModel<String> notificaciones;
	private DefaultListModel<String> entradas;
	private JLabel usuario;
	private JButton confirmarReservas;
	private JButton comprarAbonos;
	private JButton cerrar;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public BienvenidaLogin() {
		SpringLayout l=new SpringLayout();
		this.setLayout(l);
		this.setPreferredSize(new Dimension(1000,600));
		
		bienvenidaBuscar=new BienvenidaBuscar();
		
		l.putConstraint(SpringLayout.WEST, bienvenidaBuscar, 0, SpringLayout.WEST, this);
		l.putConstraint(SpringLayout.NORTH, bienvenidaBuscar, 20, SpringLayout.NORTH, this);
		
		notificaciones = new DefaultListModel<String>(); 
		entradas = new DefaultListModel<String>();
		
		JLabel bienvenido = new JLabel("Bienvenida, ");
		l.putConstraint(SpringLayout.EAST, bienvenido, -250, SpringLayout.EAST, this);
		l.putConstraint(SpringLayout.NORTH, bienvenido, 40, SpringLayout.NORTH, this);
		
		usuario= new JLabel("h");
		l.putConstraint(SpringLayout.WEST, usuario, 10, SpringLayout.EAST, bienvenido);
		l.putConstraint(SpringLayout.NORTH, usuario, 0, SpringLayout.NORTH, bienvenido);
		
		JList<String> listaNoti = new JList<>(notificaciones);
		l.putConstraint(SpringLayout.WEST, listaNoti, 0, SpringLayout.WEST, bienvenido);
		l.putConstraint(SpringLayout.NORTH, listaNoti, 50, SpringLayout.NORTH, bienvenido);
		
		JList<String> listaEntradas = new JList<>(entradas);
		l.putConstraint(SpringLayout.WEST, listaEntradas, 0, SpringLayout.WEST, listaNoti);
		l.putConstraint(SpringLayout.NORTH, listaEntradas, 50, SpringLayout.SOUTH, listaNoti);
		
		confirmarReservas=new JButton("Confirmar reservas");
		l.putConstraint(SpringLayout.SOUTH, confirmarReservas, -20, SpringLayout.SOUTH, this);
		l.putConstraint(SpringLayout.EAST, confirmarReservas, -20, SpringLayout.EAST, this);
		
		comprarAbonos=new JButton("Comprar abonos");
		l.putConstraint(SpringLayout.SOUTH, comprarAbonos, -20, SpringLayout.SOUTH, this);
		l.putConstraint(SpringLayout.EAST, comprarAbonos, -20, SpringLayout.WEST, confirmarReservas);
		
		cerrar=new JButton("Cerrar sesión");
		l.putConstraint(SpringLayout.NORTH, cerrar, 20, SpringLayout.NORTH, this);
		l.putConstraint(SpringLayout.WEST, cerrar, -5, SpringLayout.WEST, confirmarReservas);
		
		this.add(bienvenidaBuscar);
		this.add(listaNoti);
		this.add(listaEntradas);
		this.add(bienvenido);
		this.add(usuario);
		this.add(confirmarReservas);
		this.add(comprarAbonos);
		this.add(cerrar);
		
	}
	/**
	 * actualiza la información de los componentes de BienvenidaLogin
	 */
	public void actualizar() {
		this.usuario.setText(Theaterfy.getTheaterfy().getUsuarioActual().getNick());
		
		this.notificaciones.removeAllElements();
		this.notificaciones.addElement("Notificaciones");
		for(Notificacion n: Theaterfy.getTheaterfy().getUsuarioActual().getNotificaciones()) {
			this.notificaciones.addElement(""+n);
		}
		
		this.entradas.removeAllElements();
		this.entradas.addElement("Entradas");
		for(Entrada e:Theaterfy.getTheaterfy().getUsuarioActual().getEntradas()) {
			if(e.getClass()==Entrada.class) {
				this.entradas.addElement("Entrada para "+e.getEventName()+" el "+e.getEventDate());
			} else if(e.getClass()==EntradaReservada.class) {
				this.entradas.addElement("Entrada reservada para "+e.getEventName()+" el "+e.getEventDate());
			}
		}
		
	}
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.bienvenidaBuscar.setControlador(c);
		this.confirmarReservas.addActionListener(c);
		this.cerrar.addActionListener(c);
		this.comprarAbonos.addActionListener(c);
	}
	
	public String getEvento() {
		return bienvenidaBuscar.getEvento();
	}
	
	public int getFiltro() {
		return bienvenidaBuscar.getFiltro();
	}
}
