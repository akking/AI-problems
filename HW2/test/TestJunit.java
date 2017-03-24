package HW2.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by DLI on 1/25/17.
 */
public class TestJunit {
    @Test

    public void testAdd() {
        String str = "Junit is working fine";
        assertEquals("Junit is working fine",str);
    }
}
