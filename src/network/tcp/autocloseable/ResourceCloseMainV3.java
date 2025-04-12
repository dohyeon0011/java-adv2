package network.tcp.autocloseable;

public class ResourceCloseMainV3 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    /**
     * 이전에 발생했던 다음 2가지 문제를 해결했다.
     *  close()` 시점에 실수로 예외를 던지면, 이후 다른 자원을 닫을 수 없는 문제 발생
     * `finally` 블럭 안에서 자원을 닫을 때 예외가 발생하면, 핵심 예외가 `finally` 에서 발생한 부가 예외로 바뀌어 버린다.
     *  그리고 핵심 예외가 사라진다.
     */
    private static void logic() throws CallException, CloseException {
        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;

        try {
            resource1 = new ResourceV1("resource1");
            resource2 = new ResourceV1("resource2");

            resource1.call();
            resource1.callEx(); // CallException 발생해서 finally 블럭으로 이동
        } catch (CallException e) {
            System.out.println("ex: " + e);
            throw e;
        } finally {
            /**
             * finally` 블럭에서 각각의 자원을 닫을 때도, 예외가 발생하면 예외를 잡아서 처리하도록 했다.
             * 이렇게 하면 자원 정리 시점에 예외가 발생해도, 다음 자원을 닫을 수 있다.
             * 자원 정리 시점에 발생한 예외를 잡아서 처리했기 때문에, 자원 정리 시점에 발생한 부가 예외가 핵심 예외를 가리지 않는다.
             */
            if (resource2 != null) {
                try {
                    resource2.closeEx();
                } catch (CloseException e) {
                    // close()에서 발생한 예외는 우선 크게 중요하지 않으니 버린다. 필요하면 로깅 정도
                    // 자원 정리 시점에 발생한 예외는 당장 더 처리할 수 있는 부분이 없다.
                    // 이 경우 로그를 남겨서 개발자가 인지할 수 있게 하는 정도면 충분하다.
                    System.out.println("close ex: " + e);
                }
            }
            if (resource1 != null) {
                try {
                    resource1.closeEx();
                } catch (CloseException e) {
                    System.out.println("close ex: " + e);
                }
            }
        }
    }
}