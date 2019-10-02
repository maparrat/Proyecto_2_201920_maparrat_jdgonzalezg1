package model.logic;

public class NodoMallaVial
{
	private double idNodo, longitud, latitud;

	public NodoMallaVial(double pIdNodo, double pLongitud, double pLatitud)
	{
		idNodo = pIdNodo;
		longitud = pLongitud;
		latitud = pLatitud;
	}

	/**
	 * Retorna los datos del viaje
	 * @return los datos del viaje
	 */
	public double[] darDatosViaje()
	{
		double[] respuesta = new double[3];

		respuesta[0] = idNodo;
		respuesta[1] = longitud;
		respuesta[2] = latitud;

		return respuesta;
	}
}