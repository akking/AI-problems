package FinalProject;

import java.util.*;

/**
 * Created by DLI on 3/2/17.
 */
public class Solver {
    public static void main(String[] args) {
        Cube cube = new Cube();
        Cube.randomize(cube);
        solve(cube);
    }

    public static void solve(Cube cube) {
        Queue<Cube> q = new PriorityQueue<>();
        Set<Cube> visited = new HashSet<>();
        q.offer(cube);
        while (!q.isEmpty()) {
            Cube c = q.poll();
            if(!visited.add(c)) {
                continue;
            }
            if (c.isGoal()) {
                System.out.println("Get goal!");
                break;
            }
            q.addAll(c.successors());
        }
    }
}
