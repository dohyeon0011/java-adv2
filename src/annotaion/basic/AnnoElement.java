package annotaion.basic;

import util.MyLogger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * `RetentionPolicy.SOURCE` : 소스 코드에만 남아있다. 컴파일 시점에 제거된다.
 * `RetentionPolicy.CLASS` : 컴파일 후 .class 파일까지는 남아있지만 자바 실행 시점에 제거된다. (기본 값)
 * `RetentionPolicy.RUNTIME` : 자바 실행 중에도 남아있다. 대부분 이 설정을 사용한다.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AnnoElement {
    String value();
    int count() default 0;
    String[] tags() default {};

    // MyLogger data(); // 다른 타입은 적용 X
    Class<? extends MyLogger> annoData() default MyLogger.class; // 클래스 정보는 가능
}
