package HW2.test;

import HW2.SimacogoBoard.SimacogoBoard;
import HW2.SimacogoBoard.SimacogoBoardImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static HW2.Util.Utilities.PLAYER;
import static org.junit.Assert.*;

/**
 * Created by DLI on 1/25/17.
 */
public class SimacogoBoardImplTest {
    SimacogoBoard board = null;
    @Before
    public void setUp() throws Exception {
        board = new SimacogoBoardImpl();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void dropPlayerDiscIntoColumn() throws Exception {
        for (int i = 0; i < 9; i++) {
            assertTrue(board.dropPlayerDiscIntoColumn(0));
        }
        assertFalse(board.dropPlayerDiscIntoColumn(0));
        assertFalse(board.dropOpponentDiscIntoColumn(0));
        assertTrue(board.dropOpponentDiscIntoColumn(1));
    }

    @Test
    public void dropOpponentDiscIntoColumn() throws Exception {

    }

    @Test
    public void evaluateBoard() throws Exception {
        assertEquals(0, board.evaluateBoard());
        for (int i = 0; i < 9; i++) {
            assertTrue(board.dropPlayerDiscIntoColumn(0));
        }
        assertEquals(16, board.evaluateBoard());
        setUp();
        assertEquals(0, board.evaluateBoard());
        board.dropPlayerDiscIntoColumn(1);
        board.dropPlayerDiscIntoColumn(2);
        board.dropPlayerDiscIntoColumn(3);
        board.dropPlayerDiscIntoColumn(2);
        assertEquals(8, board.evaluateBoard());
        board.dropOpponentDiscIntoColumn(4);
        board.dropOpponentDiscIntoColumn(4);
        board.dropOpponentDiscIntoColumn(3);
        board.dropOpponentDiscIntoColumn(3);
        board.dropOpponentDiscIntoColumn(2);
        board.dropPlayerDiscIntoColumn(4);
        assertEquals(-3, board.evaluateBoard());
        setUp();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.dropPlayerDiscIntoColumn(i);
            }
        }
        for (int i = 0; i < 9; i++) {
            assertFalse(board.dropOpponentDiscIntoColumn(i));
        }
        for (int i = 0; i < 9; i++) {
            assertFalse(board.dropPlayerDiscIntoColumn(i));
        }
        System.out.println(board.evaluateBoard());


    }

}