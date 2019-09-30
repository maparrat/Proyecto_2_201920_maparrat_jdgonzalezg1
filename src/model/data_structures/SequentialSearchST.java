package model.data_structures;

import java.util.Iterator;

/** 
 * Clase auxiliar para la estructura Separate chaining
 * Tomado de Algorithms 4th edition by Robert Sedgewick and Kevin Wayne (2011)
 * Consultado el 25/09/19
 * Disponible en https://algs4.cs.princeton.edu/code/
 */
public class SequentialSearchST<K, V>
{
	private int N;           // number of key-value pairs
	private Node first;      // the linked list of key-value pairs

	// a helper linked list data type
	private class Node
	{
		private K key;
		private V val;
		private Node next;

		public Node(K key, V val, Node next) 
		{
			this.key  = key;
			this.val  = val;
			this.next = next;
		}
		
		public void asignarDato(V pDato) 
		{
			val = pDato;
		}
		
		public void asignarSiguiente(Node pSiguiente) 
		{
			next = pSiguiente;
		}
		
		public Node darSiguente()
		{
			return next;
		}
		
		public V darDato()
		{
			return val;
		}
	}

	/**
	 * Initializes an empty symbol table.
	 */
	public SequentialSearchST()
	{}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size()
	{
		return N;
	}

	/**
	 * Returns true if this symbol table is empty.
	 * @return {@code true} if this symbol table is empty;
	 *         {@code false} otherwise
	 */
	public boolean isEmpty()
	{
		return size() == 0;
	}

	/**
	 * Returns true if this symbol table contains the specified key.
	 * @param  key the key
	 * @return {@code true} if this symbol table contains {@code key};
	 *         {@code false} otherwise
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public boolean contains(K key)
	{
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	/**
	 * Returns the value associated with the given key in this symbol table.
	 * @param  key the key
	 * @return the value associated with the given key if the key is in the symbol table
	 *     and {@code null} if the key is not in the symbol table
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public V get(K key)
	{
		if (key == null) throw new IllegalArgumentException("argument to get() is null"); 
		for (Node x = first; x != null; x = x.next)
		{
			if (key.equals(x.key))
				return x.val;
		}
		return null;
	}

	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old 
	 * value with the new value if the symbol table already contains the specified key.
	 * Deletes the specified key (and its associated value) from this symbol table
	 * if the specified value is {@code null}.
	 * @param  key the key
	 * @param  val the value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void put(K key, V val)
	{
		if (key == null) throw new IllegalArgumentException("first argument to put() is null"); 
		if (val == null)
		{
			delete(key);
			return;
		}

		for (Node x = first; x != null; x = x.next)
		{
			if (key.equals(x.key))
			{
				x.val = val;
				return;
			}
		}

		first = new Node(key, val, first);
		N++;
	}

	/**
	 * Removes the specified key and its associated value from this symbol table     
	 * (if the key is in this symbol table).    
	 * @param  key the key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public V delete(K key)
	{
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");

		if(first.key.equals(key))
		{
			N--;
			Node toEliminate = first;
			first = first.next;
			return toEliminate.val;
		}
		else
		{
			Node anterior = first;
			Node actual = first.next;

			while(actual != null)
			{
				if (actual.key.equals(key))
				{
					N--;
					anterior.next = actual.next;
					return actual.val;
				}
				anterior = actual;
				actual = actual.next;
			}
			return null;
		}
	}

	/**
	 * Returns all keys in the symbol table as an {@code Iterable}.
	 * To iterate over all of the keys in the symbol table named {@code st},
	 * use the foreach notation: {@code for (Key key : st.keys())}.
	 * @return all keys in the symbol table
	 */
	public Iterator<K> keys()  {
		Queue<K> queue = new Queue<K>();
		for (Node x = first; x != null; x = x.next)
			queue.enqueue(x.key);
		return queue;
	}
}