import org.junit.Assert;
import org.junit.Test;

/**
 * @author arvikv
 * @version 1.0
 * @since 12.11.2021
 */
public class TriggerTest {
    @Test
    public void test() {
        Assert.assertEquals(1, new Trigger().someLogic());
    }

}