package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.data_structures.Node;
import model.logic.MVCModelo;
import view.MVCView;

public class Controller {

	public final static int maximoDeDatos = 20;
	
	/* Instancia del Modelo*/
	private MVCModelo modelo;

	/* Instancia de la Vista*/
	private MVCView view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}

	/**
	 * Hilo de ejecución del programa
	 */
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin )
		{
			view.printMenu();

			String in;
			in = lector.next();
			
			int option;
			try
			{
				option = Integer.parseInt(in);
			}
			catch(NumberFormatException e)
			{
				option = 0;
			}

			switch(option){
			case 1:

				int numeroTrimestre;
				try
				{
					System.out.println("--------- \nCargar archivo \nDar numero del trimestre: ");
					numeroTrimestre = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico (1 o 2)\n---------");
					break;
				}

				if(numeroTrimestre == 1 || numeroTrimestre == 2)
				{
					try
					{
						modelo.cargarArchivoViajes(numeroTrimestre);
						modelo.cargarArchivoNodos();
						System.out.println("Archivo cargado");
						System.out.println("Numero actual de elementos " + modelo.darTamano() + "\n---------");
					}
					catch (Exception e)
					{
						System.out.println("Se ha producido un error al cargar el archivo\n---------");
					}
				}
				else
				{
					System.out.println("Ingrese un valor válido (1 o 2)\n---------");	
				}
				break;

			case 2: 
				System.out.println("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	

			default: 
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}
	}	
}