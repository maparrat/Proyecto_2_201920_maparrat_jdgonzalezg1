package test.logic;

import static org.junit.Assert.*;

import model.data_structures.Node;
import model.logic.MVCModelo;

import org.junit.Before;
import org.junit.Test;

public class TestMVCModelo
{	
	private MVCModelo modelo;

	@Before
	public void setUp()
	{
		modelo= new MVCModelo();
		try
		{
			modelo.cargarArchivosViajes(1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	

	@Test
	public void testDarTamano()
	{
		assertTrue("Deberian haber elementos", modelo.darTamanoViajesWeekly()>1000000);
	}	
}