package network.exception.close.normal;

import java.io.*;
import java.net.Socket;

import static util.MyLogger.*;

public class NormalCloseClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);

        InputStream input = socket.getInputStream();

        // 서버에서 fin(연결 종료) 신호를 받으면, os가 자동으로 ack(연결 종료 응답)신호를 보내고,
        // 클라이언트에서도 해당 소켓에 대한 fin(연결 종료) 신호를 서버에게 보낸 후 서버가 ack(연결 종료 응답) 신호를 보냄.
        // close() 중복 호출을 보내도 이미 닫혀 있으면 중복으로 처리되지 않게끔 되어있음.
        readByInputStream(input, socket);
        readByBufferedReader(input, socket);
        readByDataInputStream(input, socket);

        log("연결 종료: " + socket.isClosed());
    }

    // 바이트로 직접 읽는 스트림 방식
    private static void readByInputStream(InputStream input, Socket socket) throws IOException {
        int read = input.read();
        log("read = " + read);  // -1이면 EOF(더이상 읽을 게 없음)

        if (read == -1) {
            input.close();
            socket.close();
        }
    }

    // 버퍼를 이용한 스트림 방식
    private static void readByBufferedReader(InputStream input, Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String readString = br.readLine();
        log("readString = " + readString);

        if (readString == null) {
            br.close();
            socket.close();
        }
    }

    // 직접 문자를 받는 스트림 방식
    private static void readByDataInputStream(InputStream input, Socket socket) throws IOException {
        DataInputStream dis = new DataInputStream(input);

        try {
            dis.readUTF();
        } catch (EOFException e) {
            log(e);
        } finally {
            dis.close();
            socket.close();
        }
    }
}
