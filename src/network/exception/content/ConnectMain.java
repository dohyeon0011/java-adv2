package network.exception.content;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectMain {

    public static void main(String[] args) throws IOException {
        unknownHostEx1();
        unknownHostEx2();
        connectionRefused();
    }

    private static void unknownHostEx1() throws IOException {
        try {
            Socket socket = new Socket("999.999.999.999",80);
        } catch (UnknownHostException e) {  // 호스트를 알 수 없음.(도메인 존재 X)
            e.printStackTrace();
        }
    }

    private static void unknownHostEx2() throws IOException {
        try {
            Socket socket = new Socket("google.gogo",80);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void connectionRefused() throws IOException {
        try {
            Socket socket = new Socket("localhost", 45678);
        } catch (ConnectException e) {  // 연결이 거절됨.(우선 네트워크를 통해 해당 IP의 상대 서버 컴퓨터에 접속은 했지만, 해당 상대 서버 컴퓨터가 이 포트를 사용하지 않아 TCP 연결이 거절된 것.(IP에 해당하는 서버는 켜져있지만, 사용하는 포트가 없을 떄 주로 발생))
            e.printStackTrace();
        }
    }
}
