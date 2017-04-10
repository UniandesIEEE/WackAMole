package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mundo.WackAMole;

public class InterfazWackAMole extends JFrame implements Observer{

	/**
	 * Atributo que modela el panel del banner
	 */
	private PanelBanner pnlBanner;

	/**
	 * Atributo que modela el panel del juego
	 */
	private PanelJuego pnlJuego;

	/**
	 * Atributo que modela el panel de las opciones del juego
	 */
	private PanelOpciones pnlOpciones;

	/**
	 * Atributo que modela la relación con el mundo
	 */
	private WackAMole juego;

	/**
	 * Constructor de la interfaz principal del juego
	 */
	public InterfazWackAMole() {
		setLayout(new BorderLayout());
		setTitle("Wack-A-Mole V2.0");
		setResizable(false);
		setSize(1000, 800);
		ImageIcon imagen = new ImageIcon("data/icon.png");
		setIconImage(imagen.getImage());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		juego = new WackAMole();
		juego.addObserver(this);

		pnlBanner = new PanelBanner();
		add(pnlBanner,BorderLayout.NORTH);

		pnlOpciones = new PanelOpciones(this);
		add(pnlOpciones,BorderLayout.EAST);

		pnlJuego = new PanelJuego();
		add(pnlJuego,BorderLayout.CENTER);

		JPanel pnlInf = new JPanel();
		pnlInf.setBackground(Color.BLACK);
		pnlInf.setPreferredSize(new Dimension(50,50));
		pnlInf.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		add(pnlInf,BorderLayout.SOUTH);

		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		InterfazWackAMole i = new InterfazWackAMole();
		i.setVisible(true);
	}

	public void jugar() {
		String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del jugador");
		if(nombre!=null&&!nombre.equals("")){
			try {
				juego.jugar(nombre);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(this, "Ingrese un nombre válido.","Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void practicar() {
		// TODO Auto-generated method stub

	}

	public void leaderboard() {
		// TODO Auto-generated method stub

	}

	public void about() {
		// TODO Auto-generated method stub

	}
	
	public void terminarJuego(){
		juego.terminarJuego();
	}
	
	public void dispose(){
		terminarJuego();
		super.dispose();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		String line = (String) arg1;
		int val = Integer.parseInt(line);
		pnlJuego.actualizarTiempo(val);
		if(val==0){
			JOptionPane.showMessageDialog(this, "¡Se acabó el tiempo!","Timeout",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
