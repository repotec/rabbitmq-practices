package com.demo;

public class Employee {
	private long id;
	private String name;
	private double salary;
	private String type;
	
	public Employee() {
		super();
	}
	
	public Employee(long id, String name, double salary, String type) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.type = type;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", type=" + type + "]";
	}
}
