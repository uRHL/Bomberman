package bonuses;

/**
 * Allows for the simultaneous and immediate explosion of all the bombs in the
 * board using the "tab" key. There is one every 10 levels.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December 2017
 * @version 1.1
 */
public class RemoteControlBonus extends Bonus {
    /**
     * Image representing the bonus
     */
    public static final String image = "Remote_Control_2.png";

    /**
     * Probability of the bonus appearing in a level, if the level can contain a
     * bonus of this type. Its real value is 1 / probability.
     */
    public static final byte probability = 1; // 1/1 (100%) probability
    /**
     * Frequency of appearance of the bonus through the levels. For example, a type
     * of bonus can appear only once every 5 levels. The frequency is a whole number
     * that indicates the minimum number of levels that must passed for the bonus in
     * order to appear, in this case 10.
     */
    public static final byte frequency = 10; // Only one every 10 levels

    /**
     * By default constructor
     */
    public RemoteControlBonus() {

    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }
}
