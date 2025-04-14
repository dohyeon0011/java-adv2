package network.exception.close.reset;

import util.MyLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static util.MyLogger.*;

public class ResetCloseClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // client <- server: FIN
        Thread.sleep(1000); // 서버가 close() 호출할 때 까지 잠시 대기

        // client -> server : PUSH[1] (서버를 닫던 말던 데이터 전송)
        output.write(1);

        // client <- server : RST(서버가 끊었는데 클라이언트가 push하면 서버가 클라이언트에게 RST(연결 상태를 초기화(리셋: 현재의 세션을 강제로 종료하고, 연결을 무효화하라는 뜻)해서 더 이상 현재의 연결을 유지하지 않겠다는 의미)를 보냄)
        Thread.sleep(1000); // RST 메시지 전송 대기

        try {
            // java.net.SocketException: Connection reset(RST 패킷 받은 거임), RST 패킷 받은 이후에 read() 호출
            int read = input.read();
            System.out.println("read = " + read);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {   // RST 패킷 받은 후에도 또 데이터를 보내면
            output.write(1);
        } catch (SocketException e) {   // RST 패킷 받은 이후에 write() 호출 시 java.net.SocketException: Broken pipe
            e.printStackTrace();
        }
    }
}
