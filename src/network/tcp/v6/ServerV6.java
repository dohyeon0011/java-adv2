package network.tcp.v6;

import network.tcp.v6.SessionV6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV6 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        SessionManagerV6 sessionManager = new SessionManagerV6();
        ServerSocket serverSocket = new ServerSocket(PORT); // 해당 포트로 서버 소켓 생성(특정 포트 열기, 서버 소켓은 단지 클라이언트와 서버의 TCP 연결만 지원함.), 클라이언트는 해당 포트로 서버 접속 가능
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        // ShutdownHook 등록
        // `Runtime.getRuntime().addShutdownHook()` 을 사용하면 자바 종료시 호출되는 셧다운 훅을 등록할 수 있다.
        // 여기에 셧다운이 발생했을 때 처리할 작업과 스레드를 등록하면 된다.
        // 서버를 종료하면 `shutdown` 스레드가 `shutdownHook`을 실행하고, 세션의 `Socket`의 연결을 `close()`로 닫는다.
        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook, "shutdown")); // 자바가 종료될 때 이 작업을 처리 후 자바가 종료됨.

        try {
            while (true) {
                Socket socket = serverSocket.accept(); // 블로킹
                log("소켓 연결: " + socket);

                SessionV6 session = new SessionV6(socket, sessionManager); // 세션 객체 생성 후
                Thread thread = new Thread(session); // 별도의 스레드에서 클라이언트와 통신
                thread.start();
            }
        } catch (IOException e) {
            log("서버 소켓 종료: " + e);
        }
    }

    static class ShutdownHook implements Runnable {
        private final ServerSocket serverSocket;
        private final SessionManagerV6 sessionManager;

        public ShutdownHook(ServerSocket serverSocket, SessionManagerV6 sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("shutdownHook 실행");

            try {
                sessionManager.closeAll();
                serverSocket.close(); // `shutdown` 스레드는 서버 소켓을 `close()`로 닫는다.(`serverSocket.accept();` 에서 대기하고 있던 `main` 스레드는 `java.net.SocketException:Socket closed` 예외를 받고 종료된다.)

                Thread.sleep(1000); // 해당 스레드 자원 정리 대기(세션 안에서 자원들을 정리하면서 다른 스레드의 필요한 로그를 찍을 유예 시간을 주기 위해, 왜냐면 non 데몬 스레드의 종료 여부와 관계없이 자바 프로세스가 종료되면서 다른 스레드가 모두 종료되기 때문)
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("e = " + e);
            }
        }
    }
}
