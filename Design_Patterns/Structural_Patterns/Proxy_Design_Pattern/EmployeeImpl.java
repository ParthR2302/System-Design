package Design_Patterns.Structural_Patterns.Proxy_Design_Pattern;

public class EmployeeImpl implements EmployeeInterface {
    @Override
    public void getEmployeeDetails() {
        System.out.println("Getting employee details from database...");
    }

    @Override
    public void createEmployee() {
        System.out.println("Creating employee in database...");
    }
    
}
