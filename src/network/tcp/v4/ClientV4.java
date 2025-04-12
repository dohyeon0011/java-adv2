package network.tcp.v4;

import network.tcp.SocketCloseUtil;
import util.MyLogger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.*;
import static util.MyLogger.log;

public class ClientV4 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");

        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;

        try {
            socket = new Socket("localhost", PORT); // port=12345,localport=49240(port: 서버로 연결하는 포트, localport: 내 로컬 포트를 자동으로 할당 해줌, 서버에선 이 포트로 서로 연결함.)

            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            log("소켓 연결: " + socket);

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
        } catch (IOException e) {
            log(e);
        } finally {
            SocketCloseUtil.closeAll(socket, input, output);
            log("소켓 연결 종료: " + socket);
        }
    }
}
