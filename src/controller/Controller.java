package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.data_structures.MaxHeapCP;
import model.data_structures.Queue;
import model.logic.MVCModelo;
import model.logic.UBERTrip;
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
				int limiteBajo;
				try
				{
					System.out.println("--------- \nDar limite bajo de tiempo promedio: ");
					limiteBajo = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}
				int limiteAlto;
				try
				{
					System.out.println("--------- \nDar limite Alto de tiempo promedio: ");
					limiteAlto = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}
				int N;
				try
				{
					System.out.println("--------- \nDar N cantidad de viajes : ");
					N = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}
				Queue<UBERTrip> rango = modelo.tiemposPromedioEnRango(limiteBajo, limiteAlto);
				int y =0;
				
		
				
				while(rango.hasNext()&& y < N)
				{
					UBERTrip x = rango.dequeue();

					System.out.println("Dato numero"+y);
					System.out.println("(Zona de origen:"+x.darIdorigen());
					System.out.println("(Zona de destino:"+x.darIddestino());
					System.out.println("(Mes:"+x.darTiempo());
					System.out.println("(Tiempo promedio de viaje:"+x.darMeanTravelTime());

					y++;
				}
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
				int limiteBajoB;
				try
				{
					System.out.println("--------- \nDar limite bajo de desviacion: ");
					limiteBajoB = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}
				int limiteAltoB;
				try
				{
					System.out.println("--------- \nDar limite Alto de desviacion: ");
					limiteAltoB = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}
				int NB;
				try
				{
					System.out.println("--------- \nDar N cantidad de viajes : ");
					NB = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}
				Queue<UBERTrip> rangoB = modelo.tiemposDeEspera(limiteAltoB, limiteBajoB);
				int yB =0;
				
		
				
				while(rangoB.hasNext()&& yB < NB)
				{
					UBERTrip x = rangoB.dequeue();

					System.out.println("Dato numero"+yB);
					System.out.println("(Zona de origen:"+x.darIdorigen());
					System.out.println("(Zona de destino:"+x.darIddestino());
					System.out.println("(Mes:"+x.darTiempo());
					System.out.println("(Tiempo de desviacion:"+x.darDesviacion());

					yB++;
				}
				break;

			case 8:
				//1C
				int zonaDada;
				try
				{
					System.out.println("--------- \nDar zona a buscar: ");
					zonaDada = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}
				int horaDada;
				try
				{
					System.out.println("--------- \nDar la hora a buscar : ");
					horaDada = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}
				
				Queue<UBERTrip> encontrados  = modelo.tiempoPromedioPorZona(zonaDada, horaDada);
				int p =0;
				
		
				
				while(encontrados.hasNext())
				{
					UBERTrip x = encontrados.dequeue();

					System.out.println("Dato numero"+p);
					System.out.println("(Zona de origen:"+x.darIdorigen());
					System.out.println("(Zona de destino:"+x.darIddestino());
					System.out.println("(Hora:"+x.darTiempo());
					System.out.println("(Tiempo promedio de viaje:"+x.darMeanTravelTime());

					p++;
				}
				break;

			case 9:
				//2C
				int zonallegadaDada;
				try
				{
					System.out.println("--------- \nDar zona a buscar: ");
					zonallegadaDada = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}
				int horalo;
				try
				{
					System.out.println("--------- \nDar la horan mas baja  a buscar : ");
					horalo = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}
				int horahi;
				try
				{
					System.out.println("--------- \nDar la horan mas alta  a buscar : ");
					horahi = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}
				
				Queue<UBERTrip> encontradosR  = modelo.tiempoPromedioPorRangoHora(zonallegadaDada, horalo, horahi);
				int o =0;
				
		
				
				while(encontradosR.hasNext())
				{
					UBERTrip x = encontradosR.dequeue();

					System.out.println("Dato numero"+o);
					System.out.println("(Zona de origen:"+x.darIdorigen());
					System.out.println("(Zona de destino:"+x.darIddestino());
					System.out.println("(Hora:"+x.darTiempo());
					System.out.println("(Tiempo promedio de viaje:"+x.darMeanTravelTime());

					o++;
				}

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