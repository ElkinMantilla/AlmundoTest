package co.com.almundo.entity;

import co.com.almundo.callcenter.Dispatcher;

public class Employee extends Thread implements Comparable<Employee> {

	// Datos del empeado
	private EmployeeType employeeType;
	private String EmployeeName;
	// Llamada que esta atendiendo
	private Call callAnswer;
	private Dispatcher dispatcher;

	public Employee(Dispatcher dispatcher, String name) {
		this.dispatcher = dispatcher;
		this.EmployeeName = name;
	}

	public int getPrioridad() {
		return this.employeeType.getPriority();
	}

	
	public int compareTo(Employee e2) {
		if (this.getPrioridad() < e2.getPrioridad()) {
			return -1;
		}
		if (this.getPrioridad() > e2.getPrioridad()) {
			return 1;
		}
		return 0;
	}

	public void attendCall(Call llamada) throws InterruptedException {
		this.callAnswer = llamada;
		this.start();
	}

	// Ejecución del hilo, representa que el empleado esta atendiendo la llamada.
	// El hilo queda en espera (sleep) durante el tiempo de la llamada.
	@Override
	public void run() {
		try {
			Thread.sleep(1000 * callAnswer.getDuration());
			System.out.println(
					"Llamada " + callAnswer.getDescription() + " finalizada. Duración :" + callAnswer.getDuration());
			this.addAvailableEmployee(dispatcher);
		} catch (InterruptedException e) {
			System.out.println("Error atendiendo llamada " + callAnswer.getDescription());
			e.printStackTrace();
		}
	}

	// Este metodo es implementado por los hijos para agregar
	// a la cola una instancia de empleado (Especializado)
	public void addAvailableEmployee(Dispatcher dispatcher) {
		dispatcher.addEmployeeAvailable(new Employee(dispatcher, this.getEmployeeName()));
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public Call getCallAnswer() {
		return callAnswer;
	}

	public void setCallAnswer(Call callAnswer) {
		this.callAnswer = callAnswer;
	}

	public Dispatcher getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String nameEmployee) {
		this.EmployeeName = nameEmployee;
	}

	@Override
	public String toString() {
		return "Employee [employeeType=" + employeeType + ", EmployeeName=" + EmployeeName + "]";
	}

}
