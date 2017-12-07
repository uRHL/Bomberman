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

    // The xPos, yPos, and image are fields of the superclass Sprite, declared there
    /**
     * Boolean variable controlling if the enemy if alive (is has hp) or not (0 hp).
     */
    boolean alive;

    /**
     * Initializes the field id, calling the super constructor.
     * 
     * @param id
     *            Unique ID identifying an Enemy (Sprite)
     */
    public Enemy(int id) {
        super(id);
    }

}
