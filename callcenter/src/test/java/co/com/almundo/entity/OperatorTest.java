package co.com.almundo.entity;

import co.com.almundo.callcenter.Dispatcher;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class OperatorTest extends TestCase {
	public static Test suite() {
		return new TestSuite(OperatorTest.class);
	}

	public void testOperator() {
		//Se crea llamada de 5 segundos
		Call call = new Call("llamada 1");
		call.setDuration(5);
		
		//Se crea empleado del call center (Operador)
		Dispatcher dispatcher = new Dispatcher();
		Operator operator = new Operator(dispatcher, "operador 1");
		try {
			operator.attendCall(call);
			Thread.sleep(6000);
			assertTrue(dispatcher.getEmployeeAvailable().size() == 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail();
		}

	}
}
