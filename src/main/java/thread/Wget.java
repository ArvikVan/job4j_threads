package thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * @author arvikv
 * @version 1.1
 * @since 15.11.2021
 * 1.1
 * 7) Сохраняйте файл на диск не под константным именем pom_tmp.xml, а с таким же именем, что и в URL.
 * 1) Определиться в каких единицах вы будете измерять пропускную способность. Чтобы меньше путаться
 * - сделайте в байтах в секунду. Тогда если ограничивать скорость до 1 мегабайта в секунду -
 * это 1 * 1024 * 1024 = 1048576 байт в секунду - эту цифру передавать вторым параметром. Это и будет speed.
 * 3) Создайте переменную, например long bytesWrited, в нее на каждом цикле while плюсуйте количество записанных байт.
 * Когда bytesWrited станет >= speed, то измерьте время, за которое это было сделано. Например long deltaTime
 * 4) Если deltaTime < 1000 (1000 миллисекунд = 1 секунде), то вводим задержку равную остатку от секунды.
 * 6) Хорошо, что проверяете размер args[], только сделайте отдельный метод-валидатор.
 */


public class Wget implements Runnable {
  private final String url;
  private final int speed;

  public Wget(String url, int speed) {
    this.url = url;
    this.speed = speed;
  }

  /**
   * соединяемся с урл
   * определяем файл куда будем писать
   * массив байтов
   * определяем время в этот момент
   * считываем байты  из потока в массив байтов до тех пор пока не прочтем все (-1)
   * пишем в локальный файл, определнный ранее
   * находим время, которое прошло с момента начала
   */
  @Override
  public void run() {
    try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
         FileOutputStream fileOutputStream = new FileOutputStream(url.substring(url.lastIndexOf('/') + 1))) {
      byte[] dataBuffer = new byte[1048576];
      int bytesRead;
      long bytesWrited = 0;

      while ((bytesRead = in.read(dataBuffer, 0, 1048576)) != -1) {
        long start = System.currentTimeMillis();
        fileOutputStream.write(dataBuffer, 0, bytesRead);
        bytesWrited = bytesWrited + bytesRead;
        System.out.print("\rload: " + bytesWrited + " bytes");

        if (bytesWrited >= speed) {
          long deltaTime = System.currentTimeMillis() - start;
          if (deltaTime < 1000) {
            Thread.sleep(1000 - deltaTime);
          }
        }
        start = System.currentTimeMillis();
      }
    } catch (Exception e) {
      Thread.currentThread().interrupt();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    extracted(args);
    String url = args[0];
    int speed = Integer.parseInt(args[1]);
    Thread wget = new Thread(new Wget(url, speed));
    wget.start();
    wget.join();
  }

  private static void extracted(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("No arguments");
    }
    if (!args[0].startsWith("https")) {
      throw new IllegalArgumentException("Check url");
    }
    if (Integer.parseInt(args[1]) <= 0) {
      throw new IllegalArgumentException("Speed should be positive");
    }
  }
}
