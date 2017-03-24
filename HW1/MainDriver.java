package HW1;

import HW1.AStar1.A1Solver;
import HW1.AStar2.A2Solver;
import HW1.AStar3.A3Solver;
import HW1.BFS.BFSSolver;
import HW1.Base.Debugger;
import HW1.Base.EightPuzzleNode;
import HW1.Base.PuzzleSolver;
import HW1.BestFirst.BestFirstSolver;
import HW1.DFS.DFSSolver;
import HW1.IterativeDeepening.IDSolver;
import HW1.UniformCost.UniformCostSolver;

import java.util.List;
import java.util.Scanner;

/**
 * Created by DLI on 1/13/17.
 */
public class MainDriver {
    public static void main(String[] args) {
        //1 3 4 8 6 2 7 0 5
        int[][] easyBoard = new int[][]{
                {1, 3, 4},
                {8, 6, 2},
                {7, 0, 5}
        };
        int[][] midBoard = new int[][]{
                {2, 8, 1},
                {0, 4, 3},
                {7, 6, 5}
        };
        int[][] hardBoard = new int[][]{
                {5, 6, 7},
                {4, 0, 8},
                {3, 2, 1}
        };
        Debugger.on = false;
        System.out.println("Type search method and board, eg: DFS hard");
        Scanner scanner = new Scanner(System.in);
        String[] tokens = scanner.nextLine().split(" ");
        if (tokens.length != 2) {
            throw new IllegalArgumentException();
        }
        String method = tokens[0];
        String board = tokens[1];
        PuzzleSolver solver = null;
        switch (method) {
            case "DFS":
                solver = new DFSSolver();
                break;
            case "BFS":
                solver = new BFSSolver();
                break;
            case "Best":
                solver = new BestFirstSolver();
                break;
            case "IDS":
                solver = new IDSolver();
                break;
            case "UC":
                solver = new UniformCostSolver();
                break;
            case "A1":
                solver = new A1Solver();
                break;
            case "A2":
                solver = new A2Solver();
                break;
            case "A3":
                solver = new A3Solver();
                break;
        }
        if (solver == null) {
            throw new IllegalArgumentException();
        }
        int[][] b = null;
        switch (board) {
            case "mid":
                b = midBoard;
                break;
            case "hard":
                b = hardBoard;
                break;
            case "easy":
                b = easyBoard;
                break;
        }
        if (b == null) {
            throw new IllegalArgumentException();
        }
        List<EightPuzzleNode> rst = solver.solve(b);
        if (rst != null) {
            PuzzleSolver.printSteps(rst);
        } else {
            System.out.println("no answer");
        }
    }
}
