package HW2.SimacogoBoard;

import static HW2.Util.Utilities.*;

/**
 * Created by DLI on 1/25/17.
 * A space wasting but straightforward implementation of SimacogoBoard
 */
public class SimacogoBoardImpl implements SimacogoBoard {

    int[][] board = new int[9][10];
    /**
     * row is the column of the board, imagine the matrix is rotated 90 degree counter-clock wise
     * board[i][9] is the empty slot index for this col index i, so i >= 0 && i < 9
     */

    @Override
    public boolean dropPlayerDiscIntoColumn(int colNumber) {
        if (colNumber > 8 || colNumber < 0) {
            return false;
        }
        if (board[colNumber][9] > 8) {
            return false;
        }
        int slotIdx = board[colNumber][9];
        board[colNumber][slotIdx] = PLAYER;
        board[colNumber][9]++;
        return true;
    }

    @Override
    public boolean dropOpponentDiscIntoColumn(int colNumber) {
        if (colNumber > 8 || colNumber < 0) {
            return false;
        }
        if (board[colNumber][9] > 8) {
            return false;
        }
        int slotIdx = board[colNumber][9];
        board[colNumber][slotIdx] = OPPONENT;
        board[colNumber][9]++;
        return true;
    }

    /**
     * @return score relative to play, eg: positive means player has advantage, negative means opponent has advantage
     * zero means even.
     * Rule:
     * 2 points for every pair of pieces next to each other (up, down, left, right)
     * 1 point for every pair of pieces diagonally next to each other.
     */
    @Override
    public int evaluateBoard() {
        int playerScore = 0;
        int opponentScore = 0;

        for (int colIdx = 0; colIdx < 9; colIdx++) {
            int rowNum = board[colIdx][9];
            for (int rowIdx = 0; rowIdx < rowNum; rowIdx++) {
                int who = board[colIdx][rowIdx];
                if (who == PLAYER) {
                    playerScore += calculateScore(PLAYER, board, colIdx, rowIdx);
                } else {
                    opponentScore += calculateScore(OPPONENT, board, colIdx, rowIdx);
                }
            }
        }
        int diff = playerScore - opponentScore;
        return diff;
    }

    /**
     * Replay the step from `rootBoard`
     *
     * @param steps
     * @return a new board go through steps from rootBoard
     */
    @Override
    public int replayAndGetScore(Iterable<Integer> steps) {
        SimacogoBoardImpl boardCopy = new SimacogoBoardImpl();
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                boardCopy.board[i][j] = this.board[i][j];
            }
        }
        int whoIsPlay = PLAYER;
        for (int step : steps) {
            if (whoIsPlay == PLAYER) {
                boardCopy.dropPlayerDiscIntoColumn(step);
                whoIsPlay = OPPONENT;
            } else {
                boardCopy.dropOpponentDiscIntoColumn(step);
                whoIsPlay = PLAYER;
            }
        }
        return boardCopy.evaluateBoard();
    }

    @Override
    public void prettyPrintBoard() {
        for (int col = 8; col >= 0; col--) {
            for (int row = 0; row < 9; row++) {
                String s = ".";
                int num = this.board[row][col];
                if (num == PLAYER) {
                    s = "o";
                } else if (num == OPPONENT) {
                    s = "x";
                }
                System.out.print(s);
            }
            System.out.println();
        }
        for (int i = 1; i < 10; i++) {
            System.out.print(i);
        }
        System.out.println();
    }

    @Override
    public boolean isSlotAvailableToDrop(int slotIdx) {
        return this.board[slotIdx][9] < 9;
    }

    @Override
    public int[] getAvailableSlotArray() {
        int[] limit = new int[9];
        for (int i = 0; i < 9; i++) {
            limit[i] = this.board[i][9];
        }
        return limit;
    }

    private static int calculateScore(int who, int[][] board, int colIdx, int rowIdx) {
        int score = 0;
        // check upper direction
        if (rowIdx + 1 < 9) {
            int disc = board[colIdx][rowIdx + 1];
            if (disc == who) {
                score += 2;
            }
        }
        // check right direction
        if (colIdx + 1 < 9) {
            int disc = board[colIdx + 1][rowIdx];
            if (disc == who) {
                score += 2;
            }
        }

        // check upper right direction
        if (rowIdx + 1 < 9 && colIdx + 1 < 9) {
            int disc = board[colIdx + 1][rowIdx + 1];
            if (disc == who) {
                score += 1;
            }
        }

        // check down right direction
        if (rowIdx - 1 >= 0 && colIdx + 1 < 9) {
            int disc = board[colIdx + 1][rowIdx - 1];
            if (disc == who) {
                score += 1;
            }
        }
        return score;
    }
}
