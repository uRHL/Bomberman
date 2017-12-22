package sprites;

import structures.*;

/**
 * Represents a Player. As this class extends Sprite, every Player will have an
 * unique ID number, x and y positions, a collection of images, belongs to a
 * fixed level, and some Bombs.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December, 6, 2017
 * @version 1.1
 */
public class Player extends Sprite {
    /**
     * Bomb type array storing the bombs that the player has, starting with one
     * bomb. It can be interpreted as a bag, the player can only use the bombs that
     * carries in the bag. The number of bombs can be incremented with bonuses.
     */
    private Bomb[] bombs;
    /**
     * Storages if the player has the bonus {@link bonuses.RemoteControlBonus
     * RemoteControl}.
     */
    private boolean remoteControl;

    /**
     * Number of points the player has. Points are obtained killing enemies.
     */
    private int score;

    /**
     * Variables used for changing the images of the player
     * and creating its animation
     */
    private int up = 0, down = 0, left = 0, right = 0, kill = 0;

    /**
     * Maximum speed reachable for a player
     */
    private final float PLAYER_MAX_SPEED = 1.0F;
    /**
     * Minimum speed reachable for a player
     */
    private final float PLAYER_MIN_SPEED = 0.1F;

    /**
     * 
     * @return The remoteControl
     */
    public boolean hasRemoteControl() {
        return remoteControl;
    }

