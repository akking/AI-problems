package FinalProject;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by DLI on 3/2/17.
 */
public class CubeTest {
    Cube cube;

    @Before
    public void setUp(){
        cube = new Cube();
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

    @Test
    public void testIntegrate() throws Exception {
        char[][][] orginal = this.cube.state;
        int colIdx = 0;
        char[][][] shouldSame = Cube.turnUpCol(Cube.turnDownCol(orginal, colIdx), colIdx);
        assertTrue(checkTwoState(orginal, shouldSame));
    }

    private static boolean checkTwoState(char[][][] a, char[][][] b) {
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

    /**
     * each side should be white, green, orange, yellow, blue, red
     * @throws Exception
     */
    @Test
    public void testInitialization() throws Exception {
        char[][][] state = this.cube.state;
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
            assertTrue(checkColor(state[sideIdx], colorChar));
        }
    }

    private static boolean checkColor(char[][] side, char color) {
        for (int i = 0; i < side.length; i++) {
            for (int j = 0; j < side[i].length; j++) {
                if (side[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void turnRightRow() throws Exception {
        for (int rowIdx = 0; rowIdx < 3; rowIdx++) {
            this.cube = new Cube();
            char[][][] newState = Cube.turnRightRow(this.cube.state, rowIdx);
            for (int sideIdx = 0; sideIdx < newState.length; sideIdx++) {
                char colorChar;
                switch (sideIdx) {
                    case 0:
                        colorChar = 'g';
                        break;
                    case 1:
                        colorChar = 'r';
                        break;
                    case 2:
                        colorChar = 'o';
                        break;
                    case 3:
                        colorChar = 'w';
                        break;
                    case 4:
                        colorChar = 'b';
                        break;
                    default:
                        colorChar = 'y';
                }
                char[][] side = newState[sideIdx];
                for (char c : side[rowIdx]) {
                    assertTrue(c == colorChar);
                }
            }
        }
    }

    @Test
    public void turnLeftRow() throws Exception {
        Cube c = new Cube();
        for (int rowIdx = 0; rowIdx < 3; rowIdx++) {
            char[][][] state = Cube.turnLeftRow(c.state, rowIdx);
            Cube cc = new Cube();
            char[][][] anotherState = cc.state;
            for (int i = 0; i < 3; i++) {
                // right turn three times
                anotherState = Cube.turnRightRow(anotherState, rowIdx);
            }
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        assertTrue(state[i][j][j] == anotherState[i][j][k]);
                    }
                }
            }
        }
    }

    @Test
    public void turnUpCol() throws Exception {
        int colIdx = 0;
        this.cube = new Cube();
        char[][][] state = this.cube.state;
        state = Cube.turnUpCol(state, colIdx);
        for (int sideIdx = 0; sideIdx < 6; sideIdx++) {
            char colorChar;
            switch (sideIdx) {
                case 0:
                    colorChar = 'b';
                    break;
                case 1:
                    colorChar = 'g';
                    break;
                case 2:
                    colorChar = 'w';
                    break;
                case 3:
                    colorChar = 'y';
                    break;
                case 4:
                    colorChar = 'r';
                    break;
                default:
                    colorChar = 'o';
            }
            char[][] side = state[sideIdx];
            for (int i = 0; i < 3; i++) {
                assertTrue(side[i][colIdx] == colorChar);
            }
        }

    }

    @Test
    public void turnDownCol() throws Exception {

    }

}