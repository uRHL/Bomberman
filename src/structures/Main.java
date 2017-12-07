package structures;

import sprites.*;
import blocks.*;
import java.util.Locale;

import edu.uc3m.game.GameBoardGUI;

/**
 * Main class to execute the program. Initializes a Level and a GameBoardGUI
 * (graphic representation of the Level). The class contains some static data
 * and functions used to deal with the relation between virtual-graphic board
 * and their functioning.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December, 6, 2017
 * @version 1.1
 */
public class Main {
    // See below for an explanation of throws InterruptedException

    /**
     * Maximum ID reached at the moment, starting from 0, which is the 'player',
     * incremented by one for each new 'Sprite'
     */
    public static int maxID = 0;

    /**
     * Graphic representation of the level. GameBoardGUI class contains its own
     * methods to print and set messages.
     */
    public static GameBoardGUI visualBoard = new GameBoardGUI(Constants.BOARD_SIZE, Constants.BOARD_SIZE);

    /**
     * Level type array containing all the levels of the whole game
     * 
     * ONLY ONE LEVEL FOR THIS VERSION
     */
    public static Level[] levels = new Level[] { new Level() };

    /**
     * Variable to storage the current Level, where the player is
     */
    public static byte currentLevel = 0;

    /**
     * Chronometer, started at 0, incremented by one in each loop of the 'while'
     * loop
     */
    public static long timer = 0;

    /**
     * Datum used to simplify the access to the sprites array of the Level. It means
     * the position in that array were array of Player is located
     */
    public static final byte PLAYER_INDEX = 0;

    /**
     * Datum used to simplify the access to the sprites array of the Level. It means
     * the position in that array were array of Balloon is located
     */
    public static final byte BALLOONS_INDEX = 1;
    /**
     * Datum used to simplify the access to the sprites array of the Level. It means
     * the position in that array were array of Drop is located
     */
    public static final byte DROPS_INDEX = 2;

    public static void main(String[] args) throws InterruptedException {
        // This changes the local settings of the computer to English, so the
        // GUI is shown in English
        Locale.setDefault(new Locale("en"));

        // To make the board visible
        visualBoard.setVisible(true);
        printBoard();

        // Changing the player name in the GUI
        visualBoard.gb_setTextPlayerName("Testing GUI");
        // Adding a sprite to the board, it is done in three steps
        // 1) adding it. Parameters are a unique id, the file containing the
        // image and true if we want it to be on top of any other at the same
        // position
        // 2) placing the sprite at a board position. Parameters are the sprite
        // id, the x and the y
        // visualBoard.gb_moveSprite(0, player.xPos, player.yPos);
        // 3) making the sprite visible. Parameters are the id and true to show
        // it or false to hide it
        // visualBoard.gb_setSpriteVisible(0, true);
        // Main game loop. This will be executing until we finish. As an example
        // we use an infinite loop

        initializeSprites(); // Adding all the sprites for first time to the visualBoard
        visualBoard.gb_setValueHealthMax(Constants.MAX_HEALTH);
        visualBoard.gb_setValueHealthCurrent(Constants.MAX_HEALTH);
        while (levels[currentLevel].sprites[0][0].isAlive()) { // The player stills alive
            timer = System.currentTimeMillis();
            visualBoard.gb_println(String.valueOf(timer));
            // The gb_getLastAction() method returns a String with the last
            // action the user performed in the GUI. Examples are "right", "up",
            // "space". See the documentation for more details.
            // trim() removes any heading or tailing space
            String lastAction = visualBoard.gb_getLastAction().trim();
            // board.setBounds(0, 0, BOARD_SIZE-1, BOARD_SIZE-1);

            // We only execute it if the user did something
            if (lastAction.length() > 0) {
                // Printing the action on the console to check it is correct
                visualBoard.gb_println(lastAction);
                // Checking its value. We are not controlling the borders.
                // Notice that in the real game the movements should be of 1/10
                // cell. There is a method to do it.

                if (lastAction.equals("space")) {
                    Player playerCopy = (Player) levels[currentLevel].sprites[0][0];
                    playerCopy.putBomb(timer);
                    // visualBoard.gb_moveSprite(0, player.xPos, player.yPos);
                    // player.putBomb(lastAction, timer);
                } else {
                    levels[currentLevel].sprites[0][0].move(lastAction);
                    moveSprite(0);// 0 is always the id of the player
                }

            }
            if (timer % 5 == 0) {
                moveBallons();
            }
            if ((timer + 2) % 5 == 0) {
                moveDrops();
            }
            enemiesAttacks();
            detonate();
            printBoard();
            /*
             * This makes the program to pause for 50 milliseconds. If not this loop will
             * run so fast that the pressed keys will be lost. Invoking the sleep() method
             * of the Thread class can produce an exception (an error). This can happen so
             * often that Java forces us to either explicitly say that we donÂ´t care or to
             * handle it. The "throws InterruptedException" in the header of the main method
             * is the way to say that we donÂ´t care. If the error appears the program will
             * stop. A better solution would have been to handle the exception using a
             * try-catch sentence. But it is out of the scope of this course.
             */
            Thread.sleep(50L);
        }
    }

