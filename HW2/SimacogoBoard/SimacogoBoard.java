package HW2.SimacogoBoard;

/**
 *  interface represents the board
 */
public interface SimacogoBoard {
    boolean dropPlayerDiscIntoColumn(int colNumber);
    boolean dropOpponentDiscIntoColumn(int colNumber);

    /**
     *
     * @return score relative to play, eg: positive means player has advantage, negative means opponent has advantage
     * zero means even.
     * Rule:
     * 2 points for every pair of pieces next to each other (up, down, left, right)
     * 1 point for every pair of pieces diagonally next to each other.
     */
    int evaluateBoard();

    /**
     * Replay the step from `rootBoard`
     * @param steps
     * @return the board go through steps from rootBoard
     */
    int replayAndGetScore(Iterable<Integer> steps);
    void prettyPrintBoard();
    boolean isSlotAvailableToDrop(int slotIdx);
    int[] getAvailableSlotArray();
}
