package io.file.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyMainV2 {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("temp/copy.dat");
        FileOutputStream fos = new FileOutputStream("temp/copy_new.dat");

        fis.transferTo(fos); // InputStream에서 읽은 데이터를 바로 OutputStream으로 출력(파일(copy.dat) -> 자바(byte) -> 파일(copy_new.dat)의 과정을 거친다.)

        fis.close();
        fos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
