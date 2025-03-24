package SOLID_Principles.Open_Close_Principle;

// Before applying OCP
/*
 * Problems in the below approach:
 * Every time we add a new shape (e.g., Triangle), we must modify AreaCalculator, violating OCP.
 * The calculateArea method is tightly coupled with shape classes.
 */
class Rectangle {
    double length, width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    double area() {
        return length * width;
    }
}

class Circle {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double area() {
        return Math.PI * radius * radius;
    }
}

class AreaCalculator {
    double calculateArea(Object shape) {
        double area = 0;
        if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            area = rectangle.area();
        } else if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            area = circle.area();
        }
        return area;
    }
}

public class BeforeOCP {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(10, 5);
        Circle circle = new Circle(7);

        AreaCalculator calculator = new AreaCalculator();
        System.out.println("Rectangle Area: " + calculator.calculateArea(rectangle));
        System.out.println("Circle Area: " + calculator.calculateArea(circle));
    }
}
