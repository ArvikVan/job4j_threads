package thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * @author arvikv
 * @version 1.0
 * @since 15.11.2021
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
         FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
      byte[] dataBuffer = new byte[1024];
      long start = System.currentTimeMillis();
      int bytesRead;
      while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
        fileOutputStream.write(dataBuffer, 0, bytesRead);
        long finishTime = System.currentTimeMillis() - start;
        
        long duration = (finishTime - start) / 1000;
        if (speed > duration) {
          Thread.sleep((speed - duration) * 1000);
        }
        start = System.currentTimeMillis();
      }
    } catch (Exception e) {
      Thread.currentThread().interrupt();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    if (args.length != 2) {
      throw new IllegalArgumentException();
    }
    String url = args[0];
    int speed = Integer.parseInt(args[1]);
    Thread wget = new Thread(new Wget(url, speed));
    wget.start();
    wget.join();
  }
}
