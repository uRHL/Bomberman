package sprites;

import structures.*;

public class Drop extends Enemy {
    /**
     * Maximum speed reachable for a player
     */
    private final float DROP_MAX_SPEED = 1.0F;
    /**
     * Minimum speed reachable for a player
     */
    private final float DROP_MIN_SPEED = 0.1F;

    /**
     * @see {@link Enemy#points}
     */
    private final int POINTS_DROP = 250;

    /**
     * Full constructor. Initializes a balloon in a random position (if that
     * position is available)
     * 
     * @param l
     *            Level where the Drop exists.
     * @param id
     *            Unique ID identifying the balloon
     */
    public Drop(Level l, int id) {
        super(id);
        image = "enemy211.png";        
        ownLevel = l;
        setMax_speed(DROP_MAX_SPEED);
        setMin_speed(DROP_MIN_SPEED);
        points = POINTS_DROP;
        /*
         * Setting the hp. 1 is enough because the hp of the enemies does not really
         * matter, since the explosion of one bomb will always kill them
         */
        setHp(1);

        do {
            xPos = (int) (Math.random() * (Constants.BOARD_SIZE - 1) + 1);
            yPos = (int) (Math.random() * (Constants.BOARD_SIZE - 1) + 1);

        } while (!ownLevel.board[xPos][yPos].isAvailable() || (xPos == 1 && yPos == 1));
        xCoord = xPos;
        yCoord = yPos;
    }

    /**
     * Moves the Drop. A Drop follows the player, therefore, it will try to reach
     * the Player position.
     */
    public void move() {
        double copyX = Math.min((xCoord + 0.4), Constants.BOARD_SIZE);
        double copyY = Math.min((yCoord + 0.8), Constants.BOARD_SIZE);
        int playerXpos = ownLevel.sprites[0][0].getxPos();
        int playerYpos = ownLevel.sprites[0][0].getyPos();
        if (ownLevel.board[(int) (copyX + 0.2)][(int) (copyY)].isWalkable() && playerXpos > xPos) {
            xCoord = xCoord + 0.2;
            xPos = (int) (xCoord);
        } else if (ownLevel.board[(int) (copyX - 0.2)][(int) (copyY)].isWalkable() && playerXpos < xPos) {
            xCoord = xCoord - 0.2;
            xPos = (int) (xCoord);
        } else if (ownLevel.board[(int) (copyX)][(int) (copyY - 0.2)].isWalkable() && playerYpos < yPos) {
            yCoord = yCoord - 0.2;
            yPos = (int) (yCoord);
        } else if (ownLevel.board[(int) (copyX)][(int) (copyY - 0.2)].isWalkable() && playerYpos > yPos) {
            yCoord = yCoord + 0.2;
            yPos = (int) (yCoord);
        }
    }

    /**
     * Implementation of the abstract method {@link Sprite#move(String)
     * move(String)}. This method has no code because the movements do not depend on
     * 'lastAction', instead the move is done with {@link Drop#move()}.
     */
    @Override
    public void move(String lastAction) {
        move();
    }

    /**
     * Sets the {@link Sprite#alive alive} to false.
     */
    @Override
    public void killed() {
        alive = false;
        image = "enemy222.png";

    }

}