    /**
     * 
     * @param remoteControl
     *            RemoteControl to set
     */
    public void setRemoteControl(boolean remoteControl) {
        this.remoteControl = remoteControl;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score if the new value is higher than the current score.
     * 
     * @param score
     *            the score to set
     */
    public void setScore(int score) {
        if (score > this.score) {
            this.score = score;
        }
    }

    /**
     * Adds a {@link Enemy#points determined number of points} to the player' score
     * 
     * @param points
     *            Points added
     */
    public void addScore(int points) {
        setScore(getScore() + points);
    }

    /**
     * 
     * @return the array of bombs
     */
    public Bomb[] getBombs() {
        return bombs;
    }

    /**
     * Gets the number of bombs available.
     * 
     * @return The number of bombs in the {@link Player#bombs array of bombs} that
     *         have not been placed
     */
    public int getNumOfAvailableBombs() {
        int availableBombs = 0;
        for (int i = 0; i < bombs.length; i++) {
            if (!bombs[i].isPlaced()) {
                availableBombs++;
            }
        }
        return availableBombs;
    }

    /**
     * Constructor. Initializes the ID number ('super' constructor), the X-position
     * and Y-position (1 by default), the image and the array of bombs, initially
     * with one bomb.
     * 
     * @param l
     *            Level were the Player exists.
     * @param id
     *            Unique number identifying the player.
     */
    public Player(Level l, int id) {
        super(id);
        xPos = 1;
        yPos = 1;
        xCoord = (double) xPos;
        yCoord = (double) yPos;
        setMax_speed(PLAYER_MAX_SPEED);
        setMin_speed(PLAYER_MIN_SPEED);
        speed = 0.1F;
        image = "bomberman111.png";
        ownLevel = l;
        super.setHp(100);
        remoteControl = false;
        score = 0;
        bombs = new Bomb[] { new Bomb() };

    }

    /**
     * Override method to move the player depending on the last key pressed.
     * Animation implemented
     */
    @Override
    public void move(String lastAction) {
        double copyX = Math.min((xCoord + 0.4), Constants.BOARD_SIZE);
        double copyY = Math.min((yCoord + 0.8), Constants.BOARD_SIZE);

        if (alive) {
        	// moves to the right
            if (lastAction.equals("right") && ownLevel.board[(int) (copyX + 0.2)][(int) (copyY)].isWalkable()) {
                if (right % 5 == 0) {
                    image = "bomberman131.png";
                } else if ((right + 1) % 5 == 0) {
                    image = "bomberman132.png";
                } else if ((right + 2) % 5 == 0) {
                    image = "bomberman133.png";
                } else if ((right + 3) % 5 == 0) {
                    image = "bomberman134.png";
                } else if ((right + 4) % 5 == 0) {
                    image = "bomberman135.png";
                }
                right++;
                xCoord = xCoord + speed;
                xPos = (int) (xCoord + 0.5);
            } // moves to the left
            else if (lastAction.equals("left") && ownLevel.board[(int) (copyX - 0.2)][(int) (copyY)].isWalkable()) {
                if (left % 5 == 0) {
                    image = "bomberman121.png";
                } else if ((left + 1) % 5 == 0) {
                    image = "bomberman122.png";
                } else if ((left + 2) % 5 == 0) {
                    image = "bomberman123.png";
                } else if ((left + 3) % 5 == 0) {
                    image = "bomberman124.png";
                } else if ((left + 4) % 5 == 0) {
                    image = "bomberman125.png";
                }
                left++;
                xCoord = xCoord - speed;
                xPos = (int) (xCoord + 0.5);
            } // moves up
            else if (lastAction.equals("up") && ownLevel.board[(int) (copyX)][(int) (copyY - 0.2)].isWalkable()) {
                if (up % 5 == 0) {
                    image = "bomberman101.png";
                } else if ((up + 1) % 5 == 0) {
                    image = "bomberman102.png";
                } else if ((up + 2) % 5 == 0) {
                    image = "bomberman103.png";
                } else if ((up + 3) % 5 == 0) {
                    image = "bomberman104.png";
                } else if ((up + 4) % 5 == 0) {
                    image = "bomberman105.png";
                }
                up++;
                yCoord = yCoord - speed;
                yPos = (int) (yCoord + 0.5);
            } // moves down
            else if (lastAction.equals("down") && ownLevel.board[(int) (copyX)][(int) (copyY + 0.2)].isWalkable()) {
                if (down % 5 == 0) {
                    image = "bomberman111.png";
                } else if ((down + 1) % 5 == 0) {
                    image = "bomberman112.png";
                } else if ((down + 2) % 5 == 0) {
                    image = "bomberman113.png";
                } else if ((down + 3) % 5 == 0) {
                    image = "bomberman114.png";
                } else if ((down + 4) % 5 == 0) {
                    image = "bomberman115.png";
                }
                down++;
                yCoord = yCoord + speed;
                yPos = (int) (yCoord + 0.5);
            }
            try {
                // If the bonus is not in a NormalBlock it is unreachable
                if (ownLevel.board[xPos][yPos].toString().equals("NormalBlock")) {
                    if (ownLevel.board[xPos][yPos].getBonus().consumeBonus(this)) {
                        // If the bonus was consumed successfully, eliminate it
                        ownLevel.board[xPos][yPos].setBonus(null);
                    }
                }
            } catch (Exception e) {
                // No bonus founded
            }

        }
    }

    /**
     * Place a bomb in the position were the player is in the moment of the call. It
     * will check if the player has available bombs in the 'bag' (not planted
     * bombs), and, if it has one, it will plant it (initializing the bomb with the
     * full constructor now).
     * 
     * <p>
     * The attributes of the block were the bomb is placed will be changed: it will
     * be not available, nor walkable and the mined will be set to true.
     * </p>
     * 
     * @param timer
     *            Time when the bomb was placed.
     */
    public void putBomb(long timer) {
        if (ownLevel.board[xPos][yPos].isAvailable()) {
            for (int i = 0; i < bombs.length; i++) { // Searching if there is any available bomb
                if (!bombs[i].isPlaced()) { // The bomb has not been placed
                    bombs[i] = new Bomb(ownLevel, timer, xPos, yPos);
                    ownLevel.board[xPos][yPos].setAvailable(false);
                    ownLevel.board[xPos][yPos].setWalkable(true);
                    ownLevel.board[xPos][yPos].setMined(true);
                    break;
                }
            }
        }
    }

    /**
     * Adds a new bomb to the array of bombs. This means that the Player has one
     * more bomb in his "belt", so it can place one more bomb at the same time
     */
    public void addBomb() {
        Bomb[] newArray;
        newArray = new Bomb[bombs.length + 1];
        System.arraycopy(bombs, 0, newArray, 0, bombs.length);
        newArray[bombs.length] = new Bomb();
        bombs = newArray;
    }

    /**
     * Override method. It has NO implementation as the value of the last key
     * pressed is needed.
     */
    @Override
    public void move() {

    }

    /**
     * Method used for generating the animation of the player's death.
     * Calls killed() after animation is performed. 
     */
    public void killing() {
    	if (kill%5 == 0) {
    		image = "bomberman141.png";
    		kill++;
    	} else if((kill+4)%5==0) {
    		image = "bomberman142.png";
    		kill++;
    	} else if((kill+3)%5==0) {
    		image = "bomberman143.png";
    		kill++;
    	} else if((kill+2)%5==0) {
    		image = "bomberman144.png";
    		kill++;
    	} else if((kill+1)%5==0) {
    		image = "bomberman145.png";
    		kill++;
    		killed();
    	}
    }
    
    /**
     * Kills the player by changing boolean alive to false.
     */
    public void killed() {
        alive = false;
    }

    /**
     * Decrements the health of the player depending on the attackDamage of the enemy.
     * The reason why we only decrement it when the timer is divisible by four is to avoid 
     * decreasing the player health too quickly.
     * If the health points is zero, we call killing() to animate death of player and kill it.
     * 
     * @param timer
     * 			gives the current time in milliseconds. Used for avoiding a fast health decrement
     */
    public void decrementHealth(long timer) {
    	if (hp <= 0) {
            killing();
        }
    	else if (timer%4==0) {
    		hp -= Enemy.attackDamage;
        	Main.visualBoard.gb_println("You have lost 20 points of health");
        	if (hp <= 0) {
                killing();
            }
    	}
    	
    }

}
