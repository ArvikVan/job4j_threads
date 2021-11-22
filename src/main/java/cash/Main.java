package cash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ArvikV
 * @version 1.0
 * @since 22.11.2021
 */
public class Main {
    public static void main(String[] args) {
        Map<Integer, Base> map = new HashMap<>();
        Base base = new Base(1, 0);
        map.put(base.getId(), base);

        Base user1 = map.get(base);
        user1.setName("User 1");

        Base user2 = map.get(base);
        user1.setName("User 2");

        map.put(user1.getId(), user1);
        map.put(user2.getId(), user2);

        System.out.println(map);
    }
}
