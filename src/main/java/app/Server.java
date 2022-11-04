package app;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public Server() {
    }

    private static final Logger logger = LogManager.getLogger(Server.class);

    public static void main(String args[]) throws IOException {

        // basic log4j configurator
        BasicConfigurator.configure();

        final List<PrintWriter> clientWriters = new ArrayList<>();

        ServerSocket server_sock = new ServerSocket(6013); // 6013
        while (!server_sock.isClosed()) {
            Socket client = server_sock.accept();
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            clientWriters.add(out);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            System.out.println("someone connected");
            logger.info("client connected");
            out.println("who is it?");

            Thread receive = new Thread(new Runnable() {
                String line;

                public void run() {
                    try {
                        while ((line = in.readLine()) != null) {
                            String message = line;
                            clientWriters.remove(out);
                            if (!message.equals("")) {
                                logger.info("Server " + message);
                                for (PrintWriter writer : clientWriters) {
                                    writer.println(message);
                                    writer.flush();
                                }
                                clientWriters.add(out);
                            }
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            receive.start();
        }
    }
}

