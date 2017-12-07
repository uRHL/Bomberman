package blocks;

/**
 * NormalBlock class extends the abstract class 'block'. All the blocks of this
 * type will have the same properties. The 'image' of this block is null because
 * this block is represented with a simple RGB color.
 * 
 * Normal-blocks are those composing the walkable paths.
 * 
 * @author Ramón Hernández León. Bachelor Degree in Computer Science. UC3M
 * @author Miguel Espinosa Miñano. Bachelor Degree in Computer Science. UC3M
 * @since December, 6, 2017
 * @version 1.1
 */
public class NormalBlock extends Block {
    /**
     * Values of the RGB color representing a 'NormalBlock'
     */
    public static final short red = 178, green = 255, blue = 102;

    /**
     * 
     * @return The decimal value (0-255) of the red color
     */
    public short getRed() {
        return red;
    }

    /**
     * 
     * @return The decimal value (0-255) of the green color
     */
    public short getGreen() {
        return green;
    }

    /**
     * 
     * @return The decimal value (0-255) of the blue color
     */
    public short getBlue() {
        return blue;
    }

    /**
     * Constructor. Initializes all 'NormalBlock' with the same properties: walkable
     * true (this blocks represents the walkable path), available true (bombs can be
     * placed only on this type of blocks), mined false (when created, the block
     * does not contain a bomb), breakable true (this blocks are not a obstacle,
     * however, they can be destroyed), image null ('NormalBlocks' are repsented by
     * a RGB color)
     */
    public NormalBlock() {
        setWalkable(true);
        setAvailable(true);
        setMined(false);
        setBreakable(true);
        image = null;
    }

}
