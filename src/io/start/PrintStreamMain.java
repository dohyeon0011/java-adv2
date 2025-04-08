package io.start;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.*;

public class PrintStreamMain {

    public static void main(String[] args) throws IOException {

        System.out.println("hello");

        PrintStream printStream = System.out; // 우리가 자주 사용했던 System.out이 PrintStream임. 이 스트림은 OutputStream을 상속 받음.
        printStream.println("hihi");

        byte[] bytes = "Hello!\n".getBytes(UTF_8);
        printStream.write(bytes); // OutputStream 부모 클래스가 제공
        printStream.println("Print!"); // PrintStream이 자체적으로 제공하는 추가 기능


    }
}
