package bonuses;

import sprites.Bomb;
import sprites.Player;
import structures.Main;

/**
 * Increases the {@link sprites.Bomb#range explosion range} by one unit. There
 * is one per level.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December 2017
 * @version 1.1
 */
public class FireBonus extends Bonus {
    /**
     * Image representing the bonus
     */
    public static final String image = "Fireupsprite.png";

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
    public FireBonus() {

    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Consumes a {@link FireBonus}, incrementing by one the
     * {@link sprites.Bomb#range range} of the bombs.
     * 
     * @see {@link sprites.Bomb#incrementRange()}
     * 
     * @param owner
     *            Player who had taken the bonus
     */
    @Override
    public void consumeBonus(Player owner) {
        if (Bomb.incrementRange()) {
            Main.visualBoard.gb_println("Your bombs are now more powerful!");
        }else {
            Main.visualBoard.gb_println("Your bombs are the most powerful!");
        }
    }
}
