package mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ManejadorCSV 
{
	private static final String RUTA = "data/players.txt";

	public static synchronized void guardar(List<Jugador> jugadores)throws Exception
	{
		try
		{
			File archivo = new File(RUTA);
			if(!archivo.exists())
			{
				archivo.createNewFile();
			}

			PrintWriter out = new PrintWriter(archivo);

			for (Jugador object : jugadores) 
			{
				Jugador dato = object; 
				out.println(dato.toString());
			}
			out.close();

		}
		catch(Exception e)
		{
			throw new Exception("No se pudo crear el archivo con el reporte.");
		}

	}
	public static synchronized List<Jugador> cargar(){
		List<Jugador> jugadores = new ArrayList<Jugador>();
		try{
			File archivo = new File(RUTA);
			BufferedReader bf = new BufferedReader(new FileReader(archivo));
			while(bf.ready()){
				String[] params = bf.readLine().split(";;;");
				jugadores.add(new Jugador(params[0], Integer.parseInt(params[1])));
			}
			bf.close();
		}
		catch(Exception e){
			
		}
		return jugadores;
	}
}
