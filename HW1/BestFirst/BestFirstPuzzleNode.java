package HW1.BestFirst;

import HW1.AStar1.A1PuzzleNode;
import HW1.Base.Debugger;
import HW1.Base.EightPuzzleNode;

/**
 * Created by DLI on 1/13/17.
 */
public class BestFirstPuzzleNode extends A1PuzzleNode {
    public BestFirstPuzzleNode(int[][] initialBoard) {
        super(initialBoard);
    }

    public BestFirstPuzzleNode(int[][] board, int blankTileX, int blankTileY, EightPuzzleNode parent, int heuristicCost, int totalCostToHere, char fromDirection) {
        super(board, blankTileX, blankTileY, parent, heuristicCost, totalCostToHere, fromDirection);
    }

    @Override
    public int totalCost() {
        return this.heuristicCost;
    }

    @Override
    public EightPuzzleNode createNewNode(int[][] board, int blankTileX, int blankTileY, int totalCostToHere, int heuristicCost, char fromDirection, EightPuzzleNode parent) {
        Debugger.print("new Best First Puzzle node");
        return new BestFirstPuzzleNode(board, blankTileX, blankTileY, parent, heuristicCost, totalCostToHere, fromDirection);
    }

    @Override
    public EightPuzzleNode createRootNode(int[][] board) {
        return new BestFirstPuzzleNode(board);
    }
}
