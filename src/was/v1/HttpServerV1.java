package was.v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static util.MyLogger.*;

// 크롬은 사용자가 브라우저 URL에 자동완성이 된 상태면 먼저 미리 GET 요청을 보냄.
public class HttpServerV1 { // 이 버전은 단일 스레드로만 요청 처리를 해줌.

    private final int port;

    public HttpServerV1(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        log("서버 시작 PORT: " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            process(socket);
        }
    }

    private void process(Socket socket) throws IOException {
        /**
         * `new PrintWriter(socket.getOutputStream(), false, UTF_8)`
         * `PrintWriter` 의 두 번째 인자는 `autoFlush` 여부이다.
         * 이 값을 `true`로 설정하면 `println()` 으로 출력할 때 마다 자동으로 플러시 된다.
         * 첫 내용을 빠르게 전송할 수 있지만, 네트워크 전송이 자주 발생한다.
         * 이 값을 `false` 로 설정하면 `flush()` 를 직접 호출해주어야 데이터를 전송한다.
         * 데이터를 모아서 전송하므로 네트워크 전송 횟수를 효과적으로 줄일 수 있다. 한 패킷에 많은 양의 데이터를 담아서 전송할 수 있다.
         * 여기서는 `false`로 설정했으므로 마지막에 꼭 `writer.flush()` 를 호출해야 한다.
         */
        try (socket;
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), false, StandardCharsets.UTF_8)) {

            String requestString = requestsToString(reader);
            if (requestString.contains("/favicon.ico")) {
                log("favicon 요청");
                return;
            }

            log("HTTP 요청 정보 출력");
            System.out.println(requestString);

            log("HTTP 응답 생성중...");
            sleep(3000);
            responseToClient(printWriter);
            log("HTTP 응답 전달 완료");
        }
    }

    private static String requestsToString(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            sb.append(line).append("\n");
        }

        return sb.toString();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void responseToClient(PrintWriter writer) {
        // 웹 브라우저에 전달하는 내용
        String body = "<h1>Hello World</h1>";
        int length = body.getBytes(StandardCharsets.UTF_8).length;

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");   // HTTP 공식 스펙에서 다음 라인은 `\r\n` (캐리지 리턴(해당 열의 맨 처음으로 이동ㅇㄹ) + 라인 피드)로 표현한다. 참고로 `\n` 만 사용해도 대부분의 웹브라우저는 문제없이 작동한다.
        sb.append("Content-Type: text/html\r\n");
        sb.append("Content-Length: ").append(length).append("\r\n");
        sb.append("\r\n");  // header, body 구분 라인
        sb.append(body);

        log("HTTP 응답 정보 출력");
        System.out.println(body);

        writer.println(sb);
        writer.flush(); // PrintWriter 안에 버퍼에 담긴 정보들을 플러시
    }
}
