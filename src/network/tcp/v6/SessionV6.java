package network.tcp.v6;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class SessionV6 implements Runnable {

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManagerV6 sessionManager;
    private boolean closed = false;

    public SessionV6(Socket socket, SessionManagerV6 sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
    }

    /**
     *  try-with-resources는 사용과 해제를 함께 묶어서 처리할 때 사용한다.
     * `try-with-resources`는 try 선언부에서 사용한 자원을 try가 끝나는 시점에 정리한다.
     *  따라서 try에서 자원의 선언과 자원 정리를 묶어서 처리할 때 사용할 수 있다.
     *  하지만 지금은 서버를 종료하는 시점에도 `Session`이 사용하는 자원(소켓, 스트림)을 정리해야 한다.
     *  서버를 종료하는 시점에 자원을 정리하는 것은 `Session`안에 있는 `try-with-resources`를 통해 처리할 수 없다.
     */
    @Override
    public void run() {
        try {
            while (true) {
                // 클라이언트로부터 문자 받기
                String received = input.readUTF();  // Thread-0(다른 스레드에서 세션을 종료한 거라서 에러가 터져서 catch() 문으로 이동해서 연결 종료 시킴)
                log("client -> server: " + received);

                if (received.equals("exit")) {
                    break;
                }

                // 클라이언트에게 문자 보내기
                String toSend = received + " World";
                output.writeUTF(toSend);
                log("client <- server: " + toSend);
            }
        } catch (IOException e) {
            log(e); // Thread-0
        } finally {
            sessionManager.remove(this); // 해당 세션이 끝나면 세션 매니저에서 자기 자신을 제외
            close();
        }
    }

    // 세션 종료시, 서버 종료시 동시에 호출될 수 있다.(서버가 꺼질 때, 클라이언트가 끌 때 동 시에 2번 호출이 됨)
    // 근데 synchronized 걸어주면 다른 스레드에서 close() 중복 호출이 불가능 해지니 동시 호출은 안되더라도 2번 호출은 될 거임.
    public synchronized void close() {
        if (closed) {
            return;
        }

        SocketCloseUtil.closeAll(socket, input, output);
        closed = true;
        log("연결 종료: " + socket);
    }
}
