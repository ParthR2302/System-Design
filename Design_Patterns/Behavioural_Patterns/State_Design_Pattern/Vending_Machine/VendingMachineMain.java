package Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Vending_Machine;

public class VendingMachineMain {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.addItem("Diet Coke", 5, 40);
        vendingMachine.addItem("Chips", 2, 20);
        vendingMachine.addItem("Chocolate", 10, 10);
        vendingMachine.addItem("Cookies", 5, 20);
        vendingMachine.addItem("Protein Bars", 15, 60);

        vendingMachine.printState();

        System.out.println("\nCurrent State of the vending machine: " + vendingMachine.getVendingMachineState().getStateName());

        vendingMachine.insertCoin(50);
        System.out.println("\nVending machine state after inserting coin: " + vendingMachine.getVendingMachineState().getStateName());
        vendingMachine.insertCoin(10);
        System.out.println("\nVending machine state after inserting coin: " + vendingMachine.getVendingMachineState().getStateName());
        vendingMachine.printState();

        vendingMachine.selectItem("Diet Coke");
        vendingMachine.dispenseItem();
        vendingMachine.returnCoins();

        vendingMachine.printState();
    }
}