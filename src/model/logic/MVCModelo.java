package model.logic;

import java.io.FileReader;

import com.opencsv.CSVReader;

import model.data_structures.INode;
import model.data_structures.Node;

/**
 * Definicion del modelo del mundo
 */
public class MVCModelo{

	/**
	 * Atributos del modelo del mundo
	 */
	private INode primero;

	private INode actual;

	private int tamano;

	/**
	 * Constructor del modelo del mundo
	 */
	public MVCModelo()
	{
		tamano = 0;
	}

	/**
	 * Metodo que carga los archivos
	 * @param prutaArchivo CSV
	 */
	public void cargarArchivoViajes(int numeroMes) throws Exception
	{		
		boolean primeraLectura = true;

		CSVReader reader = new CSVReader(new FileReader("data/bogota-cadastral-2018-" + numeroMes + "-All-MonthlyAggregate.csv"));

		for(String[] line: reader)
		{
			if(!primeraLectura)
			{
				UBERTrip nuevo = new UBERTrip(Short.parseShort(line[0]), Short.parseShort(line[1]), Short.parseShort(line[2]), Float.parseFloat(line[3]), Float.parseFloat(line[4]), Float.parseFloat(line[5]), Float.parseFloat(line[6])); 
				actual.asignarDato(nuevo);
				tamano++;
			}
			primeraLectura = false;
		}
	}

	public void cargarArchivoNodos() throws Exception
	{
		if(xx.darTamano() == 0)
		{
			CSVReader reader = new CSVReader(new FileReader("data/Nodes_of_red_vial-wgs84_shp.txt.csv"));

			for(String[] line: reader)
			{
				NodoMallaVial nuevo = new NodoMallaVial(Double.parseDouble(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2])); 
				actual.asignarDato(nuevo);
				tamano++;
			}
		}
	}

	/**
	 * Retorna el número de elementos en el modelo
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return tamano;
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