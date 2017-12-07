package structures;

import sprites.*;
import blocks.*;

/**
 * Level class represents a level, storing the information of a board (formed by
 * 'blocks' ) and the different 'sprites' (player and enemies)
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December, 6, 2017
 * @version 1.1
 */
public class Level {
    /**
     * 'Block' type matrix containing the actual situation of each block.
     */
    public final Block[][] board = new Block[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
    /**
     * Irregular matrix containing arrays of all the sprites of the level. The index
     * one is reserved for the array of 'players', the second for the array of
     * 'balloons' and the third for the array of 'drops'
     */
    public final static Sprite[][] sprites = new Sprite[2][]; // dimension 2 until 'Drops' are developed

    /**
     * Constructor. Initializes a full level, containing a board and the sprites
     * that are inside it.
     */
    public Level() {
        // Initializing the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                /*
                 * All the blocks situated in the frame of the board or at an even index, will
                 * be wall blocks
                 */

                if (i == 0 || i == Constants.BOARD_SIZE - 1 || j == 0 || j == Constants.BOARD_SIZE - 1
                        || (i % 2 == 0 && j % 2 == 0)) {
                    board[i][j] = new WallBlock();

                } else if ((i > 2 && j > 2) && ((int) (Math.random() * 2) == 0)) {
                    // If a block is set to be a 'normal' or a 'brick' block randomly
                    board[i][j] = new BrickBlock();

                } else {
                    board[i][j] = new NormalBlock();
                }
            }

        }
        // Only one player for this version
        sprites[0] = new Player[] { new Player(this, Main.useID()) };
        sprites[1] = createsBallons();

        // sprites[2] = createDrops();
        // Drop class needs to be developed yet

    }

    /**
     * Creates between 1 and 10 Balloons and storages them in an array
     * 
     * @return An array containing all the 'Balloons' in this level.
     */
    public Balloon[] createsBallons() {
        Balloon[] balloon = new Balloon[(int) ((Math.random() * 10) + 1)];
        for (int ii = 0; ii < balloon.length; ii++) {
            balloon[ii] = new Balloon(this, Main.useID());

        }
        return balloon;
    }

    /**
     * Finds an sprite, among all of them, and returns the sprite with the specified
     * ID
     * 
     * @param ID
     *            of the sprite that is being searched
     * @return The sprite with that ID
     */
    public static Sprite getSpriteByID(int ID) {
        Sprite target = null;
        // Searching in the sprites array of the level (that contains all the sprites)
        // the sprite with the specified ID
        for (int i = 0; i < sprites.length; i++) {
            for (int j = 0; j < sprites[i].length; j++) {
                if (sprites[i][j].getID() == ID) {
                    target = sprites[i][j];
                }
            }
        }
        return target;
    }
}