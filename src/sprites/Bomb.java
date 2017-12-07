package sprites;

import blocks.NormalBlock;
import structures.*;

/**
 * The class Bomb represents a simple bomb. It has a countdown to explode, and
 * range of explosion. Bombs can destroy the BrickBlocks that are inside the
 * range of explosion.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December, 6, 2017
 * @version 1.1
 */
public class Bomb {
    /**
     * Image of a bomb. It will be the same for all of them.
     */
    public static final String image = "bomb1.gif";

    /**
     * Range of explosion of a bomb, initialized at one. Can be incremented with
     * bonuses
     */
    private static int range = 1;

    /**
     * Time when the bomb were placed
     */
    private int initialTime;

    /**
     * X-index of the board of the level were the bomb were placed.
     */
    private int xPos;

    /**
     * Y-index of the board of the level were the bomb were placed.
     */
    private int yPos;

    /**
     * Boolean value that storages if the bomb had been planted (true) or not
     * (false).
     */
    private boolean placed;

    /**
     * Reference to the level were the bomb exists. It is already defined for the
     * Player (who has the bombs), but the reference makes easier to deal with the
     * interactions between the board (of the Level) and the bomb.
     */
    private Level ownLevel;

    /**
     * By default constructor
     */
    public Bomb() {

    }

    /**
     * Full constructor. Initializes the corresponding fields with the parameters,
     * and set 'placed' to true (because the use of the full constructor implies
     * that the bomb have been placed).
     * 
     * @param l
     *            Level were the bomb exists
     * @param initialTime
     *            Time when the bomb had been placed
     * @param xPos
     *            X-index were the bomb is placed
     * @param yPos
     *            Y-index were the bomb is placed
     */
    public Bomb(Level l, int initialTime, int xPos, int yPos) {
        ownLevel = l;
        this.initialTime = initialTime;
        this.xPos = xPos;
        this.yPos = yPos;
        placed = true;
    }

    /**
     * 
     * @return The placed of the bomb
     */
    public boolean isPlaced() {
        return placed;
    }

    /**
     * 
     * @return The xPos of the bomb
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * 
     * @return The yPos of the bomb
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * 
     * @return The initialTime of the bomb
     */
    public int getInitialTime() {
        return initialTime;
    }

    /**
     * Checks if the countdown of the bomb has finished. If it have finished calls
     * the method detonate to explode the bomb
     * 
     * @param currentTime
     *            value of the timer in the moment of calling
     * @return True if had passed enough time to detonate. False otherwise
     */
    public void detonate(int currentTime) {
        final int COUNT_DOWN = 60;

        if (currentTime > this.initialTime + COUNT_DOWN) {
            if (this.isPlaced()) {
                explode();
                this.resetBomb();
            }
        }

    }

    /**
     * Checks if the blocks in the range (of explosion) of the bomb can be destroyed
     * and destroy them, if it is possible. Checks the four directions (up, down,
     * left, right), one by one.
     */
    private void explode() {
        // Checking the blocks to the right
        if (ownLevel.board[xPos + 1][yPos].isBreakable()) {
            for (int i = xPos; i <= xPos + range; i++) {
                if (ownLevel.board[i][yPos].isBreakable()) {
                    ownLevel.board[i][yPos] = new NormalBlock();
                }
            }
        }
        // Checking the blocks to the left
        if (ownLevel.board[xPos - 1][yPos].isBreakable()) {
            for (int i = xPos; i >= xPos - range; i--) {
                if (ownLevel.board[i][yPos].isBreakable()) {
                    ownLevel.board[i][yPos] = new NormalBlock();
                }
            }
        }
        // Checking the blocks upwards
        if (ownLevel.board[xPos][yPos + 1].isBreakable()) {
            for (int i = yPos; i <= yPos + range; i++) {
                if (ownLevel.board[xPos][i].isBreakable()) {
                    ownLevel.board[xPos][i] = new NormalBlock();
                }
            }
        }
        // Checking the blocks downwards
        if (ownLevel.board[xPos][yPos - 1].isBreakable()) {
            for (int i = yPos; i >= yPos - range; i--) {
                if (ownLevel.board[xPos][i].isBreakable()) {
                    ownLevel.board[xPos][i] = new NormalBlock();
                }
            }
        }
    }

    /**
     * Set the values of the fields to 0 or false.
     */
    public void resetBomb() {
        this.initialTime = 0;
        this.placed = false;
        this.xPos = 0;
        this.yPos = 0;
    }

}
