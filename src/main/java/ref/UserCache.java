package ref;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ArvikV
 * @version 1.0
 * @since 16.11.2021
 * public List<User> findAll() {
 *         return new ArrayList<>(users.values());
 *     }
 * Другое решение проблемы многопоточной среды -  избавление от общих ресурсов.
 * Как избавиться от общих ресурсов? Можно сделать копию общего ресурса.
 * В этом случае каждая нить работает с локальной копией.
 */

public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    /**
     * работаем не напрямую с мапой users
     * @return на выходе список-копия от users
     */
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        for (User value : users.values()) {
            userList.add(User.of(value.getName()));
        }
        return userList;
    }
}
