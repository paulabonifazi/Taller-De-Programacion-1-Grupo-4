package negocio;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.ContrasenaInvalida_Exception;
import excepciones.MozoMenorDeEdad_Exception;
import excepciones.NoPuedeHaberMasDe6Mozos_Exception;
import excepciones.NyARepetido_Exception;
import excepciones.UserNameRepetido_Exception;
import modelo.Administrador;
import modelo.Mozo;
import modelo.Operario;

public class FuncionalidadAdminTest {
	
	private FuncionalidadAdmin func = new FuncionalidadAdmin(new Administrador("Admin", "pass"));

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUpEscenario1() throws Exception {
		Sistema.getInstance().getMozos().clear();
		Sistema.getInstance().getMesas().clear();
		Sistema.getInstance().getOperarios().clear();
		Sistema.getInstance().getProductos().clear();
		//Sistema.getInstance().getPromocionProds().clear();
		//Sistema.getInstance().getPromocionTemps().clear();
		//Sistema.getInstance().getComandas().clear();
		//se ejecuta antes de cada metodo
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAgregaMozo() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAgregaMozoCorrecto() {
		try {
			this.func.agregaMozo("Juan Perez", 2, 10, 1983, 5);		
		} catch (NyARepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		} catch (NoPuedeHaberMasDe6Mozos_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		} catch (MozoMenorDeEdad_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		}
		Mozo mozo = Sistema.getInstance().getMozos().get("Juan Prez");
		Assert.assertNotNull("El mozo deberia estar en la coleccion de mozos", mozo);
	}
	
	@Test
	public void testAgregaMozoIncorrecto() {
		try {
			this.func.agregaMozo("Juan Perez", 5, 6, 1999, 0);	
			Assert.fail("Se deberia haber lanzado excepcion");
		} catch (NyARepetido_Exception e) {
			
		} catch (NoPuedeHaberMasDe6Mozos_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo NoPuedeHaberMasDe6Mozos");
		} catch (MozoMenorDeEdad_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo MozoMenorDeEdad_Exception");
		}
	}
	
	@Test
	public void testAgregaMozoIncorrecto1() {
		try {
			this.func.agregaMozo("Rodrigo Hernandez", 5, 6, 1999, 0);	
			this.func.agregaMozo("Juan Domingo", 5, 6, 1999, 0);	
			this.func.agregaMozo("Sarmiento", 5, 6, 1999, 0);	
			this.func.agregaMozo("Juan Pz", 5, 6, 1999, 0);	
			this.func.agregaMozo("Susana Perez", 5, 6, 1999, 0);	
			this.func.agregaMozo("Cristian Perez", 5, 6, 1999, 0);	
			Assert.fail("Se deberia haber lanzado excepcion");
		} catch (NyARepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo NyARepetido_Exception");
		} catch (NoPuedeHaberMasDe6Mozos_Exception e) {

		} catch (MozoMenorDeEdad_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo MozoMenorDeEdad_Exception");
		}
	}
	
	@Test
	public void testAgregaMozoIncorrecto2() {
		try {
			this.func.agregaMozo("Juancito", 5, 6, 2007, 0);	
			Assert.fail("Se deberia haber lanzado excepcion");
		} catch (NyARepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo NyARepetido_Exception");
		} catch (NoPuedeHaberMasDe6Mozos_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo NoPuedeHaberMasDe6Mozos_Exception");
		} catch (MozoMenorDeEdad_Exception e) {
			
		}
	}
	
	@Test
	public void testEliminaMozoCorrecto() {
		this.func.eliminaMozo("Juan Perez");
		Mozo mozo = Sistema.getInstance().getMozos().get("Juan Perez");
		Assert.assertNull("El mozo NO deberia estar en la coleccion de mozos", mozo);
	}
	
	@Test
	public void testEliminaMozoIncorrecto() {
		this.func.eliminaMozo("Luis Miguel");
		Assert.fail("Deberia lanzarse excepcion de que no existe el mozo");
	}
	
	
	@Test
	public void testAgregaOperarioCorrecto() {
		try {
			this.func.agregaOperario("Hernan Cattaneo", "hernan", "Cattaneo123");
		} catch (UserNameRepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		} catch (ContrasenaInvalida_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		}
	}
	
	@Test
	public void testAgregaOperarioIncorrecto() {
		try {
			this.func.agregaOperario("Hernan Cristian", "hernan", "Cristian123");
			Assert.fail("Deberia lanzarse excepcion de tipo UserNameRepetido_Exception");
		} catch (UserNameRepetido_Exception e) {
			
		} catch (ContrasenaInvalida_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		}
	}
	
	@Test
	public void testAgregaOperarioIncorrecto2() {
		try {
			this.func.agregaOperario("Luisa Fernandez", "luisaF", "luisa");
			Assert.fail("Deberia lanzarse excepcion de tipo ContrasenaInvalida_Exception");
		} catch (UserNameRepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");			
		} catch (ContrasenaInvalida_Exception e) {
		}
	}
	
	@Test
	public void testEliminaOperarioCorrecto() {
		this.func.eliminaOperario("hernan");
		Operario op = Sistema.getInstance().getOperarios().get("hernan");
		Assert.assertNull("El operario deberia haber sido eliminado");
	}
	
	@Test
	public void testEliminaOperarioIncorrecto() {
		this.func.eliminaOperario("pepe");
		Assert.fail("Deberia lanzar excepcion de que no existe el operario a eliminar");
	}
	
	@Test
	public void testPasswordValida() {
		boolean res = false;
		res = this.func.PasswordValida("Hernan1234");
		Assert.assertTrue("La contrasenia deberia ser valida", res);
	}
	
	@Test
	public void testPasswordValidaIncorrecto() {
		boolean res = true;
		res = this.func.PasswordValida("hernan1234");
		Assert.assertFalse("La contrasenia NO deberia ser valida", res);
	}

	@Test
	public void testActivaDesactivaOperario() {
		this.func.activaDesactivaOperario("hernan", false);
		Assert.assertFalse("Deberia estar inactivo el operario", Sistema.getInstance().getOperarios().get("hernan").get);
	}
	
	@Test
	public void testAgregaProductoCorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAgregaProductoIncorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEliminaProductoCorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEliminaProductoIncorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testModificaStockProductoCorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testModificaStockProductoIncorrecto() {
		fail("Not yet implemented");
	}
	

	@Test
	public void testAgregaMesaCorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAgregaMesaIncorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEliminaMesaCorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEliminaMesaIncorrecto() {
		fail("Not yet implemented");
	}
}
