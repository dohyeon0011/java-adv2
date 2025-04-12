package network.tcp.autocloseable;

public class ResourceCloseMainV4 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");

            // 발생한 부가 예외를 핵심 예외안에 Suppressed로 담아서 반환한다.
            // 개발자는 자원 정리 중에 발생한 부가 예외를 `e.getSuppressed()` 를 통해 활용할 수 있다.
            // `try-with-resources` 를 사용하면 핵심 예외를 반환하면서, 동시에 부가 예외도 필요하면 확인할 수 있다.
            // 참고로 자바 예외에는 `e.addSuppressed(ex)` 라는 메서드가 있어서 예외 안에 참고할 예외를 담아둘 수 있다.
            // 참고로 이 기능도 `try-with-resources` 와 함께 등장했다.
            Throwable[] suppressed = e.getSuppressed();
            for (Throwable throwable : suppressed) {
                System.out.println("suppressedEx = " + throwable);
            }

            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        // try-with-resources가 자동으로 사용한 자원들에 대해서 close() 해줌.(ResourceV2의 오버라이딩된 close()를 자동 호출, 자동으로 후순위 선언한 자원부터 닫아줌.)
        // try-with-resources는 자원 해제 중 발생한 오류는 핵심 오류라 생각하지 않고,
        /**
         * **2가지 핵심 문제**
         * `close()` 시점에 실수로 예외를 던지면, 이후 다른 자원을 닫을 수 없는 문제 발생
         * `finally` 블럭 안에서 자원을 닫을 때 예외가 발생하면, 핵심 예외가 `finally` 에서 발생한 예외가 부가 예외로 바뀌어 버린다. 그리고 핵심 예외가 사라진다.
         * **Try with resources 장점**
         * 리소스 누수 방지: 모든 리소스가 제대로 닫히도록 보장한다. 실수로 `finally` 블록을 적지 않거나,
         *               `finally` 블럭 안에서 자원 해제 코드를 누락하는 문제들을 예방할 수 있다.
         *
         * 코드 간결성 및 가독성 향상: 명시적인 `close()` 호출이 필요 없어 코드가 더 간결하고 읽기 쉬워진다.
         *
         * 스코프 범위 한정: 예를 들어 리소스로 사용되는 `resource1,2` 변수의 스코프가 `try` 블럭 안으로 한정된다.
         *               따라서 코드 유지보수가 더 쉬워진다.
         *
         * 조금 더 빠른 자원 해제: 기존에는 try-catch-finally로 catch 이후에 자원을 반납했다.
         *                  Try-with-resources 구분은 `try` 블럭이 끝나면 즉시 `close()` 를 호출한다.(그 다음 catch로 넘어감)
         *
         * 자원 정리 순서: 먼저 선언한 자원을 나중에 정리한다.
         */
        try (ResourceV2 resource1 = new ResourceV2("resource1");
             ResourceV2 resource2 = new ResourceV2("resource2")) {

            resource1.call();
            resource2.callEx(); // CallException
        } catch (CallException e) {
            System.out.println("ex: " + e);
            throw e;
        }
    }
}