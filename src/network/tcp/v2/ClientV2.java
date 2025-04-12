package network.tcp.v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class ClientV2 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");
        Socket socket = new Socket("localhost", PORT); // port=12345,localport=49240(port: 서버로 연결하는 포트, localport: 내 로컬 포트를 자동으로 할당 해줌, 서버에선 이 포트로 서로 연결함.)

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        log("소켓 연결: " + socket);

        /**
         * **클라이언트가 "Hello, world!"라는 메시지를 서버에 전송하는 경우**
         *  클라이언트
         *   -애플리케이션 -> OS TCP 송신 버퍼 -> 클라이언트 네트워크 카드(랜 카드)
         *
         *  클라이언트가 보낸 메시지가 서버에 도착했을 때, 서버
         *   -서버 네트워크 카드 -> OS TCP 수신 버퍼 -> 애플리케이션
         *
         *  여기서 다른 클라이언트가 보낸 메시지는 서버 애플리케이션에서 아직 읽지 않았기 때문에, 서버 OS의 TCP 수신 버퍼에서 대기하게 된다.
         *  여기서 핵심적인 내용이 있는데, 소켓 객체 없이 서버 소켓만으로도 TCP 연결은 완료된다는 점이다. (서버 소켓은 연결만 담당한다)
         *  하지만 연결 이후에 서로 메시지를 주고 받으러면 소켓 객체가 필요하다.
         * `accept()` 는 이미 연결된 TCP 연결 정보를 기반으로 서버 측에 소켓 객체를 생성한다.
         *  그리고 이 소켓 객체가 있어야 스트림을 사용해서 메시지를 주고 받을 수 있다.
         */
        Scanner sc = new Scanner(System.in);
        while (true) {
            // 서버에게 문자 보내기
            System.out.print("전송 문자: ");
            String toSend = sc.nextLine();

            output.writeUTF(toSend);
            log("client -> server: " + toSend);

            if (toSend.equals("exit")) {
                break;
            }

            // 서버로부터 문자 받기
            String received = input.readUTF();
            log("client <- server: " + received);
        }

        // 자원 정리
        log("연결 종료: " + socket);
        input.close();
        output.close();
        socket.close();
    }
}
