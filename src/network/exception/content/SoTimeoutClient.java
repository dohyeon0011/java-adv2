package network.exception.content;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 반드시 연결 타임아웃과 소켓 타임아웃을 지정해야 한다~
 * -> 그렇지 않으면 클라이언트가 문제가 생기는 소켓에 계속 요청 스레드가 누적이 되어서 서버가 터지게 되기 때문에
 */
public class SoTimeoutClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        InputStream input = socket.getInputStream();

        try {
            socket.setSoTimeout(3000);  // 타임아웃 시간 설정(read 타임아웃 설정 하지 않으면 서버에서 응답이 없어서 클라이언트는 무한정 대기하게 됨.)
            int read = input.read();
            System.out.println("read = " + read);
        } catch (Exception e) {
            e.printStackTrace();
        }

        socket.close();
    }
}
