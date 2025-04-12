package network.tcp;

import util.MyLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static util.MyLogger.*;

public class SocketCloseUtil {

    public static void closeAll(Socket socket, InputStream input, OutputStream output) {
        closeInput(input);
        closeOutput(output);
        closeSocket(socket);
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

    public static void closeOutput(OutputStream output) {
        if (output != null) {
            try {
                output.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
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
}
