package io.text;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.*;

/**
 * Writer` , `Reader` 클래스를 사용하면 바이트 변환 없이 문자를 직접 다룰 수 있어서 편리하다.
 * 하지만 실제로는 내부에서 byte로 변환해서 저장한다는 점을 꼭 기억하자.
 * 모든 데이터는 바이트 단위로 다룬다! 문자를 직접 저장할 수는 없다!
 * 그리고 반드시 기억하자, 문자를 byte로 변경하려면 항상 문자 집합(인코딩 셋)이 필요하다!
 */
public class ReaderWriterMainV3 {

    public static void main(String[] args) throws IOException {
        String writeString = "가나다";
        System.out.println("writeString = " + writeString);

        /**
         * `FileWriter` 에 파일명과, 문자 집합(인코딩 셋)을 전달한다.
         * `FileWriter` 는 사실 내부에서 스스로 `FileOutputStream` 을 하나 생성해서 사용한다.
         */
        // 파일에 쓰기
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8);
        fw.write(writeString);  // 이렇게 문자를 쓰면 `FileWriter` 내부에서는 인코딩 셋을 사용해서 문자를 byte로 변경하고, `FileOutputStream` 을 사용해서 파일에 저장한다.
        fw.close();

        // 파일 읽기
        StringBuilder sb = new StringBuilder();
        FileReader fr = new FileReader(FILE_NAME, UTF_8); // 문자 배열을 생략하면 시스템의 기본 문자 배열을 사용(MS949, UTF-8 ...), 내부에서 `FileInputStream` 를 생성해서 사용한다

        int ch;
        while ((ch = fr.read()) != -1) {
            sb.append((char) ch).append(" ");
        }
        fr.close();

        System.out.println("read String: = " + sb);
    }
}
