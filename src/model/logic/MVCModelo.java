package model.logic;

import java.io.FileReader;

import com.opencsv.CSVReader;

import com.google.gson.*;

import model.data_structures.INode;
import model.data_structures.MaxHeapCP;
import model.data_structures.Node;
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
		
		for(String[] line: reader)
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

		int i = 0;
		
		for(String[] line: reader)
		{
			if(!primeraLectura && i < 4000000)
			{
				UBERTrip nuevo = new UBERTrip(Short.parseShort(line[0]), Short.parseShort(line[1]), Short.parseShort(line[2]), Float.parseFloat(line[3]), Float.parseFloat(line[4]), Float.parseFloat(line[5]), Float.parseFloat(line[6])); 
				String key = trimestre + "-" + line[0] + "-" + line[1];
				viajesHourly.put(key, nuevo);
				i++;
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

	//---------------------------------------------------------------------
	//Parte A
	//---------------------------------------------------------------------

	public String[] letrasMasComunes(int N)
	{
		return null;
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