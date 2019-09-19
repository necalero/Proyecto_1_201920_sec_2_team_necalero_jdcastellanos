package src.model.logic;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import src.model.data_structures.ArregloDinamico;
import src.model.data_structures.Cola;
import src.model.data_structures.DoublyLinkedList;
import src.model.data_structures.IArregloDinamico;
import src.model.data_structures.Nodo;
import src.model.data_structures.Pila;
import src.view.MVCView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;



/**
 * Definicion del modelo del mundo
 *
 */

public class MVCModelo <Integer extends Comparable<Integer>>
{
	/**
	 * Atributos del modelo del mundo
	 */


	ArrayList<DoublyLinkedList<Viaje>> trimestresHora;
	ArrayList<DoublyLinkedList<Viaje>> trimestresDia; 
	ArrayList<DoublyLinkedList<Viaje>> trimestresMes;





	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		trimestresHora = new ArrayList<>();
		trimestresDia = new ArrayList<>(); 
		trimestresMes = new ArrayList<>();
		for(int i = 0; i<4;i++)
		{
			trimestresHora.add(new DoublyLinkedList<>());
			trimestresDia.add(new DoublyLinkedList<>());
			trimestresMes.add(new DoublyLinkedList<>());
		}
	}





	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 * tipo: Tipo de fecha (Mes, Dia, Hora).
	 * trimestre: Trimestre del cual se quiere consultar el tamaño.
	 */
	public int darTamano(int trimestre, String tipo)
	{
		if(tipo.compareTo("mes")==0)
		{
			int n = trimestre-1;
			int m = trimestresMes.get(n).darSize();
			return m;
		}
		else if(tipo.compareTo("dia")==0)
		{
			int n = trimestre-1;
			int m = trimestresDia.get(n).darSize();
			return m;
		}
		else if(tipo.compareTo("hora")==0)
		{
			int n = trimestre-1;
			int m = trimestresHora.get(n).darSize();
			return m;
		}
		else
		{
			return -1;
		}
	}


	public void cargar(int trimestre, String date) throws Exception
	{
		CSVReader reader;

		if(date.equals("mes"))
		{
			for(int i = 1; i < 5; i++)
			{
				if(trimestre==i)
				{
					reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-"+i+"-All-MonthlyAggregate.csv"));
					String [] nextLine=reader.readNext();
					while (nextLine != null) 
					{
						trimestresMes.get(i).añadirUltimo(new Viaje(nextLine[0],nextLine[1],nextLine[2],nextLine[3],nextLine[4],nextLine[5], nextLine[6]));
						nextLine = reader.readNext();
					}
				} 
			}
		}

		else if(date.equals("dia"))
		{
			for(int i = 1; i < 5; i++)
			{
				if(trimestre==i)
				{
					reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-"+i+"-All-WeeklyAggregate.csv"));
					String [] nextLine=reader.readNext();
					while (nextLine != null) 
					{
						trimestresDia.get(i).añadirUltimo(new Viaje(nextLine[0],nextLine[1],nextLine[2],nextLine[3],nextLine[4],nextLine[5], nextLine[6]));
						nextLine = reader.readNext();
					}
				}
			}
		}
		
		else if(date.equals("hora"))
		{
			for(int i = 1; i < 5; i++)
			{
				if(trimestre==i)
				{
					reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-"+i+"-All-HourlyAggregate.csv"));
					String [] nextLine=reader.readNext();
					while (nextLine != null) 
					{
						trimestresHora.get(i).añadirUltimo(new Viaje(nextLine[0],nextLine[1],nextLine[2],nextLine[3],nextLine[4],nextLine[5], nextLine[6]));
						nextLine = reader.readNext();
					}
				}
			}
			
		}
		else
		{
			throw new Exception("Revisar si se introdujo mal el trimestre o la informacion de mes, dia, hora (asegurarse de que esta en minuscula)");
		}
	}
	
	public void consultarTiempoPromedioDesviaciónEstandarMes(int destino, int origen, double month)
	{
		boolean centinela = false;
		for(int i = 0; i<4&&!centinela ; i++)
		{
			Nodo nodoActual = trimestresMes.get(i).darPrimero();
			while(nodoActual!=null&&!centinela)
			{
				Viaje viajeActual = (Viaje) nodoActual.darItem();
				if(viajeActual.darDate()==month)
				{
					if(viajeActual.darDstid()==destino&&viajeActual.darSourceid()==origen)
					{
						String mtt = "" + viajeActual.darMean_travel_time();
						String gsdtt = "" + viajeActual.darGeometricStandardDeviationTravelTime();
						MVCView.printMessage("El tiempo promedio es "+mtt+", su desviacion estandar es "+gsdtt);
						centinela=true;
					}
				}
				nodoActual = nodoActual.darSiguiente();
			}
		}
		if(centinela = false)
		{
			MVCView.printMessage("No se encontro dicho objeto.");
		}
	}
	
	public void consultarTiempoPromedioDesviaciónEstandarDia(int destino, int origen, double dow)
	{
		boolean centinela = false;
		for(int i = 0; i<4&&!centinela ; i++)
		{
			Nodo nodoActual = trimestresDia.get(i).darPrimero();
			while(nodoActual!=null&&!centinela)
			{
				Viaje viajeActual = (Viaje) nodoActual.darItem();
				if(viajeActual.darDate()==dow)
				{
					if(viajeActual.darDstid()==destino&&viajeActual.darSourceid()==origen)
					{
						String mtt = "" + viajeActual.darMean_travel_time();
						String gsdtt = "" + viajeActual.darGeometricStandardDeviationTravelTime();
						MVCView.printMessage("El tiempo promedio es "+mtt+", su desviacion estandar es "+gsdtt);
						centinela=true;
					}
				}
				nodoActual = nodoActual.darSiguiente();
			}
		}
		if(centinela = false)
		{
			MVCView.printMessage("No se encontro dicho objeto.");
		}
	}
	
	public void nViajesMayorTiempoPromedioMes(int n, int month)
	{		
		Viaje[] mayores = new Viaje[n];
		
		for(int i = 0; i<4; i++)
		{
			Nodo nodoActual = trimestresMes.get(i).darPrimero();
			while(nodoActual!=null)
			{
				Viaje viajeActual = (Viaje) nodoActual.darItem();
				if(viajeActual.darDate()==month)
				{
					boolean centinela = false;
					for(int j = 0; j<n&&!centinela;j++)
					{
						if(mayores[j]==null)
						{
							mayores[j]=viajeActual;
							centinela = true;
							
						}
						else if(mayores[j].darGeometric_mean_travel_time()<viajeActual.darGeometric_mean_travel_time())
						{
							mayores[j]=viajeActual;
							centinela=true;
						}
					}
				}
				nodoActual = nodoActual.darSiguiente();
			}
		}
		
		MVCView.printMessage("Los viajes con mayor tiempo promedio son:");
		for(int k = 0; k<n; k++)
		{
			Viaje z = mayores[k];
			String zO= "" + z.darSourceid();
			String zD= "" + z.darDstid();
			String zProm = "" +z.darGeometric_mean_travel_time();
			String zDes = "" + z.darGeometricStandardDeviationTravelTime();
			MVCView.printMessage("EL viaje con zona de origen "+zO+", zona de destino "+zD+", el tiempo promedio de viaje fue "+zProm+"+/-"+zDes);
		}
	}
	
	public void nViajesMayorTiempoPromedioDia(int n, int dow)
	{		
		Viaje[] mayores = new Viaje[n];
		
		for(int i = 0; i<4; i++)
		{
			Nodo nodoActual = trimestresDia.get(i).darPrimero();
			while(nodoActual!=null)
			{
				Viaje viajeActual = (Viaje) nodoActual.darItem();
				if(viajeActual.darDate()==dow	)
				{
					boolean centinela = false;
					for(int j = 0; j<n&&!centinela;j++)
					{
						if(mayores[j]==null)
						{
							mayores[j]=viajeActual;
							centinela = true;
							
						}
						else if(mayores[j].darGeometric_mean_travel_time()<viajeActual.darGeometric_mean_travel_time())
						{
							mayores[j]=viajeActual;
							centinela=true;
						}
					}
				}
				nodoActual = nodoActual.darSiguiente();
			}
		}
		
		MVCView.printMessage("Los viajes con mayor tiempo promedio son:");
		for(int k = 0; k<n; k++)
		{
			Viaje z = mayores[k];
			String zO= "" + z.darSourceid();
			String zD= "" + z.darDstid();
			String zProm = "" +z.darGeometric_mean_travel_time();
			String zDes = "" + z.darGeometricStandardDeviationTravelTime();
			MVCView.printMessage("EL viaje con zona de origen "+zO+", zona de destino "+zD+", el tiempo promedio de viaje fue "+zProm+"+/-"+zDes);
		}
	}
	
	public void tresC(int destino, int origen )
	{
		int cantidadminutos=0;
		String asteriscos="";
		boolean hay=false;
		MVCView.printMessage("Aproximación en minutos de viajes entre zona origen y zona destino. ");
		MVCView.printMessage("Trimestre "+darTrimestre()+"del 2018 detallado por cada hora del día");
		MVCView.printMessage("Zona Origen: "+origen+" Zona Destino: "+destino);
		MVCView.printMessage("Hora|  # de minutos");
		for (int i=0;i<viajes.dar(HORA).darLongitud();i++)
		{
			if(viajes.dar(HORA).dar(i).getDstid()==destino)
			{
				if(viajes.dar(HORA).dar(i).getSourceid()==destino)
				{
					cantidadminutos=(int) (viajes.dar(HORA).dar(i).getMean_travel_time()/60);
					hay=true;
				}
			}
			for(int j=0;j<cantidadminutos;j++)
			{
			asteriscos=asteriscos+"*";
			}
			if(hay==true)
			{
				MVCView.printMessage(viajes.dar(HORA).dar(i).getHod()+"|"+asteriscos);
			}
			else
			{
				MVCView.printMessage(viajes.dar(HORA).dar(i).getHod()+"| Hora sin viajes");	
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
