package GUI.panelBienvenida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * JPanel, con la posibilidad de iniciar sesion o registrarse
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class BienvenidaCuentas extends JPanel{
	
	private BienvenidaIniciarSesion bienvenidaIniciarSesion;
	private BienvenidaRegistrarse bienvenidaRegistrarse;
	private JButton loginGestor;
	private JPasswordField contGestor;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 * @param width anchura del panel
	 * @param height altura del panel
	 */
	public BienvenidaCuentas (int width, int height) {
		SpringLayout l=new SpringLayout(); 
		this.setLayout(l);
		this.setPreferredSize(new Dimension(width,height));
		
		bienvenidaIniciarSesion=new BienvenidaIniciarSesion((int)(width/1.43),(int)(height/3.49));
		bienvenidaRegistrarse=new BienvenidaRegistrarse((int)(width/1.43),(int)(height/3.49));
		contGestor=new JPasswordField(10);
		contGestor.setSize((int)(width/2.7),(int)(height/11.8));
		loginGestor=new JButton("Login gestor");
		loginGestor.setSize((int)(width/5.16), (int)(height/6.55));
		
		l.putConstraint(SpringLayout.WEST, bienvenidaIniciarSesion,(int)(width/6.55), SpringLayout.WEST,this);
		l.putConstraint(SpringLayout.WEST, bienvenidaRegistrarse,(int)(width/6.55), SpringLayout.WEST,this);
		l.putConstraint(SpringLayout.WEST, contGestor,(int)(width/6.55), SpringLayout.WEST,this);
		l.putConstraint(SpringLayout.WEST, loginGestor,(int)(width/6.55), SpringLayout.WEST,this);
		
		l.putConstraint(SpringLayout.NORTH, bienvenidaIniciarSesion,(int)(height/20.31), SpringLayout.NORTH,this);
		l.putConstraint(SpringLayout.NORTH, bienvenidaRegistrarse,(int)(height/8.73), SpringLayout.SOUTH,bienvenidaIniciarSesion);
		l.putConstraint(SpringLayout.NORTH, contGestor,(int)(height/20.63), SpringLayout.SOUTH,bienvenidaRegistrarse);
		l.putConstraint(SpringLayout.NORTH, loginGestor,(int)(height/37.83), SpringLayout.SOUTH,contGestor);

		
		this.add(bienvenidaIniciarSesion);
		this.add(bienvenidaRegistrarse);
		this.add(contGestor);
		this.add(loginGestor);
	}
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.bienvenidaIniciarSesion.setControlador(c);
		this.bienvenidaRegistrarse.setControlador(c);
		this.loginGestor.addActionListener(c);
	}
	
	public String getNombreUsuario() {
		return this.bienvenidaIniciarSesion.getNombreUsuario();
	}
	
	public String getContraUsuario() {
		return this.bienvenidaIniciarSesion.getContraUsuario();
	}
	
	public String getContraGestor() {
		String string="";
		for(char c : this.contGestor.getPassword()) {
			string+=c;
		}
		return string;
	}
}
