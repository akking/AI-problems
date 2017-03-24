package HW2.SimacogoNode;

import HW2.AI.AIPlayerImpl;
import HW2.SimacogoBoard.SimacogoBoard;

import java.util.*;

import static HW2.SimacogoNode.NodeType.MAXNODE;
import static HW2.SimacogoNode.NodeType.MINNODE;

/**
 * Created by DLI on 1/25/17.
 */
public class Node {
    public int bestScore;
    NodeType nodeType;
    Node parent;
    int slotIdx;
    int[] limit;
    public int minimaxDirection;
    int depth;

    // constructor for root node
    public Node(SimacogoBoard board) {
        nodeType = MAXNODE;
        bestScore = Integer.MIN_VALUE;
        limit = board.getAvailableSlotArray();
        parent = null;
        depth = 0;
    }


    public Node(NodeType nodeType, Node parent, int slotIdx, int[] limit, int depth) {
        this.nodeType = nodeType;
        bestScore = nodeType == MAXNODE ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        this.parent = parent;
        this.slotIdx = slotIdx;
        this.limit = limit;
        this.depth = depth;
    }

    public int alphabeta(int alpha, int beta) {
        if (this.depth == AIPlayerImpl.ply) {
            return this.getLeafScore();
        }
        int v = nodeType == MAXNODE ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (Node child : this.successors()) {
            int bestVal = child.alphabeta(alpha, beta);
            if (nodeType == MAXNODE) {
                if (bestVal > v) {
                    v = bestVal;
                    this.minimaxDirection = child.slotIdx;
                }
                if (v >= beta) {
                    return v;
                }
                alpha = Math.max(alpha, v);
            } else {
                if (bestVal < v) {
                    v = bestVal;
                    this.minimaxDirection = child.slotIdx;
                }
                if (v <= alpha) {
                    return v;
                }
                beta = Math.min(beta, v);
            }
        }
        return v;
    }


    public int minimax() {
        if (this.depth == AIPlayerImpl.ply) {
            return this.getLeafScore();
        }
        int val = nodeType == MAXNODE ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (Node child : successors()) {
            int childMinimax = child.minimax();
            if (nodeType == MAXNODE) {
                val = Math.max(val, childMinimax);
                if (val == childMinimax) {
                    this.minimaxDirection = child.slotIdx;
                }
            } else {
                val = Math.min(val, childMinimax);
            }
        }
        this.bestScore = val;
        return val;
    }

    private int getLeafScore() {
        Deque<Integer> steps = new ArrayDeque<>();
        Node ptr = this;
        while (ptr.parent != null) {
            steps.addFirst(ptr.slotIdx);
            ptr = ptr.parent;
        }
        if (ptr instanceof RootNode) {
            int score =  ((RootNode)ptr).board.replayAndGetScore(steps);
            return score;
        } else { // should not happen
            return 0;
        }
    }

    private List<Node> successors() {
        List<Node> rst = new ArrayList<>();
        NodeType childType = nodeType == MAXNODE ? MINNODE : MAXNODE;
        for (int i = 0; i < 9; i++) {
            if (limit[i] < 9) {
                int[] newLimit = Arrays.copyOf(limit, limit.length);
                newLimit[i] += 1;
                Node child = new Node(childType, this, i, newLimit, this.depth + 1);
                rst.add(child);
            }
        }
        return rst;
    }

}
