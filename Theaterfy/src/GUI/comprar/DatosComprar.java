package GUI.comprar;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;

import theaterfy.Theaterfy;
import theaterfy.sucesos.Evento;
import theaterfy.sucesos.Representacion;
import theaterfy.zona.Zona;

/**
 * panel para que el usuario introduzca los datos de la representación para la que vaya a comprar entradas
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class DatosComprar extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private JLabel dia;
	private JLabel zona;
	private JLabel precio;
	private JLabel pregunta;
	private JComboBox<String> comboDias;
	private JComboBox<String> comboZonas;
	private JRadioButton compraButton;
	private JRadioButton reservaButton;
	private ButtonGroup group;
	private DatosBancarios banco;
	private ReservaEvento reserva;
	private JLabel tituloEvento;
	private JLabel precioEvento;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public DatosComprar() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(400, 600));
		
		dia=new JLabel("1. Selecciona el dia y la hora");
		pregunta=new JLabel("2. ¿Que desea?");
		zona=new JLabel("3. Selecciona la zona");
		precio = new JLabel("Precio:");
		comboDias = new JComboBox<String> ();
		comboZonas = new JComboBox<String> ();
		compraButton = new JRadioButton("Comprar");
		reservaButton = new JRadioButton("Reservar");
		group = new ButtonGroup();
		banco = new DatosBancarios();
		reserva = new ReservaEvento();
		
		layout.putConstraint(SpringLayout.WEST, dia, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, dia, 40, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, comboDias, 45, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, comboDias, 10, SpringLayout.SOUTH, dia);

		
		layout.putConstraint(SpringLayout.WEST, zona, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, zona, 10, SpringLayout.SOUTH, compraButton);

		layout.putConstraint(SpringLayout.WEST, comboZonas, 45, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, comboZonas, 10, SpringLayout.SOUTH, zona);
		

		layout.putConstraint(SpringLayout.WEST, precio, 10, SpringLayout.EAST, comboZonas);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, precio, 0, SpringLayout.VERTICAL_CENTER, comboZonas);
		
		layout.putConstraint(SpringLayout.WEST, pregunta, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, pregunta, 10, SpringLayout.SOUTH, comboDias);
		layout.putConstraint(SpringLayout.WEST, compraButton, 45, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, compraButton, 10, SpringLayout.SOUTH, pregunta);
		layout.putConstraint(SpringLayout.WEST, reservaButton, 20, SpringLayout.EAST, compraButton);
		layout.putConstraint(SpringLayout.NORTH, reservaButton, 10, SpringLayout.SOUTH, pregunta);
		group.add(compraButton);
		group.add(reservaButton);
		
		layout.putConstraint(SpringLayout.WEST, banco, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, banco, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, reserva, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, reserva, 0, SpringLayout.SOUTH, this);
		
		banco.setVisible(false);
		reserva.setVisible(false);
		
		tituloEvento=new JLabel("");
		layout.putConstraint(SpringLayout.WEST, tituloEvento, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, tituloEvento, 0, SpringLayout.NORTH, this);
		
		precioEvento=new JLabel("");
		layout.putConstraint(SpringLayout.WEST, precioEvento, 10, SpringLayout.EAST, precio);
		layout.putConstraint(SpringLayout.NORTH, precioEvento, 0, SpringLayout.NORTH, precio);
		
		this.add(dia);
		this.add(comboDias);
		this.add(zona);
		this.add(comboZonas);
		this.add(precio);
		this.add(pregunta);
		this.add(compraButton);
		this.add(reservaButton);
		this.add(banco);
		this.add(reserva);
		this.add(tituloEvento);
		this.add(precioEvento);
		
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControladores(ActionListener c) {
		this.compraButton.addActionListener(c);
		this.reservaButton.addActionListener(c);
		this.comboZonas.addActionListener(c);
		this.comboDias.addActionListener(c);
		this.banco.setControladores(c);
		this.reserva.setControladores(c);
	}
	
	
	/**
	 * Crea el comboBox de las zonas
	 */
	public void setComboZonas() {
		this.comboZonas.removeAllItems();
		this.comboZonas.addItem("Zonas");
		for(Zona z : Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas())) {
			this.comboZonas.addItem(z.getNombre());
		}
			
	}
	
	/**
	 * Crea el comboBox de los dias a partir de un evento
	 * 
	 * @param evento
	 */
	public void setComboDias(Evento evento) {
		this.comboDias.removeAllItems();
		this.comboDias.addItem("Fechas");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		for(Representacion r :evento.getRepresentaciones()) {
			this.comboDias.addItem(sdf.format(r.getFecha().getTime()));
		}
	}
	
	
	/**
	 * @return si el boton de comprar esta seleccionado o no
	 */
	public boolean compraSelected() {
		return compraButton.isSelected();
	}
	
	/**
	 * @return si el boton de reservar esta seleccionado o no
	 */
	public boolean reservaSelected() {
		return reservaButton.isSelected();
	}
	
	public String getDia() {
		return (String)this.comboDias.getSelectedItem();
	}
	
	public String getZona() {
		return this.comboZonas.getSelectedItem().toString();
	}
	
	public DatosBancarios getBanco() {
		return this.banco;
	}
	public ReservaEvento getReserva() {
		return this.reserva;
	}

	
	/**
	 * Actualiza los datos de los paneles de comprar a partir de un evento
	 * 
	 * @param e evento
	 */
	public void actualizar(Evento e) {
		this.setComboDias(e);
		this.setComboZonas();
		this.tituloEvento.setText(e.getNombre());
	}
	
	public String getTituloEvento() {
		return this.tituloEvento.getText();
	}
	
	public void setPrecio(float p) {
		this.precioEvento.setText(Float.toString(p));
	}
}
