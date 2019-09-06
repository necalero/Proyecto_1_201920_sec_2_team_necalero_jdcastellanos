	package model.logic;

public class Nodo<r> 
{
	private r item;
	private Nodo<r> siguienteNodo;
	private Nodo<r> anteriorNodo;

	
	
	public Nodo (r elemento)
	{
		siguienteNodo = null;
		anteriorNodo = null;
		this.item = elemento;
	}
	
	public r darItem()
	{
		return item;
	}
	
	
	public void cambiarItem(r item)
	{
		this.item = item;
	}
	
	public Nodo<r> darSiguiente()
	{
		return siguienteNodo;
	}
	
	public void cambiarSiguiente(Nodo<r> nodo)
	{
		siguienteNodo = nodo;
		
	}
	
	public Nodo<r> darAnterior()
	{
		return anteriorNodo;
	}
	
	public void cambiarAnterior(Nodo<r> nodo)
	{
		this.anteriorNodo = nodo;
		
	}
	

}
