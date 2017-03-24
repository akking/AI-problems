package HW1.AStar1;

import HW1.AStar2.A2PuzzleNode;
import HW1.Base.Debugger;
import HW1.Base.EightPuzzleNode;

/**
 * Created by DLI on 1/13/17.
 */
public class A1PuzzleNode extends EightPuzzleNode {
    public A1PuzzleNode(int[][] initialBoard) {
        super(initialBoard);
    }

    public A1PuzzleNode(int[][] board, int blankTileX, int blankTileY, EightPuzzleNode parent, int heuristicCost, int totalCostToHere, char fromDirection) {
        super(board, blankTileX, blankTileY, parent, heuristicCost, totalCostToHere, fromDirection);
    }

    @Override
    public int costToMoveTo(int x, int y, int[][] parentBoard) {
        return parentBoard[x][y];
    }

    @Override
    public int heuristicsCost(int x, int y, int[][] childBoard) {
        return numTilesNotInPosition(childBoard);
    }

    private int numTilesNotInPosition(int[][] board) {
        int rst = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int n = board[x][y];
                if (correctPosition[n][0] != x || correctPosition[n][1] != y) {
                    rst++;
                }
            }
        }
        return rst;
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
        Debugger.print("new A1 node");
        return new A1PuzzleNode(board, blankTileX, blankTileY, parent, heuristicCost, totalCostToHere, fromDirection);
    }

    @Override
    public EightPuzzleNode createRootNode(int[][] board) {
        return new A1PuzzleNode(board);
    }
}
