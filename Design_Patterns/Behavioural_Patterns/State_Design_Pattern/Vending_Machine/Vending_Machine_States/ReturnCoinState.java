package Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Vending_Machine.Vending_Machine_States;

import Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Vending_Machine.VendingMachine;

public class ReturnCoinState implements VendingMachineState{
    @Override
    public VendingMachineState insertCoin(VendingMachine vendingMachine, int coins) {
        return this;
    }

    @Override
    public VendingMachineState selectItem(VendingMachine vendingMachine, String item) {
        return this;
    }

    @Override
    public VendingMachineState dispenseItem(VendingMachine vendingMachine) {
        return this;
    }

    @Override
    public VendingMachineState returnCoin(VendingMachine vendingMachine) {
        int coinsToReturn = vendingMachine.getCoins();
        if(coinsToReturn > 0) {
            System.out.println("\nReturning " + coinsToReturn + " back to buyer");
            vendingMachine.setCoins(0);
        }
        return new IdleState();
    }

    @Override
    public VendingMachineState refillItem(VendingMachine vendingMachine, String item, int quantity) {
        return this;
    }

    @Override 
    public String getStateName() {
        return "RETURN_COIN_STATE";
    }
}
