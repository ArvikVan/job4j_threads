package pool;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ArvikV
 * @version 1.0
 * @since 24.11.2021
 * создаем пул
 * метод емайл
 *      в нем Через ExecutorService создайте задачу, которая будет создавать данные
 *      для пользователя и передавать их в метод send
 * метод клоуз
 *      закрываем пул
 * метод сенд
 *      пустой
 */
public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    /**
     * метод в котором добавляем задачу в пул и сразу ее выполняем
     * @param user на входе передаем
     */
    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format("Notification {%s} to email {%s}", user.getUserName(), user.getEmail());
            String body = String.format("Add a new event to {%s}", user.getUserName());
            System.out.println(subject + " "  + body);
            send(subject, body, user.getEmail());
        });
    }

    /**
     * Закрываем пул и ждем пока все задачи завершатся.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Создайте метод public void send(String subject, String body, String email) - он должен быть пустой.
     * @param subject subject
     * @param body body
     * @param email email
     */
    public void send(String subject, String body, String email) {

    }

    public static void main(String[] args) {
        EmailNotification emailNotification = new EmailNotification();
        User user = new User("Petr", "petr@mail.com");
        emailNotification.emailTo(user);
        emailNotification.close();
    }
}
