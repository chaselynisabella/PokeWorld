import java.util.Random;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.Graphics;

/**
 * The abstract Pokemon for the PokeBattle Simulation
 *
 * @author Heather, Aniruddham, cbaca3
 * @version 1.0
 */

public abstract class Pokemon {

    private Rectangle bounds;
    private int xPos;
    private int yPos;
    private ImageIcon image;
    private int level = 1;
    private int health = 150;
    private int maxLevel;


    /**
     * Constructor
     *
     * Represents a Pokemon in the PokeWorld. Each Pokemon
     * has a location in the world and attributes which help
     * it reproduce and thrive.
     * @param xPos The X position of this Pokemon
     * @param yPos The Y position of this Pokemon
     * @param bounds The boundaries of the PokeWorld where
     *               the Pokemon can exist
     */
    public Pokemon(int xPos, int yPos, Rectangle bounds) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.bounds = bounds;
    }

    /**
     * @return the X position of this Pokemon
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * @return the Y position of this Pokemon
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * @return the bounding rectangle of the PokeWorld
     *             that this Pokemon exists in
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * @return the health of this Pokemon
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return the level of this Pokemon
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the maximum level of this Pokemon
     */
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     * Sets the maximum value of the Pokemon
     * @param maxLevel Pokemon maximum level
     */
    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    /**
     * Sets the xposition value of the Pokemon
     * @param xpos xposition of the Pokemon
     */
    public void setXPos(int xpos) {
        this.xPos = xpos;
    }

    /**
     * Sets the yposition value of the Pokemon
     * @param ypos xposition of the Pokemon
     */
    public void setYPos(int ypos) {
        this.yPos = ypos;
    }

    /**
     * Sets the health value of the Pokemon
     * @param health Pokemon health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Sets the level value of the Pokemon
     * @param level Pokemon level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
    * Sets the image attribute for this pokemon
    * @param image the ImageIcon to use to represent this Pokemon
    */
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    /**
     * Should draw the Pokemon at its location.
     * @param g object for drawing use
     */
    public void draw(Graphics g) {
        image.paintIcon(null, g, xPos, yPos);
    }

    /**
     * Creates an abstract method to move the pokemon
     */
    public abstract void move();

    /**
     * Checks to see if the Pokemon are on the same location or touching on the
     * board.
     * @param otherPokemon a Pokemon object
     * @return true if the Pokemon are at the same location or touch
     */
    public boolean collidesWithPokemon(Pokemon otherPokemon) {
        if (Math.abs(this.getXPos() - otherPokemon.getXPos()) <= 90
            && Math.abs(this.getYPos() - otherPokemon.getYPos()) <= 90) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the Pokemon are of the same species and if they are
     * it returns a default 15% chance that they'll reproduce
     * @param otherPokemon a Pokemon object
     * @return false if the pokemon are not off the same class
     */
    public boolean canReproduceWithPokemon(Pokemon otherPokemon) {
        Random chance = new Random();
        if (this.getClass() != otherPokemon.getClass()) {
            return false;
        }
        return 85 < chance.nextInt(100) + 1;
    }

    /**
     * Creates an abstract method reproduce and make a child with another
     * Pokemon
     * @param otherPokemon a Pokemon object
     * @return returns new baby Pokemon object of the same Pokemon type
     */
    public abstract Pokemon reproduceWithPokemon(Pokemon otherPokemon);

    /**
     * Creates an abstract method to check if the Pokemon's level is over the
     * max level for that species or type
     * @return returns true if Pokemon is above max level
     */
    public abstract boolean isOld();

    /**
     * Creates an abstract method to check if the pokemon can harm the other
     * Pokemon. It also assigns a probability based on the species of the other
     * pokemon if the two collide.
     * @param otherPokemon a Pokemon object
     * @return true if the Pokemon is able to harm the other
     */
    public abstract boolean canHarmPokemon(Pokemon otherPokemon);

    /**
     * Creates an abstract method to harm the other pokemon by a probability
     * that is determined by the type of the otherPokemon
     * @param otherPokemon a Pokemon object
     */
    public abstract void harmPokemon(Pokemon otherPokemon);

    /**
     * The Pokemon faints if it's old and sets health = 0
     */
    public void faint() {
        if (this.isOld()) {
            this.health = 0;
        }
    }

    /**
     * Creates a method to check if the Pokemon has fainted
     * @return true if the Pokemon's health is at or below 0 or if it has
     * exceeded its max level
     */
    public boolean hasFainted() {
        return this.getHealth() <= 0 || this.getLevel() > this.getMaxLevel();
    }

    /**
     * Checks to see if the Pokemon is within the bounds of the map and then
     * the method is used in the move method. If it outside the bounds, it
     * bounces back.
     */
    public void inBounds() {
        if (this.getXPos() < 0) {
            setXPos(0);
        }
        if (this.getXPos() + 90 > getBounds().getWidth()) {
            setXPos((int) (getBounds().getWidth()) - 90);
        }
        if (this.getYPos() < 0) {
            setYPos(0);
        }
        if (this.getYPos() + 90 > getBounds().getHeight()) {
            setYPos((int) (getBounds().getHeight()) - 90);
        }
    }
}
