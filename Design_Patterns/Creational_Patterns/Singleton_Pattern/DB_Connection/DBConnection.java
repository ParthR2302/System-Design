package Design_Patterns.Creational_Patterns.Singleton_Pattern.DB_Connection;

public class DBConnection {
    private static volatile DBConnection connection;
    private String name;
    private DBConnection(String name) {
        // private constructor to prevent instantiation
        this.name = name;
    }

    // Thread: T1, T2 -> At initial stage
    // Thread: T2, T3, T4,...TN -> Later stage when connection is already created
    static DBConnection getConnection(String name) {
        if(connection == null) {
            synchronized (DBConnection.class) {
                // Double check locking
                // Code is slow only at time of connection creation, after that it is fast
                if(connection == null) { 
                    connection = new DBConnection(name);
                }
            }
        }
        
        return connection;
    }

    public String getName() {
        return name;
    }
}
