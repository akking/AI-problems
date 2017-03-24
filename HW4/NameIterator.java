package HW4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by DLI on 2/23/17.
 */
public class NameIterator implements Iterator<String> {
    Scanner sc = null;
    static final String boyFileName = "namesBoys" + ".txt";
    static final String girlFileName = "namesGirls" + ".txt";

    public NameIterator(int babySex) {
        try {
            this.sc = new Scanner(new BufferedReader(
                    new FileReader(
                            "/Users/DLI/Downloads/" + (babySex == 0 ? girlFileName : boyFileName))));
        } catch (Exception e) {
            // ignore
            System.out.println("exception: " + e);
        }
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        if (sc == null) {
            return false;
        }
        return sc.hasNextLine();
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public String next() {
        if (!sc.hasNextLine()) {
            throw new NoSuchElementException();
        }
        return sc.nextLine();
    }
}
