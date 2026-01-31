package Design_Patterns.Creational_Patterns.Abstract_Factory;

interface Button {
    void paint();
}

interface Checkbox {
    void paint();
}

class WindowsButton implements Button {
    public void paint() {
        System.out.println("Rendering a Windows Button.");
    }
}

class MacButton implements Button {
    public void paint() {
        System.out.println("Rendering a Mac Button.");
    }
}

class WindowsCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering a Windows Checkbox.");
    }
}

class MacCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering a Mac Checkbox.");
    }
}

interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

public class ButtonAndCheckbox {
    public static void main(String[] args) {
        GUIFactory windowsFactory = new WindowsFactory();

        Button winButton = windowsFactory.createButton();
        Checkbox winCheckbox = windowsFactory.createCheckbox();

        winButton.paint();
        winCheckbox.paint();
    }
}
