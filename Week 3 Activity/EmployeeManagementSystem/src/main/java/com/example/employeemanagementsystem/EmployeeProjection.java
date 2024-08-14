package com.example.employeemanagementsystem;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {
	    @Value("#{target.id}")
	    Long getId();

	    @Value("#{target.firstName} ${target.lastName}")
	    String getName();

	    String getEmail();
}
