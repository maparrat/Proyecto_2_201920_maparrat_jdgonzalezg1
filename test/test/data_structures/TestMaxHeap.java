package test.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.MaxHeapCP;

public class TestMaxHeap
{
	private MaxHeapCP cp;

	@Before
	public void setUp1() 
	{
		cp = new MaxHeapCP(1);
	}
	public void setUp2() 
	{
		cp.agregar(1);
		cp.agregar(2);
		cp.agregar(3);
		cp.agregar(4);
		cp.agregar(5);
		cp.agregar(6);
		cp.agregar(7);
		cp.agregar(8);
		cp.agregar(9);
		cp.agregar(10);
	}

	@Test
	public void testPut()
	{
		cp.agregar(5);
		cp.agregar(10);
		cp.agregar(11);

		System.out.println(cp.darNumeroElementos());
		assertTrue(cp.darNumeroElementos()+1 == 3);
	}

	@Test
	public void testDelete()
	{
		setUp2();
		cp.sacarMax();
		System.out.println(cp.darNumeroElementos());

		assertTrue(cp.darNumeroElementos()+1 == 9);
	}
}