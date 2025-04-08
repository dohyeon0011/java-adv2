package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamStartMain1 {

    public static void main(String[] args) throws IOException {
        /**
         *  디코딩(OutputStream 으로), 파일에 데이터를 출력하는 스트림이다.
         *  파일이 없으면 파일을 자동으로 만들고, 데이터를 해당 파일에 저장한다.
         *  폴더를 만들지는 않기 때문에 폴더는 미리 만들어두어야 한다.
         */
        FileOutputStream fos = new FileOutputStream("temp/hello.dat"); // append 옵션(true, false)을 주면 기존 문서에 추가하거나 새로운 문서를 작성하거나를 함.
        fos.write(65);
        fos.write(66);
        fos.write(67);

        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat"); // 파일에서 데이터를 읽어오는 스트림이다.

        int data;
        while ((data = fis.read()) != -1) {
            System.out.println("data = " + data);
        }

//        System.out.println("fis = " + fis.read()); // 한 바이트씩 읽음
//        System.out.println("fis = " + fis.read());
//        System.out.println("fis = " + fis.read());
//        System.out.println("fis = " + fis.read());

        fis.close();
    }
}
