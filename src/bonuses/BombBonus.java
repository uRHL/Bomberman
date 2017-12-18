package bonuses;

/**
 * Increases by one the number of simultaneous bombs. Several of them can be
 * found at every level.
 * 
 * @author Ram�n Hern�ndez Le�n. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Mi�ano. Bachelor Degree in Computer Science. UC3M
 * @since December, 6, 2017
 * @version 1.1
 */
public class BombBonus extends Bonus {
    /**
     * Image representing the bonus
     */
    public static final String image = "bomb1.gif";

    /**
     * Probability of the bonus appearing in a level, if the level can contain a
     * bonus of this type. Its real value is 1 / probability.
     */
    public static final byte probability = 1; // 1/1 (100%) probability

    /**
     * Frequency of appearance of the bonus through the levels. For example, a type
     * of bonus can appear only once every 5 levels. The frequency is a whole number
     * that indicates the minimum number of levels that must passed for the bonus in
     * order to appear, in this case 1.
     */
    public static final byte frequency = 1;

    /**
     * By default constructor
     */
    public BombBonus() {

    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }
}
