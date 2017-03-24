package HW1.IterativeDeepening;

import HW1.Base.EightPuzzleNode;
import HW1.Base.PuzzleSolver;

import java.util.*;

/**
 * Created by DLI on 1/13/17.
 */
public class IDSolver implements PuzzleSolver{
    public static final int MAX_DEPTH = Integer.MAX_VALUE;
    private static long maxNumNodes = 0;
    private static long numNodesExploded = 0;
    @Override
    public List<EightPuzzleNode> solve(EightPuzzleNode root) {
        int depth = 0;
        while (depth <= MAX_DEPTH) {
            EightPuzzleNode node = DLS(depth, root);
            if (node != null) {
                return node.routeFromRootToHere();
            }
            depth++;
        }
        return null;
    }

    private static EightPuzzleNode DLS(int depth, EightPuzzleNode node) {
        if (depth < 0) {
            return null;
        }
        Queue<EightPuzzleNode> q = new ArrayDeque<>();
        Set<EightPuzzleNode> added = new HashSet<>();
        added.add(node);
        q.offer(node);
        while (depth != 0) {
            int levelSize = q.size();
            for (int i = 0; i < levelSize; i++) {
                for (EightPuzzleNode childNode : q.poll().successors()) {
                    if (!added.contains(childNode)) {
                        q.offer(childNode);
                        added.add(childNode);
                    }
                }
                maxNumNodes = Math.max(maxNumNodes, q.size());
            }
            depth--;
        }
        numNodesExploded += q.size();
        while (!q.isEmpty()) {
            EightPuzzleNode leafNode = q.poll();
            if (leafNode.isGoal()) {
                System.out.println("Goal found! Number of nodes exploded: " + numNodesExploded);
                System.out.println("Max number of nodes in the queue: " + maxNumNodes);
                return leafNode;
            }
        }
        return null;
    }

    @Override
    public List<EightPuzzleNode> solve(int[][] initialBoard) {
        return solve(new IDPuzzleNode(initialBoard));
    }
}
