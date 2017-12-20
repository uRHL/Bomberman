
package bonuses;

import sprites.Bomb;
import sprites.Player;
import structures.Main;

/**
 * Increases the {@link sprites.Bomb#range explosion range} to its maximum (5
 * cells). There is one every 5 levels.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December 2017
 * @version 1.1
 */
public class SpecialFireBonus extends Bonus {
    /**
     * Image representing the bonus
     */
    public static final String image = "Fullfiresprite.png";

    /**
     * Probability of the bonus appearing in a level, if the level can contain a
     * bonus of this type. Its real value is 1 / probability.
     */
    public static final byte probability = 1; // 1/1 (100%) probability
    /**
     * Frequency of appearance of the bonus through the levels. For example, a type
     * of bonus can appear only once every 5 levels. The frequency is a whole number
     * that indicates the minimum number of levels that must passed for the bonus in
     * order to appear, in this case 5.
     */
    public static final byte frequency = 5; // One every 5 levels

    /**
     * By default constructor
     */
    public SpecialFireBonus() {

    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Consumes a {@link SpecialFireBonus}, setting the range of the {@link Bomb} to
     * its {@link sprites.Bomb#MAX_RANGE maximum}
     * 
     * @see Bomb#fullRange()
     * @param owner
     *            Player who had taken the bonus
     * @return True, because SpecialFireBonuses are always consumed when the player
     *         gets them
     */
    @Override
    public boolean consumeBonus(Player owner) {
        Bomb.fullRange();
        Main.visualBoard.gb_println("Your bombs are the most powerful!");

        return true;
    }
}
