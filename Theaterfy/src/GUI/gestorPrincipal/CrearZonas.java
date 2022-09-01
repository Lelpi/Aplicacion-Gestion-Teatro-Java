package GUI.gestorPrincipal;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * panel para crear una zona
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class CrearZonas extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private JLabel crear;
	private JLabel nombre;
	private JLabel tipo;
	private JLabel precio;
	private JTextField nomField;
	private JTextField precioField;
	JComboBox<String> comboZonas;
	private CrearZonaNumerada numerada;
	private CrearZonaNoNum noNumerada;
	private JButton conf;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public CrearZonas() {	
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(320, 550));
		
		crear=new JLabel("Crear zona");
		nombre=new JLabel("Nombre de la zona");
		tipo=new JLabel("Tipo de zona");
		precio=new JLabel("Precio abono anual");
		nomField = new JTextField(15);
		precioField = new JTextField(4);
		numerada = new CrearZonaNumerada();
		noNumerada = new CrearZonaNoNum();
		conf = new JButton("Crear zona");
		
		layout.putConstraint(SpringLayout.WEST, crear, 40, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, crear, 30, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, nombre, 0, SpringLayout.WEST, crear);
		layout.putConstraint(SpringLayout.NORTH, nombre, 20, SpringLayout.SOUTH, crear);
		layout.putConstraint(SpringLayout.WEST, nomField, 15, SpringLayout.EAST, nombre);
		layout.putConstraint(SpringLayout.NORTH, nomField, 0, SpringLayout.NORTH, nombre);
		layout.putConstraint(SpringLayout.WEST, tipo, 0, SpringLayout.WEST, nombre);
		layout.putConstraint(SpringLayout.NORTH, tipo, 20, SpringLayout.SOUTH, nombre);
		
		String zonas[] = {"Selecciona zona", "Numerada", "No numerada"};
		comboZonas = new JComboBox<String> (zonas);
		layout.putConstraint(SpringLayout.WEST, comboZonas, 0, SpringLayout.WEST, nomField);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, comboZonas, 0, SpringLayout.VERTICAL_CENTER, tipo);
		layout.putConstraint(SpringLayout.WEST, precio, 0, SpringLayout.WEST, tipo);
		layout.putConstraint(SpringLayout.NORTH, precio, 20, SpringLayout.SOUTH, tipo);
		layout.putConstraint(SpringLayout.WEST, precioField, 0, SpringLayout.WEST, nomField);
		layout.putConstraint(SpringLayout.NORTH, precioField, 0, SpringLayout.NORTH, precio);
		layout.putConstraint(SpringLayout.WEST, numerada, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, numerada, 20, SpringLayout.SOUTH, precioField);
		layout.putConstraint(SpringLayout.WEST, noNumerada, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, noNumerada, 20, SpringLayout.SOUTH, precioField);
		layout.putConstraint(SpringLayout.WEST, conf, 95, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, conf, 0, SpringLayout.SOUTH, numerada);
		
		numerada.setVisible(false);
		noNumerada.setVisible(false);
		
		this.add(crear);
		this.add(nombre);
		this.add(nomField);
		this.add(tipo);
		this.add(comboZonas);
		this.add(precio);
		this.add(precioField);
		this.add(numerada);
		this.add(noNumerada);
		this.add(conf);
	}
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControladores(ActionListener c) {
		this.comboZonas.addActionListener(c);
		this.conf.addActionListener(c);
	}
	
	public String getNombreZona() {
		return this.nomField.getText();
	}
	
	public float getPrecioAbonoAnual() {
		if(this.precioField.getText().toString().equals(""))
			return -1;
		return Float.parseFloat(this.precioField.getText().toString());
	}
	
	/**
	 * 
	 * @return el nombre de la zona seleccionada
	 */
	public String getSelected() {
		return this.comboZonas.getSelectedItem().toString();
	}
	
	public CrearZonaNumerada getNumerada() {
		return this.numerada;
	}
	
	public CrearZonaNoNum getNoNumerada() {
		return this.noNumerada;
	}
}
