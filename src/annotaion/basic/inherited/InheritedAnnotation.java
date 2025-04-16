package annotaion.basic.inherited;


import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited  // 클래스 상속 시 정의된 것이 자식에게도 애노테이션 적용
@Retention(RetentionPolicy.RUNTIME)
public @interface InheritedAnnotation {
}
