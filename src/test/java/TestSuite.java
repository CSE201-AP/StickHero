import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.*;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@SuiteClasses({CustomAnimationTimerTest.class})
public class TestSuite {
    @Test
    @Rule
    public void test() {
        System.out.println("Running Test Suite");
    }
}
