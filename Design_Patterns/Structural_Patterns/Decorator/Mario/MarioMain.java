package Design_Patterns.Structural_Patterns.Decorator.Mario;

public class MarioMain {
    public static void main(String[] args) {
        Character mario = new GunPower(new HeightUp(new Mario()));

        System.out.println(mario.getAbilities());

        mario = new HeightUp(new StarPower(new Mario()));

        System.out.println(mario.getAbilities());
    }
}

interface Character{
    public String getAbilities();
}

class Mario implements Character {
    @Override public String getAbilities() {
        return "Mario";
    }
}

// Why abstract class, why not interface
// If abstract class then coffee attribute is automatically copied to implementing classes
// We don't need to have a seperate coffee variable in each children classes
abstract class CharacterDecorator implements Character {
    protected Character character;

    public CharacterDecorator(Character character) {
        this.character = character;
    }
}

class HeightUp extends CharacterDecorator {

    public HeightUp(Character character) {
        super(character);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " with HeightUp";
    }
}

class GunPower extends CharacterDecorator {

    public GunPower(Character character) {
        super(character);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " with GunPower";
    }
}

class StarPower extends CharacterDecorator {

    public StarPower(Character character) {
        super(character);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " with StarPower";
    }
}