package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.employeemanagementsystem.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	// Custom query method using keywords
    List<Department> findByNameContaining(String name);
    
    // Custom query method using @Query annotation
    @Query("SELECT d FROM Department d WHERE d.location = :location")
    List<Department> findDepartmentsByLocation(@Param("location") String location);
}