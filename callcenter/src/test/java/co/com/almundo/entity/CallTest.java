package co.com.almundo.entity;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CallTest extends TestCase {
	public static Test suite() {
		return new TestSuite(CallTest.class);
	}

	public void testCall() {
		Call call = new Call("llamada test");
		System.out.println("Duracion llamada: " + call.getDuration());
		assertTrue(call.getDuration() >= Call.MIN_DURATION);
		assertTrue(call.getDuration() <= Call.MAX_DURATION);
	}
}
