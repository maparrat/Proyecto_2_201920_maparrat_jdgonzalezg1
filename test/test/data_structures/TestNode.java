package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.data_structures.Node;

public class TestNode
{
	private Node node;

	public void setUp1() 
	{
		node = new Node();
		node.asignarDato("Hola");
	}
	
	public void setUp2() 
	{
		int x = 1;
		int y = 2;
		Node psig = new Node();
		node= new Node();
		node.asignarDato(x);
		node.asignarSiguiente(psig);
		psig.asignarDato(y);
	}
	
	@Test
	public void testNode()
	{
		setUp1();
		assertTrue(node != null);
		assertEquals("Hola", node.darDato());
		assertEquals(null, node.darSiguente());
	}
	@Test
	public void testNode2()
	{
		setUp2();
		assertTrue(node != null);
		assertEquals(1, node.darDato());
		
		//Con el setUp2 y esta línea se prueban todos los métodos de la clase.
		assertEquals(2, node.darSiguente().darDato());
	}
}