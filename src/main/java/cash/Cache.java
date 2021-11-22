package cash;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ArvikV
 * @version 1.0
 * @since 22.11.2021
 */
public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    /**
     * метод описывает добавление в память
     * @param model на входе что надо добавить
     * @return на выходе добавляем в мапу модел
     * если модел.гетайди == нулл, то доавляем модел в мапу
     */
    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    /**
     * метод описывает обновление данных
     * @param model на входе ячейка которую надо проапгрейдить
     *              если ячейка не равна нулл
     *              с помощью computeIfPresent для ячейки с ключиком model.getId(), вычисляем
     *              значение в котором, есливерсии отличаются то выкидываем исключение
     *              при этом в любом случае нужно обновлять версию каждый раз на 1
     *              обновляем имя.
     */
    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (k, v) -> {
            if (model.getVersion() != v.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            v.setVersion(v.getVersion() + 1);
            v.setName(model.getName());
            return v;
        }) != null;
    }

    /**
     * метод удаления
     * @param model на входе что нам надо удалить
     */
    public void delete(Base model) {
        memory.remove(model.getId());
    }
}
