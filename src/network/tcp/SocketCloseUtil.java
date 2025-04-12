package network.tcp;

import util.MyLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static util.MyLogger.*;

public class SocketCloseUtil {

    /**
     * 기본적인 `null` 체크와 자원 종료시 예외를 잡아서 처리하는 코드가 들어가 있다.
     * 참고로 자원 정리 과정에서 문제가 발생해도 코드에서 직접 대응할 수 있는 부분은 거의 없다. 이 경우 간단히 로그를 남겨서 이후에 개발자가 인지할 수 있는 정도면 충분하다.
     * 각각의 예외를 잡아서 처리했기 때문에 `Socket` , `InputStream` , `OutputStream` 중 하나를 닫는 과정에서 예외가 발생해도 다음 자원을 닫을 수 있다.
     */
    public static void closeAll(Socket socket, InputStream input, OutputStream output) {
        System.out.println("자원 해제");
        closeInput(input);
        closeOutput(output);
        closeSocket(socket);
    }

    public static void closeInput(InputStream input) {
        if (input != null) {
            try {
                input.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

    public static void closeOutput(OutputStream output) {
        if (output != null) {
            try {
                output.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

    public static void closeSocket(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }
}
