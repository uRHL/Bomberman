package blocks;

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
public abstract class Block {
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
        return image;
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

}
