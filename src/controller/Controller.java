package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.logic.MVCModelo;
import model.logic.MVCModelo.contadora;
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
					System.out.println("Debe ingresar un valor numérico (1 o 4)\n---------");
					break;
				}

				if(numeroTrimestre >= 1 && numeroTrimestre <= 4)
				{
					try
					{
						modelo.cargarArchivosViajes(numeroTrimestre);
						modelo.cargarArchivoNodos();
						modelo.cargarArchivoZonas();
						System.out.println("Archivos cargados");
						System.out.println("Numero actual de zonas: " + modelo.darTamanoZonas()  + "\n---------");
						System.out.println("Numero actual de nodos: " + modelo.darTamanoNodos() + "\n---------");
						System.out.println("Numero actual de viajes por mes: " + modelo.darTamanoViajesMonthly() + "\n---------");
						System.out.println("Numero actual de viajes por día de la semana: " + modelo.darTamanoViajesWeekly() + "\n---------");
						System.out.println("Numero actual de viajes por hora: " + modelo.darTamanoViajesHourly() + "\n---------");
					}
					catch (Exception e)
					{
						e.printStackTrace();
						System.out.println("Se ha producido un error al cargar el archivo\n---------");
					}
				}
				else
				{
					System.out.println("Ingrese un valor válido (1 o 2)\n---------");	
				}
				break;

			case 2: 
				int numeroLetras;
				try
				{
					System.out.println("--------- \nDar cantidad de letras a buscar: ");
					numeroLetras = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}

				if(numeroLetras >= 1 && numeroLetras <= 27)
				{
					try
					{
						contadora[] letras = modelo.letrasMasComunes(numeroLetras);

						for (contadora contadoraActual : letras) {
							System.out.println("Letra: " + contadoraActual.darLetra());
							while(contadoraActual.darZonas().hasNext()){
								System.out.println("Nombre de la zona: " + contadoraActual.darZonas().next().darScanombre());
							}
							System.out.println("\n---------");

						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
						System.out.println("Se ha producido un error al cargar el archivo\n---------");
					}
				}
				else
				{
					System.out.println("Ingrese un valor válido (1 a 27)\n---------");	
				}
				break;

			case 3:

				break;

			default: 
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}
	}	
}