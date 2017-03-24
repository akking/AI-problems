package HW1.AStar2;

import HW1.Base.EightPuzzleNode;

/**
 * Created by DLI on 1/13/17.
 */
public class A2PuzzleNode extends EightPuzzleNode{

    public A2PuzzleNode(int[][] board) {
        super(board);
    }

    public A2PuzzleNode(int[][] board, int blankTileX, int blankTileY, EightPuzzleNode parent, int heuristicCost, int totalCostToHere, char fromDirection) {
        super(board, blankTileX, blankTileY, parent, heuristicCost, totalCostToHere, fromDirection);
    }

    public int costToMoveTo(int x, int y, int[][] oldBoard) {
        // "The cost of a move is the VALUE OF THE TILE BEING MOVED"
        return board[x][y];
    }

    public int heuristicsCost(int x, int y, int[][] board) {
        // Manhattan Distance
        int dis = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int n = board[i][j];
                dis += Math.abs(correctPosition[n][0] - i) + Math.abs(correctPosition[n][1] - j);
            }
        }
        return dis;
    }

    /**
     * Class which extends this abstract class has its own implementation
     * @param board
     * @param blankTileX
     * @param blankTileY
     * @param totalCostToHere
     * @param heuristicCost
     * @param fromDirection
     * @param parent
     * @return a new concrete subclass of this abstract class
     */
    public EightPuzzleNode createNewNode(int[][] board,
                                           int blankTileX,
                                           int blankTileY,
                                           int totalCostToHere,
                                           int heuristicCost,
                                           char fromDirection,
                                           EightPuzzleNode parent) {
        return new A2PuzzleNode(board, blankTileX,blankTileY, parent,heuristicCost, totalCostToHere, fromDirection);
    }

    @Override
    public EightPuzzleNode createRootNode(int[][] board) {
        return new A2PuzzleNode(board);
    }


}
