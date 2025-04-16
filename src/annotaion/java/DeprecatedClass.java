package annotaion.java;

public class DeprecatedClass {

    public void call1() {
        System.out.println("DeprecatedClass.call1");
    }

    /**
     * `@Deprecated` 는 더 이상 사용되지 않는다는 뜻이다. 이 애노테이션이 적용된 기능은 사용을 권장하지 않는다.
     * 예를 들면 다음과 같은 이유이다.
     * 해당 요소를 사용하면 오류가 발생할 가능성이 있다.
     * 호환되지 않게 변경되거나 향후 버전에서 제거될 수 있다.
     * 더 나은 최신 대체 요소로 대체되었다.
     * 더 이상 사용되지 않는 기능이다.
     */
    @Deprecated
    public void call2() {
        System.out.println("DeprecatedClass.call2");
    }

    // `since` : 더 이상 사용하지 않게된 버전 정보
    // `forRemoval` : 미래 버전에 코드가 제거될 예정이다.
    @Deprecated(since = "2.4", forRemoval = true)
    public void call3() {
        System.out.println("DeprecatedClass.call3");
    }
}
