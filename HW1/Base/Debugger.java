package HW1.Base;

/**
 * Created by DLI on 1/13/17.
 */
public class Debugger {
    public static boolean on = true;
    public static void print(String s) {
        if (on) System.out.println(s);
    }
}
