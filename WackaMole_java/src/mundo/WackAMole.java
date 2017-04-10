package mundo;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class WackAMole extends Observable implements Observer{

	
	private static final String OFF = "0";
	
	private static final String ON = "1";
	
	private static final String SCORE = "2";
	
	private static final int TIME = 90;
	
	/**
	 * Atributo que modela la relación con el lector de Arduino
	 */
	private LectorArduino lector;
	
	/**
	 * Atributo que modela la lista de jugadores existentes
	 */
	private List<Jugador> jugadores;
	
	/**
	 * Atributo que modela la relación con el jugador que actualmente está jugando
	 */
	private Jugador actual;
	
	/**
	 * Atributo que modela el indicador si el juego que es de practica
	 */
	private boolean practica;
	
	/**
	 * Constructor del Juego
	 * @throws Exception Si ocurre alguna falla al conectar el Arduino
	 */
	public WackAMole(LectorArduino pLector) {
		lector = pLector;
		lector.addObserver(this);
		jugadores = ManejadorCSV.cargar();
	}
	
	/**
	 * Método que retorna los jugadores que se tienen desde BD
	 * @return Lista de los jugadores que han intentado el juego
	 */
	public List<Jugador> darJugadores(){
		return jugadores;
	}
	
	/**
	 * Método que retorna 
	 * @return
	 */
	public Jugador darActual(){
		return actual;
	}
	
	/**
	 * Método que ordena la lista de jugadores
	 */
	public void ordenarJugadores(){
		
		for (int i = 0; i < jugadores.size(); i++) {
			for (int j = i+1; j < jugadores.size(); j++) {
				Jugador actualI = jugadores.get(i);
				Jugador actualJ = jugadores.get(j);
				if(actualJ.darPuntaje()>actualI.darPuntaje()){
					jugadores.set(i, actualJ);
					jugadores.set(j, actualI);
				}
			}
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		String obj = (String)arg;
		if(obj.equals(SCORE)){
			actual.aumentarPuntaje();
			setChanged();
			notifyObservers(actual);
		}
		
	}

	public void jugar(String nombre) throws Exception{
		actual = new Jugador(nombre, 0);
		practica = false;
		lector.jugar();
		Thread t = new Thread(){
			public void run(){
				int contador = TIME;
				setChanged();
				notifyObservers(""+contador);
				while(contador>0){
					try {
						Thread.sleep(1000);
						contador--;
						setChanged();
						notifyObservers(""+contador);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
	}

	/**
	 * Método que acaba el juego
	 */
	public void terminarJuego() {
		lector.terminarJuego();
		if(!practica){
			jugadores.add(actual);
			ordenarJugadores();
			try {
				ManejadorCSV.guardar(jugadores);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void shutdown() {
		try {
			lector.close();
			ManejadorCSV.guardar(jugadores);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que inicia la práctica
	 */
	public void practicar() {
		actual = new Jugador("", 0);
		practica = true;
		lector.jugar();
		Thread t = new Thread(){
			public void run(){
				int contador = TIME;
				setChanged();
				notifyObservers(""+contador);
				while(contador>0){
					try {
						Thread.sleep(1000);
						contador--;
						setChanged();
						notifyObservers(""+contador);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
	}

}
