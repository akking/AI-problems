package HW1.DFS;

import HW1.Base.EightPuzzleNode;

/**
 * Created by DLI on 1/13/17.
 */
public class DFSPuzzleNode extends EightPuzzleNode {
    public DFSPuzzleNode(int[][] initialBoard) {
        super(initialBoard);
    }

    public DFSPuzzleNode(int[][] board, int blankTileX, int blankTileY, EightPuzzleNode parent, int heuristicCost, int totalCostToHere, char fromDirection) {
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

    @Override
    public EightPuzzleNode createNewNode(int[][] board, int blankTileX, int blankTileY, int totalCostToHere, int heuristicCost, char fromDirection, EightPuzzleNode parent) {
        return new DFSPuzzleNode(board, blankTileX, blankTileY, parent, heuristicCost, totalCostToHere, fromDirection);
    }

    @Override
    public EightPuzzleNode createRootNode(int[][] board) {
        return new DFSPuzzleNode(board);
    }
}
