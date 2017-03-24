package HW1.BFS;

import HW1.Base.Debugger;
import HW1.Base.EightPuzzleNode;
import HW1.Base.PuzzleSolver;

import java.util.*;

/**
 * Created by DLI on 1/13/17.
 */
public class BFSSolver implements PuzzleSolver {
    @Override
    public List<EightPuzzleNode> solve(EightPuzzleNode root) {
        Queue<EightPuzzleNode> q = new ArrayDeque<>();
        q.offer(root);
        Set<EightPuzzleNode> visited = new HashSet<>();
        long numNodesExploded = 0;
        long maxNodes = 0;
        while (!q.isEmpty()) {
            EightPuzzleNode node = q.poll();
            maxNodes = Math.max(maxNodes, q.size());
            numNodesExploded++;
            if (visited.contains(node)) {
                continue;
            }
            if (node.isGoal()) {
                System.out.println("Goad found! Number of nodes popped off the queue: " + numNodesExploded);
                System.out.println("Max number of nodes in the queue: " + maxNodes);
                return node.routeFromRootToHere();
            }
            visited.add(node);
            for (EightPuzzleNode childNode : node.successors()) {
                Debugger.print("add new child node");
                q.offer(childNode);
            }
        }
        return null;
    }

    @Override
    public List<EightPuzzleNode> solve(int[][] initialBoard) {
        return solve(new BFSPuzzleNode(initialBoard));
    }
}
