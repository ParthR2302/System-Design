package Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Vending_Machine.Vending_Machine_States;

import Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Vending_Machine.VendingMachine;


public class DispenseItemState implements VendingMachineState {
    private String item;

    public DispenseItemState(String item) {
        this.item = item;
    }

    @Override
    public VendingMachineState insertCoin(VendingMachine vendingMachine, int coins) {
        System.out.println("\nInside insertCoin method in teh DispenseItemState class");
        return this;
    }

    @Override
    public VendingMachineState selectItem(VendingMachine vendingMachine, String item) {
        return this;
    }

    @Override
    public VendingMachineState dispenseItem(VendingMachine vendingMachine) {
        System.out.println("\nItem " + item + " being dispensed from Vending Machine");
        vendingMachine.updateItemCountAndRemainingCoins(item);
        return new ReturnCoinState();
    }

    @Override
    public VendingMachineState returnCoin(VendingMachine vendingMachine) {
        return this;
    }

    @Override
    public VendingMachineState refillItem(VendingMachine vendingMachine, String item, int quantity) {
        return this;
    }

    @Override 
    public String getStateName() {
        return "DISPENSE_STATE";
    }
    
    // --------------------------------------------------------------------------- Getters and Setters

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
