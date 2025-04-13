package network.exception.close.normal;

import util.MyLogger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.*;

// TCP 연결 정상 종료 시 자원 정리 시나리오
public class NormalCloseServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(12345);
        Socket socket = serverSocket.accept();

        log("소켓 연결: " + socket);

        Thread.sleep(1000); // 로그 찍고 1초 기다렸다가 소켓 종료(서버 -> 클라이언트로 fin(연결 종료 신호 보냄))
        socket.close();
        log("소켓 종료");

    }
}
