package bonuses;

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
    protected boolean used = false;

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
     * @return the used
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * @param used
     *            the used to set
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     * By default constructor
     */
    public Bonus() {

    }

}
