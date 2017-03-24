package HW1.BestFirst;

import HW1.AStar1.A1PuzzleNode;
import HW1.AStar1.A1Solver;
import HW1.Base.EightPuzzleNode;

import java.util.List;

/**
 * Created by DLI on 1/13/17.
 */
public class BestFirstSolver extends A1Solver{

    @Override
    public List<EightPuzzleNode> solve(int[][] initialBoard) {
        return solve(new BestFirstPuzzleNode(initialBoard));
    }

}
