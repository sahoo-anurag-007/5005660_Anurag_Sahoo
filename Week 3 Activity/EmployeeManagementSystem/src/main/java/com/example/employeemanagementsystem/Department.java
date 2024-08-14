	package com.example.employeemanagementsystem;

	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
	import java.util.List;

	@Entity
	@NamedQuery(name = "Department.findByNameContaining",
    query = "SELECT d FROM Department d WHERE d.name LIKE :name")
	@NamedQuery(name = "Department.findDepartmentsByLocation",
    query = "SELECT d FROM Department d WHERE d.location = :location")
	public class Department extends Auditable{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    @OneToMany(mappedBy = "department")
	    private List<Employee> employees;

	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public List<Employee> getEmployees() {
	        return employees;
	    }

	    public void setEmployees(List<Employee> employees) {
	        this.employees = employees;
	    }
	}