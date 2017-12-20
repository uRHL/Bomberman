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
     * Possible images for a bomb
     */
    private static final String[] images = new String[] { "bomb1.gif", "bomb2.gif" };
    /**
     * Current image representing a bomb, initialized with the first possible image.
     */
    public static String image = images[0];

    /**
     * Range of explosion of a bomb, initialized at one. Can be incremented with
     * bonuses up to 5.
     */
    private static int range = 1;
    /**
     * Maximum range of a bomb: 5.
     */
    private static final int MAX_RANGE = 5;

    /**
     * Time when the bomb were placed
     */
    private long initialTime;

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
     * Time, expressed in milliseconds, that takes the bomb to explode.
     */
    public final double COUNT_DOWN = 3000;

    /**
     * By default constructor
     */
    public Bomb() {
        placed = false;
    }

    /**
     * Full constructor. Initializes the corresponding fields with the parameters,
     * and set 'placed' to true (because the use of the full constructor implies
     * that the bomb have been placed).
     * 
     * @param l
     *            Level were the bomb exists
     * @param timer
     *            Time when the bomb had been placed
     * @param xPos
     *            X-index were the bomb is placed
     * @param yPos
     *            Y-index were the bomb is placed
     */
    public Bomb(Level l, long timer, int xPos, int yPos) {
        ownLevel = l;
        this.initialTime = timer;
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
    public long getInitialTime() {
        return initialTime;
    }

    /**
     * 
     * @return The range
     */
    public static int getRange() {
        return range;
    }

    /**
     * Increments by one the value of the {@link Bomb#range range}, up to the
     * {@link Bomb#MAX_RANGE maximum}.
     * 
     * @return True if the value was changed successfully. False if not.
     */
    public static boolean incrementRange() {
        boolean changed = false;
        if (range < MAX_RANGE) {
            range++;
            changed = true;
            Main.visualBoard.gb_setValueAbility1(range);
        }
        return changed;
    }

    /**
     * Sets the range to its {@link Bomb#MAX_RANGE maximum}.
     */
    public static void fullRange() {
        range = MAX_RANGE;
        Main.visualBoard.gb_setValueAbility1(range);
    }

    /**
     * Swaps automatically the image of the bomb, animating it.
     */
    public static void animateImage() {
        if (image.equals(images[0])) {
            image = images[1];
        } else {
            image = images[0];
        }
    }

    /**
     * Checks if the countdown of the bomb has finished. If it have finished calls
     * the method detonate to explode the bomb
     * 
     * @param timer
     *            value of the timer in the moment of calling
     * @param ableRemoteControl
     *            Refers to the field of the player {@link Player#remoteControl
     *            remoteControl}
     * @return True if had passed enough time to detonate. False otherwise
     */
    public void detonate(long timer, boolean ableRemoteControl) {
        if (ableRemoteControl || timer > this.initialTime + COUNT_DOWN) {
            if (this.isPlaced()) {
                explode();
                this.resetBomb();
            }
        }
    }

    /**
     * <p>
     * Checks if the blocks in the range (of explosion) of the bomb can be destroyed
     * and destroy them, if it is possible. Checks the four directions (up, down,
     * left, right), one by one.
     * </p>
     * <p>
     * When the block is destroyed, a NormalBlock, that contains the bonus that the
     * BrickBlock had, is created.
     * </p>
     */
    private void explode() {
        // Checking the blocks to the right
        if (ownLevel.board[xPos + 1][yPos].isBreakable()) {
            for (int i = xPos; i <= xPos + range && i < Constants.BOARD_SIZE; i++) {
                if (ownLevel.board[i][yPos].isBreakable()) {
                    ownLevel.board[i][yPos] = new NormalBlock(ownLevel.board[i][yPos].getBonus());
                    damage(i, yPos);

                }
            }
        }
        // Checking the blocks to the left
        if (ownLevel.board[xPos - 1][yPos].isBreakable()) {
            for (int i = xPos; i >= xPos - range && i > 0; i--) {
                if (ownLevel.board[i][yPos].isBreakable()) {
                    ownLevel.board[i][yPos] = new NormalBlock(ownLevel.board[i][yPos].getBonus());
                    damage(i, yPos);
                }
            }
        }
        // Checking the blocks upwards
        if (ownLevel.board[xPos][yPos + 1].isBreakable()) {
            for (int i = yPos; i <= yPos + range && i > 0; i++) {
                if (ownLevel.board[xPos][i].isBreakable()) {
                    ownLevel.board[xPos][i] = new NormalBlock(ownLevel.board[xPos][i].getBonus());
                    damage(xPos, i);
                }
            }
        }
        // Checking the blocks downwards
        if (ownLevel.board[xPos][yPos - 1].isBreakable()) {
            for (int i = yPos; i >= yPos - range && i < Constants.BOARD_SIZE; i--) {
                if (ownLevel.board[xPos][i].isBreakable()) {
                    ownLevel.board[xPos][i] = new NormalBlock(ownLevel.board[xPos][i].getBonus());
                    damage(xPos, i);
                }
            }
        }
    }

    private void damage(int xPos, int yPos) {
        for (int i = 0; i < Main.maxID; i++) {
            if (ownLevel.getSpriteByID(i).xPos == xPos && ownLevel.getSpriteByID(i).yPos == yPos) {
                ownLevel.getSpriteByID(i).setHp(0);
                if (i == 0) { // The player had been harmed
                    Main.visualBoard.gb_println("You have killed yourself!!");
                } else {
                    ((Player) (ownLevel.getSpriteByID(Main.PLAYER_ID)))
                            .addScore(((Enemy) (ownLevel.getSpriteByID(i))).getPoints());
                    Main.visualBoard.gb_println("You have killed the enemy " + i + ". You won "
                            + ((Enemy) (ownLevel.getSpriteByID(i))).getPoints() + " points");
                }
            }
        }
    }

    /**
     * Resets the bomb, setting the values of the fields to 0 or false.
     */
    public void resetBomb() {
        this.initialTime = 0;
        this.placed = false;
        this.xPos = 0;
        this.yPos = 0;
    }

}
