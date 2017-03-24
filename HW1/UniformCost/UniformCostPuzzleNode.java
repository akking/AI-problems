package HW1.UniformCost;

import HW1.AStar1.A1PuzzleNode;
import HW1.Base.Debugger;
import HW1.Base.EightPuzzleNode;

/**
 * Created by DLI on 1/13/17.
 */
public class UniformCostPuzzleNode extends A1PuzzleNode {

    public UniformCostPuzzleNode(int[][] initialBoard) {
        super(initialBoard);
    }

    public UniformCostPuzzleNode(int[][] board, int blankTileX, int blankTileY, EightPuzzleNode parent, int heuristicCost, int totalCostToHere, char fromDirection) {
        super(board, blankTileX, blankTileY, parent, heuristicCost, totalCostToHere, fromDirection);
    }

    @Override
    public int heuristicsCost(int x, int y, int[][] childBoard) {
        return 0;
    }

    @Override
    public EightPuzzleNode createNewNode(int[][] board, int blankTileX, int blankTileY, int totalCostToHere, int heuristicCost, char fromDirection, EightPuzzleNode parent) {
        Debugger.print("new uniform cost node");
        return new UniformCostPuzzleNode(board, blankTileX, blankTileY, parent, heuristicCost, totalCostToHere, fromDirection);
    }

    @Override
    public EightPuzzleNode createRootNode(int[][] board) {
        return new UniformCostPuzzleNode(board);
    }
}
