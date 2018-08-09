import java.util.Random;
import java.awt.Rectangle;

/**
 * An electric type pokemon
 * @author cbaca3
 * @version 1
 */
public abstract class ElectricType extends Pokemon {


    /**
    * Constructor
    * @param x The X position of the Electric type
    * @param y The Y position of the Electric type
    * @param bounds The bounding rectangle
    */
    public ElectricType(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    /**
    * Overrides the abstract move method and moves the electric type in a random
    * fashion. If it is in it's designated quadrant it increases its level by
    * more. When not in the quadrant, the health decreases and level increases
    * normally.
    */
    @Override
    public void move() {
        Random xPosition = new Random();
        Random yPosition = new Random();
        int moveX = xPosition.nextInt(22) - 11;
        int moveY = yPosition.nextInt(22) - 11;
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
    * Overrides the abstract canHarmPoke method and changes the chance that it
    * will do damage to other pokemon by different values depending on the type
    * of the pokemon that was passed into the method. it has a 40% chance of
    * harming a fire type, a 40% chance of harming a water type, and 70% chance
    * of harming a grass type. If it's none of those, it has a 50% chance.
    * @param otherPokemon another pokemon object
    */
    @Override
    public boolean canHarmPokemon(Pokemon otherPokemon) {
        Random chance = new Random();
        boolean doDamage = false;
        if (collidesWithPokemon(otherPokemon)) {
            if (otherPokemon instanceof FireType
                && 60 < chance.nextInt(100) + 1) {
                doDamage = true;
            } else if (otherPokemon instanceof WaterType
                && 60 < chance.nextInt(100) + 1) {
                doDamage = true;
            } else if (otherPokemon instanceof GrassType
                && 30 < chance.nextInt(100) + 1) {
                doDamage = true;
            } else if (!(otherPokemon instanceof FireType
                || (!(otherPokemon instanceof WaterType)))) {
                if (50 < chance.nextInt(100) + 1) {
                    doDamage = true;
                }
            }
        }
        return doDamage;
    }
}