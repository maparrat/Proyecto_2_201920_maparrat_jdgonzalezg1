package view;

public class MVCView 
{
	/**
	 * Metodo constructor
	 */
	public MVCView()
	{}

	/**
	 * Método que imprime el menú por consola
	 */
	public void printMenu()
	{
		System.out.println("1. Cargar archivo");
		System.out.println("2. Consultar viajes según mes y zona de origen");
		System.out.println("3. Ver el total de viajes reportados en el semestre");
		System.out.println("4. Ver el total de viajes según mes");
		System.out.println("5. Ver el total de viajes según mes y zona de origen");
		System.out.println("6. Exit");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}
}