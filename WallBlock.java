package blocks;

/**
 * WallBlock class extends the abstract class 'block'. All the blocks of this
 * type will have the same image and properties.
 * 
 * Wall-blocks are those composing the walls of the board.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December, 6, 2017
 * @version 1.1
 */
public class WallBlock extends Block {
    /**
     * Constructor. Initializes all Wall-Blocks with the same properties: same
     * image, walkable false (player cannot go through the walls), available false
     * (no bombs can be placed on the walls), breakable false (the explosion of a
     * bomb will not damage Wall-blocks).
     */
    public WallBlock() {
        image = "wall.gif";
        setWalkable(false);
        setAvailable(false);
        setBreakable(false);
    }
}
