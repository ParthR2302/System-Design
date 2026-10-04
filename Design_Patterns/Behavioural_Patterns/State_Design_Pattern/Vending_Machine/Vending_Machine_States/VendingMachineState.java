package Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Vending_Machine.Vending_Machine_States;

import Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Vending_Machine.VendingMachine;

public interface VendingMachineState {
    VendingMachineState insertCoin(VendingMachine vendingMachine, int coin);
    VendingMachineState selectItem(VendingMachine vendingMachine, String item);
    VendingMachineState dispenseItem(VendingMachine vendingMachine);
    VendingMachineState returnCoin(VendingMachine vendingMachine);
    VendingMachineState refillItem(VendingMachine vendingMachine, String item, int quantity);

    String getStateName();
}
