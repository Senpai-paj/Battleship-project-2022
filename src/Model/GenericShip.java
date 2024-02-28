package Model;

/**
 * @author Johannes Rosengren Systemutvecklare HT22 (datorID: an6380)
 * @author Victor Pirojoc Systemutvecklare HT22 (datorID: an4721)
 *
 * Should be implemented by ships wanting to participate in the game-board
 */
public interface GenericShip {

    /**
     * Logic of what happens when a ship is hit
     */
    void hit();

    /**
     * Returns whether a ship has been sunk
     * @return true if sunk/ false if not sunk
     */
    boolean isSunk();

    /**
     * Returns the shipType of the ship
     * @return shipType of the ship
     */
    ShipType getType();

}