    /**
     * Function to move the Balloons of the Level. Moves every single Balloon
     * contained in the level (Balloon array in the Sprite array of the Level) first
     * in the virtual board and then update their image in the visualBoard
     */
    private static void moveBallons() {

        for (int ii = 0; ii < levels[currentLevel].sprites[BALLOONS_INDEX].length; ii++) {

            levels[currentLevel].sprites[BALLOONS_INDEX][ii].move();
            moveSprite(levels[currentLevel].sprites[BALLOONS_INDEX][ii].getID());
        }

    }

    private static void moveDrops() {
        for (int ii = 0; ii < levels[currentLevel].sprites[DROPS_INDEX].length; ii++) {

            levels[currentLevel].sprites[DROPS_INDEX][ii].move();
            moveSprite(levels[currentLevel].sprites[DROPS_INDEX][ii].getID());
        }
    }

    private static void enemiesAttacks() {
        Player playerCopy = (Player) levels[currentLevel].sprites[0][0];
        for (int i = 1; i < levels[currentLevel].sprites.length; i++) {
            for (int j = 0; j < levels[currentLevel].sprites[i].length; j++) {
                if (levels[currentLevel].sprites[i][j].getxPos() == playerCopy.getxPos()
                        && levels[currentLevel].sprites[i][j].getyPos() == playerCopy.getyPos()) {
                    // playerCopy.decrementHealth();
                    visualBoard.gb_println("You have lost 20 points of health");
                    visualBoard.gb_setValueHealthCurrent(playerCopy.getHealth());
                    if (playerCopy.getHealth() == 0) {
                        playerCopy.killed();
                        visualBoard.gb_setSpriteImage(0, playerCopy.getImage());
                        visualBoard.gb_println("You have been killed");
                    }

                }
            }
        }
    }

    /**
     * Prints the current situation of the Level
     * 
     */
    public static void printBoard() {
        for (int ii = 0; ii < levels[currentLevel].board.length; ii++) {
            for (int jj = 0; jj < levels[currentLevel].board[ii].length; jj++) {
                if (levels[currentLevel].board[ii][jj].getImage() != null) {// Then it will be a brick or a wall block
                    visualBoard.gb_setSquareImage(ii, jj, levels[currentLevel].board[ii][jj].getImage());
                } else if (levels[currentLevel].board[ii][jj].isMined()) {// Bomb placed in that block
                    visualBoard.gb_setSquareImage(ii, jj, Bomb.image);
                } else {
                    visualBoard.gb_setSquareImage(ii, jj, null);
                    visualBoard.gb_setSquareColor(ii, jj, NormalBlock.red, NormalBlock.green, NormalBlock.blue);

                }
            }
        }
    }

    /**
     * Adds a new sprite to the visualBoard, and set it visible
     * 
     * @param id
     *            ID of the sprite to add
     */
    public static void addSpriteByID(int id) {
        Sprite currentSprite = Level.getSpriteByID(id);
        visualBoard.gb_addSprite(id, currentSprite.getImage(), true);
        moveSprite(id);
        setVisible(id);

    }

    public static void addSprite(Sprite spriteToAdd) {
        visualBoard.gb_addSprite(spriteToAdd.getID(), spriteToAdd.getImage(), true);
        moveSprite(spriteToAdd.getID());
        setVisible(spriteToAdd.getID());

    }

    /**
     * Makes a sprite visible in the visualBoard
     * 
     * @param id
     *            ID of the sprite
     */
    public static void setVisible(int id) {
        visualBoard.gb_setSpriteVisible(id, true);
    }

    /**
     * Gets the value of the maximum ID until the moment of the call. Then,
     * increments it by one, because the value returned has been used (due to this
     * calling) and cannot be used again for a different sprite.
     * 
     * @return The max ID used at moment
     */
    public static int useID() {
        return maxID++;
    }

    /**
     * Locates the sprite to move by its ID, then moves it in the visualBoard to its
     * new positions.
     * 
     * @param id
     *            ID of the sprite
     */
    public static void moveSprite(int id) {
        Sprite movingSprite = Level.getSpriteByID(id);
        try {
            visualBoard.gb_setSpriteImage(id, movingSprite.getImage());
            visualBoard.gb_moveSprite(id, movingSprite.getxPos(), movingSprite.getyPos());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add to the 'visual board', for first time, ALL the sprites contained in the
     * level.
     */
    public static void initializeSprites() {
        for (int i = 0; i < levels[currentLevel].sprites.length; i++) {
            for (int j = 0; j < levels[currentLevel].sprites[i].length; j++) {
                addSprite(levels[currentLevel].sprites[i][j]);
            }
        }
    }

    /**
     * Calls the method 'detonate' of the Bomb class for every bomb that the Player
     * has.
     */
    public static void detonate() {
        Player playerCopy = (Player) levels[currentLevel].sprites[PLAYER_INDEX][PLAYER_INDEX];
        for (int i = 0; i < playerCopy.bombs.length; i++) {
            playerCopy.bombs[i].detonate(timer);
        }
    }

}
