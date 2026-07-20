package Design_Patterns.Creational_Patterns.Singleton_Pattern.DB_Connection;

public class DBConnMain {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            DBConnection conn1 = DBConnection.getConnection("conn1");
            System.out.println("Thread 1: " + conn1.getName());
        });
        Thread thread2 = new Thread(() -> {
            DBConnection conn2 = DBConnection.getConnection("conn2");
            System.out.println("Thread 2: " + conn2.getName());
        });

        thread1.start();
        thread2.start();
    }
}
