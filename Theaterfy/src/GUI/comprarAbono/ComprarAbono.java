package GUI.comprarAbono;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import theaterfy.Theaterfy;
import theaterfy.sucesos.Ciclo;
import theaterfy.zona.Zona;

/**
 * JPanel para comprar un abono
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ComprarAbono extends JPanel {
	
	private JButton comprar;
	private JButton cancelar;
	private JComboBox<String> comboCiclos;
	private JComboBox<String> comboZonas;
	private JTextField tarjeta;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public ComprarAbono() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(1000, 600));
		
		JLabel titulo=new JLabel("Compra de Abonos");
		layout.putConstraint(SpringLayout.WEST, titulo, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, titulo, 10, SpringLayout.NORTH, this);
		
		JLabel tipo=new JLabel("1. Selecciona un tipo de abono:");
		layout.putConstraint(SpringLayout.WEST, tipo, 400, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, tipo, 60, SpringLayout.SOUTH, titulo);
        
		ArrayList<String> nombreCiclos=new ArrayList<String>();
		nombreCiclos.add("Anual");
        for(Ciclo c:Theaterfy.getTheaterfy().getPrecioAbonoCiclo().keySet()) {
        	nombreCiclos.add(c.getNombre());
        }
        String auxCiclos[]=new String[nombreCiclos.size()];
        auxCiclos=nombreCiclos.toArray(auxCiclos);
		comboCiclos = new JComboBox<String>(auxCiclos);
		layout.putConstraint(SpringLayout.WEST, comboCiclos, 400, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, comboCiclos, 10, SpringLayout.SOUTH, tipo);
		
		
		JLabel zona=new JLabel("2. Selecciona la zona");
		layout.putConstraint(SpringLayout.WEST, zona, 400, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, zona, 40, SpringLayout.SOUTH, comboCiclos);

		ArrayList<String> nombreZonas=new ArrayList<String>();
        for(Zona z:Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas())) {
            nombreZonas.add(z.getNombre());
        }
        String auxZonas[]=new String[nombreZonas.size()];
        auxZonas=nombreZonas.toArray(auxZonas);
		comboZonas = new JComboBox<String> (auxZonas);
		layout.putConstraint(SpringLayout.WEST, comboZonas, 400, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, comboZonas, 10, SpringLayout.SOUTH, zona);
		
		
		JLabel banco=new JLabel("3. Introduce tus datos bancarios");
		layout.putConstraint(SpringLayout.WEST, banco, 400, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, banco, 40, SpringLayout.SOUTH, comboZonas);
		JLabel numTarjeta = new JLabel("Numero de tarjeta");
		layout.putConstraint(SpringLayout.WEST, numTarjeta, 400, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, numTarjeta, 10, SpringLayout.SOUTH, banco);
		tarjeta=new JTextField(15);
		layout.putConstraint(SpringLayout.WEST, tarjeta, 8, SpringLayout.EAST, numTarjeta);
		layout.putConstraint(SpringLayout.NORTH, tarjeta, 10, SpringLayout.SOUTH, banco);
		JLabel nombreTitular = new JLabel("Nombre del titular");
		layout.putConstraint(SpringLayout.WEST, nombreTitular, 400, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, nombreTitular, 10, SpringLayout.SOUTH, tarjeta);
		JTextField titular = new JTextField(15);
		layout.putConstraint(SpringLayout.WEST, titular, 8, SpringLayout.EAST, numTarjeta);
		layout.putConstraint(SpringLayout.NORTH, titular, 10, SpringLayout.SOUTH, tarjeta);
		
		comprar=new JButton("Comprar");
		cancelar=new JButton("Cancelar");
		
		layout.putConstraint(SpringLayout.WEST, comprar, 400, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, comprar, 40, SpringLayout.SOUTH, titular);
		layout.putConstraint(SpringLayout.WEST, cancelar, 110, SpringLayout.EAST, comprar);
		layout.putConstraint(SpringLayout.NORTH, cancelar, 0, SpringLayout.NORTH, comprar);
		
		
		this.add(titulo);
		this.add(tipo);
		this.add(comboCiclos);
		this.add(zona);
		this.add(comboZonas);
		this.add(banco);
		this.add(numTarjeta);
		this.add(tarjeta);
		this.add(nombreTitular);
		this.add(titular);
		this.add(comprar);
		this.add(cancelar);
	}
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.comprar.addActionListener(c);
		this.cancelar.addActionListener(c);
	}
	
	public String getTarjeta() {
		return this.tarjeta.getText();
	}
	
	public String getZona() {
		return (String)this.comboZonas.getSelectedItem();
	}
	
	public String getCiclo() {
		return (String)this.comboCiclos.getSelectedItem();
	}
	
	public void actualizar() {
		comboCiclos.removeAllItems();
		comboCiclos.addItem("Anual");
        for(Ciclo c:Theaterfy.getTheaterfy().getPrecioAbonoCiclo().keySet()) {
        	comboCiclos.addItem(c.getNombre());
        }
        comboZonas.removeAllItems();
        for(Zona z:Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas())) {
            comboZonas.addItem(z.getNombre());
        }
	}
	
}
