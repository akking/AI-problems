package HW1.AStar3;

import HW1.AStar2.A2PuzzleNode;
import HW1.AStar2.A2Solver;
import HW1.Base.EightPuzzleNode;

import java.util.List;

/**
 * Created by DLI on 1/13/17.
 */
public class A3Solver extends A2Solver{
    @Override
    public List<EightPuzzleNode> solve(int[][] initialBoard) {
        return solve(new A3PuzzleNode(initialBoard));
    }
}
