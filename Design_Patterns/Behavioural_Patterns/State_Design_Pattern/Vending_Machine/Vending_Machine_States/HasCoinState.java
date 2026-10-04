package Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Vending_Machine.Vending_Machine_States;

import Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Vending_Machine.VendingMachine;

public class HasCoinState implements VendingMachineState {

    @Override
    public VendingMachineState insertCoin(VendingMachine vendingMachine, int coin) {
        System.out.println("\nInside insertCoin method in teh HasCoinState class");
        return this;
    }

    @Override
    public VendingMachineState selectItem(VendingMachine vendingMachine, String item) {

        if(!vendingMachine.getItemCountMap().containsKey(item) || vendingMachine.getItemCountMap().get(item) == 0 ) {
            System.out.println("\nSelected item " + item + " is not currently present in the Vending Machine");
            return new ReturnCoinState();
        }

        int itemCount = vendingMachine.getItemCountMap().get(item);
        int coinsEntered = vendingMachine.getCoins();
        int itemPrice = vendingMachine.getItemCostMap().get(item);
        if(itemCount > 0 && coinsEntered >= itemPrice) {
            return new DispenseItemState(item);
        }
        else {
            System.out.println("\nEntered amount is not sufficient, please collect your money and start from the beginning");
            return new ReturnCoinState();
        }
    }

    @Override
    public VendingMachineState dispenseItem(VendingMachine vendingMachine) {
        return this;
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
        return "HAS_COIN_STATE";
    }
}
