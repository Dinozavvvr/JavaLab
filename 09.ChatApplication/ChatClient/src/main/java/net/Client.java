package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client
 * created: 10-12-2020 - 0:30
 * project: 09.ChatApplication
 *
 * @author dinar
 * @version v0.1
 */
public class Client implements Runnable {

    private Socket client;

    private PrintWriter out;

    private BufferedReader input;

    private ServerListener serverListener;

    private Scanner scanner;

    private final String ipAddress;

    private final int port;

    private final int DEFAULT_TIMEOUT = 5000;

    public Client(String ipAddress, int port) {
        this.client = new Socket();
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            client.connect(new InetSocketAddress(ipAddress, port), DEFAULT_TIMEOUT);
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);

            serverListener = new ServerListener(out, input);
            new Thread(serverListener).start();

            scanner = new Scanner(System.in);

            String message;
            while (!client.isInputShutdown()) {
                message = scanner.nextLine();
                out.println(message);
            }

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
