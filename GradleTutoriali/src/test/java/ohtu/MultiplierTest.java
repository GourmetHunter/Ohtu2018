package ohtu;

import static org.junit.Assert.*;
import org.junit.Test;

public class MultiplierTest {

    @Test
    public void kertominenToimii() {
        Multiplier multiplier = new Multiplier(5);

        assertEquals(5, multiplier.multipliedBy(1));
        assertEquals(35, multiplier.multipliedBy(7));
    }

}
