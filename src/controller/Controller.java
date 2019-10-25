package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

import model.data_structures.MaxHeapCP;
import model.data_structures.Queue;
import model.data_structures.SeparateChaining;
import model.logic.MVCModelo;
import model.logic.NodoMallaVial;
import model.logic.UBERTrip;
import model.logic.ZonaUBER;
import model.logic.MVCModelo.Nodo;
import model.logic.MVCModelo.Zona;
import model.logic.MVCModelo.Contadora;
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
					System.out.println("Debe ingresar un valor numérico (1 a 4)\n---------");
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
					System.out.println("Ingrese un valor válido (1 a 4)\n---------");	
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
						MaxHeapCP<Contadora> letras = modelo.letrasMasComunes(Math.min(23, numeroLetras));

						int i = 1;

						while(letras.darNumeroElementos() > 0)
						{
							Contadora contadoraActual = letras.sacarMax();
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
				modelo.cargarArchivoZonas();
				break;

			case 3:
				//2A

				double latitud;
				double longitud;
				try
				{
					System.out.println("--------- \nDar latitud a buscar: ");
					latitud = lector.nextDouble();
					System.out.println("--------- \nDar longitud a buscar: ");
					longitud = lector.nextDouble();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}

				int auxLat = (int)(latitud*1000);
				int auxLong = (int)(longitud*1000);

				SeparateChaining<String, Nodo> nodos = modelo.nodosQuelimitanZonas(auxLat, auxLong);

				System.out.println("Número de nodos: " + nodos.size() + "\n");

				int k = 1;
				Queue llaves = (Queue) nodos.keys();

				while(llaves.darNumeroElementos() > 0 && k <= maximoDeDatos)
				{
					Nodo actual = nodos.delete((String) llaves.dequeue());

					System.out.println("Nodo #" + k + ": ");
					System.out.println("Latitud: " + actual.darLatitud());
					System.out.println("Longitud: " + actual.darLongitud());
					System.out.println("Nombre de la zona: " + actual.darNombreZona() + "\n---------");
					k++;
				}

				System.out.println("(Se imprimen hasta 20 nodos)\n---------");
				modelo.cargarArchivoZonas();
				break;

			case 4:
				//3A
				int limiteBajo;
				int limiteAlto;
				int N;
				try
				{
					System.out.println("--------- \nDar limite inferior de tiempo promedio: ");
					limiteBajo = lector.nextInt();
					System.out.println("--------- \nDar limite superior de tiempo promedio: ");
					limiteAlto = lector.nextInt();
					System.out.println("--------- \nDar cantidad de viajes: ");
					N = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}

				Queue<UBERTrip> rango = modelo.tiemposPromedioEnRango(limiteBajo, limiteAlto);

				int y = 1;

				while(rango.hasNext() && y <= N)
				{
					UBERTrip x = rango.dequeue();

					System.out.println("Dato #" + y + ": ");
					System.out.println("Zona de origen: " + x.darIdorigen());
					System.out.println("Zona de destino: " + x.darIddestino());
					System.out.println("Mes: " + x.darTiempo());
					System.out.println("Tiempo promedio de viaje: " + x.darMeanTravelTime() + "\n---------");

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
						MaxHeapCP<Zona> zonas = modelo.zonasMasAlNorte();

						int l = 1;

						while(!zonas.estaVacia() && l <= numeroZonas)
						{
							Zona actual = zonas.sacarMax();

							System.out.println("Zona #" + l + ": ");
							System.out.println("Nombre de la zona: " + actual.darNombreZona());
							System.out.println("(Latitud, longitud) del punto mas al norte: (" + actual.darLatitudNorte() + ", " + actual.darLongitudNorte() + ")\n---------");
							l++;
						}						
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
				modelo.cargarArchivoZonas();
				break;

			case 6:
				//2B

				double latitud2B;
				double longitud2B;
				try
				{
					System.out.println("--------- \nDar latitud a buscar: ");
					latitud2B = lector.nextDouble();
					System.out.println("--------- \nDar longitud a buscar: ");
					longitud2B = lector.nextDouble();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}

				int auxLat2B = (int)(latitud2B*1000);
				int auxLong2B = (int)(longitud2B*1000);

				SeparateChaining<Double, NodoMallaVial> nodos2B = modelo.nodosPorLocalizacion(auxLat2B, auxLong2B);

				System.out.println("Número de nodos: " + nodos2B.size() + "\n");

				int n = 1;
				Queue llaves2B = (Queue) nodos2B.keys();

				while(llaves2B.darNumeroElementos() > 0 && n <= maximoDeDatos)
				{
					NodoMallaVial actual = nodos2B.delete((Double) llaves2B.dequeue());

					System.out.println("Nodo #" + n + ": ");
					System.out.println("Id de la zona: " + actual.darDatosViaje()[0]);
					System.out.println("Latitud: " + actual.darDatosViaje()[2]);
					System.out.println("Longitud: " + actual.darDatosViaje()[1] + "\n---------");
					n++;
				}

				System.out.println("(Se imprimen hasta 20 nodos)\n---------");

				break;

			case 7:
				//3B
				int limiteBajoB;
				int limiteAltoB;
				int NB;

				try
				{
					System.out.println("--------- \nDar limite bajo de desviacion: ");
					limiteBajoB = lector.nextInt();
					System.out.println("--------- \nDar limite Alto de desviacion: ");
					limiteAltoB = lector.nextInt();
					System.out.println("--------- \nDar N cantidad de viajes : ");
					NB = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}

				Queue<UBERTrip> rangoB = modelo.tiemposDeEspera(limiteAltoB, limiteBajoB);
				int yB = 1;

				while(rangoB.hasNext() && yB <= NB)
				{
					UBERTrip x = rangoB.dequeue();

					System.out.println("Dato #" + yB + ": ");
					System.out.println("Zona de origen: " + x.darIdorigen());
					System.out.println("Zona de destino: " + x.darIddestino());
					System.out.println("Mes: " + x.darTiempo());
					System.out.println("Tiempo de desviacion: " + x.darDesviacion() + "\n---------");

					yB++;
				}
				break;

			case 8:
				//1C
				int zonaDada;
				int horaDada;
				try
				{
					System.out.println("--------- \nDar zona a buscar: ");
					zonaDada = lector.nextInt();
					System.out.println("--------- \nDar la hora a buscar : ");
					horaDada = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}

				Queue<UBERTrip> encontrados  = modelo.tiempoPromedioPorZona(zonaDada, horaDada);
				int p = 1;

				while(encontrados.hasNext())
				{
					UBERTrip x = encontrados.dequeue();

					System.out.println("Dato #" + p + ": ");
					System.out.println("Zona de origen: " + x.darIdorigen());
					System.out.println("Zona de destino: " + x.darIddestino());
					System.out.println("Hora: " + x.darTiempo());
					System.out.println("Tiempo promedio de viaje: " + x.darMeanTravelTime() + "\n---------");

					p++;
				}
				break;

			case 9:
				//2C
				int zonallegadaDada;
				int horalo;
				int horahi;

				try
				{
					System.out.println("--------- \nDar zona a buscar: ");
					zonallegadaDada = lector.nextInt();
					System.out.println("--------- \nDar la hora mas baja a buscar: ");
					horalo = lector.nextInt();
					System.out.println("--------- \nDar la hora mas alta a buscar: ");
					horahi = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}

				Queue<UBERTrip> encontradosR  = modelo.tiempoPromedioPorRangoHora(zonallegadaDada, horalo, horahi);
				int o = 1;		

				while(encontradosR.hasNext())
				{
					UBERTrip x = encontradosR.dequeue();

					System.out.println("Dato #" + o + ": ");
					System.out.println("Zona de origen: " + x.darIdorigen());
					System.out.println("Zona de destino:" + x.darIddestino());
					System.out.println("Hora: " + x.darTiempo());
					System.out.println("Tiempo promedio de viaje: " + x.darMeanTravelTime() + "\n---------");

					o++;
				}

				break;

			case 10:
				//3C				
				int cantidadZonas;
				try
				{
					System.out.println("--------- \nDar cantidad de zonas a buscar: ");
					cantidadZonas = lector.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Debe ingresar un valor numérico\n---------");
					break;
				}

				if(cantidadZonas >= 1)
				{
					MaxHeapCP<ZonaUBER> zonas = modelo.zonasMasNodos();

					int m = 1;

					while(!zonas.estaVacia() && m <= cantidadZonas)
					{
						ZonaUBER actual = zonas.sacarMax();

						System.out.println("Zona #" + m + ": ");
						System.out.println("Nombre de la zona: " + actual.darScanombre());
						System.out.println("Número de nodos en la frontera: " + actual.darCoordinates().darNumeroElementos() + "\n---------");
						m++;
					}						
				}
				else
				{
					System.out.println("Ingrese un valor válido\n---------");	
				}				

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