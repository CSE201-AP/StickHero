import com.example.stickhero.spritesheet.CustomAnimationTimer;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class CustomAnimationTimerTest {
    private static final long LENGTHNS = 10;
    private CountingCustomAnimationTimer countingCustomAnimationTimer;

    @Before()
    public void setUp() {
        countingCustomAnimationTimer = new CountingCustomAnimationTimer(LENGTHNS);
    }

    @Test
    public void testHandleCalledFirstTime() {
        countingCustomAnimationTimer.handle(0);
        assertEquals("User handle() should be called once in first CustomAnimationTimer handle", 1, countingCustomAnimationTimer.getUserHandleCount());
    }

    @Test
    public void testHandleNotCalledInInterval() {
        countingCustomAnimationTimer.handle(0);
        countingCustomAnimationTimer.handle(5);
        assertEquals("User handle() should not be called again in same interval length", 1, countingCustomAnimationTimer.getUserHandleCount());
    }

    @Test
    public void testHandleCalledAtInterval() {
        System.out.println("Testt");
        countingCustomAnimationTimer.handle(0);
        countingCustomAnimationTimer.handle(LENGTHNS);
        assertEquals("User handle() should be called at end of interval length", 2, countingCustomAnimationTimer.getUserHandleCount());
    }

    @Test
    public void testHandleCalledAfterInterval() {
        countingCustomAnimationTimer.handle(0);
        countingCustomAnimationTimer.handle(LENGTHNS+1);
        assertEquals("User handle() should be called after end of interval length", 2, countingCustomAnimationTimer.getUserHandleCount());
    }

    @Test
    public void testHandleResetsLastCallTime() {
        countingCustomAnimationTimer.handle(0);
        countingCustomAnimationTimer.handle(LENGTHNS);
        countingCustomAnimationTimer.handle(LENGTHNS + 1);
        assertEquals("Last call time should be reset on invocation", 2, countingCustomAnimationTimer.getUserHandleCount());
    }

    private static class CountingCustomAnimationTimer extends CustomAnimationTimer {
        private int userHandleCount = 0;
        public CountingCustomAnimationTimer(long lengthNs) {
            super(lengthNs);
        }
        @Override
        public void handle() {
            userHandleCount += 1;
        }
        public int getUserHandleCount() {
            return userHandleCount;
        }
    }
}