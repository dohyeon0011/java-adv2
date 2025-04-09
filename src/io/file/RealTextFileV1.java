package io.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.charset.StandardCharsets.*;

public class RealTextFileV1 {

    private static final String PATH = "temp/hello2.txt";

    public static void main(String[] args) throws IOException {
        String writeString = "가나다\nabc";
        System.out.println("== Write String ==");
        System.out.println(writeString);

        Path path = Path.of(PATH);

        // 파일에 쓰기
        Files.writeString(path, writeString, UTF_8);

        // 파일 읽기
        String readstring = Files.readString(path, UTF_8);

        System.out.println("== Read String ==");
        System.out.println(readstring);
    }
}
