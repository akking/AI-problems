package FinalProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by DLI on 3/2/17.
 */
public class Cube implements Comparable<Cube> {
    static final char[][][] goal = generateGoalState();
    char[][][] state = null;
    int costToHere;
    int heuristicCost = 0;


    public Cube() {
        this.state = new char[6][3][3];
        this.heuristicCost = 0;
        this.costToHere = 0;
        makeBrandNewCube();
    }

    private void makeBrandNewCube() {
        // paint each side of the cube the same color
        // white, green, orange, yellow, blue, red
        for (int sideIdx = 0; sideIdx < state.length; sideIdx++) {
            char colorChar;
            switch (sideIdx) {
                case 0:
                    colorChar = 'w';
                    break;
                case 1:
                    colorChar = 'g';
                    break;
                case 2:
                    colorChar = 'o';
                    break;
                case 3:
                    colorChar = 'y';
                    break;
                case 4:
                    colorChar = 'b';
                    break;
                default:
                    colorChar = 'r';
            }
            for (int rowIdx = 0; rowIdx < state[sideIdx].length; rowIdx++) {
                for (int colIdx = 0; colIdx < state[sideIdx][rowIdx].length; colIdx++) {
                    state[sideIdx][rowIdx][colIdx] = colorChar;
                }
            }
        }
    }

    public Cube(char[][][] state, int costToHere, int hCost) {
        this.state = state;
        this.costToHere = costToHere;
        this.heuristicCost = hCost;
    }

    public static void randomize(Cube cube) {
        Random rand = new Random();
        int randomTimes = 100;
        for (int i = 0; i < randomTimes; i++) {
            boolean vertical = rand.nextBoolean();
            if (vertical) {
                int colIdx = rand.nextInt(6);
                boolean up = rand.nextBoolean();
                if (up) {
                    cube.state = Cube.turnUpCol(cube.state, colIdx);
                } else {
                    cube.state = Cube.turnDownCol(cube.state, colIdx);
                }
            } else {
                int rowIdx = rand.nextInt(3);
                boolean right = rand.nextBoolean();
                if (right) {
                    cube.state = Cube.turnRightRow(cube.state, rowIdx);
                } else {
                    cube.state = Cube.turnLeftRow(cube.state, rowIdx);
                }
            }
        }
    }

    public static char[][][] turnUpCol(char[][][] cur, int colIdx) {
        char[][][] copy = deepCopyState(cur);
        char[][] side;
        if (colIdx < 3) {
            side = copy[0];
            char[] tmp = new char[3];
            for (int i = 0; i < 3; i++) {
                tmp[i] = side[i][colIdx];
            }
            char[][] downSide = copy[4];
            for (int i = 0; i < 3; i++) {
                side[i][colIdx] = downSide[i][colIdx];
            }
            char[][] next = copy[5];
            for (int i = 0; i < 3; i++) {
                downSide[2 - i][colIdx] = next[i][colIdx];
            }
            char[][] upSide = copy[2];
            for (int i = 0; i < 3; i++) {
                next[i][colIdx] = upSide[i][colIdx];
            }
            for (int i = 0; i < 3; i++) {
                upSide[2 - i][colIdx] = tmp[i];
            }
            return copy;
        }
        colIdx -= 3;
        side = copy[3];
        char[] tmp = new char[3];
        for (int i = 0; i < 3; i++) {
            tmp[i] = side[i][colIdx];
        }
        char[][] downSide = copy[4];
        for (int i = 0; i < 3; i++) {
            side[i][colIdx] = downSide[2 - i][colIdx];
        }
        char[][] next = copy[1];
        for (int i = 0; i < 3; i++) {
            downSide[i][colIdx] = next[i][colIdx];
        }
        char[][] upSide = copy[2];
        for (int i = 0; i < 3; i++) {
            next[i][colIdx] = upSide[i][2 - colIdx];
        }
        for (int i = 0; i < 3; i++) {
            upSide[i][colIdx] = tmp[i];
        }
        return copy;
    }

    private static char[][][] deepCopyState(char[][][] org) {
        char[][][] copy = new char[org.length][org[0].length][org[0][0].length];
        for (int i = 0; i < org.length; i++) {
            for (int j = 0; j < org[0].length; j++) {
                for (int k = 0; k < org[0][0].length; k++) {
                    copy[i][j][k] = org[i][j][k];
                }
            }
        }
        return copy;
    }

    public static char[][][] turnDownCol(char[][][] cur, int colIdx) {
        char[][][] copy = deepCopyState(cur);
        char[][] side;
        if (colIdx < 3) {
            side = copy[0];
            char[] tmp = new char[3];
            for (int i = 0; i < 3; i++) {
                tmp[i] = side[i][colIdx];
            }
            char[][] upSide = copy[2];
            for (int i = 0; i < 3; i++) {
                side[i][colIdx] = upSide[2 - i][colIdx];
            }
            char[][] next = copy[5];
            for (int i = 0; i < 3; i++) {
                upSide[i][colIdx] = next[i][colIdx];
            }
            char[][] downSide = copy[4];
            for (int i = 0; i < 3; i++) {
                next[i][colIdx] = downSide[2 - i][colIdx];
            }
            for (int i = 0; i < 3; i++) {
                downSide[i][colIdx] = tmp[i];
            }
            return copy;
        }
        colIdx -= 3;
        side = copy[3];
        char[] tmp = new char[3];
        for (int i = 0; i < 3; i++) {
            tmp[i] = side[i][colIdx];
        }
        char[][] upSide = copy[2];
        for (int i = 0; i < 3; i++) {
            side[i][colIdx] = upSide[i][colIdx];
        }
        char[][] next = copy[1];
        for (int i = 0; i < 3; i++) {
            upSide[i][colIdx] = next[2 - i][colIdx];
        }
        char[][] downSide = copy[4];
        for (int i = 0; i < 3; i++) {
            next[i][colIdx] = downSide[i][colIdx];
        }
        for (int i = 0; i < 3; i++) {
            downSide[i][colIdx] = tmp[2 - i];
        }
        return copy;
    }

