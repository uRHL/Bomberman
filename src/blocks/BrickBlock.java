package blocks;

import structures.Bonus;

/**
 * BrickBlock class extends the abstract class 'block'. All the blocks of this
 * type will have the same image and properties. Brick-blocks can randomly
 * contain a 'bonus' (Bonus type), that will appear once the block have been
 * destroyed.
 * 
 * Brick-blocks represent those blocks that cannot be walked at first, but can
 * be destroyed in order to open a new path.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December, 6, 2017
 * @version 1.1
 */
public class BrickBlock extends Block {
    /**
     * Bonus type field that storages, if it has, the bonus contained in the block
     */
    Bonus bonus;

    /**
     * Constructor. Initializes all the blocks with the same properties: same image,
     * walkable false (the player cannot walk through it), available false (bombs
     * cannot be placed on a block of this type), breakable true (bombs can destroy
     * this type of block). In addition, if the block contains a bonus is set
     * randomly
     * 
     */
    public BrickBlock() {
        image = "bricks.gif";
        setWalkable(false);
        setAvailable(false);
        setBreakable(true);
        if ((int) (Math.random() * 2) == 0) {
            bonus = new Bonus();
        }

    }

}
