package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.employeemanagementsystem.Employee;
import com.example.repository.EmployeeRepository;


import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Create
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    // Read
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        return new ResponseEntity<>(employee, employee != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);
        if (existingEmployee != null) {
            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setDepartment(employee.getDepartment());
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employees/department/{departmentName}")
    public Page<Employee> findEmployeesByDepartmentName(@PathVariable String departmentName, 
                                                      @RequestParam(defaultValue = "0") int page, 
                                                      @RequestParam(defaultValue = "10") int size, 
                                                      @RequestParam(defaultValue = "id,asc") String[] sort) {
        Pageable pageable = PageRequest.of(page, size, getSort(sort));
        return employeeRepository.findEmployeesByDepartmentName(departmentName, (org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable) pageable);
    }

    @GetMapping("/employees/salary/{salary}")
    public Page<Employee> findEmployeesWithSalaryGreaterThan(@PathVariable double salary, 
                                                            @RequestParam(defaultValue = "0") int page, 
                                                            @RequestParam(defaultValue = "10") int size, 
                                                            @RequestParam(defaultValue = "id,asc") String[] sort) {
        Pageable pageable = PageRequest.of(page, size, getSort(sort));
        return employeeRepository.findEmployeesWithSalaryGreaterThan(salary, (org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable) pageable);
    }

    @GetMapping("/employees")
    public Page<Employee> findAllEmployees(@RequestParam(defaultValue = "0") int page, 
                                          @RequestParam(defaultValue = "10") int size, 
                                          @RequestParam(defaultValue = "id,asc") String[] sort) {
        Pageable pageable = PageRequest.of(page, size, getSort(sort));
        return employeeRepository.findAll(pageable);
    }

    private Sort getSort(String[] sort) {
        Sort sortable = Sort.unsorted();
        for (String s : sort) {
            String[] split = s.split(",");
            sortable = sortable.and(Sort.by(split[0]).ascending());
            if (split[1].equals("desc")) {
                sortable = sortable.and(Sort.by(split[0]).descending());
            }
        }
        return sortable;
    }
}