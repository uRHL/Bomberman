package sprites;

import structures.Level;

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
    Level ownLevel;

    protected boolean alive;
    
    protected byte health;

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
    
    public byte getHealth() {
        return health;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
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

}
