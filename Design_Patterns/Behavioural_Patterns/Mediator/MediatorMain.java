package Design_Patterns.Behavioural_Patterns.Mediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MediatorMain {
    public static void main(String[] args) {
        Mediator chatMediator = new ChatMediator();
        User user1 = new User("Alice", chatMediator);
        User user2 = new User("Bob", chatMediator);
        User user3 = new User("John", chatMediator);
        User user4 = new User("Rob", chatMediator);
        
        user1.muteUser(user2.getName());
        
        user1.sendAll("This is a message from Alice");
        System.out.println();

        user3.sendATo(user4.getName(), "Hi Rob, this is a message from John");
        System.out.println();

        user3.muteUser(user4.getName());
        user3.sendATo(user4.getName(), "Hi Rob, this is a message from John after John muted Rob");
        System.out.println();
    }
}

interface Mediator {
    void register(Collegue collegue);

    void sendAll(String from, String message);

    void sendATo(String from, String to, String message);

    void mute(String who, String whom);
}

interface Collegue {
    String getName();

    void sendAll(String message);

    void sendATo(String to, String message);

    void receive(String from, String message);

    void muteUser(String whom);
}

class ChatMediator implements Mediator {

    ArrayList<Collegue> collegues;
    Map<String, ArrayList<String>> mutes; // {muter, muted}

    public ChatMediator() {
        collegues = new ArrayList<>();
        mutes = new HashMap<>();
    }

    @Override
    public void register(Collegue collegue) {
        collegues.add(collegue);
    }

    public void mute(String who, String whom) {
        mutes.computeIfAbsent(who, k -> new ArrayList<>()).add(whom);
    }

    @Override
    public void sendAll(String from, String message) {
        System.out.println("[ " + from + " broadcasts]: " + message);

        for (Collegue collegue : collegues) {
            String collegueName = collegue.getName();
            if (collegueName.equals(from))
                continue;

            boolean isMuted = false;
            if (mutes.containsKey(from)) {
                for (String mutedForThisUser : mutes.get(from)) {
                    if (collegueName.equals(mutedForThisUser)) {
                        isMuted = true;
                        break;
                    }
                }
            }

            if (!isMuted) {
                collegue.receive(from, message);
            }
        }
    }

    @Override
    public void sendATo(String from, String to, String message) {
        System.out.println("[ " + from + " -> " + to + "]: " + message);
        for (Collegue collegue : collegues) {
            if (collegue.getName().equals(to)) {
                boolean isMuted = false;
                if (mutes.containsKey(from)) {
                    for (String mutedForThisUser : mutes.get(from)) {
                        if (to.equals(mutedForThisUser)) {
                            isMuted = true;
                            break;
                        }
                    }
                }

                if(!isMuted) {
                    collegue.receive(from, message);
                    return;
                }
            }
        }
        System.out.println("[Mediator] User " + to + " not found or is muted");
    }

}

class User implements Collegue {

    String name;
    Mediator mediator;

    public User(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
        mediator.register(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void sendAll(String message) {
        mediator.sendAll(name, message);
    }

    @Override
    public void sendATo(String to, String message) {
        mediator.sendATo(name, to, message);
    }

    @Override
    public void receive(String from, String message) {
        System.out.println("    " + name + " got message from " + from + ": " + message);
    }

    @Override
    public void muteUser(String whom) {
        mediator.mute(name, whom);
    }
}