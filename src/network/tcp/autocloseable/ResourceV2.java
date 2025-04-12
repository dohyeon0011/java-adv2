package network.tcp.autocloseable;

/**
 * try-with-resources를 위한 AutoCloseable 구현
 */
public class ResourceV2 implements AutoCloseable {

    private String name;

    public ResourceV2(String name) {
        this.name = name;
    }

    public void call() {    // 정상 로직 호출
        System.out.println(name + " call");
    }

    public void callEx() throws CallException { // 비정상 로직 호출
        System.out.println(name + " call Ex");
        throw new CallException(name + " Call Ex");
    }

    // `close()` 는 항상 `CloseException` 을 던지도록
    @Override
    public void close() throws CloseException {   // 정상 종료
        System.out.println(name + " close");
        throw new CloseException(name + " ex");
    }
}

