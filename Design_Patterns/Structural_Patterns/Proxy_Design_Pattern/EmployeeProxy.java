package Design_Patterns.Structural_Patterns.Proxy_Design_Pattern;

public class EmployeeProxy implements EmployeeInterface { 
    public String userRole;
    public EmployeeImpl employeeImpl;

    public EmployeeProxy(String userRole) {
        this.userRole = userRole;
        this.employeeImpl = new EmployeeImpl();
    }
    
    @Override
    public void getEmployeeDetails() {
        if(userRole.equals("ADMIN") || userRole.equals("USER")) {
            employeeImpl.getEmployeeDetails();
        } else {
            System.out.println("Access denied. You do not have permission to view employee details.");
        }
    }

    @Override
    public void createEmployee() {
        if(userRole.equals("ADMIN")) {
            employeeImpl.createEmployee();
        } else {
            System.out.println("Access denied. You do not have permission to create employee details.");
        }
    }
}
