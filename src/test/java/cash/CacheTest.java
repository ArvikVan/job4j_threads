package cash;

import com.mchange.v2.coalesce.CoalesceChecker;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ArvikV
 * @version 1.0
 * @since 23.11.2021
 */
public class CacheTest {

    @Test
    public void add() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        assertTrue(cache.add(base));
    }

    @Test
    public void update() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        base.setName("qwerty");
        cache.add(base);
        Base base1 = new Base(1, 0);
        base.setName("notqwerty");
        assertTrue(cache.update(base1));
    }

    @Test
    public void delete() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        cache.add(base);
        cache.delete(base);
        assertTrue(cache.add(base));
    }
}