package Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Vending_Machine.Vending_Machine_States;

import Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Vending_Machine.VendingMachine;

public class IdleState implements VendingMachineState {

    @Override
    public VendingMachineState insertCoin(VendingMachine vendingMachine, int coins) {
        if (coins <= 0) {
            System.out.println("\nCoin amount must be greater than zero");
            return this;
        }
        vendingMachine.setCoins(vendingMachine.getCoins() + coins);
        return new HasCoinState();
    }

    @Override
    public VendingMachineState selectItem(VendingMachine vendingMachine, String item) {
        System.out.println("\nInsert coins before selecting an item");
        return this;
    }

    @Override
    public VendingMachineState dispenseItem(VendingMachine vendingMachine) {
        System.out.println("\nSelect an item before dispensing");
        return this;
    }

    @Override
    public VendingMachineState returnCoin(VendingMachine vendingMachine) {
        System.out.println("\nNo coins to return");
        return this;
    }

    @Override
    public VendingMachineState refillItem(VendingMachine vendingMachine, String item, int quantity) {
        if (quantity <= 0) {
            System.out.println("\nRefill quantity must be greater than zero");
            return this;
        }
        if (!vendingMachine.getItemCostMap().containsKey(item)) {
            System.out.println("\nCannot refill item without a configured price: " + item);
            return this;
        }
        int currentCount = vendingMachine.getItemCountMap().getOrDefault(item, 0);
        vendingMachine.getItemCountMap().put(item, currentCount + quantity);
        System.out.println("\nAdded " + quantity + " of " + item);
        return this;
    }

    @Override 
    public String getStateName() {
        return "IDLE_STATE";
    }
}