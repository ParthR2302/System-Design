package Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Vending_Machine;

import java.util.HashMap;
import java.util.Map;

import Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Vending_Machine.Vending_Machine_States.IdleState;
import Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Vending_Machine.Vending_Machine_States.VendingMachineState;

// In state design pattern, we use a class to work with different states
// This class is called a Context class

public class VendingMachine {
    private VendingMachineState vendingMachineState;
    private Map<String, Integer> itemCountMap;
    private Map<String, Integer> itemCostMap;
    private int coins;

    public VendingMachine() {
        this.vendingMachineState = new IdleState();
        this.itemCountMap = new HashMap<>();
        this.itemCostMap = new HashMap<>();
        this.coins = 0;
    }

    public void refillItem(String item, int count) {
        vendingMachineState.refillItem(this, item, count);
    }

    public void addItem(String item, int count, int cost) {
        itemCountMap.put(item, itemCountMap.getOrDefault(item, 0) + count);
        itemCostMap.put(item, cost);
    }

    public void insertCoin(int coin) {
        this.vendingMachineState = vendingMachineState.insertCoin(this, coin);
    }

    public void selectItem(String item) {
        this.vendingMachineState = this.vendingMachineState.selectItem(this, item);
    }

    public void dispenseItem() {
        this.vendingMachineState = this.vendingMachineState.dispenseItem(this);
    }

    public void returnCoins() {
        this.vendingMachineState = this.vendingMachineState.returnCoin(this);
    }

    // We can add update cost and update count methods as well

    public void updateItemCountAndRemainingCoins(String item) {
        this.itemCountMap.put(item, this.itemCountMap.get(item) - 1);
        this.coins -= this.itemCostMap.get(item);
    }

    public void printState() {
        System.out.println("\n----- Vending Machine State -----");

        for (String item : itemCostMap.keySet()) {
            System.out.println(
                "Item: " + item +
                " | Cost: " + itemCostMap.get(item) +
                " | Quantity: " + itemCountMap.getOrDefault(item, 0)
            );
        }

        System.out.println("\nTotal coins added in the vending machine: " + coins);

        System.out.println("\n---------------------------------");
    }

    // --------------------------------------------------------------------------- Getters and Setters

    public VendingMachineState getVendingMachineState() {
        return vendingMachineState;
    }

    public void setVendingMachineState(VendingMachineState vendingMachineState) {
        this.vendingMachineState = vendingMachineState;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Map<String, Integer> getItemCountMap() {
        return itemCountMap;
    }

    public void setItemCountMap(Map<String, Integer> itemCountMap) {
        this.itemCountMap = itemCountMap;
    }

    public Map<String, Integer> getItemCostMap() {
        return itemCostMap;
    }

    public void setItemCostMap(Map<String, Integer> itemCostMap) {
        this.itemCostMap = itemCostMap;
    }

    
}
