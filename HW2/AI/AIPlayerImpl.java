package HW2.AI;

import HW2.SimacogoBoard.SimacogoBoard;
import HW2.SimacogoNode.Node;
import HW2.SimacogoNode.RootNode;

/**
 * Created by DLI on 1/25/17.
 */
public class AIPlayerImpl implements AIPlayer {
    public static int ply = 4;

    @Override
    public void setDifficulty(int difficulty) {
        this.ply = difficulty;
    }

    @Override
    public int makeNextMove(SimacogoBoard board) {
        Node root = new RootNode(board);
        root.alphabeta(Integer.MIN_VALUE, Integer.MAX_VALUE);
        // uncomment below if you want minimax without alphabeta pruning
        // root.minimax();
        return root.minimaxDirection;
    }
}
