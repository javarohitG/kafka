package com.rohit.training.domain;

public class Employee {
	private int emp_id;
	private String name;
	private String designation;
	private double salary;
	public Employee(int emp_id, String name, String designation) {
		super();
		this.emp_id = emp_id;
		this.name = name;
		this.designation = designation;
	}
	public Employee(int emp_id, String name, String designation, double salary) {
		super();
		this.emp_id = emp_id;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	

}
