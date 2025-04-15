package was.v4;

import was.v4.HttpRequestHandlerV4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static util.MyLogger.log;

public class HttpServerV4 {

    // ExecutorService` : 스레드 풀을 사용한다. 여기서는 `newFixedThreadPool(10)` 을 사용해서 최대 동시에 10개의 스레드를 사용할 수 있도록 했다.
    // 결과적으로 10개의 요청을 동시에 처리할 수 있다.
    // 참고로 실무에서는 상황에 따라 다르지만 보통 수백 개 정도의 스레드를 사용한다.
    private final ExecutorService es = Executors.newFixedThreadPool(10);
    private final int port;

    public HttpServerV4(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        log("서버 시작 PORT: " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            es.submit(new HttpRequestHandlerV4(socket));    // 스레드 풀에 HttpRequestHandlerV2 작업 요청(스레드 풀에 있는 스레드가 HttpRequestHandlerV2의 run()을 수행)
        }
    }
}
