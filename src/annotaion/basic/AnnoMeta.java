package annotaion.basic;

import java.lang.annotation.*;

/**
 * `@Retention` : `RUNTIME` 자바 실행 중에도 애노테이션 정보가 남아있다. 따라서 런타임에 리플렉션을 통해서 읽을 수 있다.
 * 만약 다른 설정을 적용한다면 자바 실행 시점에 애노테이션이 사라지므로 리플렉션을 통해서 읽을 수 없다.
 * `@Target` : `ElementType.METHOD` , `ElementType.TYPE` 메서드와 타입(클래스, 인터페이스, enum 등)에 `@AnnoMeta` 애노테이션을 적용할 수 있다. 다른 곳에 적용하면 컴파일 오류가 발생한다.
 * `@Documented` : 자바 API 문서를 만들 때 해당 애노테이션이 포함된다.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface AnnoMeta {
}
