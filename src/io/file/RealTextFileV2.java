package io.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class RealTextFileV2 {

    private static final String PATH = "temp/hello2.txt";

    public static void main(String[] args) throws IOException {
        String writeString = "가나다\nabc";
        System.out.println("== Write String ==");
        System.out.println(writeString);

        Path path = Path.of(PATH);

        // 파일에 쓰기
        Files.writeString(path, writeString, UTF_8);

        // 파일 읽기
        System.out.println("== Read String ==");
        List<String> lines = Files.readAllLines(path, UTF_8);   // 파일의 용량이 클 때 안 좋음.(OOM이 올 수도, 파일 전체 내용을 한 번에 가져와서)

        for (int i = 0; i < lines.size(); i++) {
            System.out.println((i + 1) + ": " + lines.get(i));
        }

        // 전체를 가져오되 1줄 씩 가져와서 메모리 부담을 줄임.
        /*Stream<String> lineStream = Files.lines(path, UTF_8);
        lineStream.forEach(line -> System.out.println(line));
        lineStream.close();*/
    }
}
