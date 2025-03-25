package System_Design_Principles.DRY_Principle;

public class DRY {
    // Below three functions don't follow DRY principle
    public double calculate_food_tax(double price) {
        double rate = 0.07;

        return price * (1 + rate);
    }
    public double calculate_clothing_tax(double price) {
        double rate = 0.12;

        return price * (1 + rate);
    }
    public double calculate_electronic_tax(double price) {
        double rate = 0.15;

        return price * (1 + rate);
    }

    // Below function is made with applying DRY principle
    public double calculate_tax(double price, double rate) {
        return price * (1.0 + rate);
    }

    public static void main(String[] args) {
        DRY obj = new DRY();

        System.out.println("Tax on food with price 100: " + obj.calculate_food_tax(100));
        System.out.println("Tax on cloths with price 150: " + obj.calculate_clothing_tax(150));
        System.out.println("Tax on electronic with price 200: " + obj.calculate_electronic_tax(200));

        System.out.println("\n------------------------------------------------------------------\n");

        System.out.println("Tax on food with price 100: " + obj.calculate_tax(100, 0.07));
        System.out.println("Tax on cloths with price 150: " + obj.calculate_tax(150, 0.12));
        System.out.println("Tax on electronic with price 200: " + obj.calculate_tax(200, 0.15));
    }
}
