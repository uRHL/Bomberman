package bonuses;

import sprites.Player;
import structures.Main;

/**
 * Increases the player speed by a fixed value:
 * {@link RollerSkateBonus#INCREMENT INCREMENT}. It can randomly appear (50%
 * chance) in even levels.
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
     * Increment of the speed when a Player finds a RollerSkateBonus. Value = 0.1
     */
    private final float INCREMENT = 0.1F;

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

    /**
     * Consumes a {@link RollerSkateBonus}, {@link RollerSkateBonus#INCREMENT
     * incrementing} the speed of the {@link Player}
     * 
     * @see {@link sprites.Sprite#setSpeed(float)}
     * @param owner
     *            Player who had taken the bonus
     */
    @Override
    public void consumeBonus(Player owner) {        
        if (owner.setSpeed(owner.getSpeed() + INCREMENT)) {
            Main.visualBoard.gb_println("You move faster now!");
        } else {
            Main.visualBoard.gb_println("You cannot move faster!");
        }
    }
}
