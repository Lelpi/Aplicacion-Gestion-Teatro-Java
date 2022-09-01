package GUI.anyadirEvento;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * panel para añadir eventos nuevos
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class AnyadirEvento extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private DatosAnyadir datosEvento;
	private DescripcionAnyadir descripcionEvento;
	private DanzaDatos danzaDatos;
	private MusicaDatos musicaDatos;
	private TeatroDatos teatroDatos;
	private Precios panelPrecios;
	private JButton confirma;
	private JButton cancela;

	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public AnyadirEvento() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(1000, 600));
		
		datosEvento = new DatosAnyadir();
		descripcionEvento = new DescripcionAnyadir();
		danzaDatos = new DanzaDatos();
		musicaDatos = new MusicaDatos();
		teatroDatos = new TeatroDatos();
		panelPrecios = new Precios();
		confirma = new JButton("Añadir evento");
		cancela = new JButton("Cancelar");
		
		layout.putConstraint(SpringLayout.WEST, datosEvento, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, datosEvento, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, descripcionEvento, 0, SpringLayout.EAST, datosEvento);
		layout.putConstraint(SpringLayout.SOUTH, descripcionEvento, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, danzaDatos, 30, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, danzaDatos, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, musicaDatos, 30, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, musicaDatos, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, teatroDatos, 30, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, teatroDatos, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, panelPrecios, 30, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, panelPrecios, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, confirma, -40, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, confirma, 440, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, cancela, 0, SpringLayout.EAST, confirma);
		layout.putConstraint(SpringLayout.NORTH, cancela, 10, SpringLayout.SOUTH, confirma);
		
		danzaDatos.setVisible(false);
		musicaDatos.setVisible(false);
		teatroDatos.setVisible(false);
		panelPrecios.setVisible(false);
		
		this.add(datosEvento);
		this.add(descripcionEvento);
		this.add(danzaDatos);
		this.add(musicaDatos);
		this.add(teatroDatos);
		this.add(panelPrecios);
		this.add(confirma);
		this.add(cancela);
	}
	
	public void setControladores(ActionListener c) {
		this.confirma.addActionListener(c);
		this.cancela.addActionListener(c);
		this.datosEvento.setControladores(c);
		this.descripcionEvento.setControladores(c);
		this.panelPrecios.setControladores(c);
	}
	
	public DatosAnyadir getPanelDatos() {
		return this.datosEvento;
	}
	
	public DescripcionAnyadir getPanelDescripcion() {
		return this.descripcionEvento;
	}
	public DanzaDatos getPanelDanza() {
		return this.danzaDatos;
	}
	public MusicaDatos getPanelMusica() {
		return this.musicaDatos;
	}
	public TeatroDatos getPanelTeatro() {
		return this.teatroDatos;
	}
	public Precios getPanelPrecios() {
		return this.panelPrecios;
	}
	
	/**
	 * actualiza la información de los componentes de AnyadirEvento
	 */
	public void actualizar() {
		this.panelPrecios.actualizar();
	}
	
}
