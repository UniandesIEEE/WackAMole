package mundo;

public class Jugador {

	/**
	 * Atributo que modela el nombre del jugador
	 */
	private String nombre;
	
	/**
	 * Atributo que modela el puntaje del jugador
	 */
	private int puntaje;
	
	/**
	 * Contructor de la clase
	 * @param pNombre Nombre especificado desde BD
	 * @param pPuntaje Puntaje especificado desde BD
	 */
	public Jugador(String pNombre, int pPuntaje) {
		nombre=pNombre;
		puntaje=pPuntaje;
	}
	
	/**
	 * M�todo que retorna el nombre del jugador
	 * @return Nombre del jugador
	 */
	public String darNombre(){
		return nombre;
	}
	
	/**
	 * M�todo que retorna el puntaje del jugador
	 * @return Puntaje del jugador
	 */
	public int darPuntaje(){
		return puntaje;
	}
	
	/**
	 * M�todo que aumenta el puntaje del jugador
	 */
	public void aumentarPuntaje(){
		puntaje+=100;
	}
	
	public String toString(){
		return nombre+";;;"+puntaje;
	}
}
