package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * ClientListener
 * created: 10-12-2020 - 0:21
 * project: 09.ChatApplication
 *
 * @author dinar
 * @version v0.1
 */
public class ClientListener implements Runnable, Observer {

    private Server server;

    private Socket client;

    private PrintWriter output;

    private BufferedReader input;

    private SocketAddress inetSocketAddress;

    public ClientListener(Socket client, Server server) {
        try {
            this.server = server;
            this.client = client;
            this.inetSocketAddress = client.getRemoteSocketAddress();
            output = new PrintWriter(client.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String message) {
        output.println(message);
    }

    @Override
    public void run() {
        try {
            while (client.isConnected()) {
                String messageFromClient = input.readLine();

                while (messageFromClient != null && client.isConnected()) {
                    server.notifyObservers(messageFromClient);
                    messageFromClient = input.readLine();
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } finally {
            server.removeObserver(this);
            server.notifyObservers(inetSocketAddress.toString() + " left the chat.");
        }
    }
}
