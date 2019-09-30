package model.data_structures;

import java.util.Iterator;

public interface ISeparateChaining<K, V> 
{
	/**
	 * Agregaa una dupla (K, V) a la tabla. Si la llave K existe, se reemplaza su valor V asociado. V no puede ser null.
	 * @param k
	 * @param v
	 */
	public void put(K k, V v);
	
	/**
	 * Obtiene el valor V asociado a la llave K. Se obtiene null solo si la llave K no existe.
	 * @param k
	 */
	public V get(K k);
	
	/**
	 * Borra la dupla asociada a la llave K. Se obtiene el valor V asociado a la llave K. Se obtiene null solo si la llave K no existe.
	 * @param k
	 * @return el valor asociado a la llave k
	 */
	public V delete(K k);
	
	/**
	 * Conjunto de llaves K presentes en la tabla
	 * @return las llaves presentes en la tabla
	 */
	public Iterator<K> keys();
}