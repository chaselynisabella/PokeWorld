import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.Random;

/**
* Rapidash
* @author Heather, cbaca3
* @version 1.0
*/
public class Rapidash extends FireType {
    private int numKids = 0;


    /**
     * Constructor
     * @param x The X position of Rapidash
     * @param y The Y position of Rapidash
     * @param bounds The bounding Rectangle
     */
    public Rapidash(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage(new ImageIcon("../resources/rapidash.png"));
        setMaxLevel(190);
    }

    @Override
    public boolean canReproduceWithPokemon(Pokemon otherPokemon) {
        Random chance = new Random();
        if (otherPokemon instanceof Rapidash && 85 < chance.nextInt(100) + 1) {
            return true;
        }
        return false;
    }

    @Override
    public Rapidash reproduceWithPokemon(Pokemon otherPokemon) {
        Rapidash baby = null;
        if (this.canReproduceWithPokemon(otherPokemon)
            && this.collidesWithPokemon(otherPokemon)) {
            if (numKids <= 10) {
                baby = new Rapidash(getXPos(), getYPos(), getBounds());
                baby.move();
                numKids++;
                return baby;
            }
        }
        return baby;
    }

    @Override
    public boolean canHarmPokemon(Pokemon otherPokemon) {
        Random chance = new Random();
        boolean doDamage = false;
        if (otherPokemon instanceof FireType && (this.getLevel()
            > otherPokemon.getLevel()) && 30 < chance.nextInt(100) + 1)  {
            doDamage = true;
        } else if (88 < chance.nextInt(100) + 1) {
            doDamage = true;
        }
        return doDamage;
    }

    @Override
    public void harmPokemon(Pokemon otherPokemon) {
        if (this.canHarmPokemon(otherPokemon)) {
            otherPokemon.setHealth(otherPokemon.getHealth() - 10);
        }
    }

    @Override
    public boolean isOld() {
        return getLevel() > getMaxLevel();
    }



}
