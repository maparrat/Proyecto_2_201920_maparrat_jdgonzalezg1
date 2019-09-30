package model.data_structures;

import java.util.Iterator;

public class Queue <T> implements IQueue <T>, Iterator<T>
{
	private Node front;
	private Node back;

	private int numeroElementos;

	public Queue()
	{
		numeroElementos = 0;
	}

	public void enqueue(T item) 
	{
		Node nuevo = new Node();
		nuevo.asignarDato(item);

		if(back == null)
		{
			front = nuevo;
		}
		else
		{
			back.asignarSiguiente(nuevo);
		}

		back = nuevo;

		numeroElementos++;
	}

	public T dequeue()
	{
		if(front != null)
		{
			T respuesta = (T) front.darDato();
			front = front.darSiguente();
			numeroElementos--;
			
			if(numeroElementos == 0)
			{
				back = null;
			}
			
			return respuesta;
		}
		else
		{
			return null;
		}
	}
	
	public int darNumeroElementos()
	{
		return numeroElementos;
	}
	
	public Node darPrimerNodo()
	{
		return front;
	}
	
	public Node darUltimoNodo()
	{
		return back;
	}

	@Override
	public boolean hasNext() {
		if(darPrimerNodo() != null)
			return true;
		else
			return false;
	}

	@Override
	public T next() {
		return dequeue();
	}


}