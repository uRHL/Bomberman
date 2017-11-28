
import java.util.Locale;

import edu.uc3m.game.GameBoardGUI;

/**
 * Example of a main class using the GameBoardGUI and showing some of the most
 * helpful methods it provides. For a detailed description of them and of many
 * others see the javadoc. Notice this example does not use objects other than a
 * GameBoardGUI one. For the final project a previous class design must be
 * performed.
 * 
 * @author Programming Professors. PLG-UC3M
 * @since November 2017
 * @version 1.0
 *
 */
public class Main {
	// See below for an explanation of throws InterruptedException
	public static void main(String[] args) throws InterruptedException {
		// This changes the local settings of the computer to English, so the
		// GUI is shown in English
		Locale.setDefault(new Locale("en"));
		// For the size of the board we create a constant		
		// Creating a GameBoardGUI object. It has a sole constructor receiving
		// the size
		GameBoardGUI visualBoard = new GameBoardGUI(Constants.BOARD_SIZE, Constants.BOARD_SIZE);
		// To make the board visible
		visualBoard.setVisible(true);
		// Showing how to change the color of cells. As an example we create a
		// chess board pattern
		Level l1=new Level();
		for (int ii = 0; ii < l1.board.length; ii++) {
			for (int jj = 0; jj < l1.board[ii].length; jj++) {
				
				
				if (l1.board[ii][jj].getImage()!=null) {// Then it will be a brick or a wall block
					visualBoard.gb_setSquareImage(ii, jj, l1.board[ii][jj].getImage());
				}else{
					visualBoard.gb_setSquareColor(ii, jj, 178, 255, 102);
				}				
			}
		}

		// Changing the player name in the GUI
		visualBoard.gb_setTextPlayerName("Testing GUI");
		// Adding a sprite to the board, it is done in three steps
		// 1) adding it. Parameters are a unique id, the file containing the
		// image and true if we want it to be on top of any other at the same
		// position
		Player p1= new Player();
		visualBoard.gb_addSprite(0, p1.image, true);
		// We will store its coordinates here		
		// 2) placing the sprite at a board position. Parameters are the sprite
		// id, the x and the y
		visualBoard.gb_moveSprite(0, p1.xPos, p1.yPos);
		// 3) making the sprite visible. Parameters are the id and true to show
		// it or false to hide it
		visualBoard.gb_setSpriteVisible(0, true);
		// Main game loop. This will be executing until we finish. As an example
		// we use an infinite loop
		while (true) {
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

				p1.move(lastAction, l1);
				visualBoard.gb_moveSprite(0, p1.xPos, p1.yPos);
			}

			/*
			 * This makes the program to pause for 50 milliseconds. If not this
			 * loop will run so fast that the pressed keys will be lost.
			 * Invoking the sleep() method of the Thread class can produce an
			 * exception (an error). This can happen so often that Java forces
			 * us to either explicitly say that we don´t care or to handle it.
			 * The "throws InterruptedException" in the header of the main
			 * method is the way to say that we don´t care. If the error appears
			 * the program will stop. A better solution would have been to
			 * handle the exception using a try-catch sentence. But it is out of
			 * the scope of this course.
			 */
			Thread.sleep(50L);
		}
	}

}
