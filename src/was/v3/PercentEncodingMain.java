package was.v3;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * **% 인코딩, 디코딩 진행 과정**
 * 1. 클라이언트: `가` 전송 희망
 * 2. 클라이언트 % 인코딩: `%EA%B0%80`
 *      "가"를 UTF-8로 인코딩
 *      `EA`, `B0`, `80` 3byte 획득
 *      각 byte를 16진수 문자로 표현하고 각각의 앞에 `%` 를 붙임
 * 3. 클라이언트 서버 전송: `q=%EA%B0%80`
 * 4. 서버: `%EA%B0%80` ASCII 문자를 전달 받음
 *      '%` 가 붙은 경우 디코딩해야 하는 문자로 인식
 *      `EA` , `B0` , `80` 을 byte로 변환, 3byte를 획득
 *      `EA` , `B0` , `80` (3byte)를 UTF-8로 디코딩 문자 "가" 획득
 */
public class PercentEncodingMain {

    public static void main(String[] args) {

        String encode = URLEncoder.encode("가", StandardCharsets.UTF_8); // encode = %EA%B0%80
        System.out.println("encode = " + encode);

        String decode = URLDecoder.decode(encode, StandardCharsets.UTF_8);  // decode = 가
        System.out.println("decode = " + decode);
    }
}
