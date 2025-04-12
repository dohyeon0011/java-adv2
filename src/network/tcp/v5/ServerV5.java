package network.tcp.v5;

import network.tcp.v5.SessionV5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV5 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT); // 해당 포트로 서버 소켓 생성(특정 포트 열기, 서버 소켓은 단지 클라이언트와 서버의 TCP 연결만 지원함.), 클라이언트는 해당 포트로 서버 접속 가능
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        while (true) {
            Socket socket = serverSocket.accept(); // 블로킹
            log("소켓 연결: " + socket);

            SessionV5 session = new SessionV5(socket); // 세션 객체 생성 후
            Thread thread = new Thread(session); // 별도의 스레드에서 클라이언트와 통신
            thread.start();
        }
    }
}
