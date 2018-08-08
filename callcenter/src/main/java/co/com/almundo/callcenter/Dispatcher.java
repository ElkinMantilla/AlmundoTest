package co.com.almundo.callcenter;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import co.com.almundo.entity.Employee;
import co.com.almundo.entity.Call;

public class Dispatcher extends Thread {

	private BlockingQueue<Employee> employeeAvailable;
	private BlockingQueue<Call> calls;

	public Dispatcher() {
		employeeAvailable = new PriorityBlockingQueue<Employee>();
		calls = new LinkedBlockingQueue<Call>();
	}

	public void dispatchCall(Call llamada) throws InterruptedException {
		System.out.println("Llamada entrante " + llamada.getDescription());
		this.calls.put(llamada);
	}

	public void run() {
		Call call;
		Employee employee;
		try {
			while (true) {
				call = calls.take();
				employee = employeeAvailable.take();
				System.out.println(
						"El empleado " + employee.getEmployeeName() + " va a tomar la llamda " + call.getDescription());
				employee.attendCall(call);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void addEmployeeAvailable(Employee empleado) {
		this.employeeAvailable.add(empleado);
		System.out.println("Empleados disponibles:" + employeeAvailable.toString());
	}

	public Queue<Call> getCalls() {
		return calls;
	}

	public Queue<Employee> getEmployeeAvailable() {
		return employeeAvailable;
	}

}
