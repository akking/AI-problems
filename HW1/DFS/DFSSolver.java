package HW1.DFS;

import HW1.Base.Debugger;
import HW1.Base.EightPuzzleNode;
import HW1.Base.PuzzleSolver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Created by DLI on 1/13/17.
 */
public class DFSSolver implements PuzzleSolver {
    @Override
    public List<EightPuzzleNode> solve(EightPuzzleNode root) {
        Stack<EightPuzzleNode> stk = new Stack<>();
        stk.push(root);
        Set<EightPuzzleNode> visited = new HashSet<>();
        long numNodeExploded = 0;
        long maxNumNodes = 0;
        while (!stk.isEmpty()) {
            EightPuzzleNode node = stk.pop();
            maxNumNodes = Math.max(maxNumNodes, stk.size());
            numNodeExploded++;
            if (visited.contains(node)) {
                Debugger.print("duplicate");
                continue;
            }
            visited.add(node);
            if (node.isGoal()) {
                System.out.println("Total node exploded: " + numNodeExploded);
                System.out.println("Size of the queue at its max: " + maxNumNodes);
                return node.routeFromRootToHere();
            }
            for (EightPuzzleNode child : node.successors()) {
                Debugger.print("added new node");
                stk.push(child);
            }
        }
        return null;
    }

    @Override
    public List<EightPuzzleNode> solve(int[][] initialBoard) {
        return solve(new DFSPuzzleNode(initialBoard));
    }
}
