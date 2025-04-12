package network.tcp.v1;

import util.MyLogger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.*;

public class ServerV1 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT); // 해당 포트로 서버 소켓 생성(특정 포트 열기, 서버 소켓은 단지 클라이언트와 서버의 TCP 연결만 지원함.), 클라이언트는 해당 포트로 서버 접속 가능
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        /**
         * `accept()` 를 호출하면 OS backlog queue에서 TCP 연결 정보를 조회한다. (클라이언트 - IP:Port, 서버 - IP:Port 저장) -> 여기 까지만 서버 소켓의 역할
         * 먄약 TCP 연결 정보가 없다면, 연결 정보가 생성될 때 까지 대기한다. (블로킹)
         * 해당 정보를 기반으로 `Socket` 객체를 생성한다.
         * 사용한 TCP 연결 정보는 backlog queue에서 제거된다.
         */
        Socket socket = serverSocket.accept(); // 해당 포트로 서로 연결
        log("소켓 연결: " + socket);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        // 클라이언트로부터 문자 받기
        String received = input.readUTF();
        log("client -> server: " + received);

        // 클라이언트에게 문자 보내기
        String toSend = received + "World";
        output.writeUTF(toSend);
        log("client <- server: " + toSend);

        // 자원 정리
        log("연결 종료: " + socket);
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}
