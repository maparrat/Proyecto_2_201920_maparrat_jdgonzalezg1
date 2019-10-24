package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.data_structures.MaxHeapCP;
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
				//1A
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
						MaxHeapCP<contadora> letras = modelo.letrasMasComunes(Math.min(23, numeroLetras));

						int i = 1;
						
						while(letras.darNumeroElementos() > 0)
						{
							contadora contadoraActual = letras.sacarMax();
							System.out.println("Letra #" + i + ": " + contadoraActual.darLetra());
							System.out.println("Número de zonas con esta letra: " + contadoraActual.darZonas().darNumeroElementos());
							System.out.println("Primeras zonas con esta letra: ");
							
							int j = 1;
							
							while(contadoraActual.darZonas().hasNext() && j <= maximoDeDatos)
							{
								System.out.println("Nombre de la zona #" + j + ": " + contadoraActual.darZonas().next().darScanombre());
								j++;
							}
							System.out.println("---------");
							
							i++;
						}
												
						if(numeroLetras>23)
						{System.out.println("(Solo hay 23 letras que son iniciales de las zonas)");}
						
						System.out.println("(Se imprimen hasta 20 zonas por letra)\n---------");

					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("Ingrese un valor válido (1 a 27)\n---------");	
				}
				break;

			case 3:
				//2A
				
				break;
				
			case 4:
				//3A
				
				break;
				
			case 5:
				//1B
				
				int numeroZonas;
				try
				{
					System.out.println("--------- \nDar cantidad de zonas a buscar: ");
					numeroZonas = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}

				if(numeroZonas >= 1)
				{
					try
					{
						//
						MaxHeapCP<contadora> letras = modelo.letrasMasComunes(Math.min(1160, numeroZonas));

						int i = 1;
						
						while(letras.darNumeroElementos() > 0)
						{
							contadora contadoraActual = letras.sacarMax();
							System.out.println("Letra #" + i + ": " + contadoraActual.darLetra());
							System.out.println("Número de zonas con esta letra: " + contadoraActual.darZonas().darNumeroElementos());
							System.out.println("Primeras zonas con esta letra: ");
							
							int j = 1;
							
							while(contadoraActual.darZonas().hasNext() && j <= maximoDeDatos)
							{
								System.out.println("Nombre de la zona #" + j + ": " + contadoraActual.darZonas().next().darScanombre());
								j++;
							}
							System.out.println("---------");
							
							i++;
							
							//
						}
												
						if(numeroZonas>1160)
						{System.out.println("(Solo hay 1160 zonas)");}
						
						System.out.println("(Se imprimen hasta 20 zonas por letra)\n---------");

					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("Ingrese un valor válido\n---------");	
				}				

				break;
				
			case 6:
				//2B

				break;
				
			case 7:
				//3B

				break;
				
			case 8:
				//1C

				break;
				
			case 9:
				//2C

				break;
				
			case 10:
				//3C

				break;
				
			case 11:
				//4C

				break;
				
			case 12:

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