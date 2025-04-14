package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class Server {
    private final int port;
    private final CommandManager commandManager;
    private final SessionManager sessionManager;

    private ServerSocket serverSocket;

    public Server(int port, CommandManager commandManager, SessionManager sessionManager) {
        this.port = port;
        this.commandManager = commandManager;
        this.sessionManager = sessionManager;
    }

    public void start() throws IOException {
        log("서버 시작: " + commandManager.getClass());
        serverSocket = new ServerSocket(port);
        log("서버 소켓 시작 - 리스닝 포트: " + port);

        // 셧다운 훅 등록
        addShutdownHook();

        // 프로그램 작동
        running();
    }

    private void addShutdownHook() {
        ShutdownHook target = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(target, "shutdown"));
    }

    private void running() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();  // 블로킹
                log("소캣 연결: " + socket);

                Session session = new Session(socket, commandManager, sessionManager);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            log("서버 소캣 종료: " + e);
        }
    }

    static class ShutdownHook implements Runnable {
        private final ServerSocket serverSocket;
        private final SessionManager sessionManager;

        public ShutdownHook(ServerSocket serverSocket, SessionManager sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("shutdownHook 실행");
            try {
                sessionManager.closeAll();
                serverSocket.close();   // `shutdown` 스레드는 서버 소켓을 `close()`로 닫는다.(`serverSocket.accept();` 에서 대기하고 있던 `main` 스레드는 `java.net.SocketException:Socket closed` 예외를 받고 종료된다.)

                Thread.sleep(1000); // 해당 스레드 자원 정리 대기(세션 안에서 자원들을 정리하면서 다른 스레드의 필요한 로그를 찍을 유예 시간을 주기 위해, 왜냐면 non 데몬 스레드의 종료 여부와 관계없이 자바 프로세스가 종료되면서 다른 스레드가 모두 종료되기 때문)
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("e = " + e);
            }

        }
    }

}
