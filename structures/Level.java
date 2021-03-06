package structures;

import sprites.*;
import blocks.*;
import bonuses.*;

/**
 * Level class represents a level, storing the information of a board (formed by
 * 'blocks' ) and the different 'sprites' (player and enemies)
 * 
 * @author Ram�n Hern�ndez Le�n. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Mi�ano. Bachelor Degree in Computer Science. UC3M
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
     * zero is reserved for the array of {@link Player Players}, the index one for the
     * array of {@link Balloon Balloons} and the index two for the array of {@link Drop
     * Drops}
     */
    public final Sprite[][] sprites = new Sprite[3][];

    /**
     * Constructor. Initializes a full level, containing a board and the sprites
     * that are inside it.
     */
    public Level() {
        // Initializing the board
        Bonus[] bonuses = initializeBonuses();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                /*
                 * All the blocks situated in the frame of the board or at an even index, will
                 * be wall blocks
                 */

                if (i == 0 || i == Constants.BOARD_SIZE - 1 || j == 0 || j == Constants.BOARD_SIZE - 1
                        || (i % 2 == 0 && j % 2 == 0)) {
                    board[i][j] = new WallBlock();

                } else if ((i > 2 && j > 2) && ((int) (Math.random() * 4) == 0)) {
                    /*
                     * Just next to the initial position of the player (i=2, j=2) there can be no
                     * BrickBlocks, otherwise the player will be trapped.
                     * 
                     * If a block is 'normal' or a 'brick' block is set randomly. There is a 25% of
                     * probability of setting a BrickBLock
                     */
                    board[i][j] = new BrickBlock();
                } else {
                    board[i][j] = new NormalBlock();
                }
            }

        }
        /*
         * Assigning every bonus in a random BrickBoard of the board
         */
        for (int i = 0; i < bonuses.length; i++) {
            int xIndex, yIndex;
            do {
                xIndex = (int) (Math.random() * 14 + 1);
                yIndex = (int) (Math.random() * 14 + 1);

            } while (!board[xIndex][yIndex].toString().equals("BrickBlock") && (!board[xIndex][yIndex].doHaveBonus()));
            board[xIndex][yIndex].setBonus(randomBonus(bonuses));
            // this is just for debugging
            Main.visualBoard.gb_println(
                    "bonus added: " + board[xIndex][yIndex].getBonus().getImage() + " | " + xIndex + ", " + yIndex);
        }
        // Only one player for this version
        sprites[0] = new Player[] { new Player(this, Main.useID()) };
        sprites[1] = createsBallons();
        sprites[2] = createDrops();

    }

    /**
     * Creates between 1 and 10 Balloons and storages them into an array
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
     * Creates between 1 and 3 Drops and storages them into an array
     * 
     * @return An array containing all the 'Drops' in this level.
     */
    public Drop[] createDrops() {
        Drop[] drop = new Drop[(int) ((Math.random() * 3) + 1)];
        for (int ii = 0; ii < drop.length; ii++) {
            drop[ii] = new Drop(this, Main.useID());
        }
        return drop;
    }

    /**
     * Initializes the different bonuses, checking first if the specified
     * Requirements of each type of bonus (frequency and probability) are satisfied.
     * 
     * @return An array containing all the bonuses created for this level
     */
    public Bonus[] initializeBonuses() {

        Bonus[] bonuses = new Bonus[] { new Door() };
        /*
         * Checking if a level is able to have a determined type of bonus.
         */
        if (Main.currentLevel % BombBonus.frequency == 0
                && ((int) (Math.random() * RollerSkateBonus.probability) == 0)) {
            // There can be between zero and three BombBonuses
            for (int i = 0; i < ((int) (Math.random() * 4)); i++) {
                bonuses = addBonus(bonuses, new BombBonus());
            }
        }
        if (Main.currentLevel % FireBonus.frequency == 0
                && ((int) (Math.random() * RollerSkateBonus.probability) == 0)) {
            bonuses = addBonus(bonuses, new FireBonus());
        }

        if (Main.currentLevel != 0 && Main.currentLevel % SpecialFireBonus.frequency == 0
                && ((int) (Math.random() * RollerSkateBonus.probability) == 0)) {
            // There cannot be a Bonus of this type in the first level
            bonuses = addBonus(bonuses, new SpecialFireBonus());
        }
        if (Main.currentLevel != 0 && Main.currentLevel % RemoteControlBonus.frequency == 0
                && ((int) (Math.random() * RollerSkateBonus.probability) == 0)) {
            // There cannot be a Bonus of this type in the first level
            bonuses = addBonus(bonuses, new RemoteControlBonus());
        }
        if (Main.currentLevel % RollerSkateBonus.frequency == 0
                && ((int) (Math.random() * RollerSkateBonus.probability) == 0)) {
            bonuses = addBonus(bonuses, new RollerSkateBonus());
        }
        if (Main.currentLevel % GetaBonus.frequency == 0 && ((int) (Math.random() * GetaBonus.probability) == 0)) {
            bonuses = addBonus(bonuses, new GetaBonus());
        }
        return bonuses;
    }

    /**
     * Adds a new bonus to the array of bonuses, incrementing its length by one if
     * necessary.
     * 
     * @param oldArray
     *            Old array to copy
     * @param newBonus
     *            New bonus to add
     * @return A new array with the new bonus added at the end.
     */
    private Bonus[] addBonus(Bonus[] oldArray, Bonus newBonus) {
        Bonus[] newArray;
        newArray = new Bonus[oldArray.length + 1];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        newArray[oldArray.length] = newBonus;

        return newArray;
    }

    /**
     * Selects a random bonus among all of them. Once it had been selected, its
     * 'used' is set to true
     * 
     * @param bonuses
     *            Array with all the bonuses
     * @return
     */
    private Bonus randomBonus(Bonus[] bonuses) {
        Bonus temp = new Bonus();
        do {
            int randomIndex = (int) (Math.random() * bonuses.length);
            try {
                if (!bonuses[randomIndex].isPlaced()) {
                    temp = bonuses[randomIndex];
                    bonuses[randomIndex].setPlaced(true);
                }
            } catch (Exception e) {
                // null pointer exception
                // Trying again with a different randomIndex
            }
        } while (!temp.isPlaced());

        return temp;
    }

    /**
     * Finds an sprite, among all of them, and returns the sprite with the specified
     * ID
     * 
     * @param ID
     *            of the sprite that is being searched
     * @return The sprite with that ID
     */
    public Sprite getSpriteByID(int ID) {
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

    /**
     * Tells if all the enemies are dead.
     * @see {@link Level#sprites}
     * 
     * @return True if all the enemies are dead. False otherwise.
     */
    public boolean allEnemiesDead() {
        boolean allDead = true;
        /*
         * i starts at 1 because the zero index in the sprites array is reserved for the
         * Players, and only the enemies are being considered.
         */
        for (int i = 1; i < sprites.length; i++) {
            for (int j = 0; j < sprites[i].length; j++) {
                if (sprites[i][j].isAlive()) {
                    allDead = false;
                }
            }
        }
        return allDead;
    }
}