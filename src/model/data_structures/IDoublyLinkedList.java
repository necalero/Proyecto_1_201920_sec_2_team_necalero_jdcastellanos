package src.model.data_structures;

import src.model.logic.NoExisteException;

public interface IDoublyLinkedList<r> extends Iterable <r> 
{
	public void añadir(Comparable item);
    public void eliminar( Nodo<r> nodo ) throws NoExisteException;
    public Nodo<r> buscarNodo(r item);
    public void vaciar();
    
}
