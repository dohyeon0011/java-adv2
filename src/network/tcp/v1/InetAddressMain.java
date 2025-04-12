package network.tcp.v1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressMain {

    public static void main(String[] args) throws UnknownHostException {
        /**
         * InetAddress.getByName()
         *  1. 해당하는 IP 주소를 조회
         *  2. 이 과정에서 시스템의 호스트 파일을 먼저 확인한다.
         *      (ex: /etc/hosts(리눅스, mac) || C:\Windows\System32\drivers\etc\hosts(윈도우, Windows)
         *      호스트 파일 - 예시
         *      127.0.0.1   localhost
         *      255.255.255.255 broadcastHost
         *      ::1         localhost
         *  3. 호스트 파일에 정의되어 있지 않다면, DNS 서버에 요청해서 IP 주소를 얻는다.
         */
        InetAddress localhost = InetAddress.getByName("localhost");
        System.out.println("localhost = " + localhost);

        InetAddress google = InetAddress.getByName("google.com");
        System.out.println("google = " + google);
    }
}
