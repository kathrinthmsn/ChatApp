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

    private static final Logger logger = LogManager.getLogger(Server.class);
    final List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String args[]) throws IOException {
        new Server().go();
    }

    public void go() throws IOException {
        // basic log4j configurator
         BasicConfigurator.configure();

        ServerSocket server_sock = new ServerSocket(6013); // 6013
    while (!server_sock.isClosed()) {
        Socket client = server_sock.accept();
        PrintWriter printWriter = new PrintWriter(client.getOutputStream(), true);
        clientWriters.add(printWriter);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));

        logger.info("client connected");
        printWriter.println("who is it?");

        receiveAndSendMessages(printWriter, bufferedReader);
    }
}

    public void receiveAndSendMessages(PrintWriter printWriter, BufferedReader bufferedReader){

        Thread receive = new Thread(new Runnable() {
            String message;

            public void run() {
                try {
                    while ((message = bufferedReader.readLine()) != null) {

                        clientWriters.remove(printWriter);

                        if (!message.equals("")) {
                            sendToAllClients(message);
                            clientWriters.add(printWriter);
                            logger.info("Server " + message); }}
                } catch (IOException e1) {
                    e1.printStackTrace();
                }}});
        receive.start();
    }

    public void sendToAllClients(String message){
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
            writer.flush();
        }
    }
}

