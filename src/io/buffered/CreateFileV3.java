package io.buffered;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

public class CreateFileV3 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER_SIZE); // 넘겨준 버퍼 크기만큼 내부에서 버퍼를 생성해서 읽기, 쓰기 최적화 해줌.

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < FILE_SIZE; i++) { // 10 * 1024 * 1024 만큼 반복
            bos.write(1); // 버퍼가 가득차면 FileInputStream에 있는 write(byte[]) 메서드를 호출해서 쓰고 다시 버퍼를 비우고를 반복.
        }
        bos.close(); // fos도 같이 연쇄적으로 close() 호출(버퍼가 남아있는 상태로 호출하면 flush()로 버퍼를 비우고 close() 호출), 반드시 마지막에 연결한 Stream을 닫아줘야 함.(ex: bos)

        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + FILE_NAME);
        System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
