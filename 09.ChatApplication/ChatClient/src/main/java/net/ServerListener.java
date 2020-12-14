package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ServerListener
 * created: 10-12-2020 - 0:20
 * project: 09.ChatApplication
 *
 * @author dinar
 * @version v0.1
 */
public class ServerListener implements Runnable {

    private PrintWriter output;

    private BufferedReader input;

    public ServerListener(PrintWriter output, BufferedReader input) {
        this.output = output;
        this.input = input;
    }

    @Override
    public void run() {
        try {
            String message = input.readLine();

            while (message != null) {
                System.out.println(message);
                message = input.readLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
