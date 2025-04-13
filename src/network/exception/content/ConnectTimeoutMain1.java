package network.exception.content;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class ConnectTimeoutMain1 {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        try {
            Socket socket = new Socket("192.168.1.250", 45678);
        } catch (ConnectException e) { // TCP 연결 타임 아웃을 설정해주지 않아서 OS 환경에 맞는 기본 대기 시간만큼 클라이언트는 어떤 응답도 못 받고 무한정 대기해야함.
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("end = " + (end - start));
    }
}
