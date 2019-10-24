package model.logic;

/**
 * Clase que representa un viaje en UBER
 */
public class UBERTrip implements Comparable<UBERTrip>
{
	private short sourceid, dstid, hod;
	
	private float mtt, sdtt, gmtt, gsdtt;
	
	public UBERTrip(short pSourceid, short pDstid, short pHod, float pMtt, float pSdtt)
	{
		sourceid = pSourceid;
		dstid = pDstid;
		hod = pHod;
		mtt = pMtt;
		sdtt = pSdtt;
	
	}
	
	/**
	 * Retorna los datos del viaje
	 * @return los datos del viaje
	 */
	public double[] darDatosViaje()
	{
		double[] respuesta = new double[7];

		respuesta[0] = sourceid;
		respuesta[1] = dstid;
		respuesta[2] = hod;
		respuesta[3] = mtt;
		respuesta[4] = sdtt;
		respuesta[5] = gmtt;
		respuesta[6] = gsdtt;
		
		return respuesta;
	}

	@Override
	public int compareTo(UBERTrip param)
	{
		if(mtt > param.mtt)
		{
			return 1;
		}
		else if(mtt <  param.mtt)
		{
			return -1;
		}
		else
		{
			if(sdtt > param.sdtt)
			{
				return 1;
			}
			else if(sdtt < sdtt)
			{
				return -1;
			}
		}		
		return 0;
	}
	public short darIdorigen()
	{
		return sourceid;
	}
	/**
	 * Retorna el dato de la ID de destino
	 * @return la ID del destino
	 */
	public short darIddestino()
	{
		return dstid;
	}
	public short darTiempo()
	{
		return hod;
	}
	public float darMeanTravelTime()
	{
		return mtt;
	}
	public float darDesviacion()
	{
		return sdtt;
	}
}