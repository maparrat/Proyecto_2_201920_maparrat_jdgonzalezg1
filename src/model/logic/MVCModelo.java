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

	private RedBlackBST<String, UBERTrip> viajesMonthly, viajesWeekly, viajesHourly;

	/**
	 * Constructor del modelo del mundo
	 */
	public MVCModelo()
	{
		zonas = new MaxHeapCP<>(1);
		nodos = new SeparateChaining<>(1);
		viajesMonthly = new RedBlackBST<>();
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
				UBERTrip nuevo = new UBERTrip(Short.parseShort(line[0]), Short.parseShort(line[1]), Short.parseShort(line[2]), Float.parseFloat(line[3]), Float.parseFloat(line[4]), Float.parseFloat(line[5]), Float.parseFloat(line[6])); 
				String key = trimestre + "-" + line[0] + "-" + line[1];
				viajesMonthly.put(key, nuevo);
			}
			primeraLectura = false;
		}

		primeraLectura = true;

		reader = new CSVReader(new FileReader("data/bogota-cadastral-2018-" + trimestre + "-WeeklyAggregate.csv"));

		for(String[] line: reader)
		{
			if(!primeraLectura)
			{
				UBERTrip nuevo = new UBERTrip(Short.parseShort(line[0]), Short.parseShort(line[1]), Short.parseShort(line[2]), Float.parseFloat(line[3]), Float.parseFloat(line[4]), Float.parseFloat(line[5]), Float.parseFloat(line[6])); 
				String key = trimestre + "-" + line[0] + "-" + line[1];
				viajesWeekly.put(key, nuevo);
			}
			primeraLectura = false;
		}

		primeraLectura = true;

		reader = new CSVReader(new FileReader("data/bogota-cadastral-2018-" + trimestre + "-All-HourlyAggregate.csv"));

		for (int i = 0; i < 1000000; i++)
		{
			String[] line = reader.readNext();
			
			if(!primeraLectura)
			{
				UBERTrip nuevo = new UBERTrip(Short.parseShort(line[0]), Short.parseShort(line[1]), Short.parseShort(line[2]), Float.parseFloat(line[3]), Float.parseFloat(line[4]), Float.parseFloat(line[5]), Float.parseFloat(line[6])); 
				String key = trimestre + "-" + line[0] + "-" + line[1];
				viajesHourly.put(key, nuevo);
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
	 * Retorna el n�mero de elementos en el modelo
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

	public class contadora implements Comparable<contadora>
	{
		private  int cantidadVeces ;
		private  String letra;

		private Queue<ZonaUBER> zonas;
		public contadora(String zletra)
		{
			letra = zletra;
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

		public int compareTo(contadora param) 
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

	//---------------------------------------------------------------------
	//Parte A
	//---------------------------------------------------------------------

	public MaxHeapCP<contadora> letrasMasComunes(int N)
	{
		MaxHeapCP<ZonaUBER> zonasCopia = zonas;
		int contador = 0;
		contadora[] buscador = new contadora[27];

		while(zonasCopia.darNumeroElementos() > 0)
		{
			ZonaUBER zonaActual = zonasCopia.sacarMax();
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
				buscador[contador] = new contadora(pletra);
				buscador[contador++].zonas.enqueue(zonaActual);
			}
		}

		MaxHeapCP<contadora> ordenado = new MaxHeapCP<>(27);
		for (int z = 0 ; z < N; z++)
		{
			ordenado.agregar(buscador[z]);
		}

		return ordenado;
	}

	public double[] nodosQuelimitanZonas(double longitid, double latitud)
	{
		return null;
	}

	public UBERTrip[] tiemposPromedioEnRango(int limiteBajo,int limiteAlto, int N)
	{
		return null;
	}

	//---------------------------------------------------------------------
	//Parte B
	//---------------------------------------------------------------------

	public double[] zonasMasAlNorte(int N)
	{
		return null;
	}

	public double[] nodosPorLocalizacion(double latitud, double longitud)
	{
		return null;
	}

	public UBERTrip[] tiemposDeEspera(int limiteAlto,  int limiteBajo, int N)
	{
		return null;
	}

	//---------------------------------------------------------------------
	//Parte C
	//---------------------------------------------------------------------

	public UBERTrip[] tiempoPromedioPorZona(int zonaSalida, int hora)
	{
		return null;
	}

	public UBERTrip[] tiempoPromedioPorRangoHora(int zonaSalida, int horaA, int horaB)
	{
		return null;
	}

	public Integer[] zonasMasNodos(int N)
	{
		return null;
	}

	public Integer[] tablaASCIIdatosFaltantes()
	{
		return null;
	}
}