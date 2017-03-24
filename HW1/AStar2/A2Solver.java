package HW1.AStar2;

import HW1.Base.Debugger;
import HW1.Base.EightPuzzleNode;
import HW1.Base.PuzzleSolver;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by DLI on 1/13/17.
 */
public class A2Solver implements PuzzleSolver {
    @Override
    public List<EightPuzzleNode> solve(EightPuzzleNode root) {
        long numNodeExploded = 0;
        PriorityQueue<EightPuzzleNode> pq = new PriorityQueue<>();
        pq.offer(root);
        Set<EightPuzzleNode> visited = new HashSet<>();
        long maxNumNodes = 0;
        while (!pq.isEmpty()) {
            maxNumNodes = Math.max(maxNumNodes, pq.size());
            EightPuzzleNode node = pq.poll();
            numNodeExploded++;
            if (visited.contains(node)) {
                continue;
            }
            if (node.isGoal()){
                Debugger.print("Goal! Total node checked: " + numNodeExploded);
                System.out.println("Find goal state! Total node checked: " + numNodeExploded);
                System.out.println("Max number of nodes in the queue: " + maxNumNodes);
                return node.routeFromRootToHere();
            }
            visited.add(node);
            for (EightPuzzleNode child : node.successors()) {
                pq.offer(child);
            }
        }
        return null;
    }

    @Override
    public List<EightPuzzleNode> solve(int[][] initialBoard) {
        return solve(new A2PuzzleNode(initialBoard));
    }
}
