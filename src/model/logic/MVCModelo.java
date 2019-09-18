package src.model.logic;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import src.model.data_structures.ArregloDinamico;
import src.model.data_structures.Cola;
import src.model.data_structures.DoublyLinkedList;
import src.model.data_structures.IArregloDinamico;
import src.model.data_structures.Nodo;
import src.model.data_structures.Pila;

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


	private DoublyLinkedList<Viaje> datosHora;
	private DoublyLinkedList<Viaje> datosDiaDeSemana;
	private DoublyLinkedList<Viaje> datosMes;
	




	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{

		datosHora = new DoublyLinkedList<>();
		datosDiaDeSemana = new DoublyLinkedList<>();
		datosMes = new DoublyLinkedList<>();
		
	}





	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano(String tipo)
	{
		if(tipo.compareTo("mes")==0)
		{
			return datosMes.darSize();
		}
		else if(tipo.compareTo("dia")==0)
		{
			return datosDiaDeSemana.darSize();
		}
		else if(tipo.compareTo("hora")==0)
		{
			return datosHora.darSize();
		}
		else
		{
			return -1;
		}
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(String dato)
	{	
		datos.añadirUltimo(dato);
	}

	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public String buscar(String dato)
	{
		return datos.buscarNodo(dato).toString();
	}

	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public String eliminar(String dato)
	{
		return datos.eliminar(datos.buscarNodo(dato)).toString();
	}


	public static void read(String[] args) {

		CSVReader reader = null;
		try {

			reader = new CSVReader(new FileReader("./data/ bogota-cadastral-2018-1-All-MonthlyAggregate.csv"));
			for(String[] nextLine : reader) {
				nextLine[0].split(",");
				System.out.println("col1: " + nextLine[0] + ", col2: "+ nextLine[1]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public void cargarDatos(String pRuta) throws NumberFormatException, IOException
	{
		String archCSV = pRuta;
		CSVReader csvReader = new CSVReader(new FileReader(archCSV));
		String[] fila = null;
		int cont=0;
		Nodo primerNodo;
		Nodo temp= datos.darPrimero();
		while((fila = csvReader.readNext()) != null) 
		{
			int cantidadDeViajes= 0;
			if(cont<1)
			{
				cont++;
			}
			else if (temp==null)
			{
				double sa = Double.parseDouble(fila[0]);
				cantidadDeViajes++;
				temp = new Nodo(Double.parseDouble(fila[0]));
			}
			else 
			{
				cantidadDeViajes++;
				temp=temp.darSiguiente();
				temp = new Nodo(Double.parseDouble(fila[0]));
			}

		}
	}

	public int totalViajesPila() throws IOException
	{
		int respuesta =0;
		String archCSV = "./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv";
		CSVReader csvReader = new CSVReader(new FileReader(archCSV));
		String[] fila = null;
		while((fila = csvReader.readNext()) != null) 
		{
			respuesta ++;

		}
		return respuesta;
	}

	public int totalViajesCola() throws IOException
	{
		int respuesta =0;
		String archCSV = "./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv";
		CSVReader csvReader = new CSVReader(new FileReader(archCSV));
		String[] fila = null;
		while((fila = csvReader.readNext()) != null) 
		{
			respuesta ++;

		}
		return respuesta;
	}

}
