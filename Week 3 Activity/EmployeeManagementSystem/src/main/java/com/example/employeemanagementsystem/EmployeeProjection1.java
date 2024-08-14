package com.example.employeemanagementsystem;

public class EmployeeProjection1 {
	public class EmployeeProjection {
	    private Long id;
	    private String name;
	    private String email;

	    public EmployeeProjection(Long id, String firstName, String lastName, String email) {
	        this.id = id;
	        this.name = firstName + " " + lastName;
	        this.email = email;
	    }

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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	    // getters
	}
}
