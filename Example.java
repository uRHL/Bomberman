import java.util.Locale;

import edu.uc3m.game.GameBoardGUI;

public class Example {
	public static void main(String[] args) throws InterruptedException {
		Locale.setDefault(new Locale("en"));
		final int BOARD_WIDTH = 10;
		final int BOARD_HEIGHT = 10;
		int x1 = 0, y1 = 0;
		GameBoardGUI board = new GameBoardGUI(BOARD_WIDTH, BOARD_HEIGHT);
		/*
		 * Interesting methods: set or gb
		 */
		board.setVisible(true);

		// All the interactions inside the while method
		for (int i = 0; i < BOARD_WIDTH; i++) {
			for (int j = 0; j < BOARD_HEIGHT; j++) {
				if ((i + j) % 2 == 0) {
					board.gb_setSquareColor(i, j, 100, 100, 100);
				} else {
					board.gb_setSquareColor(i, j, 230, 230, 230);
				}
			}
		}
		
		board.gb_setTextPlayerName("Eustaquio");
		board.gb_println("Meme:)");

		// id starts at 1
		board.gb_addSprite(1, "bomberman112.png", true);		
		board.gb_moveSprite(1, x1, y1);
		board.gb_setSpriteVisible(1, true);
		
		board.gb_addSprite(2, "enemy112.png", true);
		board.gb_moveSprite(2, 5, 5);
        board.gb_setSpriteVisible(2, true);
        

		while (true) {
			String lastAction = board.gb_getLastAction();
			if (lastAction.length() > 0) {
				board.gb_println(lastAction);
				if (lastAction.equals("right")) {
					board.gb_moveSprite(1, ++x1, y1);
				} else if (lastAction.equals("down")) {
					board.gb_moveSprite(1, x1, ++y1);
				}
			}
			Thread.sleep(50L);
		}

	}
}
