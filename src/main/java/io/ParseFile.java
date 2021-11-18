package io;

import java.io.*;
import java.util.function.Predicate;

/**
 * @author ArvikV
 * @version 1.0
 * @since 18.11.2021
 * 2. Поправьте код с ошибками в коде.
 * - Избавиться от get set за счет передачи File в конструктор.
 * - Ошибки в многопоточности. Сделать класс Immutable. Все поля final.
 * - Ошибки в IO. Не закрытые ресурсы. Чтение и запись файла без буфера.
 * - Нарушен принцип единой ответственности. Тут нужно сделать два класса.
 * - Методы getContent написаны в стиле копипаста.
 * Нужно применить шаблон стратегия. content(Predicate<Character> filter)
 */
public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }
    /**
     * класс сделан имьютбл, все поля файнал, класс файнал
     * сеттеры и геттеры убраны, передали File в конструктор
     *
     * @return
     */
    public StringBuilder content(Predicate<Character> filter) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = inputStream.read()) > 0) {
                    if (filter.test((char) data)) {
                        stringBuilder.append(data);
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    public StringBuilder getContent() throws IOException {
        return content(data -> true);
    }

    public StringBuilder getContentWithoutUnicode() throws IOException {
        return content(data -> data < 0x80);
    }

    /**
     * ресурсы закрыл, чтение с буфером
     * убрана копипаста, разбиты методы
     */
}
