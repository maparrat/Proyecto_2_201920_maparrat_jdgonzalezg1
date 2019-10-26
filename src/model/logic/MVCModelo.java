package model.logic;

import java.io.FileReader;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.opencsv.CSVReader;

import model.data_structures.MaxHeapCP;
import model.data_structures.Node;
import model.data_structures.Queue;
import model.data_structures.RedBlackBST;
import model.data_structures.SeparateChaining;

/**
 * Definicion del modelo del mundo
 */
public class MVCModelo{

	/**
	 * Atributos del modelo del mundo
	 */
	private MaxHeapCP<ZonaUBER> zonas;

	private SeparateChaining nodos;
	private Queue listaViajes;
	private RedBlackBST<Integer, UBERTrip> viajesMonthly, viajesWeekly, viajesHourly, viajesMonthyDesviacion;

	/**
	 * Constructor del modelo del mundo
	 */
	public MVCModelo()
	{
		zonas = new MaxHeapCP<>(1);
		nodos = new SeparateChaining<>(1);
		viajesMonthly = new RedBlackBST<>();
		viajesMonthyDesviacion =  new RedBlackBST<>();
		listaViajes = new Queue<>();
		viajesWeekly = new RedBlackBST<>();
		viajesHourly = new RedBlackBST<>();
	}

	/**
	 * Metodo que carga los archivos
	 * @param prutaArchivo CSV
	 */
	public void cargarArchivosViajes(int trimestre) throws Exception
	{		
		boolean primeraLectura = true;

		CSVReader reader = new CSVReader(new FileReader("data/bogota-cadastral-2018-" + trimestre + "-All-MonthlyAggregate.csv"));

		for(String[] line: reader )
		{
			if(!primeraLectura)
			{
				UBERTrip nuevo = new UBERTrip(Short.parseShort(line[0]), Short.parseShort(line[1]), Short.parseShort(line[2]), Float.parseFloat(line[3]), Float.parseFloat(line[4])); 
				Integer key = (int)(Double.parseDouble(line[3]));
				Integer key2 = (int)(Double.parseDouble(line[4]));
				viajesMonthly.put(key, nuevo);
				viajesMonthyDesviacion.put(key2, nuevo);
			}
			primeraLectura = false;
		}

		primeraLectura = true;

		reader = new CSVReader(new FileReader("data/bogota-cadastral-2018-" + trimestre + "-WeeklyAggregate.csv"));

		for(String[] line: reader)
		{
			if(!primeraLectura)
			{
				UBERTrip nuevo = new UBERTrip(Short.parseShort(line[0]), Short.parseShort(line[1]), Short.parseShort(line[2]), Float.parseFloat(line[3]), Float.parseFloat(line[4])); 
				Integer key = (int)(Double.parseDouble(line[3]));

				viajesWeekly.put(key, nuevo);
			}
			primeraLectura = false;
		}

		primeraLectura = true;

		reader = new CSVReader(new FileReader("data/bogota-cadastral-2018-" + trimestre + "-All-HourlyAggregate.csv"));

		for (int i = 0; i < 100000; i++)
		{
			String[] line = reader.readNext();

			if(!primeraLectura)
			{
				UBERTrip nuevo = new UBERTrip(Short.parseShort(line[0]), Short.parseShort(line[1]), Short.parseShort(line[2]), Float.parseFloat(line[3]), Float.parseFloat(line[4])); 
				Integer key = (int)(Double.parseDouble(line[2]));
				//Integer key = (int)(Double.parseDouble(line[3])*Double.parseDouble(line[4]));

				viajesHourly.put(key, nuevo);
				listaViajes.enqueue(nuevo);
			}
			primeraLectura = false;
		}
	}

