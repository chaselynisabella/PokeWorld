import java.util.Random;
import java.awt.Rectangle;

/**
 * A water type pokemon
 *
 * @author Farhan Tejani, cbaca3
 * @version 1.0
 */
public abstract class WaterType extends Pokemon {

    /**
     * Constructor
     * @param x The X position of this Water Type Pokemon
     * @param y The Y position of this Water Type Pokemon
     * @param bounds The bounding Rectangle
     */
    public WaterType(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    @Override
    public void move() {
        Random xPosition = new Random();
        Random yPosition = new Random();
        int moveX = xPosition.nextInt(30) - 15;
        int moveY = yPosition.nextInt(30) - 15;
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

    @Override
    public boolean canHarmPokemon(Pokemon otherPokemon) {
        Random chance = new Random();
        boolean doDamage = false;
        if (collidesWithPokemon(otherPokemon)) {
            if (otherPokemon instanceof GrassType
                && 70 < chance.nextInt(100) + 1) {
                doDamage = true;
            } else if (otherPokemon instanceof FireType
                && 10 < chance.nextInt(100) + 1) {
                doDamage = true;
            } else if (otherPokemon instanceof WaterType
                && 50 < chance.nextInt(100) + 1) {
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
