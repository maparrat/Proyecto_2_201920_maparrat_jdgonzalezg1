package test.data_structures;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.SeparateChaining;

public class TestSeparateChaining
{
	private SeparateChaining<Integer, String> sc;

	@Before
	public void setUp1() 
	{
		sc = new SeparateChaining<>(5);
	}
	public void setUp2() 
	{
		sc.put(1, "A");
		sc.put(2, "B");
		sc.put(3, "C");
		sc.put(4, "D");
		sc.put(5, "E");
		sc.put(6, "F");
		sc.put(7, "G");
		sc.put(8, "H");
		sc.put(9, "I");
		sc.put(10, "J");
	}

	@Test
	public void testPut()
	{
		sc.put(5, "A");
		sc.put(10, "B");
		sc.put(11, "C");
		sc.put(11, "D");

		assertTrue(sc.contains(11));
		assertTrue(sc.size() == 3);
		assertNotNull(sc.get(10));
	}

	@Test
	public void testGet()
	{
		setUp2();
		assertEquals("A", sc.get(1));
		assertEquals("B", sc.get(2));
		assertEquals("C", sc.get(3));
		assertEquals("D", sc.get(4));
		assertEquals("E", sc.get(5));
		assertNotEquals("A", sc.get(6));
		assertNotEquals("A", sc.get(7));
		assertNotEquals("A", sc.get(8));
		assertNotEquals("A", sc.get(9));
		assertNotEquals("A", sc.get(10));
	}

	@Test
	public void testDelete()
	{
		setUp2();
		assertEquals("A", sc.delete(1));
		assertNull(sc.get(1));
		assertEquals("B", sc.delete(2));
		assertNull(sc.get(2));
		assertEquals("C", sc.delete(3));
		assertNull(sc.get(3));
		assertEquals("D", sc.delete(4));
		assertNull(sc.get(4));
		assertEquals("E", sc.delete(5));
		assertNull(sc.get(5));
	}
	
	@Test
	public void testKeys()
	{
		setUp2();
		Iterator<Integer> respuesta = sc.keys();
		
		assertTrue(respuesta.hasNext());
		
		for (int i = 0; i < 10; i++)
		{
			respuesta.next();
		}
		assertFalse(respuesta.hasNext());
	}
	
	@Test
	public void testRehash()
	{
		sc.rehash(4);
		assertEquals(4, sc.tamanoArreglo());
	}
}