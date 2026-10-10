package Design_Patterns.Structural_Patterns.Decorator.Coffee_Example;

public class CoffeeMain {
    public static void main(String[] args) {
        Coffee coffee = new CoffeeWithChocolateChips(new CoffeeWithWhippedCream(new Espresso()));

        System.out.println(coffee.serveCoffee());

        coffee = new CoffeeWithChocolateChips(new CoffeeWithIceCream(new Cappuccino()));

        System.out.println(coffee.serveCoffee());
    }
}

interface Coffee {
    public String serveCoffee();
}

class Cappuccino implements Coffee {

    @Override
    public String serveCoffee() {
        return "Ready to serve Cappuccino Coffee";
    }
}

class Espresso implements Coffee {

    @Override
    public String serveCoffee() {
        return "Ready to serve Espresso Coffee";
    }
}

// Why abstract class, why not interface
// If abstract class then coffee attribute is automatically copied to implementing classes
// We don't need to have a seperate coffee variable in each children classes
abstract class CoffeeDecorator implements Coffee {
    Coffee coffee;
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}

class CoffeeWithWhippedCream extends CoffeeDecorator {
    public CoffeeWithWhippedCream(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String serveCoffee() {
        return coffee.serveCoffee() + " with Whipped Cream";
    }
}

class CoffeeWithChocolateChips extends CoffeeDecorator {
    public CoffeeWithChocolateChips(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String serveCoffee() {
        return coffee.serveCoffee() + " with Chocolate Chips";
    }
}

class CoffeeWithIceCream extends CoffeeDecorator {
    public CoffeeWithIceCream(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String serveCoffee() {
        return coffee.serveCoffee() + " with Ice Cream";
    }
}