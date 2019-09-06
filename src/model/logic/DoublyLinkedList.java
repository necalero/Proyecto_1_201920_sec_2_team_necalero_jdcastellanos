package model.logic;

import static org.junit.Assert.assertArrayEquals;


/**
 * ~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotï¿½ - Colombia)
 * Departamento de Ingenierï¿½a de Sistemas y Computaciï¿½n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Pablo Barvo - 9-May-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~
 */

import java.io.Serializable;
import java.util.Iterator;

import com.sun.corba.se.impl.logging.IORSystemException;
import Nodo.java;
/**
 * Lista doblemente encadenada con cabeza y cola
 * @param <T> Tipo de datos a almacenar en la lista
 */
public class DoublyLinkedList<r> implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
    /**
	 * Constante para la serializaciï¿½n
	 */
	private static final long serialVersionUID = 1L;
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Cabeza de la lista encadenada
     */
    private Nodo<r> primerNodo;

    /**
     * ï¿½ltimo elemento de la lista encadenada
     */
    private Nodo<r> ultimoNodo;
    
    private int size;


    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la lista vacï¿½a. <br>
     * <b>post: </b> Se construyï¿½ una lista vacï¿½a.
     */
    public DoublyLinkedList( )
    {
        primerNodo = null;
        ultimoNodo = null;
        size = 0; 
    }

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------

    /**
     * Inserta el elemento en la cabeza (inicio) de la lista. <br>
     * <b>post: </b> Se insertï¿½ el elemento en la cabeza de la lista. La cantidad de elementos aumenta <br>
     * En caso que la lista no sea vacï¿½a, el nuevo elemento queda como el primero. Si la lista es vacï¿½a el nuevo elemento es el primero y el ï¿½ltimo<br>
     * @param nodo Nodo a insertar
     */
    public void añadirPrimero(r item)
    {
    	Nodo<r> nodo = new Nodo(item);	
    	if( primerNodo == null)
        {
            primerNodo = nodo;
            ultimoNodo = nodo;
        }
        else if(primerNodo != null)
        {
        	añadirAntes(primerNodo, nodo);
        	
            primerNodo = nodo;
        }
    	
        
    }
    
    /**
     * Inserta el elemento en la cola (final) de la lista. <br>
     * <b>post: </b> Se insertï¿½ el elemento en la cola de la lista. La cantidad de elementos aumenta <br>
     * En caso que la lista no sea vacï¿½a, el nuevo elemento queda como el ï¿½ltimo. Si la lista es vacï¿½a el nuevo elemento es el primero y el ï¿½ltimo<br>
     * @param elemento Elemento a insertar<br>
     */
    public void añadirUltimo( r item )
    {
        Nodo<r> nodo = new Nodo<r>( item );
        if( primerNodo == null )
        {
            primerNodo = nodo;
            ultimoNodo = nodo;
        }
        else
        {
        	
            añadirDespues(ultimoNodo, nodo);
            
            ultimoNodo = nodo;
        }
        
        
    }
    
    public void añadirDespues(Nodo nodoPrevio, Nodo nodoNuevo)
    {
    	if(nodoPrevio.darSiguiente()!=null)
    	{
    		Nodo aux = nodoPrevio.darSiguiente();
        	nodoNuevo.cambiarSiguiente(aux);
        	aux.cambiarAnterior(nodoNuevo);
        	nodoPrevio.cambiarSiguiente(nodoNuevo);
        	nodoNuevo.cambiarAnterior(nodoPrevio);
    	}
    	else
    	{
    		nodoPrevio.cambiarSiguiente(nodoNuevo);
    		nodoNuevo.cambiarAnterior(nodoPrevio);
    	}
    	size++;
    }
    
    public void añadirAntes(Nodo nodoPosterior, Nodo nodoNuevo)
    {
    	if(nodoPosterior.darAnterior()!=null)
    	{
    		Nodo aux = nodoPosterior.darAnterior();
        	nodoNuevo.cambiarAnterior(aux);
        	aux.cambiarSiguiente(nodoNuevo);
        	nodoPosterior.cambiarAnterior(nodoNuevo);
        	nodoNuevo.cambiarSiguiente(nodoPosterior);
    	}
    	else
    	{
    		nodoPosterior.cambiarAnterior(nodoNuevo);
    		nodoNuevo.cambiarSiguiente(nodoPosterior);
    	}
    	size++;
    }
    

    /**
     * Retorna el primer nodo de la lista. <br>
     * <b>post: </b> Se retornï¿½ el primer nodo de la lista.
     * @return Primer nodo de la lista. Null en caso que la lista sea vacï¿½a.
     */
    public Nodo<r> darPrimero( )
    {
        return primerNodo;
    }

    /**
     * Retorna el ï¿½ltimo nodo de la lista. <br>
     * <b>post: </b> Se retornï¿½ el ï¿½ltimo nodo de la lista.
     * @return ï¿½ltimo nodo de la lista. Null en caso que la lista sea vacï¿½a.
     */
    public Nodo<r> darUltimo( )
    {
        return ultimoNodo;
    }
    
    public int darSize()
    {
    	return size;
    }
    
    
    /**
     * Retorna el nodo de la lista que contiene el item proporcionado. <br>
     * @return nodo de la lista que contiene el item proporcionado. Null en caso de que no se haya encontrado.
     */
    public Nodo<r> buscarNodo(r item)
    {
    	Nodo actual = primerNodo;
    	while(actual!=null)
    	{
    		if(actual.darItem().equals(item)) return actual;
    		else actual = actual.darSiguiente();
    	}
    	return null;
    }

    /**
     * Evalï¿½a si la lista contiene el elemento que se pasa por parï¿½metro<br>
     * @param modelo Modelo del elemento a buscar.<br>
     * @return True en caso que el elemento dado exista en la lista, false de lo contrario.<br>
     */
    public boolean contiene( r item )
    {
        return buscarNodo( item ) != null;
    }
    

    

    /**
     * Elimina el nodo de la lista encadenada. <br>
     * <b>post: </b> Se eliminï¿½ el nodo especificado de la lista. La cantidad de elementos de la lista se reduce.<br>
     * @param nodo Nodo a ser eliminado de la lista<br>
     * @throws NoExisteException El nodo especificado no pertenece a la lista<br>
     */
    public r eliminar( Nodo<r> nodo ) throws NoExisteException
    {
        if( buscarNodo( nodo.darItem() ) == null ) throw new NoExisteException( "El nodo especificado no pertenece a la lista" );
        else if( primerNodo == nodo )
        {
        	if(primerNodo.darSiguiente()!=null) 
        	{
        		primerNodo = primerNodo.darSiguiente();
        		primerNodo.cambiarAnterior(null);
        	}
           	else primerNodo = null;
        }
        else if(ultimoNodo == nodo ) 
        {
        	Nodo aux = ultimoNodo.darAnterior();
        	aux.cambiarSiguiente(null);
        	ultimoNodo = aux;
        }
        else
        {
        	Nodo<r> y = buscarNodo(nodo.darItem());
        	Nodo<r> x = y.darSiguiente();
        	Nodo<r> z = y.darAnterior();
        	
        	x.cambiarSiguiente(z);
        	z.cambiarAnterior(x);
        }
        size--;
        return nodo.darItem();
    }

    /**
     * Elimina el primer nodo (cabeza) de la lista y devuelve el elemento que contenï¿½a. <br>
     * <b>post: </b> Se eliminï¿½ el primer nodo de la lista.
     */
    public void eliminarPrimero( )
    {
    	if(primerNodo!=null)
    	{
    		if( primerNodo.darSiguiente() == null )
            {
                vaciar();
            }
            else
            {
            	Nodo<r> x = primerNodo.darSiguiente();
            	primerNodo = x;
            	x.cambiarAnterior(null);
                
            }
    		size--;
    	}
    	
        
    }

    /**
     * Elimina el ultimo nodo y devuelve el elemento que contenï¿½a. <br>
     * <b>post: </b> Se eliminï¿½ el ï¿½ltimo nodo de la lista. Se retornï¿½ el elemento contenido en el nodo eliminado. La cantidad de elementos se reduce en uno. Si la lista no
     * tiene nodos se retorna null.
     * @return Elemento del nodo eliminado
     */
    public r eliminarUltimo( )
    {
    	if(ultimoNodo!=null)
    	{
    		ultimoNodo = ultimoNodo.darAnterior();
    		ultimoNodo.cambiarSiguiente(null);
    		size--;
    	}
    }




    /**
     * Elimina todos los elementos de la lista.<br>
     * <b>post: </b> La lista ahora es vacï¿½a. primero = null, ultimo=null, numElems = 0<br>
     */
    public void vaciar( )
    {
        primerNodo = null;
        ultimoNodo = null;
        size = 0;
       
    }

    
    
    
}