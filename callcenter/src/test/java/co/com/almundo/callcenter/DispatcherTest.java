package co.com.almundo.callcenter;

import co.com.almundo.entity.Manager;
import co.com.almundo.entity.Call;
import co.com.almundo.entity.Operator;
import co.com.almundo.entity.Supervisor;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DispatcherTest extends TestCase {
	final int TIME_MAX_CALL_MILISEC = 10000;

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(DispatcherTest.class);
	}

	public void testTwoCalls() {
		try {
			System.out.println("-----------TEST 2 LLAMADAS--------------");
			Dispatcher dispatcher = new Dispatcher();
			dispatcher.start();

			// Se generan los empleados del call center
			dispatcher.addEmployeeAvailable(new Operator(dispatcher, "Operator 1"));

			// Se crean 2 llamada de 5 segundos cada una
			Call llamada1 = new Call("Llamda 1 ");
			llamada1.setDuration(5);
			Call llamada2 = new Call("Llamda 2 ");
			llamada2.setDuration(5);

			// Se reciben 2 llamadas concurrentes de 5 segundos cada una
			dispatcher.dispatchCall(llamada1);
			dispatcher.dispatchCall(llamada2);

			// Las llamadas de este test duran 5s y como hay 1 solo operador, las llamadas
			// tienen que ser atendidas en 10s.
			Thread.sleep(10000);

			// Al finalizar este tiempo, las llamdas deberian haber sido atendidas
			assertTrue(dispatcher.getCalls().isEmpty());

		} catch (InterruptedException e) {
			e.printStackTrace();
			fail();
		}

	}

	public void testThreeCalls() {
		try {
			System.out.println("-----------TEST 3 LLAMADAS--------------");
			Dispatcher dispatcher = new Dispatcher();
			dispatcher.start();

			// Se generan los empleados del call center
			dispatcher.addEmployeeAvailable(new Supervisor(dispatcher, "Supervisor 1"));
			dispatcher.addEmployeeAvailable(new Operator(dispatcher, "Operator 1"));

			// Se crean 3 llamada de 5 segundos cada una
			Call llamada1 = new Call("Llamda 1 ");
			llamada1.setDuration(5);
			Call llamada2 = new Call("Llamda 2 ");
			llamada2.setDuration(5);
			Call llamada3 = new Call("Llamda 3 ");
			llamada3.setDuration(5);

			// Se reciben 3 llamadas concurrentes de 5 segundos cada una
			dispatcher.dispatchCall(llamada1);
			dispatcher.dispatchCall(llamada2);

			// Se hace espera a que los empleados se desocupen
			Thread.sleep(5000);
			dispatcher.dispatchCall(llamada3);

			// Las llamadas de este test duran 5s y como hay solo un operador y un Director,
			// las llamadas tienen que ser atendidas en 10s pero ya hay una espera de 5s.
			Thread.sleep(5000);

			// Al finalizar este tiempo, las llamdas deberian haber sido atendidas
			assertTrue(dispatcher.getCalls().isEmpty());

		} catch (InterruptedException e) {
			e.printStackTrace();
			fail();
		}

	}

	public void testTenCalls() {
		System.out.println("-----------TEST 10 LLAMADAS CONCURRENTES--------------");
		try {
			Dispatcher dispatcher = new Dispatcher();
			dispatcher.start();

			// Se generan los empleados del call center
			dispatcher.addEmployeeAvailable(new Operator(dispatcher, "Operator 1"));
			dispatcher.addEmployeeAvailable(new Supervisor(dispatcher, "Supervisor 1"));
			dispatcher.addEmployeeAvailable(new Manager(dispatcher, "Manager 1"));

			// Se reciben 10 llamadas concurrentes
			dispatcher.dispatchCall(new Call(" 1 "));
			dispatcher.dispatchCall(new Call(" 2 "));
			dispatcher.dispatchCall(new Call(" 3 "));
			dispatcher.dispatchCall(new Call(" 4 "));
			dispatcher.dispatchCall(new Call(" 5 "));
			dispatcher.dispatchCall(new Call(" 6 "));
			dispatcher.dispatchCall(new Call(" 7 "));
			dispatcher.dispatchCall(new Call(" 8 "));
			dispatcher.dispatchCall(new Call(" 9 "));
			dispatcher.dispatchCall(new Call(" 10 "));

			// Espero el tiempo maximo para que todas las llamadas hayan sido atendidas:
			// (( tiempo de duracion max de llamada * cant llamadas) / cant empleados)
			Thread.sleep((TIME_MAX_CALL_MILISEC * 10) / 3);

			// Al finalizar este tiempo, las llamdas deberian haber sido atendidas
			assertTrue(dispatcher.getCalls().isEmpty());

		} catch (InterruptedException e) {
			e.printStackTrace();
			fail();
		}

	}

	public void testWaintingCalls() {
		System.out.println("-----------TEST LLAMADAS EN ESPERA --------------");
		try {
			Dispatcher dispatcher = new Dispatcher();
			dispatcher.start();

			// Se reciben 6 llamadas al principio
			dispatcher.dispatchCall(new Call(" 1 "));
			dispatcher.dispatchCall(new Call(" 2 "));
			dispatcher.dispatchCall(new Call(" 3 "));
			dispatcher.dispatchCall(new Call(" 4 "));
			dispatcher.dispatchCall(new Call(" 5 "));
			dispatcher.dispatchCall(new Call(" 6 "));

			// Se generan los empleados del call center
			dispatcher.addEmployeeAvailable(new Operator(dispatcher, "Operator 1"));
			dispatcher.addEmployeeAvailable(new Operator(dispatcher, "Operator 2"));
			dispatcher.addEmployeeAvailable(new Operator(dispatcher, "Operator 3"));
			dispatcher.addEmployeeAvailable(new Operator(dispatcher, "Operator 4"));
			dispatcher.addEmployeeAvailable(new Supervisor(dispatcher, "Supervisor 1"));
			dispatcher.addEmployeeAvailable(new Manager(dispatcher, "Manager 1"));

			// Se reciben 10 llamadas concurrentes ademas de las 6 ya recibidas
			// anteriormente
			dispatcher.dispatchCall(new Call(" 7 "));
			dispatcher.dispatchCall(new Call(" 8 "));
			dispatcher.dispatchCall(new Call(" 9 "));
			dispatcher.dispatchCall(new Call(" 10 "));
			dispatcher.dispatchCall(new Call(" 11 "));
			dispatcher.dispatchCall(new Call(" 12 "));
			dispatcher.dispatchCall(new Call(" 13 "));
			dispatcher.dispatchCall(new Call(" 14 "));
			dispatcher.dispatchCall(new Call(" 15 "));
			dispatcher.dispatchCall(new Call(" 16 "));
			dispatcher.dispatchCall(new Call(" 17 "));

			// Espero el tiempo maximo para que todas las llamadas hayan sido atendidas:
			// (( tiempo de duracion max de llamada * cant llamadas) / cant empleados)
			Thread.sleep((TIME_MAX_CALL_MILISEC * 17) / 6);

			// Al finalizar este tiempo, las llamdas deberian haber sido atendidas
			assertTrue(dispatcher.getCalls().isEmpty());

		} catch (InterruptedException e) {
			e.printStackTrace();
			fail();
		}

	}

}
