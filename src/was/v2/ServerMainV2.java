package was.v2;

import java.io.IOException;

public class ServerMainV2 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        HttpServerV2 httpServerV2 = new HttpServerV2(PORT);
        httpServerV2.start();
    }
}
