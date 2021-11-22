package cash;

/**
 * модель данных
 * @author ArvikV
 * @version 1.0
 * @since 22.11.2021
 * ID - уникальный идентификатор. В системе будет только один объект с таким ID.
 * version - определяет достоверность версии в кеше.
 * Поле name - это поля бизнес модели. В нашем примере это одно поле name. Это поле имеет get set методы.
 */
public class Base {
    private final int id;
    private int version;
    private String name;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }
}
