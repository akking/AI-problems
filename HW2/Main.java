package HW2;

import HW2.AI.AIPlayer;
import HW2.AI.AIPlayerImpl;
import HW2.SimacogoBoard.SimacogoBoard;
import HW2.SimacogoBoard.SimacogoBoardImpl;

import java.util.Scanner;

/**
 * Created by DLI on 1/25/17.
 */
public class Main {
    public static void main(String[] args) {
        SimacogoBoard board = new SimacogoBoardImpl();
        AIPlayer ai = new AIPlayerImpl();
        ai.setDifficulty(10); // number of plys
        System.out.println("New game begins");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Please type in the column number to drop the disc, eg: 1 to drop disk into column one");
            int colNum;
            try {
                colNum = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number from 1 to 9");
                colNum = Integer.parseInt(scanner.nextLine()) - 1;
            }
            board.dropPlayerDiscIntoColumn(colNum);
            board.prettyPrintBoard();
            System.out.println("Your opponent is thinking...");
            board.dropOpponentDiscIntoColumn(ai.makeNextMove(board));
            board.prettyPrintBoard();
            System.out.println("Current score for you: " + board.evaluateBoard());
        }
    }
}
