package annotaion.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 경고를 억제하는 애노테이션. 자바 컴파일러가 문제를 경고하지만, 개발자가 해당 문제를 잘 알고 있기 때문에, 더는 경고하지 말라고 지시하는 애노테이션.
public class SuppressWarningCase {

    @SuppressWarnings("unused")
    public void unusedWarning() {
        // 사용되지 않는 변수 경고 억제
        int unusedVariable = 10;
    }

    @SuppressWarnings("deprecation")
    public void deprecatedMethod() {    // 더이상 사용되지 않는 메서드 호출 시
        Date date = new Date();
        int date1 = date.getDate();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void uncheckedCase() {
        // 제네릭 타입 캐스팅 경고 억제(제네릭 타입이 명시되지 않은 raw 타입), raw type 사용 권고
        List list = new ArrayList<>();

        // 제네릭 타입과 관련된 unchecked 경고
        List<String> stringList = (List<String>)list;
    }

    @SuppressWarnings("all")
    public void suppressAllWarning() {
        // 모든 경고 억제
        int unusedVariable = 10;

        Date date = new Date();
        int date1 = date.getDate();

        List list = new ArrayList<>();
        List<String> stringList = (List<String>)list;
    }
}
