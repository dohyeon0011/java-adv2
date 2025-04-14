package chat.server;

import chat.server.command.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManagerV3 implements CommandManager {

    private static final String DELIMITER = "\\|";
    private final Map<String, Command> commandMap = new HashMap<>(); // 여러 스레드가 `commandMap = new HashMap<>()` 을 동시에 접근해서 데이터를 조회한다.
                                                                    // 하지만 `commandMap` 는 데이터 초기화 이후에는 데이터를 전혀 변경하지 않는다. 따라서 여러 스레드가 동시에 값을 조회해도 문제가 발생하지 않는다.
                                                                    // 만약 `commandMap` 의 데이터를 중간에 변경할 수 있게 하려면 동시성 문제를 고민해야 한다.

    public CommandManagerV3(SessionManager sessionManager) {
        commandMap.put("/join", new JoinCommand(sessionManager));
        commandMap.put("/message", new MessageCommand(sessionManager));
        commandMap.put("/change", new ChangeCommand(sessionManager));
        commandMap.put("/users", new UsersCommand(sessionManager));
        commandMap.put("/exit", new ExitCommand());
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {
        String[] args = totalMessage.split(DELIMITER);
        String key = args[0];

        Command command = commandMap.get(key);

        if (command == null) {
            session.send("처리할 수 없는 명령어입니다. " + totalMessage);
            return;
        }

        command.execute(args, session);
    }
}

