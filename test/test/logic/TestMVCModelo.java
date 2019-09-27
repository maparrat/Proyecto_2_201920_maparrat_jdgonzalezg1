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
			modelo.cargarArchivoCSV(1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	

	@Test
	public void testDarTamano()
	{
		assertTrue("Deberian haber elementos", modelo.darTamano()>1000000);
	}	

	@Test
	public void testBusquedaPorMesYZonaOrigen()
	{
		Node respuesta = modelo.busquedaPorMesYZonaOrigen(2, 955);
		assertNotNull("Debería devolver la lista con al menos un nodo.", respuesta);
	}

	@Test
	public void testNumeroViajesSegunMes()
	{
		double respuesta = modelo.numeroViajesSegunMes(2);
		assertTrue("Deberían existir elementos", respuesta > 0);	
	}

	@Test
	public void testNumeroViajesSegunMesYZonaOrigen()
	{
		double respuesta = modelo.numeroViajesSegunMesYZonaOrigen(2, 955);
		assertTrue("Deberían existir elementos", respuesta > 0);	
	}
}