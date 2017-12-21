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
	 * Variables used for changing the images of the bomb
	 */
	private int up = 0, up1 = 0, down = 0, down1 = 0, left = 0, left1 = 0, right = 0, right1 = 0, center = 0;

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
	 * Time when the bomb was placed
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
	 * Time, expressed in milliseconds, that takes the bomb while exploding.
	 */
	public final double EXPLODING_TIME = 1000;

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
	 * Checks if the countdown of the bomb has finished. If it has finished, calls
	 * the method exploding() to make the animation of the explosion. Checks if the 
	 * exploding time of the bomb has finished. If it has finished, calls the method explode().
	 * 
	 * @param timer
	 *            value of the timer in the moment of calling
	 * @param ableRemoteControl
	 *            Refers to the field of the player {@link Player#remoteControl
	 *            remoteControl}
	 */
	public void detonate(long timer, boolean ableRemoteControl) {
		if (ableRemoteControl || timer > this.initialTime + COUNT_DOWN) {
			if (this.isPlaced()) {
				exploding();
				if (timer > this.initialTime + COUNT_DOWN + EXPLODING_TIME) {
					explode();
					this.resetBomb();
				}
			}
		}
	}

	/**
	 * Makes the animation of explosion for the surrounding blocks when the bomb countdown
	 * has finished. It separately checks the four directions for breakable blocks. To get the 
	 * correct image for each situation, for each direction we call a different method that returns the 
	 * corresponding image, taking into account the reach-ability and the position of that block with 
	 * respect to the block where the bomb was first exploded.
	 */
	public void exploding() {
		if (ownLevel.board[xPos][yPos].isBreakable()) {
			Main.visualBoard.gb_setSquareImage(xPos, yPos, explosionCenter());
		}

		// Exploding animation to the right blocks
		if (ownLevel.board[xPos + 1][yPos].isBreakable()) {
			for (int i = 1; i <= range && i < Constants.BOARD_SIZE; i++) {
				if (ownLevel.board[(i+xPos)][yPos].isBreakable()) {
					Main.visualBoard.gb_setSquareImage(xPos+i, yPos, explosionsRight(i,range));
				}
			}
		}

		// Exploding animation to the left blocks
		if (ownLevel.board[xPos - 1][yPos].isBreakable()) {
			for (int i = 1; i <= range && i < Constants.BOARD_SIZE; i++) {
				if (ownLevel.board[(xPos-i)][yPos].isBreakable()) {
					Main.visualBoard.gb_setSquareImage(xPos-i, yPos, explosionsLeft(i,range));
				}
			}
		}

		// Exploding animation to the upper blocks
		if (ownLevel.board[xPos][yPos+1].isBreakable()) {
			for (int i = 1; i <= range && i < Constants.BOARD_SIZE; i++) {
				if (ownLevel.board[xPos][yPos+1].isBreakable()) {
					Main.visualBoard.gb_setSquareImage(xPos, yPos+i, explosionsUp(i,range));
				}
			}
		}
		// Exploding animation to the lower blocks
		if (ownLevel.board[xPos][yPos-1].isBreakable()) {
			for (int i = 1; i <= range && i < Constants.BOARD_SIZE; i++) {
				if (ownLevel.board[xPos][yPos-1].isBreakable()) {
					Main.visualBoard.gb_setSquareImage(xPos, yPos-i, explosionsDown(i,range));
				}
			}
		}
	}

	/**
	 * It gets a different image depending on an increasing variable to generate 
	 * a sense of an animated explosion for the central block.
	 * 
	 * @return the image for the explosion on the center
	 */
	private String explosionCenter() {
		if (center % 4 == 0) {
			center++;
			return "explosion_C1.gif";
		} else if ((center + 1) % 4 == 0) {
			center++;
			return "explosion_C2.gif";
		} else if ((center + 2) % 4 == 0) {
			center++;
			return "explosion_C3.gif";
		} else if ((center + 3) % 4 == 0) {
			center++;
			return "explosion_C4.gif";
		} else {
			center++;
			return "explosion_C1.gif";
		}
	}
	
	/**
	 * It chooses the adequate image depending on the range.
	 * Also, by changing the image to similar ones, we generate
	 * the sense of an animated explosion.
	 * 
	 * @param i 
	 * 			it tells the position with respect to the central block
	 * @param range
	 * 			it depends on the bonuses that have been collected
	 * @return the image for the explosion on the right blocks
	 */
	private String explosionsRight(int i, int range) {
		if (i==range) {
			if (right % 4 == 0) {
				right++;
				return "explosion_E1.gif";
			} else if ((right + 1) % 4 == 0) {
				right++;
				return "explosion_E2.gif";
			} else if ((right + 2) % 4 == 0) {
				right++;
				return "explosion_E3.gif";
			} else if ((right + 3) % 4 == 0) {
				right++;
				return "explosion_E4.gif";
			} else {
				right++;
				return "explosion_E1.gif";
			}
		} else {
			if (right1 % 4 == 0) {
				right1++;
				return "explosion_H1.gif";
			} else if ((right + 1) % 4 == 0) {
				right1++;
				return "explosion_H2.gif";
			} else if ((right + 2) % 4 == 0) {
				right1++;
				return "explosion_H3.gif";
			} else if ((right + 3) % 4 == 0) {
				right1++;
				return "explosion_H4.gif";
			} else {
				right1++;
				return "explosion_H1.gif";
			}
		}
	}
	

	/**
	 * It chooses the adequate image depending on the range.
	 * Also, by changing the image to similar ones, we generate
	 * the sense of an animated explosion.
	 * 
	 * @param i 
	 * 			it tells the position with respect to the central block
	 * @param range
	 * 			it depends on the bonuses that have been collected
	 * @return the image for the explosion on the left blocks
	 */
	private String explosionsLeft(int i, int range) {
		if (i==range) {
			if (left % 4 == 0) {
				left++;
				return "explosion_W1.gif";
			} else if ((left + 1) % 4 == 0) {
				left++;
				return "explosion_W2.gif";
			} else if ((left + 2) % 4 == 0) {
				left++;
				return "explosion_W3.gif";
			} else if ((left + 3) % 4 == 0) {
				left++;
				return "explosion_W4.gif";
			} else {
				left++;
				return "explosion_W1.gif";
			}
		} else {
			if (left1 % 4 == 0) {
				left1++;
				return "explosion_H1.gif";
			} else if ((left + 1) % 4 == 0) {
				left1++;
				return "explosion_H2.gif";
			} else if ((left + 2) % 4 == 0) {
				left1++;
				return "explosion_H3.gif";
			} else if ((left + 3) % 4 == 0) {
				left1++;
				return "explosion_H4.gif";
			} else {
				left1++;
				return "explosion_H1.gif";
			}
		}
	}

	/**
	 * It chooses the adequate image depending on the range.
	 * Also, by changing the image to similar ones, we generate
	 * the sense of an animated explosion.
	 * 
	 * @param i 
	 * 			it tells the position with respect to the central block
	 * @param range
	 * 			it depends on the bonuses that have been collected
	 * @return the image for the explosion on the upper blocks
	 */
	private String explosionsUp(int i, int range) {
		if (i==range) {
			if (up % 4 == 0) {
				up++;
				return "explosion_S1.gif";
			} else if ((up + 1) % 4 == 0) {
				up++;
				return "explosion_S2.gif";
			} else if ((up + 2) % 4 == 0) {
				up++;
				return "explosion_S3.gif";
			} else if ((up + 3) % 4 == 0) {
				up++;
				return "explosion_S4.gif";
			} else {
				up++;
				return "explosion_S1.gif";
			}
		} else {
			if (up1 % 4 == 0) {
				up1++;
				return "explosion_V1.gif";
			} else if ((up + 1) % 4 == 0) {
				up1++;
				return "explosion_V2.gif";
			} else if ((up + 2) % 4 == 0) {
				up1++;
				return "explosion_V3.gif";
			} else if ((up + 3) % 4 == 0) {
				up1++;
				return "explosion_V4.gif";
			} else {
				up1++;
				return "explosion_V1.gif";
			}
		}
	}

	/**
	 * It chooses the adequate image depending on the range.
	 * Also, by changing the image to similar ones, we generate
	 * the sense of an animated explosion.
	 * 
	 * @param i 
	 * 			it tells the position with respect to the central block
	 * @param range
	 * 			it depends on the bonuses that have been collected
	 * @return the image for the explosion on the lower blocks
	 */
	private String explosionsDown(int i, int range) {
		if (i==range) {
			if (down % 4 == 0) {
				down++;
				return "explosion_N1.gif";
			} else if ((up + 1) % 4 == 0) {
				down++;
				return "explosion_N2.gif";
			} else if ((up + 2) % 4 == 0) {
				down++;
				return "explosion_N3.gif";
			} else if ((up + 3) % 4 == 0) {
				down++;
				return "explosion_N4.gif";
			} else {
				down++;
				return "explosion_N1.gif";
			}
		} else {
			if (down1 % 4 == 0) {
				down1++;
				return "explosion_V1.gif";
			} else if ((up + 1) % 4 == 0) {
				down1++;
				return "explosion_V2.gif";
			} else if ((up + 2) % 4 == 0) {
				down1++;
				return "explosion_V3.gif";
			} else if ((up + 3) % 4 == 0) {
				down1++;
				return "explosion_V4.gif";
			} else {
				down1++;
				return "explosion_V1.gif";
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

		/**
		 * It checks whether any of the sprites where present (in the same XY-position) when the bomb
		 * blasted off. If the sprite present was the player (ID==0), then the player will have killed
		 * himself. A game-over message will be shown, displaying the total amount of points accumulated.
		 * If the sprite present is an enemy, we kill the enemy and add points to the player for killing it.
		 * By checking that the sprite is alive, we are ensuring that we only kill it once (has it could
		 * happen that the enemy moved from one cell to the other while exploding, therefore killing it 
		 * again and adding extra points to the player for killing the same enemy twice. 
		 * 
		 * @param xPos 
		 * 			is the X-position of the block where part of an explosion took place 
		 * @param yPos
		 * 			is the Y-position of the block where part of an explosion took place
		 */
		private void damage(int xPos, int yPos) {
			for (int i = 0; i < Main.maxID; i++) {
				if (ownLevel.getSpriteByID(i).xPos == xPos && ownLevel.getSpriteByID(i).yPos == yPos
						&& ownLevel.getSpriteByID(i).isAlive()) {
					ownLevel.getSpriteByID(i).setHp(0);
					if (i == 0) { // The player had been harmed
						Main.visualBoard.gb_println("You have killed yourself!!!");
						((Player)(ownLevel.getSpriteByID(Main.PLAYER_ID))).killing();
						Main.visualBoard.gb_showMessageDialog("GAME OVER "+"\n"+"You earned "+((Player)(ownLevel.getSpriteByID(Main.PLAYER_ID))).getScore()+" points");
					} else {
						((Player) (ownLevel.getSpriteByID(Main.PLAYER_ID)))
						.addScore(((Enemy) (ownLevel.getSpriteByID(i))).getPoints());
						Main.visualBoard.gb_println("You have killed the enemy " + i + ". You won "
								+ ((Enemy) (ownLevel.getSpriteByID(i))).getPoints() + " points");
						ownLevel.getSpriteByID(i).killed();
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
