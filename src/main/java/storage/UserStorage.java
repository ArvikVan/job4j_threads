package storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ArvikV
 * @version 1.0
 * @since 19.11.2021
 */

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> map = new ConcurrentHashMap<>();

    /**
     * добавляем акк
     * @param user что надо добвать
     * @return на выходе добавленый ид - юзер
     * синхронизировали
     */
    public synchronized boolean add(User user) {
        return map.putIfAbsent(user.getId(), user) == null;
    }

    /**
     * метод обновляет акк
     * @param user то что надо обновить
     * @return на выходе старое (по ид) удаляем, новое записываем
     * синхронизировали
     */
    public synchronized boolean update(User user) {
        return map.replace(user.getId(), map.get(user.getId()), user);
    }

    /**
     * удаляем акк
     * @param user то что надо удалить
     * @return на выходе удаление по ид
     * синхронизировали
     */
    public synchronized boolean delete(User user) {
        return map.remove(user.getId(), user);
    }

    /**
     * перевод
     * @param fromId на входе откуда
     * @param toId на входе куда
     * @param amount на входе сколько
     * @return на выходе узнаем откуда, куда
     * есди откуда не нулл, куда не нулл, и денег откуда больше чем надо перевести
     * то откуда отнимаем сколько надо перевести, а куда прибавляем сколько надо перевести
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User userFromId = map.get(fromId);
        User userToId = map.get(toId);
        boolean result = false;
        if (userToId != null && userFromId != null && userFromId.getAmount() > amount) {
            userFromId.setAmount(userToId.getAmount() - amount);
            userToId.setAmount(userToId.getAmount() + amount);
            result = true;
        }
        return result;
    }

}
