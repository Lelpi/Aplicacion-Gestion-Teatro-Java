package GUI.busqueda;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;
import theaterfy.Theaterfy;
import theaterfy.sucesos.Evento;
import theaterfy.sucesos.Representacion;
import theaterfy.sucesos.Suceso;

/**
 * JPanel para que el gestor pueda modificar aspectos de un evento en concreto
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class BusquedaGestor extends JPanel{
	
	
	private PanelParaBusqueda busqueda;
	private JButton guardar;
	private JLabel nombreEvento;
	private JLabel descEvento;
	private JTextField restriccion;
	private JComboBox<String> comboFechas;
	private JComboBox<String> fechaPosponer1;
	private JTextField fechaPosponer2;
	private JButton volver;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public BusquedaGestor() {
		
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(1000, 600));
		
		JLabel gestor=new JLabel("Búsqueda para Gestor");
		layout.putConstraint(SpringLayout.WEST, gestor, 80, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, gestor, 30, SpringLayout.NORTH, this);
		
		
		busqueda = new PanelParaBusqueda();
		
		layout.putConstraint(SpringLayout.NORTH, busqueda, 50, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, busqueda, 150, SpringLayout.WEST, this);
		
		JLabel cancelar=new JLabel("Cancelar actuación");
		layout.putConstraint(SpringLayout.WEST, cancelar, 10, SpringLayout.WEST, gestor);
		layout.putConstraint(SpringLayout.NORTH, cancelar, 150, SpringLayout.NORTH, gestor);
		
		String dias[] = {};
		comboFechas = new JComboBox<String> (dias);
		layout.putConstraint(SpringLayout.WEST, comboFechas, 80, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, comboFechas, 20, SpringLayout.SOUTH, cancelar);
		
		
		JLabel restringir=new JLabel("Restringir el aforo a: (%)");
		layout.putConstraint(SpringLayout.WEST, restringir, 80, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, restringir, 40, SpringLayout.SOUTH, comboFechas);
		
		restriccion=new JTextField(2);
		layout.putConstraint(SpringLayout.WEST, restriccion, 10, SpringLayout.EAST, restringir);
		layout.putConstraint(SpringLayout.NORTH, restriccion, 0, SpringLayout.NORTH, restringir); 
		
		JLabel posponer=new JLabel("Posponer actuación");
		layout.putConstraint(SpringLayout.WEST, posponer, 10, SpringLayout.WEST, gestor);
		layout.putConstraint(SpringLayout.NORTH, posponer, 40, SpringLayout.SOUTH, restringir);
		
		JLabel original=new JLabel("Fecha actual:");
		layout.putConstraint(SpringLayout.WEST, original, 80, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, original, 20, SpringLayout.SOUTH, posponer);
		
		fechaPosponer1 = new JComboBox<String> (dias);
		layout.putConstraint(SpringLayout.WEST, fechaPosponer1, 80, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, fechaPosponer1, 20, SpringLayout.SOUTH, original);
		
		JLabel cambio=new JLabel("Fecha modificada: (dd/mm/yyyy hh:mm)");
		layout.putConstraint(SpringLayout.WEST, cambio, 75, SpringLayout.EAST, original);
		layout.putConstraint(SpringLayout.NORTH, cambio, 0, SpringLayout.NORTH, original);
		fechaPosponer2 = new JTextField (6);
		layout.putConstraint(SpringLayout.WEST, fechaPosponer2, 30, SpringLayout.EAST, fechaPosponer1);
		layout.putConstraint(SpringLayout.NORTH, fechaPosponer2, 0, SpringLayout.NORTH, fechaPosponer1);
		
		
		
		JLabel titulo=new JLabel("Título:");
		layout.putConstraint(SpringLayout.EAST, titulo, 600, SpringLayout.WEST, gestor);
		layout.putConstraint(SpringLayout.NORTH, titulo, 150, SpringLayout.SOUTH, gestor);
		nombreEvento=new JLabel("");
		layout.putConstraint(SpringLayout.WEST, nombreEvento, 0, SpringLayout.WEST, titulo);
		layout.putConstraint(SpringLayout.NORTH, nombreEvento, 10, SpringLayout.SOUTH, titulo);
		
		JLabel descripcion=new JLabel("Descripción:");
		layout.putConstraint(SpringLayout.WEST, descripcion, 0, SpringLayout.WEST, nombreEvento);
		layout.putConstraint(SpringLayout.NORTH, descripcion, 30, SpringLayout.SOUTH, nombreEvento);
		descEvento=new JLabel("");
		layout.putConstraint(SpringLayout.WEST, descEvento, 0, SpringLayout.WEST, descripcion);
		layout.putConstraint(SpringLayout.NORTH, descEvento, 10, SpringLayout.SOUTH, descripcion);
		
		guardar = new JButton("Guardar cambios");
		layout.putConstraint(SpringLayout.WEST, guardar, 80, SpringLayout.WEST, descripcion);
		layout.putConstraint(SpringLayout.NORTH, guardar, 200, SpringLayout.SOUTH, descripcion);
		
		volver = new JButton("Cancelar");
		layout.putConstraint(SpringLayout.WEST, volver, 10, SpringLayout.EAST, guardar);
		layout.putConstraint(SpringLayout.NORTH, volver, 0, SpringLayout.NORTH, guardar);
		
		this.add(gestor);
		this.add(busqueda);
		this.add(cancelar);
		this.add(comboFechas);
		this.add(restringir);
		this.add(restriccion);
		this.add(posponer);
		this.add(original);
		this.add(fechaPosponer1);
		this.add(cambio);
		this.add(fechaPosponer2);
		this.add(titulo);
		this.add(nombreEvento);
		this.add(descripcion);
		this.add(descEvento);
		this.add(guardar);
		this.add(volver);
		
	}

	/**
	 * Actualiza el nombre del evento, la descripciond el evento y los comboBox de las fechas
	 * 
	 * @param e
	 */
	public void actualizar(Evento e) {
		this.nombreEvento.setText(e.getNombre());
		this.descEvento.setText(e.getDescripcion());
		
		ArrayList<String> listaFechas=new ArrayList<>();
        for(Representacion r:e.getRepresentaciones()) {
        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            listaFechas.add(sdf.format(r.getFecha().getTime()));          
        }
        
        comboFechas.removeAllItems();
        fechaPosponer1.removeAllItems();
        comboFechas.addItem("Cancelar fecha");
        fechaPosponer1.addItem("Posponer fecha");
        for(String s:listaFechas) {
        	comboFechas.addItem(s);
        	fechaPosponer1.addItem(s);
        }
		
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.guardar.addActionListener(c);
		this.busqueda.setControlador(c);
		this.volver.addActionListener(c);
	}
	
	public String getNombreEvento() {
		return this.busqueda.getEvento();
	}
	
	public int getFiltro() {
		return this.busqueda.getFiltro();
	}
	
	public String getRestriccion() {
		return this.restriccion.getText();
	}
	
	public String getFechaPosponer2() {
		return this.fechaPosponer2.getText();
	}
	
	public String getFechaCancelar() {
		return (String)this.comboFechas.getSelectedItem();
	}
	
	public String getFechaPosponer1() {
		return (String)this.fechaPosponer1.getSelectedItem();
	}
	
	/**
	 * Metodo que devuelve el evento al cual se accedio en base al nombre
	 * 
	 * @return el evento accedido
	 */
	public Evento getEvento() {
		for(Suceso s:Theaterfy.getTheaterfy().getSucesos()){
			if(s.getNombre().equals(this.nombreEvento.getText())) {
				return (Evento)s;
			}
		}
		return null;
	}
}