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
     * Variables used for changing the images of the drop
     * and creating animation
     */
    private int up = 0, down = 0, left = 0, right = 0;
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
        speed = 0.1F;
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
     * the Player position. Animation implemented
     */
    public void move() {
        double copyX = Math.min((xCoord + 0.4), Constants.BOARD_SIZE);
        double copyY = Math.min((yCoord + 0.9), Constants.BOARD_SIZE);
        int playerXpos = ownLevel.sprites[0][0].getxPos();
        int playerYpos = ownLevel.sprites[0][0].getyPos();
        // moving to the right
        if (ownLevel.board[(int)(copyX+speed)][(int)(copyY)].isWalkable() && playerXpos > xPos) {
        	if (right % 4 == 0) {
                image = "enemy222.png";
            } else if ((right + 1) % 4 == 0) {
                image = "enemy221.png";
            } else if ((right + 2) % 4 == 0) {
                image = "enemy223.png";
            } else if ((right + 3) % 4 == 0) {
            	image = "enemy221.png";
            }
            right++;
            xCoord = xCoord + 0.2;
            xPos = (int) (xCoord+0.5);
        } // moving to the left
        else if (ownLevel.board[(int) (copyX - 0.2)][(int) (copyY)].isWalkable() && playerXpos < xPos)  {
        	if (left % 4 == 0) {
                image = "enemy212.png";
            } else if ((left + 1) % 4 == 0) {
                image = "enemy211.png";
            } else if ((left + 2) % 4 == 0) {
                image = "enemy213.png";
            } else if ((left + 3) % 4 == 0) {
            	image = "enemy211.png";
            }
            left++;
            xCoord = xCoord - 0.2;
            xPos = (int) (xCoord+0.5);
        } //moving up
        else if (ownLevel.board[(int) (copyX)][(int) (copyY - 0.2)].isWalkable() && playerYpos <= yPos) {
        	if (up % 4 == 0) {
                image = "enemy212.png";
            } else if ((up + 1) % 4 == 0) {
                image = "enemy211.png";
            } else if ((up + 2) % 4 == 0) {
                image = "enemy213.png";
            } else if ((up + 3) % 4 == 0) {
            	image = "enemy211.png";
            }
            up++;
            yCoord = yCoord - 0.2;
            yPos = (int) (yCoord+0.5);
        } //moving down
        else if (ownLevel.board[(int) (copyX)][(int) (copyY - 0.2)].isWalkable() && playerYpos >= yPos) {
        	if (down % 4 == 0) {
                image = "enemy222.png";
            } else if ((down + 1) % 4 == 0) {
                image = "enemy221.png";
            } else if ((down + 2) % 4 == 0) {
                image = "enemy223.png";
            } else if ((down + 3) % 4 == 0) {
            	image = "enemy221.png";
            }
            down++;
            yCoord = yCoord + 0.2;
            yPos = (int) (yCoord+0.5);
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
        image = "enemy200.png";

    }

}
