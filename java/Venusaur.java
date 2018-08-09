import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.Random;

/**
* Venusaur
* @author Heather, cbaca3
* @version 1.0
*/
public class Venusaur extends GrassType {
    private double babyChance = .10;
    private int numKids = 0;

    /**
     * Constructor
     * @param x The X position of Venusaur
     * @param y The Y position of Venusaur
     * @param bounds The bounding Rectangle
     */
    public Venusaur(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage(new ImageIcon("../resources/venusaur.png"));
        setMaxLevel(140);
    }

    @Override
    public boolean canReproduceWithPokemon(Pokemon otherPokemon) {
        if (otherPokemon instanceof Venusaur) {
            return Math.random() <= .10;
        } else {
            return false;
        }
    }

    @Override
    public Venusaur reproduceWithPokemon(Pokemon otherPokemon) {
        if (canReproduceWithPokemon(otherPokemon) && numKids < 3) {
            numKids++;
            Venusaur baby = new Venusaur(getXPos(), getYPos(), getBounds());
            return baby;
        } else {
            return null;
        }
    }

    @Override
    public boolean canHarmPokemon(Pokemon otherPokemon) {
        Random chance = new Random();
        boolean doDamage = false;
        if (otherPokemon instanceof FireType
            &&  70 < chance.nextInt(100) + 1)  {
            doDamage = true;
        } else if (otherPokemon instanceof Poliwhirl
            && 88 < chance.nextInt(100) + 1) {
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
