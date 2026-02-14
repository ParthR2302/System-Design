package Design_Patterns.Structural_Patterns.Proxy_Design_Pattern;

public interface EmployeeMain {
    public static void main(String[] args) {
        EmployeeInterface employeeProxyAdmin = new EmployeeProxy("USER");
        employeeProxyAdmin.getEmployeeDetails();
        employeeProxyAdmin.createEmployee();
    }
}
