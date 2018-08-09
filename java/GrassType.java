import java.awt.Rectangle;
import java.util.Random;

/**
 * A grass type pokemon
 *
 * @author Farhan Tejani, cbaca3
 * @version 1.0
 */
public abstract class GrassType extends Pokemon {

    /**
     * Constructor
     * @param x The X position of the Grass type Pokemon
     * @param y The Y position of the Grass type Pokemon
     * @param bounds The bounding Rectangle
     */
    public GrassType(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }


    /**
    * Overrides the abstract move method and moves the grass type in a random
    * fashion. If it is in it's designated quadrant it increases it's level
    * by more.
    * When not in the quadrant, the health decreases and level increases
    * normally.
    */
    @Override
    public void move() {
        Random xPosition = new Random();
        Random yPosition = new Random();
        int moveX = xPosition.nextInt(32) - 16;
        int moveY = yPosition.nextInt(32) - 16;
        inBounds();
        if (this.getXPos() <= (int) (getBounds().getWidth() / 2) - 90
            && this.getYPos() >= (int) (getBounds().getHeight() / 2) - 90) {
            this.setHealth(this.getHealth() - 1);
            this.setLevel(this.getLevel() + 3);
            this.setXPos(this.getXPos() + moveX);
            this.setYPos(this.getYPos() + moveY);
        } else {
            this.setXPos(this.getXPos() + (moveX));
            this.setYPos(this.getYPos() + (moveY));
        }
        this.setHealth(this.getHealth() - 1);
        this.setLevel(this.getLevel() + 1);
    }

    /**
    * Overrides the abstract canHarmPokemon method and changes the chance that it
    * will do damage to other pokemon by different values depending on the type
    * of the pokemon that was passed into the method. It has a 20% chance of
    * harming a FireType, 40% chance of harming an electric type, and 10% chance
    * of harming a water type, and 50% chance of harming other types.
    * @param otherPokemon another pokemon object
    */
    @Override
    public boolean canHarmPokemon(Pokemon otherPokemon) {
        Random chance = new Random();
        boolean doDamage = false;
        if (collidesWithPokemon(otherPokemon)) {
            if (otherPokemon instanceof FireType
                && 80 < chance.nextInt(100) + 1) {
                doDamage = true;
            } else if (otherPokemon instanceof ElectricType
                && 60 < chance.nextInt(100) + 1) {
                doDamage = true;
            } else if (otherPokemon instanceof WaterType
                && 10 < chance.nextInt(100) + 1) {
                doDamage = true;
            } else if (50 < chance.nextInt(100) + 1) {
                doDamage = true;
            }
        }
        return doDamage;
    }
}
