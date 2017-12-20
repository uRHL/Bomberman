package bonuses;

import sprites.Player;

/**
 * Bonus class represent a bonus that might be inside a BrickBlock and will
 * appear once that block had been destroyed, with a bomb.
 * 
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December, 6, 2017
 * @version 1.1
 */
public class Bonus {
    /**
     * Boolean datum storing if the bonus has been already associated with a brick
     * (true) or not (false).
     */
    protected boolean placed = false;

    /**
     * Returns the corresponding image of the bonus. If a simple 'Bonus' calls this
     * method, since 'Bonus' has no image, null will be returned.
     * 
     * @return null
     */

    public String getImage() {
        return null;
    }

    /**
     * 
     * @return the placed
     */
    public boolean isPlaced() {
        return placed;
    }

    /**
     * @param placed
     *            the used to set
     */
    public void setPlaced(boolean placed) {
        this.placed = placed;
    }   

    /**
     * By default constructor
     */
    public Bonus() {

    }

    /**
     * Gets the simple name of the calling class.
     * 
     * @return The name of class
     */
    public String toString() {
        return this.getClass().getSimpleName();
    }

    /**
     * Gets and apply the effect of the bonus to the Player that had taken it.
     * 
     * @param Player
     *            who had taken the bonus.
     */
    public void consumeBonus(Player owner) {        
    }

}
