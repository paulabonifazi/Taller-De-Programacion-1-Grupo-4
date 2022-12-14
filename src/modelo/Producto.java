package modelo;

import java.io.Serializable;

public class Producto implements Serializable
{
    private static int siguienteIdProd = 0;
    private int idProd = siguienteIdProd++;
    private String nombre;
    private double precioCosto;
    private double precioVenta;
    private int stockInicial;
    private int stockActual;
    
	public Producto() {}

	/**
	 * Pre: precioCosto debe ser mayor a 0. <br>
	 * Pre: precioVenta debe ser mayor a 0. <br>
	 * Pre: stockInicial debe ser mayor a 0. <br>
	 * Pre: precioCosto debe ser menor a precioInicial <br>
	 * @param nombre
	 * @param precioCosto
	 * @param precioVenta
	 * @param stockInicial
	 * @param stockInicial
	 */
    public Producto(String nombre, double precioCosto, double precioVenta, int stockInicial)
	{
		super();
		this.nombre = nombre;
		this.precioCosto = precioCosto;
		this.precioVenta = precioVenta;
		this.stockInicial = stockInicial;
		this.stockActual = stockInicial;
	}

	public int getIdProd()
	{
		return idProd;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public double getPrecioCosto()
	{
		return precioCosto;
	}

	public void setPrecioCosto(double precioCosto)
	{
		this.precioCosto = precioCosto;
	}

	public double getPrecioVenta()
	{
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta)
	{
		this.precioVenta = precioVenta;
	}

	public int getStockInicial()
	{
		return stockInicial;
	}

	public void setStockInicial(int stockInicial)
	{
		this.stockInicial = stockInicial;
	}

	public int getStockActual()
	{
		return stockActual;
	}

	public void setStockActual(int stockActual)
	{
		this.stockActual = stockActual;
	}
    
}
