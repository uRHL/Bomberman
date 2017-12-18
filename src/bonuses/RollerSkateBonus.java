package bonuses;

/**
 * Increases the player speed by 1/10 of cell, up to a maximum of 10/10. It can
 * randomly appear (50% chance) in even levels.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December2017
 * @version 1.1
 */
public class RollerSkateBonus extends Bonus {
    /**
     * Image representing the bonus
     */
    public static final String image = "Skatesprite.png";
    /**
     * Probability of the bonus appearing in a level, if the level can contain a
     * bonus of this type. Its real value is 1 / probability.
     */
    public static final byte probability = 2; // 1/2 (50%) probability
    /**
     * Frequency of appearance of the bonus through the levels. For example, a type
     * of bonus can appear only once every 5 levels. The frequency is a whole number
     * that indicates the minimum number of levels that must passed for the bonus in
     * order to appear, in this case the bonus appears in even levels.
     */
    public static final byte frequency = 2; // Only appears in even levels

    /**
     * By default constructor
     */
    public RollerSkateBonus() {

    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }
}
