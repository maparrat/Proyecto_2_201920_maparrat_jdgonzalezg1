package view;

public class MVCView 
{
	/**
	 * Metodo constructor
	 */
	public MVCView()
	{}

	/**
	 * M�todo que imprime el men� por consola
	 */
	public void printMenu()
	{
		System.out.println("1. Cargar archivo");
		System.out.println("2. Buscar las letras m�s frecuentes por las que comienza el nombre de una zona");
		System.out.println("3. Buscar los nodos que delimitan las zonas por Localizaci�n Geogr�fica");
		System.out.println("4. Buscar los tiempos promedio de viaje que est�n en un rango en el primer trimestre");
		System.out.println("5. Buscar las zonas que est�n m�s al norte");
		System.out.println("6. Buscar nodos de la malla vial por Localizaci�n Geogr�fica");
		System.out.println("7. Buscar los tiempos de espera que tienen una desviaci�n est�ndar en un rango dado en el primer trimestre");
		System.out.println("8. Buscar los tiempos de viaje promedio que salen de una zona dada y a una hora dada");
		System.out.println("9. Buscar los tiempos de viaje que llegan de una zona dada y en un rango de horas");
		System.out.println("10. Buscar las zonas priorizadas por la mayor cantidad de nodos que definen su frontera.");
		System.out.println("11. Generar gr�fica ASCII con el porcentaje de datos faltantes para el primer semestre");		
		System.out.println("12. Exit");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}
}