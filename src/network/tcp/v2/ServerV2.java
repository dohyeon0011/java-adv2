package network.tcp.v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class ServerV2 {

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

        Scanner sc = new Scanner(System.in);

        /**
         * ServerV2의 문제
         *  `ServerV2` 에 둘 이상의 클라이언트가 작동하지 않는 이유는 다음과 같다.
         *  새로운 클라이언트가 접속하면?
         *    -새로운 클라이언트가 접속했을 때 서버의 `main` 스레드는 `accept()` 메서드를 절대로 호출할 수 없다!
         *      왜냐하면 `while` 문으로 기존 클라이언트와 메시지를 주고 받는 부분만 반복하기 때문이다.
         *    -`accept()` 를 호출해야 소켓 객체를 생성하고 클라이언트와 메시지를 주고 받을 수 있다.
         *
         * 2개의 블로킹 작업 - 핵심은 별도의 스레드가 필요하다!
         *    -`accept()` : 클라이언트와 서버의 연결을 처리하기 위해 대기
         *    -`readXxx()` : 클라이언트의 메시지를 받아서 처리하기 위해 대기
         *    -각각의 블로킹 작업은 별도의 스레드에서 처리해야 한다. 그렇지 않으면 다른 블로킹 메서드 때문에 계속 대기할 수 있다.
         */
        while (true) {
            // 클라이언트로부터 문자 받기
            String received = input.readUTF();
            log("client -> server: " + received);

            if (received.equals("exit")) {
                break;
            }

            // 클라이언트에게 문자 보내기
            String toSend = received + " World";
            output.writeUTF(toSend);
            log("client <- server: " + toSend);
        }

        // 자원 정리
        log("연결 종료: " + socket);
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}
