package model.logic;

import model.data_structures.Queue;

public class ZonaUBER implements Comparable<ZonaUBER>
{
	private String type;

	private Queue<double[]> coordinates;

	private int cartodb_id;

	private String scacodigo;

	private int scatipo;

	private String scanombre;

	private double shape_leng, shape_area;

	private String MOVEMENT_ID, DISPLAY_NAME;

	public ZonaUBER(String pType, Queue<double[]> pCoordinates, int pCartodb_id, String pScacodigo, int pScatipo, String pScanombre, double pShape_leng, double pShape_area, String pMOVEMENT_ID, String pDISPLAY_NAME)
	{
		type = pType;
		coordinates = pCoordinates;
		cartodb_id = pCartodb_id;
		scacodigo = pScacodigo;
		scatipo = pScatipo;
		scanombre = pScanombre;
		shape_leng = pShape_leng;
		shape_area = pShape_area;
		MOVEMENT_ID = pMOVEMENT_ID;
		DISPLAY_NAME = pDISPLAY_NAME;
	}

	@Override
	public int compareTo(ZonaUBER param)
	{
		if(coordinates.darNumeroElementos() > param.coordinates.darNumeroElementos())
		{
			return 1;
		}
		else if(coordinates.darNumeroElementos() <  param.coordinates.darNumeroElementos())
		{
			return -1;
		}
		return 0;
	}
	
	public String darNombre()
	{
		return DISPLAY_NAME;
	}
	
	public String darMID()
	{
		return MOVEMENT_ID;
	}
	
	public String darType()
	{
		return type;
	}
	
	public Queue darCoordinates()
	{
		return coordinates;
	}
	
	public String darScanombre()
	{
		return scanombre;
	}
	
	public double darShape_leng()
	{
		return shape_leng;
	}
	
	public double darShape_area()
	{
		return shape_area;
	}
	
	public int darCartob_id()
	{
		return cartodb_id;
	}
	
	public String darScacodigo()
	{
		return scacodigo;
	}
	
	public int darScatipo()
	{
		return scatipo;
	}
}