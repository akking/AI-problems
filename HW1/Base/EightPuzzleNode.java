package HW1.Base;

import java.util.*;

/**
 * Created by DLI on 1/13/17.
 * A template class represents a node in the search tree
 * Each method override the abstract methods
 */
abstract public class EightPuzzleNode implements Comparable<EightPuzzleNode> {
    public static final int[][] correctPosition = new int[][] {
            {1, 1}, {0, 0}, {0, 1}, {0, 2}, {1, 2}, {2, 2}, {2, 1}, {2, 0}, {1, 0}
    };
    public static final int[][] GOAL_BOARD = new int[][] {
            {1, 2, 3},
            {8, 0, 4},
            {7, 6, 5},
    };
    public int[][] board = null;
    public int blankTileX;
    public int blankTileY;
    public EightPuzzleNode parent = null;
    public int heuristicCost = 0;
    public int totalCostToHere = 0;
    public char fromDirection = '0'; // the blank tile came from which direction, '0' means root node

    public EightPuzzleNode(int[][] initialBoard) {
        this.board = process(initialBoard);
    }

    public EightPuzzleNode(int[][] board, int blankTileX, int blankTileY, EightPuzzleNode parent, int heuristicCost, int totalCostToHere, char fromDirection) {
        this.board = board;
        this.blankTileX = blankTileX;
        this.blankTileY = blankTileY;
        this.parent = parent;
        this.heuristicCost = heuristicCost;
        this.totalCostToHere = totalCostToHere;
        this.fromDirection = fromDirection;
    }

    private int[][] process(int[][] board) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (board[x][y] == 0) {
                    this.blankTileX = x;
                    this.blankTileY = y;
                }
            }
        }
        this.heuristicCost = heuristicsCost(this.blankTileX, this.blankTileY, board);
        return board;
    }

    public boolean isSameState(EightPuzzleNode that) {
        return isSameBoard(that.board);
    }

    private boolean isSameBoard(int[][] otherBoard) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (this.board[x][y] != otherBoard[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGoal() {
        return isSameBoard(GOAL_BOARD);
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof EightPuzzleNode)) {
            return false;
        }
        return this.isSameState((EightPuzzleNode)o);
    }

    @Override
    public int compareTo(EightPuzzleNode node) {
        return this.totalCost() - node.totalCost();
    }

    public int totalCost() {
        return this.totalCostToHere + this.heuristicCost;
    }

    @Override
    public int hashCode() {
        int rst = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rst += this.board[i][j] | i | j;
            }
        }
        return (rst * 37) << 2; // just made up
    }

    public List<EightPuzzleNode> routeFromRootToHere() {
        LinkedList<EightPuzzleNode> ret = new LinkedList<>();
        EightPuzzleNode node = this;
        while (node != null) {
            ret.addFirst(node);
            node = node.parent;
        }
        return ret;
    }


    public List<EightPuzzleNode> successors() {
        List<EightPuzzleNode> ret = null;
        for (char dir : Arrays.asList('U', 'D', 'L', 'R')) {
            int newX = this.blankTileX, newY = this.blankTileY;
            // find up, down, left, right child node
            switch (this.fromDirection) {
                // don't go back where comes from
                case 'U':
                    if (dir == 'D') {
                        continue;
                    }
                    break;
                case 'D':
                    if (dir == 'U') {
                        continue;
                    }
                    break;
                case 'L':
                    if (dir == 'R') {
                        continue;
                    }
                    break;
                case 'R':
                    if (dir == 'L') {
                        continue;
                    }
                    break;
            }
            switch (dir) {
                case 'U':
                    newX--;
                    break;
                case 'D':
                    newX++;
                    break;
                case 'L':
                    newY--;
                    break;
                case 'R':
                    newY++;
                    break;
            }
            int[][] childBoard = getNewNodeWithBlankTileMoveTo(newX, newY, this);
            if (childBoard == null) {
                continue;
            }
            int cost = totalCostToHere + costToMoveTo(newX, newY, this.board);
            int heuristicCost = heuristicsCost(newX, newY, childBoard);
            EightPuzzleNode newNode = createNewNode(childBoard, newX, newY, cost, heuristicCost, dir, this);
            if (ret == null) {
                ret = new ArrayList<>();
            }
            ret.add(newNode);
        }
        return ret;
    }

    public abstract int costToMoveTo(int x, int y, int[][] parentBoard);

    public abstract int heuristicsCost(int x, int y, int[][] childBoard);

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
    public abstract EightPuzzleNode createNewNode(int[][] board,
                                           int blankTileX,
                                           int blankTileY,
                                           int totalCostToHere,
                                           int heuristicCost,
                                           char fromDirection,
                                           EightPuzzleNode parent);

    public abstract EightPuzzleNode createRootNode(int[][] board);


    /**
     *
     * @param newX
     * @param newY
     * @param node
     * @return new int[][] board with blank tile moved to `newX` and `newY`.
     */
    private int[][] getNewNodeWithBlankTileMoveTo(int newX, int newY, EightPuzzleNode node) {
        if (newX < 0 || newX > 2 || newY < 0 || newY > 2) {
            return null;
        }
        int[][] newBoard = cloneBoard(this.board);
        int tmp = this.board[newX][newY];
        newBoard[newX][newY] = 0;
        newBoard[this.blankTileX][this.blankTileY] = tmp;
        return newBoard;
    }

    private static int[][] cloneBoard(int[][] ori) {
        int[][] ret = new int[ori.length][ori[0].length];
        for (int i = 0; i < ori.length; i++) {
            for (int j = 0; j < ori[0].length; j++) {
                ret[i][j] = ori[i][j];
            }
        }
        return ret;
    }


    public void printCurrentState() {
        System.out.println("Total cost from root node to here: " + this.totalCostToHere);
        System.out.println("Heuristic cost: " + this.heuristicCost);
        for (int x = 0; x < 3; x++) {
            System.out.print("|");
            for (int y = 0; y < 3; y++) {
                System.out.print(this.board[x][y]);
                System.out.print("|");
            }
            System.out.println();
        }
    }
}
