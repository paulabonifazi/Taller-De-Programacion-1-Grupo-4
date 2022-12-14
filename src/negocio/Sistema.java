/*
 * Los estados de mozo y operario los maneja cada uno al final del dia o el sistema / admin??
 * Si esta en el contrato lo consideramos como una precondicion? Por lo tanto no lanzamos excepcion (producto)
 * 
 */

package negocio;

import java.util.ArrayList;
import java.util.HashMap;

import excepciones.ContrasenaIncorrecta_Exception;
import excepciones.OperarioNoActivo_Exception;
import excepciones.UserNameIncorrecto_Exception;
import modelo.Administrador;
import modelo.Cerveceria;
import modelo.Comanda;
import modelo.Enumerados;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import modelo.PromocionProd;
import modelo.PromocionTemporal;

/**
 * Clase Singleton que representa a la empresa gastronomica. mozos: Key -> Nya
 * Value -> Mozo productos: Key -> id del producto Value -> Producto operarios:
 * Key -> userNamer Value -> Operario mesas: Key -> numero de mesa Value -> Mesa
 * promocionProds: Key -> idProm Value -> PromocionProd
 */

public class Sistema
{

	private HashMap<String, Mozo> mozos = Cerveceria.getInstance().getMozos();
	private HashMap<Integer, Producto> productos = Cerveceria.getInstance().getProductos();
	private HashMap<String, Operario> operarios = Cerveceria.getInstance().getOperarios();
	private HashMap<Integer, Mesa> mesas = Cerveceria.getInstance().getMesas();
	private HashMap<Integer, PromocionProd> promocionProds = Cerveceria.getInstance().getPromocionProds();
	private HashMap<Integer,Comanda> comandas = Cerveceria.getInstance().getComandas();
	private ArrayList<PromocionTemporal> promocionTemps = Cerveceria.getInstance().getPromocionTemp();
	private static Sistema instance = null;
	private final String usernameADMIN = "ADMIN";
	private final String passwordADMIN = "ADMIN1234";
	// private Sueldo sueldo; //ESTO ACA ESTA
	// RAROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO, hay que volarlo y explicar que
	// usamos el metodo y atributo de la CLASE

	public static Sistema getInstance()
	{
		if (instance == null)
			instance = new Sistema();
		return instance;
	}

	public HashMap<String, Mozo> getMozos()
	{
		return mozos;
	}

	public HashMap<Integer, Producto> getProductos()
	{
		return productos;
	}

	public HashMap<String, Operario> getOperarios()
	{
		return operarios;
	}

	public HashMap<Integer, Mesa> getMesas()
	{
		return mesas;
	}

	public HashMap<Integer, PromocionProd> getPromocionProds()
	{
		return promocionProds;
	}

	public HashMap<Integer,Comanda> getComandas()
	{
		return comandas;
	}

	public ArrayList<PromocionTemporal> getPromocionTemps()
	{
		return promocionTemps;
	}

	/**
	 * metodo para logear un operario. <br>
	 * Pre: El operario debe estar activo <br>
	 * Pre: El campo password debe contener entre 6 y 12 caracteres. Con al menos un
	 * digito y una mayuscula <br>
	 * Post: El operario queda logeado. <br>
	 * 
	 * @param userName utilizado para logear al usuario. <br>
	 * @param password corresp ondiente al userName. <br>
	 * @return Retorna un objeto de la clase Operario. <br>
	 * @throws UserNameIncorrecto_Exception   no existe el userName.
	 * @throws ContrasenaIncorrecta_Exception la contrasenia es incorrecta.
	 */

	public FuncionalidadOperarios login(String username, String password, String NyAAdmin, String nuevaPasswordAdmin)
			throws UserNameIncorrecto_Exception, ContrasenaIncorrecta_Exception, OperarioNoActivo_Exception
	{
		FuncionalidadOperarios fO = null;
		if (username.equals(this.usernameADMIN))
		{
			fO = loginAdmin(password,NyAAdmin,nuevaPasswordAdmin);
		} else if (operarios.containsKey(username))
			fO = loginOperario(username,password);
		else
			throw new UserNameIncorrecto_Exception(username);

		return fO;
	}
	
	private FuncionalidadAdmin loginAdmin(String password, String NyAAdmin, String nuevaPasswordAdmin) throws ContrasenaIncorrecta_Exception
	{
		FuncionalidadAdmin fA = null;
		if (password.equals(this.passwordADMIN))
			fA = new FuncionalidadAdmin(new Administrador(NyAAdmin, nuevaPasswordAdmin));
		else if (this.operarios.get("ADMIN").getPassword().equals(password))
			fA = new FuncionalidadAdmin(this.operarios.get("ADMIN"));
		else
			throw new ContrasenaIncorrecta_Exception();
		
		return fA;
	}
	
	
	private FuncionalidadOperarios loginOperario(String username, String password) throws OperarioNoActivo_Exception, ContrasenaIncorrecta_Exception
	{
		FuncionalidadOperarios fO = null;
		if (operarios.get(username).getPassword().equals(password))
		{
			Operario op = operarios.get(username);
			if (!op.isActivo())
				throw new OperarioNoActivo_Exception(username);
			else
				fO = new FuncionalidadOperarios(op);
		} else
			throw new ContrasenaIncorrecta_Exception();
		
		return fO;
	}

}
