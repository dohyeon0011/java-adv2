package io.text;

import java.io.*;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 기본(기반, 메인) 스트림
 *      File, 메모리, 콘솔등에 직접 접근하는 스트림
 *      단독으로 사용할 수 있음
 *      예) `FileInputStream` , `FileOutputStream` , `FileReader` , `FileWriter` ,
 *      `ByteArrayInputStream` , `ByteArrayOutputStream`
 *
 * 보조 스트림
 *      기본 스트림을 도와주는 스트림
 *      단독으로 사용할 수 없음, 반드시 대상 스트림이 있어야함
 *      예) `BufferedInputStream` , `BufferedOutputStream` , `InputStreamReader` ,
 *      `OutputStreamWriter` , `DataOutputStream` , `DataInputStream` , `PrintStream`
 */
public class ReaderWriterMainV4 { // 한 줄로 읽기, 쓰기

    private static final int BUFFER_SIZE = 8192;

    public static void main(String[] args) throws IOException {
        String writeString = "ABC\n가나다";
        System.out.println("=== Write String ===");
        System.out.println(writeString);

        // 파일에 쓰기
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8);
        BufferedWriter bw = new BufferedWriter(fw, BUFFER_SIZE);
        bw.write(writeString);
        bw.close();

        // 파일 읽기
        StringBuilder sb = new StringBuilder();
        FileReader fr = new FileReader(FILE_NAME, UTF_8);
        BufferedReader br = new BufferedReader(fr, BUFFER_SIZE);

        String line;
        while ((line = br.readLine()) != null) { // 한 줄 단위로 가져오고 다음 한 줄 가져오기
            sb.append(line).append("\n");
        }
        br.close();

        System.out.println("=== Read String ===");
        System.out.println(sb);
    }
}
