package HW1.AStar1;

import HW1.AStar2.A2PuzzleNode;
import HW1.AStar2.A2Solver;
import HW1.Base.EightPuzzleNode;
import HW1.Base.PuzzleSolver;

import java.util.List;

/**
 * Created by DLI on 1/13/17.
 */
public class A1Solver extends A2Solver{

    @Override
    public List<EightPuzzleNode> solve(int[][] initialBoard) {
        return solve(new A1PuzzleNode(initialBoard));
    }
}
