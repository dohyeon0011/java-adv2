package charset;

import java.nio.charset.Charset;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

/**
 * **영문**
 *  US-ASCII, ISO-8859-1, EUC-KR, MS949, UTF-8은 모두 ASCII와 호환된다.
 *  영문 A는 1byte만 사용하고, 숫자 65로 인코딩된다.
 *  UTF-16은 ASCII와 호환되지 않는다.
 *  영문 A는 2byte를 사용하고, 숫자 [0, 65]로 인코딩된다.
 *
 * **한글**
 *  EUC-KR, MS949는 한글 인코딩에 2byte를 사용하고 같은 값으로 인코딩한다.
 *  EUC-KR을 확장해서 만든 것이 MS949이다.
 *  UTF-8은 한글 인코딩에 3byte를 사용한다.
 *  UTF-16은 한글 인코딩에 2byte를 사용한다.
 *
 *  참고**: UTF_16, UTF_16BE, UTF_16LE가 있는데, 우리는 UTF_16BE를 사용하면 된다.
 *  BE, LE는 byte의 순서의 차이이다.
 * `UTF_16BE` : [-84, 0]
 * `UTF_16LE` : [0, -84]
 * `UTF_16` : 인코딩한 문자가 BE, LE중에 어떤 것인지 알려주는 2byte가 앞에 추가로 붙는다.
 * 이제 UTF_16을 잘 사용하지 않고, UTF-8은 이런 이슈가 없으므로 참고만 하고 넘어가자.
 */
public class EncodingMain1 {

    public static final Charset EUC_KR = Charset.forName("EUC-KR");
    public static final Charset MS_949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== ASCII 영문 처리 ==");

        encoding("A", US_ASCII);
        encoding("A", ISO_8859_1);
        encoding("A", EUC_KR);
        encoding("A", UTF_8);
        encoding("A", UTF_16BE); // 2byte를 사용하기 때문에 2바이트 단위로 출력됨.

        System.out.println("== 한글 지원 ==");
        encoding("가", EUC_KR);
        encoding("가", MS_949);
        encoding("가", UTF_8); // 3byte를 사용하기 때문에 3바이트 단위로 출력됨.
        encoding("가", UTF_16BE); // 2byte를 사용하기 때문에 2바이트 단위로 출력됨.

        String str = "A";
        byte[] bytes = str.getBytes();
        System.out.println("bytes = " + Arrays.toString(bytes));
    }

    public static void encoding(String text, Charset charset) {
        byte[] bytes = text.getBytes(charset); // 문자를 byte로 변환하려면 문자 집합이 필요.(ex: ms949, utf-16, utf-8)
        System.out.printf("%s -> [%s] 인코딩: %s %sbyte\n", text, charset, Arrays.toString(bytes), bytes.length);
    }
}
