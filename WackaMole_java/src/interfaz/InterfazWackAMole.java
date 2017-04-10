package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mundo.Jugador;
import mundo.LectorArduino;
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
	public InterfazWackAMole(LectorArduino lector) {
		setLayout(new BorderLayout());
		setTitle("Wack-A-Mole V2.0");
		setResizable(false);
		setSize(1000, 800);
		ImageIcon imagen = new ImageIcon("data/icon.png");
		setIconImage(imagen.getImage());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		juego = new WackAMole(lector);
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
		LectorArduino lector = new LectorArduino();
		try {
			lector.initialize();
			Thread f=new Thread() {
				public void run() {
					//the following line will keep this app alive for 1000 seconds,
					//waiting for events to occur and responding to them (printing incoming messages to console).
					try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
				}
			};
			f.start();
			InterfazWackAMole i = new InterfazWackAMole(lector);
			i.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que activa el juego
	 */
	public void jugar() {
		String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del jugador");
		if(nombre!=null&&!nombre.equals("")){
			try {
				juego.jugar(nombre);
				pnlJuego.inicio(juego.darActual());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(this, "Ingrese un nombre válido.","Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Método que activa el modo práctica
	 */
	public void practicar() {
		juego.practicar();
	}

	public void leaderboard() {
		
		Font fuente = new Font("Impact", Font.PLAIN, 25);
		
		List<Jugador> jugadores = juego.darJugadores();
		
		JPanel pnl = new JPanel();
		pnl.setLayout(new GridLayout(jugadores.size()+1, 2));
		
		JLabel lbl = new JLabel("Player");
		lbl.setFont(fuente);
		pnl.add(lbl);
		
		JLabel lbl1 = new JLabel("Score");
		lbl1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl1.setFont(fuente);
		pnl.add(lbl1);
		
		int contador = 1;
		
		for (Jugador jugador : jugadores) {
			JLabel temp = new JLabel(""+contador+". "+jugador.darNombre());
			temp.setFont(fuente);
			pnl.add(temp);
			
			JLabel score = new JLabel(""+jugador.darPuntaje());
			score.setHorizontalAlignment(SwingConstants.RIGHT);
			score.setFont(fuente);
			pnl.add(score);
			
			contador++;
		}
		
		JOptionPane.showMessageDialog(this, pnl,"Leaderboard",JOptionPane.PLAIN_MESSAGE);
	}

	public void about() {
		// TODO Auto-generated method stub

	}

	public void terminarJuego(){
		juego.terminarJuego();
	}

	public void dispose(){
		super.dispose();
		juego.shutdown();
		System.exit(0);
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		if(arg1 instanceof Jugador){
			Jugador jug = (Jugador) arg1;
			pnlJuego.actualizarPuntaje(""+jug.darPuntaje());
		}
		else{
			String line = (String) arg1;
			int val = Integer.parseInt(line);
			pnlJuego.actualizarTiempo(val);
			if(val==0){
				terminarJuego();
				JOptionPane.showMessageDialog(this, "¡Se acabó el tiempo!","Timeout",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
