package network.tcp.v3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV3 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        /**
         * 클라이언트가 서버에 접속하면 서버 소켓의 `accept()` 메서드가 `Socket` 을 반환한다.
         * `main` 스레드는 이 정보를 기반으로 `Runnable` 을 구현한 `Session` 이라는 별도의 객체를 만들고,
         * 새로운 스레드에서 이 객체를 실행한다. 여기서는 `Thread-0` 이 `Session` 을 실행한다.
         * `Session` 객체와 `Thread-0` 은 연결된 클라이언트와 메시지를 주고 받는다.
         *
         * 새로운 TCP 연결이 발생하면 `main` 스레드는 새로운 `Session` 객체를 별도의 스레드에서 실행한다. 그리고 이 과정을 반복한다.
         * `Session` 객체와 `Thread-1` 은 연결된 클라이언트와 메시지를 주고 받는다.
         *
         *  **역할의 분리**
         *  `main` 스레드
         *      -`main` 스레드는 새로운 연결이 있을 때 마다 `Session` 객체와 별도의 스레드를 생성하고, 별도의 스레드가 `Session` 객체를 실행하도록 한다.
         *  Session 담당 스레드
         *      -`Session` 을 담당하는 스레드는 자신의 소켓이 연결된 클라이언트와 메시지를 반복해서 주고 받는 역할을 담당한다.
         */
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT); // 해당 포트로 서버 소켓 생성(특정 포트 열기, 서버 소켓은 단지 클라이언트와 서버의 TCP 연결만 지원함.), 클라이언트는 해당 포트로 서버 접속 가능
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        while (true) {
            Socket socket = serverSocket.accept(); // 블로킹
            log("소켓 연결: " + socket);

            SessionV3 session = new SessionV3(socket); // 세션 객체 생성 후
            Thread thread = new Thread(session); // 별도의 스레드에서 클라이언트와 통신
            thread.start();
        }
    }
}
