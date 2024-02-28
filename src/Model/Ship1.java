package Model;

/**
 * @author Johannes Rosengren Systemutvecklare HT22 (datorID: an6380)
 * @author Victor Pirojoc Systemutvecklare HT22 (datorID: an4721)
 *
 * Implementation of GenericShip
 */
public class Ship1 implements GenericShip {
    private final ShipType shipType = ShipType.U_bat;
    private int parts = 1;

    @Override
    public void hit() {
        parts--;
    }

    @Override
    public boolean isSunk() {
        return parts < 1;
    }

    @Override
    public ShipType getType() {
        return shipType;
    }

}