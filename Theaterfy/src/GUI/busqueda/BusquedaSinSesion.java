package GUI.busqueda;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import theaterfy.sucesos.Evento;

/**
 * JPanel cuando se accede a través de la busqueda a un evento en particular sin haber iniciado sesion
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class BusquedaSinSesion extends JPanel {

	private PanelParaBusqueda busqueda;
	private JLabel tituloEvento;
	private JLabel descEvento;
	private JButton inicio;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public BusquedaSinSesion() {
		
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(1000, 600));
		
		JLabel busq=new JLabel("Búsqueda");
		layout.putConstraint(SpringLayout.WEST, busq, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, busq, 20, SpringLayout.NORTH, this);
		
		
		JLabel titulo=new JLabel("Título: ");
		layout.putConstraint(SpringLayout.WEST, titulo, 250, SpringLayout.WEST, busq);
		layout.putConstraint(SpringLayout.NORTH, titulo, 200, SpringLayout.SOUTH, busq);
		
		busqueda= new PanelParaBusqueda();
		layout.putConstraint(SpringLayout.WEST, busqueda, 150, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, busqueda, 50, SpringLayout.NORTH, this);
		
		tituloEvento= new JLabel("");
		layout.putConstraint(SpringLayout.WEST, tituloEvento, 0, SpringLayout.WEST, titulo);
		layout.putConstraint(SpringLayout.NORTH, tituloEvento, 10, SpringLayout.SOUTH, titulo);
		
		JLabel descripcion=new JLabel("Descripcion: ");
		layout.putConstraint(SpringLayout.WEST, descripcion, 250, SpringLayout.EAST, titulo);
		layout.putConstraint(SpringLayout.NORTH, descripcion, 0, SpringLayout.NORTH, titulo);
		descEvento= new JLabel("");
		layout.putConstraint(SpringLayout.WEST, descEvento, 0, SpringLayout.WEST, descripcion);
		layout.putConstraint(SpringLayout.NORTH, descEvento, 10, SpringLayout.SOUTH, descripcion);
		
		inicio=new JButton("Inicio");
		layout.putConstraint(SpringLayout.WEST, inicio, 440, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, inicio, 400, SpringLayout.NORTH, this);
		
		this.add(busq);
		this.add(busqueda);
		this.add(titulo);
		this.add(tituloEvento);
		this.add(descripcion);
		this.add(descEvento);
		this.add(inicio);
	}

	/**
	 * Actualiza la informacion del panel (titulo y descripcion del evento accedido)
	 * 
	 * @param e El evento accedido
	 */
	public void actualizar(Evento e) {
		this.tituloEvento.setText(e.getNombre());
		this.descEvento.setText(e.getDescripcion());
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.busqueda.setControlador(c);
		this.inicio.addActionListener(c);
	}
	
	public String getEvento() {
		return this.busqueda.getEvento();
	}
	
	public int getFiltro() {
		return this.busqueda.getFiltro();
	}
}
