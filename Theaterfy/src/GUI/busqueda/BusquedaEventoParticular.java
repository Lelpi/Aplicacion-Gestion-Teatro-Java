package GUI.busqueda;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import theaterfy.sucesos.Evento;

/**
 * JPanel para cuando se accede a un evento particular desde el panel de busqueda
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class BusquedaEventoParticular extends JPanel{
	
	private PanelParaBusqueda busqueda;
	private JLabel tituloEvento;
	private JLabel descEvento;
	private JButton entrada;
	private JButton abono;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public BusquedaEventoParticular() {
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
		
		entrada = new JButton("Comprar/Reservar entradas");
		abono = new JButton("Comprar abonos");
		layout.putConstraint(SpringLayout.WEST, entrada, 10, SpringLayout.WEST, titulo);
		layout.putConstraint(SpringLayout.NORTH, entrada, 60, SpringLayout.SOUTH, titulo);
		layout.putConstraint(SpringLayout.WEST, abono, 0, SpringLayout.WEST, entrada);
		layout.putConstraint(SpringLayout.NORTH, abono, 10, SpringLayout.SOUTH, entrada);
		
		JLabel descripcion=new JLabel("Descripcion: ");
		layout.putConstraint(SpringLayout.WEST, descripcion, 250, SpringLayout.EAST, titulo);
		layout.putConstraint(SpringLayout.NORTH, descripcion, 0, SpringLayout.NORTH, titulo);
		descEvento= new JLabel("");
		layout.putConstraint(SpringLayout.WEST, descEvento, 0, SpringLayout.WEST, descripcion);
		layout.putConstraint(SpringLayout.NORTH, descEvento, 10, SpringLayout.SOUTH, descripcion);
		
		this.add(busq);
		this.add(busqueda);
		this.add(titulo);
		this.add(tituloEvento);
		this.add(entrada);
		this.add(abono);
		this.add(descripcion);
		this.add(descEvento);		
	}
	
	/**
	 * actualiza el titulo y la descripcion del evento que se muestra en el panel
	 * 
	 * @param e evento al que se accede
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
		this.entrada.addActionListener(c);
		this.abono.addActionListener(c);
	}
	
	public String getEvento() {
		return this.busqueda.getEvento();
	}
	
	public int getFiltro() {
		return this.busqueda.getFiltro();
	}
	
	public String getEventoCompra() {
		return this.tituloEvento.getText();
	}
}
