package sprites;

import structures.Constants;
import structures.Level;

/**
 * Represents a Balloon. This type of enemy moves randomly through the board.
 * The explosion of a bomb will kill them, in addition, if a player touches one,
 * the player will lose health points (hp). Every Balloon has an unique ID
 * identifying them, and a X position and Y position.
 * 
 * This class extends Enemy, which extends Sprite.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December, 6, 2017
 * @version 1.1
 */
public class Balloon extends Enemy {
	/**
	 * Maximum speed reachable for a player
	 */
	private final float BALLOON_MAX_SPEED = 1.0F;
	/**
	 * Minimum speed reachable for a player
	 */
	private final float BALLOON_MIN_SPEED = 0.1F;
	/**
	 * @see {@link Enemy#points}
	 */
	private final int POINTS_BALLOON = 100;
	/**
	 * Variables used for changing the images of the balloon
	 * and creating animation
	 */
	private int up = 0, down = 0, left = 0, right = 0;

	/**
	 * Constructor. Initializes a balloon in a random position (if that position is
	 * available)
	 * 
	 * @param l
	 *            Level were the Balloon exists
	 * @param id
	 *            Unique ID identifying the balloon
	 */
	public Balloon(Level l, int id) {
		super(id);
		image = "enemy111.png";        
		ownLevel = l;
		setMax_speed(BALLOON_MAX_SPEED);
		setMin_speed(BALLOON_MIN_SPEED);
		points = POINTS_BALLOON;
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
	 * Method that moves in a random direction a Balloon. Animation implemented.
	 */
	@Override
	public void move() {
		double copyX = Math.min((xCoord + 0.4), Constants.BOARD_SIZE);
		double copyY = Math.min((yCoord + 0.8), Constants.BOARD_SIZE);
		// Chooses a random direction
		switch ((int) (Math.random() * 4)) {
		case 0: // moves to the right
			if (ownLevel.board[(int) (copyX + 0.2)][(int) (copyY)].isWalkable()) {
				if (right % 4 == 0) {
					image = "enemy121.png";
				} else if ((right + 1) % 4 == 0) {
					image = "enemy123.png";
				} else if ((right + 2) % 4 == 0) {
					image = "enemy122.png";
				} else if ((right + 3) % 4 == 0) {
					image = "enemy123.png";
				}
				right++;
				xCoord = xCoord + 0.2;
				xPos = (int) (xCoord+0.5);
			}
			break;
		case 1: // moves to the left
			if (ownLevel.board[(int) (copyX - 0.2)][(int) (copyY)].isWalkable()) {
				if (left % 4 == 0) {
					image = "enemy111.png";
				} else if ((left + 1) % 4 == 0) {
					image = "enemy113.png";
				} else if ((left + 2) % 4 == 0) {
					image = "enemy112.png";
				} else if ((left + 3) % 4 == 0) {
					image = "enemy113.png";
				}
				left++;
				xCoord = xCoord - 0.2;
				xPos = (int) (xCoord+0.5);
			}
			break;
		case 2: // moves upwards
			if (ownLevel.board[(int) (copyX)][(int) (copyY - 0.2)].isWalkable()) {
				if (up % 4 == 0) {
					image = "enemy121.png";
				} else if ((up+ 1) % 4 == 0) {
					image = "enemy123.png";
				} else if ((up + 2) % 4 == 0) {
					image = "enemy122.png";
				} else if ((up + 3) % 4 == 0) {
					image = "enemy123.png";
				}
				up++;
				yCoord = yCoord - 0.2;
				yPos = (int) (yCoord+0.5);
			}
			break;
		case 3: // moves downwards
			if (ownLevel.board[(int) (copyX)][(int) (copyY + 0.2)].isWalkable()) {
				if (down % 4 == 0) {
					image = "enemy111.png";
				} else if ((down + 1) % 4 == 0) {
					image = "enemy113.png";
				} else if ((down + 2) % 4 == 0) {
					image = "enemy112.png";
				} else if ((down + 3) % 4 == 0) {
					image = "enemy113.png";
				}
				down++;
				yCoord = yCoord + 0.2;
				yPos = (int) (yCoord+0.5);
			}
		}
	}

	/**
	 * Implementation of the abstract method {@link Sprite#move(String)
	 * move(String)}. This method has no code because the movements do not depend on
	 * 'lastAction', instead the move is done with {@link Balloon#move()}.
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
		image = "enemy100.png";

	}
}
