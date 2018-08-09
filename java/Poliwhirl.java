import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.Random;
/**
* Poliwhirl
* @author Heather
* @version 1
*/
public class Poliwhirl extends WaterType {
    private int numKids = 0;

    /**
     * Constructor
     * @param x The X position of Poliwhirl
     * @param y The Y position of Poliwhirl
     * @param bounds The bounding Rectangle
     */
    public Poliwhirl(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage(new ImageIcon("../resources/poliwhirl.png"));
        setMaxLevel(200);
    }

    @Override
    public boolean canReproduceWithPokemon(Pokemon otherPokemon) {
        Random chance = new Random();
        if (otherPokemon instanceof Poliwhirl && 90 < chance.nextInt(100) + 1) {
            return true;
        }
        return false;
    }

    @Override
    public Poliwhirl reproduceWithPokemon(Pokemon otherPokemon) {
        Poliwhirl baby = null;
        if (this.canReproduceWithPokemon(otherPokemon)
            && this.collidesWithPokemon(otherPokemon)) {
            if (numKids < 10) {
                baby = new Poliwhirl(getXPos(), getYPos(), getBounds());
                baby.move();
                numKids++;
                return baby;
            }
        }
        return null;
    }


    @Override
    public boolean canHarmPokemon(Pokemon otherPokemon) {
        Random chance = new Random();
        boolean doDamage = false;
        if (otherPokemon instanceof Poliwhirl && 62 < chance.nextInt(100) + 1) {
            doDamage = true;
        } else if (50 < chance.nextInt(100) + 1) {
            doDamage = true;
        }
        return doDamage;
    }

    @Override
    public void harmPokemon(Pokemon otherPokemon) {
        if (canHarmPokemon(otherPokemon)) {
            otherPokemon.setHealth(otherPokemon.getHealth() - 10);
        }
    }

    @Override
    public boolean isOld() {
        return getLevel() > getMaxLevel();
    }
}
