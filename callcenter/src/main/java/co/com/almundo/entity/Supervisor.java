package co.com.almundo.entity;

import co.com.almundo.callcenter.Dispatcher;

public class Supervisor extends Employee {
	public Supervisor(Dispatcher dispatcher, String name) {
		super(dispatcher, name);
		super.setEmployeeType(EmployeeType.SUPERVISOR);
	}

	@Override
	public void addAvailableEmployee(Dispatcher dispatcher) {
		dispatcher.addEmployeeAvailable(new Supervisor(dispatcher, super.getEmployeeName()));
	}

}
