package SOLID_Principles.OCP;

// Step 1: Define an interface for Shapes
interface Shape {
    double area();
}

// Step 2: Implement different shapes without modifying existing code
class RectangleNew implements Shape {
    double length, width;

    RectangleNew(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }
}

class CircleNew implements Shape {
    double radius;

    CircleNew(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

// Step 3: Create an AreaCalculatorNew that follows OCP
class AreaCalculatorNew {
    double calculateArea(Shape shape) {
        return shape.area();  // No need for instanceof checks
    }
}

public class AfterOCP {
    public static void main(String[] args) {
        RectangleNew rectangle = new RectangleNew(10, 5);
        Shape circle = new CircleNew(7);

        AreaCalculatorNew calculator = new AreaCalculatorNew();
        System.out.println("Rectangle Area: " + calculator.calculateArea(rectangle));
        System.out.println("Circle Area: " + calculator.calculateArea(circle));

        // Adding a new shape without modifying existing code
        Shape triangle = new Triangle(5, 10);
        System.out.println("Triangle Area: " + calculator.calculateArea(triangle));
    }
}

// New Triangle class added without modifying existing code
class Triangle implements Shape {
    double base, height;

    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double area() {
        return 0.5 * base * height;
    }
}
