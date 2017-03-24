package HW2.SimacogoNode;

import HW2.SimacogoBoard.SimacogoBoard;

/**
 * Created by DLI on 1/25/17.
 * Only rootNode has a board, others only have steps from root to leaves, to save space
 */
public class RootNode extends Node {
    public SimacogoBoard board;
    public RootNode(SimacogoBoard board) {
        super(board);
        this.board = board;
    }
}
