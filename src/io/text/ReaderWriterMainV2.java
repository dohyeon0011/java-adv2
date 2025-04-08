package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.*;

/**
 * ## byte를 다루는 클래스는 `OutputStream` , `InputStream` 의 자식이다. (FileOutputStream, FileInputStream)
 *      부모 클래스의 기본 기능도 `byte` 단위를 다룬다.
 *      클래스 이름 마지막에 보통 `OutputStream` , `InputStream` 이 붙어있다.
 *
 *
 * ## 문자를 다루는 클래스는 `Writer` , `Reader` 의 자식이다. (InputStreamWriter, OutputStreamWriter)
 *      부모 클래스의 기본 기능은 `String` , `char` 같은 문자를 다룬다.
 *      클래스 이름 마지막에 보통 `Writer` , `Reader` 가 붙어있다.
 *
 * 여기서 **꼭! 기억해야할 중요한 사실이 있다. 처음에 언급했듯이 모든 데이터는 byte 단위(숫자)로 저장된다.**
 * 따라서 `Writer` 가 아무리 문자를 다룬다고 해도 문자를 바로 저장할 수는 없다.
 * 이 클래스에 문자를 전달하면 결과적으로 내부에서는 지정된 문자 집합을 사용해서 문자를 byte로 인코딩해서 저장한다.
 */
public class ReaderWriterMainV2 {

    public static void main(String[] args) throws IOException {
        String writeString = "가나다";
        System.out.println("writeString = " + writeString);

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        OutputStreamWriter osw = new OutputStreamWriter(fos, UTF_8); // 생성자에서 인자로 넘긴 Charset으로 인코딩을 알아서 해줌.

        osw.write(writeString);
        osw.close();

        System.out.println("=== ===");

        // 파일 읽기
        FileInputStream fis = new FileInputStream(FILE_NAME);
        InputStreamReader isr = new InputStreamReader(fis, UTF_8); // byte -> UTF-8로 알아서 디코딩

        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = isr.read()) != -1) {
            sb.append((char) ch).append(" "); // read() 메서드는 int 형으로 데이터를 반환하기 때문에 char 형으로 캐스팅
        }
        isr.close();

        System.out.println("read String: = " + sb);
    }
}
