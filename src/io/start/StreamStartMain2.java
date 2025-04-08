package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class StreamStartMain2 {

    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream("temp/hello.bat");
        byte[] input = {65, 66, 67, 65, 66, 67, 65, 66}; // byte[] 배열에 원하는 데이터를 담고
        fos.write(input); // write()에 전달하면 한 번에 출력 가능

        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.bat");
        byte[] buffer = new byte[10]; // 파일에서 읽은 데이터를 담을 곳
        int readCount = fis.read(buffer, 0, 10); // 데이터를 읽을 버퍼, 시작 위치, 길이
        System.out.println("readCount = " + readCount);
        System.out.println(Arrays.toString(buffer));

        fis.close();
    }
}