	public void cargarArchivoNodos() throws Exception
	{
		if(nodos.size() == 0)
		{
			CSVReader reader = new CSVReader(new FileReader("data/Nodes_of_red_vial-wgs84_shp.txt.csv"));

			for(String[] line: reader)
			{
				NodoMallaVial nuevo = new NodoMallaVial(Double.parseDouble(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2])); 
				nodos.put(Double.parseDouble(line[0]), nuevo);
			}
		}
	}

	public void cargarArchivoZonas()
	{
		zonas = new MaxHeapCP<>(1);

		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader("data/bogota_cadastral.json"))
		{
			Object obj = jsonParser.parse(reader);

			JSONObject archivo = (JSONObject) obj;
			JSONArray array = (JSONArray) archivo.get("features");

			for(int i = 0; i < array.size(); i++)
			{
				JSONObject actual = (JSONObject) array.get(i);

				JSONObject geometry = (JSONObject) actual.get("geometry");
				Object[] coordinates1 = ((JSONArray) (((JSONArray) ((Object[]) (((JSONArray) geometry.get("coordinates")).toArray()))[0]).toArray())[0]).toArray();

				Queue<double[]> coordenadas = new Queue<>();

				for(int j = 0; j < coordinates1.length; j++)
				{
					double[] act = {(Double) ((JSONArray) coordinates1[j]).toArray()[0], (Double) ((JSONArray) coordinates1[j]).toArray()[1]};
					coordenadas.enqueue(act);
				}				

				JSONObject properties = (JSONObject) actual.get("properties");

				ZonaUBER nuevo = new ZonaUBER((String)geometry.get("type"), coordenadas, ((Long)properties.get("cartodb_id")).intValue(), (String)properties.get("scacodigo"), ((Long)properties.get("scatipo")).intValue(), (String)properties.get("scanombre"), (double)properties.get("shape_leng"), (double)properties.get("shape_area"), (String)properties.get("MOVEMENT_ID"), (String)properties.get("DISPLAY_NAME"));
				zonas.agregar(nuevo);
			}
		}
		catch (Exception e)
		{e.printStackTrace();}
	}

	/**
	 * Retorna el número de elementos en el modelo
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoZonas()
	{
		return zonas.darNumeroElementos();
	}

	public int darTamanoNodos()
	{
		return nodos.darNumeroDeElementos();
	}

	public int darTamanoViajesMonthly()
	{
		return viajesMonthly.size();
	}

	public int darTamanoViajesWeekly()
	{
		return viajesWeekly.size();
	}

	public int darTamanoViajesHourly()
	{
		return viajesHourly.size();
	}

	public class Contadora implements Comparable<Contadora>
	{
		private  int cantidadVeces ;
		private  String letra;

		private Queue<ZonaUBER> zonas;
		public Contadora(String pLetra)
		{
			letra = pLetra;
			cantidadVeces = 1;
			zonas = new Queue<>();
		}
		public int darCantidadVeces()
		{
			return cantidadVeces;
		}
		public String darLetra()
		{
			return letra;
		}

		public Queue<ZonaUBER> darZonas()
		{
			return zonas;
		}

		public int compareTo(Contadora param) 
		{
			if(darCantidadVeces() > param.darCantidadVeces())
			{
				return 1;
			}
			else if(darCantidadVeces() <  param.darCantidadVeces())
			{
				return -1;
			}
			return 0;
		}
	}

	public class Nodo
	{
		private double latitud;
		private double longitud;
		private String nombreZona;

		public Nodo(double pLatitud, double pLongitud, String pNombreZona)
		{
			latitud = pLatitud;
			longitud = pLongitud;
			nombreZona = pNombreZona;
		}

		public double darLatitud()
		{
			return latitud;
		}

		public double darLongitud()
		{
			return longitud;
		}

		public String darNombreZona()
		{
			return nombreZona;
		}
	}

	public class Zona implements Comparable<Zona>
	{
		private double latitudNorte;
		private double longitudNorte;
		private String nombreZona;

		public Zona(double pLatitudNorte, double pLongitudNorte, String pNombreZona)
		{
			latitudNorte = pLatitudNorte;
			longitudNorte = pLongitudNorte;
			nombreZona = pNombreZona;
		}

		public double darLatitudNorte()
		{
			return latitudNorte;
		}

		public double darLongitudNorte()
		{
			return longitudNorte;
		}

		public String darNombreZona()
		{
			return nombreZona;
		}

		public int compareTo(Zona param)
		{
			if(latitudNorte > param.latitudNorte)
			{
				return 1;
			}
			else if(latitudNorte <  param.latitudNorte)
			{
				return -1;
			}
			return 0;
		}
	}

	//---------------------------------------------------------------------
	//Parte A
	//---------------------------------------------------------------------

	public MaxHeapCP<Contadora> letrasMasComunes(int N)
	{
		int contador = 0;
		Contadora[] buscador = new Contadora[27];

		while(zonas.darNumeroElementos() > 0)
		{
			ZonaUBER zonaActual = zonas.sacarMax();
			String pletra = "" + zonaActual.darScanombre().charAt(0);

			boolean existe = false;
			for(int j= 0; j< buscador.length && existe == false; j++)
			{
				if (buscador[j] != null && buscador[j].darLetra().equalsIgnoreCase(pletra))
				{
					existe = true;
					buscador[j].cantidadVeces ++;
					buscador[j].zonas.enqueue(zonaActual);
				}
			}
			if(!existe)
			{
				buscador[contador] = new Contadora(pletra);
				buscador[contador++].zonas.enqueue(zonaActual);
			}
		}

		MaxHeapCP<Contadora> ordenado = new MaxHeapCP<>(27);
		for (int z = 0 ; z < N; z++)
		{
			ordenado.agregar(buscador[z]);
		}

		return ordenado;
	}

	public SeparateChaining<String, Nodo> nodosQuelimitanZonas(int pLatitud, int pLongitud)
	{
		SeparateChaining<String, Nodo> respuesta = new SeparateChaining<>(1);

		while(!zonas.estaVacia())
		{
			ZonaUBER actual = zonas.sacarMax();

			while(actual.darCoordinates().darNumeroElementos() > 0)
			{
				double[] coordenadaActual = (double[]) actual.darCoordinates().dequeue();

				double latitud = coordenadaActual[1];
				double longitud = coordenadaActual[0];

				int auxLat = (int)(latitud*1000);
				int auxLong = (int)(longitud*1000);

				if(auxLat == pLatitud && auxLong == pLongitud)
				{
					Nodo x = new Nodo(latitud, longitud, actual.darScanombre());
					respuesta.put(actual.darScanombre(), x);
				}				
			}
		}

		return respuesta;
	}

	public Queue<UBERTrip> tiemposPromedioEnRango(int limiteBajo,int limiteAlto)
	{
		Queue<UBERTrip> respuesta = (Queue<UBERTrip>) viajesMonthly.valuesInRange(limiteBajo,limiteAlto);
		
		return respuesta;
	}

	//---------------------------------------------------------------------
	//Parte B
	//---------------------------------------------------------------------

	public MaxHeapCP<Zona> zonasMasAlNorte()
	{
		MaxHeapCP<Zona> respuesta = new MaxHeapCP<Zona>(1);

		while(!zonas.estaVacia())
		{
			ZonaUBER actual = zonas.sacarMax();

			double latitudMaxima = -100.0;
			double longitudMaxima = -200.0;

			while(actual.darCoordinates().darNumeroElementos() > 0)
			{
				double[] coordenadaActual = (double[]) actual.darCoordinates().dequeue();

				double latitud = coordenadaActual[1];
				double longitud = coordenadaActual[0];

				if(latitud > latitudMaxima)
				{
					latitudMaxima = latitud;
					longitudMaxima = longitud;
				}				
			}
			respuesta.agregar(new Zona(latitudMaxima, longitudMaxima, actual.darScanombre()));			
		}

		return respuesta;
	}

	public SeparateChaining<Double, NodoMallaVial> nodosPorLocalizacion(int pLatitud, int pLongitud)
	{
		SeparateChaining<Double, NodoMallaVial> respuesta = new SeparateChaining<>(1);

		Queue llaves = (Queue) nodos.keys();

		while(llaves.darNumeroElementos() > 0)
		{
			NodoMallaVial actual = (NodoMallaVial) nodos.get(llaves.dequeue());

			double latitud = actual.darDatosViaje()[2];
			double longitud = actual.darDatosViaje()[1];

			int auxLat = (int)(latitud*1000);
			int auxLong = (int)(longitud*1000);

			if(auxLat == pLatitud && auxLong == pLongitud)
			{
				respuesta.put(actual.darDatosViaje()[0], actual);
			}
		}

		return respuesta;
	}

	public Queue<UBERTrip> tiemposDeEspera(int limiteAlto,  int limiteBajo)
	{
		Queue<UBERTrip> respuesta = (Queue<UBERTrip>) viajesMonthyDesviacion.valuesInRange(limiteBajo,limiteAlto);
		
		return respuesta;

	}


	//---------------------------------------------------------------------
	//Parte C
	//---------------------------------------------------------------------

	public Queue tiempoPromedioPorZona(int zonaSalida, int hora)
	{
		Queue<UBERTrip> respuesta = new Queue<>();
		Queue<UBERTrip> temp = listaViajes;
		while(temp.hasNext())
		{
			UBERTrip x = (UBERTrip) listaViajes.dequeue();
			if(x.darIdorigen() == zonaSalida && x.darTiempo() == hora)
			{
				respuesta.enqueue(x);
			}
		}
		return respuesta;
	}

	public Queue tiempoPromedioPorZonallegada(int zonallegada, int hora)
	{
		Queue<UBERTrip> respuesta = new Queue<>();
		Queue<UBERTrip> temp = listaViajes;
		while(temp.hasNext())
		{
			UBERTrip x = (UBERTrip) listaViajes.dequeue();
			if(x.darIddestino() == zonallegada && x.darTiempo() == hora)
			{
				respuesta.enqueue(x);
			}
		}
		return respuesta;
	}

	public Queue tiempoPromedioPorRangoHora(int zonallegada, int horaLo, int horaHi)
	{
		Queue<UBERTrip> respuesta = new Queue<>();

		for(int i = horaLo;i < horaHi ;i++ )
		{
			Queue<UBERTrip> temp= tiempoPromedioPorZonallegada(zonallegada, i);
			while(temp.hasNext())
			{
				UBERTrip z = temp.dequeue();
				respuesta.enqueue(z);
			}
		}

		return respuesta;
	}

	public MaxHeapCP<ZonaUBER> zonasMasNodos()
	{
		return zonas;
	}

	public boolean[][][] tablaASCIIdatosFaltantes()
	{
		boolean[][][] tabla = new boolean[1160][1160][24];

		Queue<Integer> llaves;
		try
		{
			llaves = (Queue<Integer>) viajesHourly.keys();

			while(llaves.darNumeroElementos() > 0)
			{
				UBERTrip actual = viajesHourly.get(llaves.dequeue());

				tabla[actual.darIdorigen()-1][actual.darIddestino()-1][actual.darTiempo()] = true;
			}	
		}
		catch (Exception e) {e.printStackTrace();}

		return tabla;
	}
}