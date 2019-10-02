package test.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.RedBlackBST;

public class TestRedBlackBST
{
	private RedBlackBST<Integer, String> bt;

	@Before
	public void setUp1() 
	{
		bt = new RedBlackBST<>();
	}
	public void setUp2() 
	{
		bt.put(1, "A");
		bt.put(2, "B");
		bt.put(3, "C");
		bt.put(4, "D");
		bt.put(5, "E");
		bt.put(6, "F");
		bt.put(7, "G");
		bt.put(8, "H");
		bt.put(9, "I");
		bt.put(10, "J");
	}

	@Test
	public void testGet()
	{
		setUp2();
		assertEquals("A", bt.get(1));
		assertEquals("B", bt.get(2));
		assertEquals("C", bt.get(3));
		assertEquals("D", bt.get(4));
		assertEquals("E", bt.get(5));
		assertNotEquals("A", bt.get(6));
		assertNotEquals("A", bt.get(7));
		assertNotEquals("A", bt.get(8));
		assertNotEquals("A", bt.get(9));
		assertNotEquals("A", bt.get(10));
	}
	
	@Test
	public void testContains()
	{
		bt.put(5, "A");
		
		assertTrue(bt.contains(5));
		assertFalse(bt.contains(6));
	}
	
	@Test
	public void testPut()
	{
		bt.put(5, "A");
		bt.put(10, "B");
		bt.put(11, "C");
		bt.put(11, "D");

		assertTrue(bt.contains(11));
		assertTrue(bt.size() == 3);
		assertNotNull(bt.get(10));
	}

	@Test
	public void testDelete()
	{
		setUp2();
		bt.delete(5);
		assertNull(bt.get(5));
	}
}