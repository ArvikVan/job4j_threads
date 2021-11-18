package io;

import java.io.*;

/**
 * @author ArvikV
 * @version 1.0
 * @since 18.11.2021
 * метод вынесен в класс, сделан имьютбл геттеры/сеттеры через "конструктор"
 * поля файнал
 */

public final class SaveParseFile {
    private final File file;

    public SaveParseFile(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        try (BufferedOutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        }
    }
}
