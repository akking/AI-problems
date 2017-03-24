package HW2.AI;

import HW2.SimacogoBoard.SimacogoBoard;

/**
 * The AI to play Simacogo
 */
public interface AIPlayer {
    void setDifficulty(int difficulty);
    int makeNextMove(SimacogoBoard board);
}
