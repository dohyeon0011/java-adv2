package was.v4;

import was.v3.HttpServerV3;

import java.io.IOException;

public class ServerMainV4 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        HttpServerV3 httpServerV3 = new HttpServerV3(PORT);
        httpServerV3.start();
    }
}
