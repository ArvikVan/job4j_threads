package storage;

import java.util.Objects;

/**
 * Класс описывает модель данных
 * @author ArvikV
 * @version 1.0
 * @since 19.11.2021
 */


public class User {
      private int id;
      private int amount;

      public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
      }

      public int getId() {
        return id;
      }

      public int getAmount() {
        return amount;
      }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
      public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (o == null || getClass() != o.getClass()) {
          return false;
        }
        User user = (User) o;
        return id == user.id && amount == user.amount;
      }

      @Override
      public int hashCode() {
        return Objects.hash(id, amount);
      }

  @Override
  public String toString() {
    return "User{"
            + "id=" + id
            + ", amount=" + amount
            + '}';
  }
}