    /**
     * turn the row in rowIdx with direction dir
     *
     * @param cur
     * @param rowIdx
     * @return
     */
    public static char[][][] turnRightRow(char[][][] cur, int rowIdx) {
        char[][][] copy = deepCopyState(cur);
        char[] row = copy[0][rowIdx];
        char[] tmp = Arrays.copyOf(row, row.length);
        char[] left = copy[1][rowIdx];
        for (int i = 0; i < row.length; i++) {
            row[i] = left[2 - i];
        }
        char[] leftLeft = copy[5][rowIdx];
        for (int i = 0; i < row.length; i++) {
            left[i] = leftLeft[i];
        }
        char[] right = copy[3][rowIdx];
        for (int i = 0; i < row.length; i++) {
            leftLeft[i] = right[2 - i];
        }
        for (int i = 0; i < row.length; i++) {
            right[i] = tmp[i];
        }
        return copy;
    }

    public static char[][][] turnLeftRow(char[][][] cur, int rowIdx) {
        char[][][] copy = deepCopyState(cur);
        char[] row = copy[0][rowIdx];
        char[] tmp = Arrays.copyOf(row, row.length);
        char[] right = copy[3][rowIdx];
        for (int i = 0; i < row.length; i++) {
            row[i] = right[i];
        }
        char[] rightRight = copy[5][rowIdx];
        for (int i = 0; i < row.length; i++) {
            right[i] = rightRight[2 - i];
        }
        char[] left = copy[1][rowIdx];
        for (int i = 0; i < row.length; i++) {
            rightRight[i] = left[i];
        }
        for (int i = 0; i < row.length; i++) {
            left[i] = tmp[2 - i];
        }
        return copy;
    }

    private static char[][][] generateGoalState() {
        char[][][] rst = new char[6][3][3];
        for (int sideIdx = 0; sideIdx < rst.length; sideIdx++) {
            char colorChar;
            switch (sideIdx) {
                case 0:
                    colorChar = 'w';
                    break;
                case 1:
                    colorChar = 'g';
                    break;
                case 2:
                    colorChar = 'o';
                    break;
                case 3:
                    colorChar = 'y';
                    break;
                case 4:
                    colorChar = 'b';
                    break;
                default:
                    colorChar = 'r';
            }
            for (int rowIdx = 0; rowIdx < rst[sideIdx].length; rowIdx++) {
                for (int colIdx = 0; colIdx < rst[sideIdx][rowIdx].length; colIdx++) {
                    rst[sideIdx][rowIdx][colIdx] = colorChar;
                }
            }
        }
        return rst;
    }

    public List<Cube> successors() {
        // generate successors of Cube cur
        List<Cube> rst = new ArrayList<>();
        char[][][] curState = this.state;
        // right
        for (int i = 0; i < 3; i++) {
            char[][][] newState = Cube.turnRightRow(curState, i);
            int hCost = Cube.calculateHeuristicCost(newState, goal);
            rst.add(new Cube(newState, this.costToHere + 1, hCost));
            newState = Cube.turnLeftRow(curState, i);
            hCost = Cube.calculateHeuristicCost(newState, goal);
            rst.add(new Cube(newState, this.costToHere + 1, hCost));
        }
        for (int i = 0; i < 6; i++) {
            char[][][] newState = Cube.turnDownCol(curState, i);
            int hCost = Cube.calculateHeuristicCost(newState, goal);
            rst.add(new Cube(newState, this.costToHere + 1, hCost));
            newState = Cube.turnUpCol(curState, i);
            hCost = Cube.calculateHeuristicCost(newState, goal);
            rst.add(new Cube(newState, this.costToHere + 1, hCost));
        }
        return rst;
    }

    private static int calculateHeuristicCost(char[][][] state, char[][][] goal) {
        int diff = 0;
        char[][][] curState = state;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (curState[i][j][k] != goal[i][j][k]) {
                        diff++;
                    }
                }
            }
        }
        return diff / 12;
    }

    public boolean isGoal() {
        char[][][] state = this.state;
        return isTwoStatesEqual(state, goal);
    }

    private static boolean isTwoStatesEqual(char[][][] a, char[][][] b) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (a[i][j][k] != b[i][j][k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        char[][][] state = this.state;
        int rst = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    rst += state[i][j][k] * i * j * k * 13;
                }
            }
        }
        return rst * 37;
    }

    @Override
    public boolean equals(Object o) {
        Cube thatCube = (Cube) o;
        return Cube.isTwoStatesEqual(this.state, thatCube.state);
    }

    @Override
    public int compareTo(Cube o) {
        return this.costToHere + this.heuristicCost - o.costToHere - o.heuristicCost;
    }
}
