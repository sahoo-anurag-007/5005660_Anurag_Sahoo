class Employee {
    private int employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}

class EmployeeManagementSystem {
    private Employee[] employees;
    private int employeeCount;

    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        employeeCount = 0;
    }

    // Method to add an employee
    public boolean addEmployee(Employee employee) {
        if (employeeCount < employees.length) {
            employees[employeeCount] = employee;
            employeeCount++;
            return true;
        } else {
            System.out.println("Array is full. Cannot add more employees.");
            return false;
        }
    }

    // Method to search for an employee by ID
    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    // Method to traverse and display all employees
    public void traverseEmployees() {
        for (int i = 0; i < employeeCount; i++) {
            System.out.println(employees[i]);
        }
    }

    // Method to delete an employee by ID
    public boolean deleteEmployee(int employeeId) {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                // Shift elements to the left
                for (int j = i; j < employeeCount - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[employeeCount - 1] = null; // Clear the last element
                employeeCount--;
                return true;
            }
        }
        return false;
    }
}

public class EMS {
    public static void main(String[] args) {
        // Create an employee management system with a capacity of 5
        EmployeeManagementSystem ems = new EmployeeManagementSystem(5);

        // Add employees
        ems.addEmployee(new Employee(1, "Alice", "Manager", 75000));
        ems.addEmployee(new Employee(2, "Bob", "Developer", 60000));
        ems.addEmployee(new Employee(3, "Charlie", "Designer", 50000));
        ems.addEmployee(new Employee(4, "Diana", "Tester", 45000));
        ems.addEmployee(new Employee(5, "Eve", "HR", 55000));

        // Traverse and display all employees
        System.out.println("All Employees:");
        ems.traverseEmployees();

        // Search for an employee by ID
        int searchId = 3;
        Employee foundEmployee = ems.searchEmployee(searchId);
        if (foundEmployee != null) {
            System.out.println("Employee found: " + foundEmployee);
        } else {
            System.out.println("Employee with ID " + searchId + " not found.");
        }

        // Delete an employee by ID
        int deleteId = 2;
        boolean deleted = ems.deleteEmployee(deleteId);
        if (deleted) {
            System.out.println("Employee with ID " + deleteId + " deleted.");
        } else {
            System.out.println("Employee with ID " + deleteId + " not found.");
        }

        // Traverse and display all employees after deletion
        System.out.println("Employees after deletion:");
        ems.traverseEmployees();
    }
}