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
	public void cargarArchivoCSV(int numeroMes) throws Exception
	{		
		if(primero == null)
		{
			primero = new Node();
			actual = primero;
		}
		else
		{
			actual = primero;
			while(actual.darSiguente() != null)
			{
				actual = actual.darSiguente();
			}
			actual.asignarSiguiente(new Node());
			actual = actual.darSiguente();
		}

		INode anterior = null;
		boolean primeraLectura = true;

		CSVReader reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-" + numeroMes + "-All-MonthlyAggregate.csv"));

		for(String[] line: reader)
		{
			if(!primeraLectura)
			{
				Double[] dato = {Double.parseDouble(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2]), Double.parseDouble(line[3]), Double.parseDouble(line[4]), Double.parseDouble(line[5]), Double.parseDouble(line[6])}; 
				actual.asignarDato(dato);
				actual.asignarSiguiente(new Node());
				anterior = actual;
				actual = actual.darSiguente();
				tamano++;
			}
			primeraLectura = false;
		}

		//Queda un nodo vacío, entonces se elimina
		anterior.asignarSiguiente(null);
		tamano--;

		reader.close();
	}

	/**
	 * Retorna el número de elementos en el modelo
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return tamano;
	}

	/**
	 * Crea una lista encadenada con los datos buscados
	 * @param mes Mes a buscar
	 * @param zonaOrigen Zona de origen a buscar
	 * @return Un nodo que inicia la lista encadenada de respuesta
	 */
	public Node busquedaPorMesYZonaOrigen(double mes, double zonaOrigen)
	{
		actual = primero;

		Node respuesta = new Node();
		Node anteriorRespuesta = null;
		Node actualRespuesta = respuesta;

		while(actual != null)
		{
			Double[] datos = (Double[]) actual.darDato();
			if(datos[2] == mes && datos[0] == zonaOrigen)
			{
				actualRespuesta.asignarDato(datos);
				actualRespuesta.asignarSiguiente(new Node());
				anteriorRespuesta = actualRespuesta;
				actualRespuesta = actualRespuesta.darSiguente();
			}
			actual = actual.darSiguente();
		}

		if(respuesta.darDato() == null)
		{
			return null;
		}
		else
		{
			//Queda un nodo de más
			anteriorRespuesta.asignarSiguiente(null);
			return respuesta;
		}
	}

	/**
	 * Indica el número de viajes en el mes indicado
	 * @param mes Mes a buscar
	 * @return el número de viajes en el mes indicado
	 */
	public double numeroViajesSegunMes(double mes)
	{
		actual = primero;
		double respuesta = 0;
		
		while(actual != null)
		{
			Double[] datos = (Double[]) actual.darDato();
			if(datos[2] == mes)
			{
				respuesta++;
			}
			actual = actual.darSiguente();
		}
		return respuesta;
	}
	
	/**
	 * Indica el número de viajes en el mes indicado con la zona de origen indicada
	 * @param mes Mes a buscar
	 * @param zonaDeOrigen Zona de origen a buscar
	 * @return el número de viajes en el mes indicado con la zona de origen indicada
	 */
	public double numeroViajesSegunMesYZonaOrigen(double mes, double zonaDeOrigen)
	{
		actual = primero;
		double respuesta = 0;
		
		while(actual != null)
		{
			Double[] datos = (Double[]) actual.darDato();
			if(datos[2] == mes && datos[0] == zonaDeOrigen)
			{
				respuesta++;
			}
			actual = actual.darSiguente();
		}
		return respuesta;
	}
}