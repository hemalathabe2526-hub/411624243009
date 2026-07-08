import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class EmployeeManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Employee> employees = new ArrayList<>();
    private static int nextEmployeeId = 101;

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    searchEmployee();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    displaySalaryDetails();
                    break;
                case 7:
                    System.out.println("Thank you for using Employee Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("\n========== Employee Management System ==========");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Search Employee");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. Display Salary Details");
        System.out.println("7. Exit");
        System.out.println("================================================");
    }

    private static void addEmployee() {
        System.out.println("\n--- Add Employee ---");
        String name = readText("Enter employee name: ");
        String department = readText("Enter department: ");
        String designation = readText("Enter designation: ");
        double basicSalary = readDouble("Enter basic salary: ");
        Employee employee = new Employee(nextEmployeeId++, name, department, designation, basicSalary);
        employees.add(employee);
        System.out.println("Employee added successfully. Employee ID: " + employee.getId());
    }

    private static void viewAllEmployees() {
        System.out.println("\n--- Employee List ---");
        if (employees.isEmpty()) {
            System.out.println("No employee records found.");
            return;
        }
        printTableHeader();
        for (Employee employee : employees) {
            employee.displayRow();
        }
    }

    private static void searchEmployee() {
        System.out.println("\n--- Search Employee ---");
        int id = readInt("Enter employee ID: ");
        Employee employee = findEmployeeById(id);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }
        printTableHeader();
        employee.displayRow();
    }

    private static void updateEmployee() {
        System.out.println("\n--- Update Employee ---");
        int id = readInt("Enter employee ID to update: ");
        Employee employee = findEmployeeById(id);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }
        System.out.println("Leave a field empty to keep the current value.");
        String name = readOptionalText("Enter new name (" + employee.getName() + "): ");
        String department = readOptionalText("Enter new department (" + employee.getDepartment() + "): ");
        String designation = readOptionalText("Enter new designation (" + employee.getDesignation() + "): ");
        String salaryInput = readOptionalText("Enter new basic salary (" + employee.getBasicSalary() + "): ");
        if (!name.isEmpty()) {
            employee.setName(name);
        }
        if (!department.isEmpty()) {
            employee.setDepartment(department);
        }
        if (!designation.isEmpty()) {
            employee.setDesignation(designation);
        }
        if (!salaryInput.isEmpty()) {
            try {
                double salary = Double.parseDouble(salaryInput);
                if (salary > 0) {
                    employee.setBasicSalary(salary);
                } else {
                    System.out.println("Salary must be greater than 0. Salary was not updated.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary. Salary was not updated.");
            }
        }
        System.out.println("Employee details updated successfully.");
    }

    private static void deleteEmployee() {
        System.out.println("\n--- Delete Employee ---");
        int id = readInt("Enter employee ID to delete: ");
        Employee employee = findEmployeeById(id);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }
        employees.remove(employee);
        System.out.println("Employee deleted successfully.");
    }

    private static void displaySalaryDetails() {
        System.out.println("\n--- Salary Details ---");
        int id = readInt("Enter employee ID: ");
        Employee employee = findEmployeeById(id);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }
        double hra = employee.calculateHra();
        double da = employee.calculateDa();
        double pf = employee.calculatePf();
        double grossSalary = employee.calculateGrossSalary();
        double netSalary = employee.calculateNetSalary();
        System.out.println("Employee ID   : " + employee.getId());
        System.out.println("Name          : " + employee.getName());
        System.out.printf("Basic Salary  : %.2f%n", employee.getBasicSalary());
        System.out.printf("HRA (20%%)     : %.2f%n", hra);
        System.out.printf("DA (10%%)      : %.2f%n", da);
        System.out.printf("PF (8%%)       : %.2f%n", pf);
        System.out.printf("Gross Salary  : %.2f%n", grossSalary);
        System.out.printf("Net Salary    : %.2f%n", netSalary);
    }

    private static Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    private static void printTableHeader() {
        System.out.printf("%-8s %-20s %-15s %-18s %-12s%n",
                "ID", "Name", "Department", "Designation", "Salary");
        System.out.println("--------------------------------------------------------------------------");
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value > 0) {
                    return value;
                }
                System.out.println("Amount must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }

    private static String readText(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    private static String readOptionalText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    static class Employee {
        private final int id;
        private String name;
        private String department;
        private String designation;
        private double basicSalary;
        Employee(int id, String name, String department, String designation, double basicSalary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.designation = designation;
            this.basicSalary = basicSalary;
        }
        int getId() {
            return id;
        }
        String getName() {
            return name;
        }
        void setName(String name) {
            this.name = name;
        }
        String getDepartment() {
            return department;
        }
        void setDepartment(String department) {
            this.department = department;
        }
        String getDesignation() {
            return designation;
        }
        void setDesignation(String designation) {
            this.designation = designation;
        }
        double getBasicSalary() {
            return basicSalary;
        }
        void setBasicSalary(double basicSalary) {
            this.basicSalary = basicSalary;
        }
        double calculateHra() {
            return basicSalary * 0.20;
        }
        double calculateDa() {
            return basicSalary * 0.10;
        }
        double calculatePf() {
            return basicSalary * 0.08;
        }
        double calculateGrossSalary() {
            return basicSalary + calculateHra() + calculateDa();
        }
        double calculateNetSalary() {
            return calculateGrossSalary() - calculatePf();
        }
        void displayRow() {
            System.out.printf("%-8d %-20s %-15s %-18s %-12.2f%n",
                    id, name, department, designation, basicSalary);
        }
    }
}