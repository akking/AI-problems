package HW1.BFS;

import HW1.Base.EightPuzzleNode;

/**
 * Created by DLI on 1/13/17.
 */
public class BFSPuzzleNode extends EightPuzzleNode {
    public BFSPuzzleNode(int[][] initialBoard) {
        super(initialBoard);
    }

    public BFSPuzzleNode(int[][] board, int blankTileX, int blankTileY, EightPuzzleNode parent, int heuristicCost, int totalCostToHere, char fromDirection) {
        super(board, blankTileX, blankTileY, parent, heuristicCost, totalCostToHere, fromDirection);
    }

    @Override
    public int costToMoveTo(int x, int y, int[][] parentBoard) {
        return parentBoard[x][y];
    }

    @Override
    public int heuristicsCost(int x, int y, int[][] childBoard) {
        return 0;
    }

    /**
     * Class which extends this abstract class has its own implementation
     *
     * @param board
     * @param blankTileX
     * @param blankTileY
     * @param totalCostToHere
     * @param heuristicCost
     * @param fromDirection
     * @param parent
     * @return a new concrete subclass of this abstract class
     */
    @Override
    public EightPuzzleNode createNewNode(int[][] board, int blankTileX, int blankTileY, int totalCostToHere, int heuristicCost, char fromDirection, EightPuzzleNode parent) {
        return new BFSPuzzleNode(board, blankTileX, blankTileY, parent, heuristicCost, totalCostToHere, fromDirection);
    }

    @Override
    public EightPuzzleNode createRootNode(int[][] board) {
        return new BFSPuzzleNode(board);
    }
}
