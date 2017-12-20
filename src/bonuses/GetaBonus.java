package bonuses;

import sprites.Player;
import structures.Main;

/**
 * Reduces the player speed to its minimum (1/10 of cell). It can randomly
 * appear (20% chance) on each level.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December2017
 * @version 1.1
 */
public class GetaBonus extends Bonus {
    /**
     * Image representing the bonus
     */
    public static final String image = "Getasprite.png";
    /**
     * Probability of the bonus appearing in a level, if the level can contain a
     * bonus of this type. Its real value is 1 / probability.
     */
    public static final byte probability = 5; // 1/5 (20%) probability

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
    public GetaBonus() {

    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Consumes a {@link GetaBonus}, setting the {@link sprites.Sprite#speed speed}
     * of the player to its minimum.
     * 
     * @param owner
     *            Player who had taken the bonus
     * @return True, because GetaBonuses are always consumed when the player gets
     *         them
     */
    @Override
    public boolean consumeBonus(Player owner) {
        owner.setSpeed(owner.getMin_speed());
        Main.visualBoard.gb_println("Your speed has been reduced to its minimum!");

        return true;

    }
}
