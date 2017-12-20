package bonuses;

import blocks.BrickBlock;
import sprites.Enemy;
import sprites.Player;
import structures.Main;

/**
 * Increases by one the number of simultaneous bombs. Several of them can be
 * found at every level.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December, 6, 2017
 * @version 1.1
 */

public class Door extends Bonus {

    /**
     * Image representing the bonus
     */
    public static final String image = "DoorClosed.png";

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
    public Door() {

    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Tries to go through the level's {@link Door}. In order to do so, all the
     * {@link Enemy enemies} must be dead, and the {@link BrickBlock} containing the
     * Door destroyed.
     * 
     * 
     * @param owner
     *            Player who had taken the bonus
     * @return True if the Player was able to go through the door. False if not
     */
    @Override
    public boolean consumeBonus(Player owner) {
        if (owner.getOwnLevel().allEnemiesDead()) {
            Main.nextLevel();
            return true;
        } else {
            Main.visualBoard.gb_println("All the enemies must die first!");
            return false;
        }
    }
}
