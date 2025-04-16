package annotaion.mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleMapping {   // 컴파일러나 런타임에서 해석될 수 있는 메타데이터 제공(코드에 메모를 달아놓는 것처럼 특정 정보나 지시를 추가하는 도구로, 코드에 대한 메타데이터를 표현하는 방법)
    String value();
}
