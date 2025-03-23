package SOLID_Principles.SRP;

// Without implementing SRP Principle
// One class doing everything
class UserManager {
    public void authenticateUser() {
    }

    public void updateUserProfile() {
    }

    public void sendEmailNotification() {
    }
}

// After applying SRP
// Different classes for different purpose
class UserAuthenticator {
    public void authenticateUser() {
    }
}

class UserProfileManager {
    public void updateUserProfile() {
    }
}

class EmailNotifier{
    public void sendEmailNotification() {
    }
}

public class SRP {
    public static void main(String[] args) {
        System.out.println("Test");
    }
}
