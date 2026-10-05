package Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.ATM;

public class ATMMain {
    public static void main(String[] args) {
        ATMHandler thousandHandler = new ThousandHandler(3);
        ATMHandler fiveHundredHandler = new FiveHundredHandler(5);
        ATMHandler hundredHandler = new HundredHandler(4);

        thousandHandler.setNextHandler(fiveHundredHandler);
        fiveHundredHandler.setNextHandler(hundredHandler);

        int amount = 5700;

        thousandHandler.printState();
        System.out.println();
        
        System.out.println("Amount entered to withdraw: " + amount);
        thousandHandler.dispense(amount);

        System.out.println();
        thousandHandler.printState();
    }
}

interface ATMHandler {
    void setNextHandler(ATMHandler atmHandler);
    boolean dispense(int amount);
    void printState();
}

enum Notes {
    THOUSAND(1000),
    FIVE_HUNDRED(500),
    HUNDRED(100);

    private final int value;

    Notes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

class ThousandHandler implements ATMHandler {

    private ATMHandler nextAtmHandler;
    private int notes;

    public ThousandHandler(int notes) {
        this.notes = notes;
    }

    @Override
    public void setNextHandler(ATMHandler nextAtmHandler) {
        this.nextAtmHandler = nextAtmHandler;
    }

    @Override
    public boolean dispense(int amount) {
        int notesNeeded = amount / Notes.THOUSAND.getValue();
        int maxNotesGranted = Math.min(notes, notesNeeded);
        int remainingAmount = amount - maxNotesGranted * Notes.THOUSAND.getValue();

        if(remainingAmount > 0) {
            if(this.nextAtmHandler == null || !this.nextAtmHandler.dispense(remainingAmount)) {
                System.out.println("Amount of " + amount + " cannot be fulfilled (Insufficinet fund in ATM)");
                return false;
            }
        }

        this.notes -= maxNotesGranted;
        System.out.println("Dispensing " + maxNotesGranted + " notes of denomination " + Notes.THOUSAND);

        return true;
    }

    @Override
    public void printState() {
        System.out.println("Total Notes present for " + Notes.THOUSAND + " denomiation are: " + this.notes);
        if(this.nextAtmHandler != null) {
            this.nextAtmHandler.printState();
        }
    }

    public ATMHandler getNextAtmHandler() {
        return nextAtmHandler;
    }
}

class FiveHundredHandler implements ATMHandler {

    private ATMHandler nextAtmHandler;
    private int notes;

    public FiveHundredHandler(int notes) {
        this.notes = notes;
    }

    @Override
    public void setNextHandler(ATMHandler nextAtmHandler) {
        this.nextAtmHandler = nextAtmHandler;
    }

    @Override
    public boolean dispense(int amount) {
        int notesNeeded = amount / Notes.FIVE_HUNDRED.getValue();
        int maxNotesGranted = Math.min(notes, notesNeeded);
        int remainingAmount = amount - maxNotesGranted * Notes.FIVE_HUNDRED.getValue();

        if(remainingAmount > 0) {
            if(this.nextAtmHandler == null || !this.nextAtmHandler.dispense(remainingAmount)) {
                return false;
            }
        }

        this.notes -= maxNotesGranted;
        System.out.println("Dispensing " + maxNotesGranted + " notes of denomination " + Notes.FIVE_HUNDRED);

        return true;
    }
    
    @Override
    public void printState() {
        System.out.println("Total Notes present for " + Notes.THOUSAND + " denomiation are: " + this.notes);
        if(this.nextAtmHandler != null) {
            this.nextAtmHandler.printState();
        }
    }

    public ATMHandler getNextAtmHandler() {
        return nextAtmHandler;
    }
}

class HundredHandler implements ATMHandler {

    private ATMHandler nextAtmHandler;
    private int notes;

    public HundredHandler(int notes) {
        this.notes = notes;
    }

    @Override
    public void setNextHandler(ATMHandler nextAtmHandler) {
        this.nextAtmHandler = nextAtmHandler;
    }

    @Override
    public boolean dispense(int amount) {
        int notesNeeded = amount / Notes.HUNDRED.getValue();
        int maxNotesGranted = Math.min(notes, notesNeeded);
        int remainingAmount = amount - maxNotesGranted * Notes.HUNDRED.getValue();

        if(remainingAmount > 0) {
            if(this.nextAtmHandler == null || !this.nextAtmHandler.dispense(remainingAmount)) {
                return false;
            }
        }

        this.notes -= maxNotesGranted;
        System.out.println("Dispensing " + maxNotesGranted + " notes of denomination " + Notes.HUNDRED);

        return true;
    }

    @Override
    public void printState() {
        System.out.println("Total Notes present for " + Notes.THOUSAND + " denomiation are: " + this.notes);
        if(this.nextAtmHandler != null) {
            this.nextAtmHandler.printState();
        }
    }

    public ATMHandler getNextAtmHandler() {
        return nextAtmHandler;
    }
}