package sprites;

/**
 * Abstract class that represent a simple enemy. It extends the class Sprite,
 * and its child class are Balloon and Drop.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December, 6, 2017
 * @version 1.1
 */
public abstract class Enemy extends Sprite {
    /**
     * Number of health points (HP) the Player will loose when an enemy hits him.
     */
    public static final byte attackDamage = 20;

    /**
     * Points that will be added to the {@link Player#score Player' score} when this
     * enemy dies.
     */
    protected int points;

    /**
     * Initializes the field id, calling the super constructor.
     * 
     * @param id
     *            Unique ID identifying an Enemy (Sprite)
     */
    public Enemy(int id) {
        super(id);
    }

    /**
     * 
     * @return The points
     */
    public int getPoints() {
        return points;
    }

}
