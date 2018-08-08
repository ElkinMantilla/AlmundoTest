package co.com.almundo.entity;

import co.com.almundo.callcenter.Dispatcher;

public class Manager extends Employee {

	public Manager(Dispatcher dispatcher, String name) {
		super(dispatcher, name);
		super.setEmployeeType(EmployeeType.MANAGER);
	}

	@Override
	public void addAvailableEmployee(Dispatcher dispatcher) {
		dispatcher.addEmployeeAvailable(new Manager(dispatcher, super.getEmployeeName()));
	}
}
