package blocks;

import bonuses.Bonus;
import structures.Main;

/**
 * Block class represents an abstract block. Blocks are represented by an
 * 'image', and some boolean variables defining different characteristics, such
 * as if it is walkable for the player. The class contains getters and setters
 * to deal with the values of the fields.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December, 6, 2017
 * @version 1.1
 */
public class Block {
    /**
     * Name of the image that represents the type of block
     */
    String image;
    /**
     * Storages if the player can walk through the block. True if it s possible;
     * false if not
     */
    protected boolean walkable;
    /**
     * Availability to put a bomb in the block. True if the player can place a bomb;
     * false if not
     */
    protected boolean available;
    /**
     * Storages if the is a bomb planted in the block. True if the is bomb; false if
     * not
     */
    protected boolean mined;
    /**
     * Storages if the block can be break with the explosion of a bomb. True if it
     * can be destroyed; false if not
     */
    protected boolean breakable;

    /**
     * Bonus type field that storages, if it has, the bonus contained in the block
     */
    protected Bonus bonus;

    /**
     * 
     * @return the 'breakable'
     */
    public boolean isBreakable() {
        return breakable;
    }

    /**
     * 
     * @param breakable
     *            the 'breakable' to set
     */
    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    /**
     * 
     * @return the 'mined'
     */
    public boolean isMined() {
        return mined;
    }

    /**
     * 
     * @param mined
     *            the 'mined' to set
     */
    public void setMined(boolean mined) {
        this.mined = mined;
    }

    /**
     * 
     * @return the image representing the block
     */
    public String getImage() {
        String img = "";
        if (Main.trueSight) {
            try {
                img = getBonus().getImage();
                Main.visualBoard.gb_println("bonus " + img);
            } catch (Exception e) {
                img = image;
            }
        } else {
            img = image;
        }
        return img;
    }

    /**
     * @return the walkable
     */
    public boolean isWalkable() {
        return walkable;
    }

    /**
     * @param walkable
     *            the walkable to set
     */
    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    /**
     * @return the available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * @param available
     *            the available to set
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * 
     * @return The bonus contained in the block, if it exists. Null otherwise
     */
    public Bonus getBonus() {
        return bonus;
    }

    /**
     * @param bonus
     *            the bonus to set
     */
    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }

}
