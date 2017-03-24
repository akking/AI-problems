package HW1.Base;

import java.util.List;

/**
 * Created by DLI on 1/13/17.
 */
public interface PuzzleSolver {
    List<EightPuzzleNode> solve(EightPuzzleNode root);
    List<EightPuzzleNode> solve(int[][] initialBoard);
    static void printSteps(List<EightPuzzleNode> res) {
        int i = 0;
        for (EightPuzzleNode step : res) {
            System.out.println("Step: " + i++);
            step.printCurrentState();
            System.out.println();
        }
    }
}
