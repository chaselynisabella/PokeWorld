import java.util.Random;
import java.awt.Rectangle;

/**
 * A fire type pokemon
 *
 * @author Farhan Tejani, cbaca3
 * @version 1.0
 */
public abstract class FireType extends Pokemon {

    /**
     * Constructor
     * @param x The X position of this Fire type
     * @param y The Y position of this Fire type
     * @param bounds The bounding Rectangle
     */
    public FireType(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    /**
    * Overrides the abstract move method and moves the fire type in a random
    * fashion. If it is in it's designated quadrant it moves more quickly.
    * When not in the quadrant, the health decreases and level increases
    * normally.
    */
    @Override
    public void move() {
        Random xPosition = new Random();
        Random yPosition = new Random();
        int moveX = xPosition.nextInt(20) - 10;
        int moveY = yPosition.nextInt(20) - 10;
        inBounds();
        if (this.getXPos() >= (int) (getBounds().getWidth() / 2) - 90
            && this.getYPos() <= (int) (getBounds().getHeight() / 2) - 90) {
            moveX = xPosition.nextInt(30) - 15;
            moveY = yPosition.nextInt(30) - 15;
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
    * of the pokemon that was passed into the method. It has a 90% chance of
    * harming a GrassType, 30% chance of harming a water type, and 50% chance
    * of harming other types.
    * @param otherPokemon another pokemon object
    */
    @Override
    public boolean canHarmPokemon(Pokemon otherPokemon) {
        Random chance = new Random();
        boolean doDamage = false;
        if (collidesWithPokemon(otherPokemon)) {
            if (otherPokemon instanceof GrassType
                && 10 < chance.nextInt(100) + 1) {
                doDamage = true;
            } else if (otherPokemon instanceof WaterType
                && 70 < chance.nextInt(100) + 1) {
                doDamage = true;
            } else if (!(otherPokemon instanceof ElectricType
                || (!(otherPokemon instanceof WaterType)))) {
                if (50 < chance.nextInt(100) + 1) {
                    doDamage = true;
                }
            }
        }
        return doDamage;
    }
}
