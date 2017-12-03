
public class Level {
	Block[][] board = new Block[Constants.BOARD_SIZE][Constants.BOARD_SIZE];

	{
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				/*
				 * All the blocks situated in the frame of the board or at an
				 * even index, will be wall blocks
				 */

				if (i == 0 || i == Constants.BOARD_SIZE - 1 || j == 0 || j == Constants.BOARD_SIZE - 1
						|| (i % 2 == 0 && j % 2 == 0)) {
					board[i][j] = new WallBlock();

				} else if ((i > 2 && j > 2) && ((int) (Math.random() * 2) == 0)) {
					board[i][j] = new BrickBlock();

				} else {
					board[i][j] = new NormalBlock();
				}
			}

		}

	}

	public Level() {

	}
}