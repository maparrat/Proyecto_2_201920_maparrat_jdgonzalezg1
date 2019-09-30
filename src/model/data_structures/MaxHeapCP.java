package model.data_structures;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 
 * Tomado de Algorithms 4th edition by Robert Sedgewick and Kevin Wayne (2011)
 * Consultado el 20/09/19
 * Disponible en http://www.albertstam.com/Algorithms.pdf
 *
 * @param <T>
 */

public class MaxHeapCP <T extends Comparable<T>> implements IMaxHeapCP<T>, Cloneable
{
	private int tamanoMax;

	private int tamanoAct;

	/**
	 * Arreglo de elementos de tamaño máximo
	 */
	private T elementos[];

	public MaxHeapCP(int tamanoTotal) {
		if(tamanoTotal < 0) throw new IndexOutOfBoundsException();
		tamanoMax = tamanoTotal;
		elementos = (T[])new Comparable[tamanoMax+1];
		tamanoAct = 0;
	}

	public boolean estaVacia() 
	{
		return tamanoAct == 0;
	}

	public T[] darElementos() 
	{
		return elementos;
	}

	public int darNumeroElementos()
	{
		return tamanoAct-1;
	}

	public void agregar(T elemento)
	{
		if(tamanoAct == tamanoMax - 1)
		{
			tamanoMax = 2 * tamanoMax;
			Object[] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];
			for(int i = 0; i <= tamanoAct; i++)
			{
				elementos[i] = (T)copia[i];
			} 
		}

		elementos[++tamanoAct] = elemento;		
		swim(tamanoAct);
	}

	public T sacarMax() {
		if(estaVacia()) throw new IndexOutOfBoundsException();
		T max = elementos[1];
		exch(1, tamanoAct);
		elementos[tamanoAct--] = null;
		sink(1);
		return max;
	}

	public T darMax()
	{
		return tamanoAct == 0 ? null: elementos[1];
	}

	private boolean less(int i, int j)
	{
		return elementos[i].compareTo(elementos[j]) < 0;
	}

	private void exch(int i, int j)
	{
		T swap = elementos[i];
		elementos[i] = elementos[j];
		elementos[j] = swap;
	}

	private void swim(int k)
	{
		while(k > 1 && less(k/2, k))
		{
			exch(k/2, k);
			k = k/2;
		}
	}

	private void sink(int k)
	{		
		while (2*k <= tamanoAct)
		{
			int j = 2*k;
			if (j < tamanoAct && less(j, j+1)) j++;
			if (!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}

	public Object clone()
	{
		Object obj = null;
		try
		{obj=super.clone();}
		catch(CloneNotSupportedException e)
		{System.out.println("No se puede clonar");}
		return obj;
	}
}