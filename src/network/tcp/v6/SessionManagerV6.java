package network.tcp.v6;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 각 세션은 소켓과 연결 스트림을 가지고 있다. 따라서 서버를 종료할 때 사용하는 세션들도 함께 종료해야 한다.
 * 모든 세션들을 찾아서 종료하려면 생성한 세션을 보관하고 관리할 객체가 필요하다.
 */
public class SessionManagerV6 { // 동시성 처리 필요

    private List<SessionV6> sessions = new ArrayList<>();

    // add() : 클라이언트의 새로운 연결을 통해, 세션이 새로 만들어지는 경우 add() 를 호출해서 세션 매니저에 세션을 추가한다.
    public synchronized void add(SessionV6 session) {
        sessions.add(session);
    }

    // remove() : 클라이언트의 연결이 끊어지면 세션도 함께 정리된다. 이 경우 remove() 를 호출해서 세션 매니저에서 세션을 제거한다
    public synchronized void remove(SessionV6 session) {
        sessions.remove(session);
    }

    // closeAll() : 모든 세션이 사용하는 자원(`Socket` , `InputStream` ,`OutputStream`)을 정리한다.
    public synchronized void closeAll() {
        for (SessionV6 session : sessions) {
            session.close();
        }
        sessions.clear();
    }
}
