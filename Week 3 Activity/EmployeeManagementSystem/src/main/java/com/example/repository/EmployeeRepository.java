package com.example.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.employeemanagementsystem.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // Custom query method using method name
    List<Employee> findByDepartmentName(String departmentName);
    
    // Custom query method using @Query annotation
    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findEmployeesWithSalaryGreaterThan(@Param("salary") double salary);
    
    // Method to find all employees with pagination and sorting
    Page<Employee> findAll(Pageable pageable);
    
    // Custom query method using @Query annotation with pagination
    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    Page<Employee> findEmployeesWithSalaryGreaterThan(@Param("salary") double salary, Pageable pageable);
    
    // Custom query method using @Query annotation with pagination
    @Query("SELECT e FROM Employee e JOIN e.department d WHERE d.name = :departmentName")
    Page<Employee> findEmployeesByDepartmentName(@Param("departmentName") String departmentName, Pageable pageable);
}