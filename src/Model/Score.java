package Model;

/**
 * @author Johannes Rosengren Systemutvecklare HT22 (datorID: an6380)
 * @author Victor Pirojoc Systemutvecklare HT22 (datorID: an4721)
 *
 * This class is responsible for keeping track of the score of one entry
 */
public class Score {
    private final int movesRequiredToWin;
    private final String name;

    /**
     * Constructor of Score
     * @param movesRequiredToWin the number of moves that was required to win the game
     * @param name the name of the person of this entry
     */
    public Score(int movesRequiredToWin, String name) {
        this.movesRequiredToWin = movesRequiredToWin;
        this.name = name;
    }

    /**
     * @return the moves required to win
     */
    public int getMovesRequiredToWin() {
        return movesRequiredToWin;
    }

    /**
     * @return returns the associated name of the entry
     */
    public String getName() {
        return name;
    }

    /**
     * @return returns a string representation of the Score class.
     */
    @Override
    public String toString() {
        return String.format("%s: %d",name,movesRequiredToWin);
    }

}
