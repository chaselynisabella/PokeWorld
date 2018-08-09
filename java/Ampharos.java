import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.Random;

/**
* Ampharos
* @author cbaca3
* @version 1
*/
public class Ampharos extends ElectricType {
    private int numKids = 0;

    /**
     * Constructor
     * @param x The X position of Poliwhirl
     * @param y The Y position of Poliwhirl
     * @param bounds The bounding Rectangle
     */
    public Ampharos(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage(new ImageIcon("../resources/ampharos.png"));
        setMaxLevel(175);
    }

    @Override
    public boolean canReproduceWithPokemon(Pokemon otherPokemon) {
        Random chance = new Random();
        if (otherPokemon instanceof Ampharos && 90 < chance.nextInt(100) + 1) {
            return true;
        }
        return false;
    }


    @Override
    public Ampharos reproduceWithPokemon(Pokemon otherPokemon) {
        if (canReproduceWithPokemon(otherPokemon) && numKids < 3) {
            numKids++;
            Ampharos baby = new Ampharos(getXPos(), getYPos(), getBounds());
            return baby;
        } else {
            return null;
        }
    }

    @Override
    public boolean canHarmPokemon(Pokemon otherPokemon) {
        Random chance = new Random();
        boolean doDamage = false;
        if (otherPokemon instanceof Ampharos && 95 < chance.nextInt(100) + 1) {
            doDamage = true;
        } else if (!(otherPokemon instanceof Ampharos)
            && 60 < chance.nextInt(100) + 1) {
            doDamage = true;
        }
        return doDamage;
    }

    @Override
    public void harmPokemon(Pokemon otherPokemon) {
        if (otherPokemon instanceof ElectricType && this.getLevel()
            > otherPokemon.getLevel()) {
            otherPokemon.setHealth(otherPokemon.getHealth() - 1);
        } else {
            otherPokemon.setHealth(otherPokemon.getHealth() - 10);
        }
    }

    @Override
    public boolean isOld() {
        return getLevel() > getMaxLevel();
    }
}
