package sprites;

import structures.Level;
import structures.Main;

/**
 * Abstract class that represents any type of sprite. Each one, will have and X
 * and Y position, a fixed image and an unique ID number identifying it.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December, 6, 2017
 * @version 1.1
 */
public abstract class Sprite {
    /**
     * Position in the X-axis of the board
     */
    protected int xPos;
    /**
     * Position in the Y-axis of the board
     */
    protected int yPos;
    /**
     * Coordinates in the X-axis of the visual board
     */
    protected double xCoord;
    /**
     * Coordinates in the Y-axis of the visual board
     */
    protected double yCoord;
    /**
     * Movement speed of the sprite, expressed in units per step.
     */
    protected float speed;
    /**
     * Maximum speed reachable
     */
    private float min_speed;
    /**
     * Minimum speed reachable
     */
    private float max_speed;

    /**
     * Image representing the sprite. Can be a collection of images in order to make
     * animations.
     */
    protected String image;
    /**
     * Unique number identifying the sprite.
     */
    protected int ID;
    /**
     * Level where this sprite exists
     */
    protected Level ownLevel;
    /**
     * Storages if the sprite is alive ({@link Sprite#hp hp}>0), or not
     * ({@link Sprite#hp hp}<=0).
     */
    protected boolean alive;
    /**
     * Integer number indicating the health points of a sprite. If it reaches 0, the
     * sprite is dead.
     */
    protected byte hp;

    /**
     * Constructor. Initializes the ID of the sprite to the number given as a
     * parameter. The rest of the fields are initialized in the constructor of the
     * respective child classes.
     * 
     * @param id
     *            Unique number identifying the sprite.
     */
    public Sprite(int id) {
        this.ID = id;
    }

    /**
     * 
     * @return The X-axis position on the board.
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * 
     * @return The Y-axis position on the board
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * 
     * @return The X-axis coordinates on the visual board
     */
    public int getxCoord() {
        return (int)(xCoord*10);
    }

    /**
     * 
     * @return The Y-axis coordinates on the visual board
     */
    public int getyCoord() {
        return (int)(yCoord*10);
    }

    /**
     * 
     * @return the image representing the sprite
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @return The ID number of the sprite.
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the ownLevel
     */
    public Level getOwnLevel() {
        return ownLevel;
    }

    public byte getHealth() {
        return hp;
    }

    /**
     * Sets the hp. Then, sets the {@link Sprite#alive alive}
     * 
     * @param healthPoints
     *            hp to set
     */
    protected void setHp(int healthPoints) {
        hp = (byte) healthPoints;
        if (hp > 0) {
            alive = true;
        } else {
            alive = false;
        }
    }

    /**
     * 
     * @return The alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @param speed
     *            the speed to set
     * @return True if the speed was changed successfully. False if not.
     */
    public boolean setSpeed(float speed) {
        boolean changed = false;
        if (speed >= this.min_speed && speed <= this.max_speed) {
            this.speed = speed;
            changed = true;
            Main.visualBoard.gb_setValueAbility2((int) (this.speed * 10));
        }
        return changed;
    }

    /**
     * @return the mIN_SPEED
     */
    public float getMin_speed() {
        return min_speed;
    }

    /**
     * @param mIN_SPEED
     *            the mIN_SPEED to set
     */
    protected void setMin_speed(float mIN_SPEED) {
        min_speed = mIN_SPEED;
    }

    /**
     * @return the mAX_SPEED
     */
    public float getMax_speed() {
        return max_speed;
    }

    /**
     * @param mAX_SPEED
     *            the mAX_SPEED to set
     */
    protected void setMax_speed(float mAX_SPEED) {
        max_speed = mAX_SPEED;
    }

    /**
     * Abstract method to move an sprite.
     */
    public abstract void move();

    /**
     * Abstract method to move an sprite with respect to the last key pressed.
     * 
     * @param lastAction
     *            Value of the last key pressed.
     */
    public abstract void move(String lastAction);

    public abstract void killed();
}
