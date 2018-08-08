package co.com.almundo.entity;

import co.com.almundo.callcenter.Dispatcher;

public class Operator extends Employee {
	public Operator(Dispatcher dispatcher, String name) {
		super(dispatcher, name);
		super.setEmployeeType(EmployeeType.OPERATOR);
	}

	@Override
	public void addAvailableEmployee(Dispatcher dispatcher) {
		dispatcher.addEmployeeAvailable(new Operator(dispatcher, super.getEmployeeName()));
	}

}
