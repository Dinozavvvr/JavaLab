package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Server
 * created: 10-12-2020 - 0:23
 * project: 09.ChatApplication
 *
 * @author dinar
 * @version v0.1
 */
public class Server implements Runnable {

    private int port;

    private ServerSocket server;

    private List<Observer> clients;

    private int clientsLimit = 16;

    public void addObserver(Observer observer) {
        clients.add(observer);
    }

    public void removeObserver(Observer observer) {
        clients.remove(observer);
    }

    public void notifyObservers(String message) {
        clients.forEach(observer -> observer.update("<<< " + message));
    }

    public Server(int port) {
        try {
            this.port = port;
            clients = new ArrayList<>();
            server = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void run() {
        while (!server.isClosed()) {
            try {
                System.out.println("waiting");
                Socket client = server.accept();
                System.out.println("client " + client.getRemoteSocketAddress() + " connected");

                ClientListener clientListener = new ClientListener(client, this);
                Thread clientListenerThread = new Thread(clientListener);

                addObserver(clientListener);
                notifyObservers(client.getRemoteSocketAddress() + " joined to the chat.");

                clientListenerThread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
