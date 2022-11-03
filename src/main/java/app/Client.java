package app;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
    Frame frame;
    PrintWriter pout;
    InputStream in;
    BufferedReader bin;
    private static final Logger logger = LogManager.getLogger(Server.class);


    public Client() throws UnknownHostException, IOException {
        frame = new Frame("(Not so) secret Chat");

    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        Client client = new Client();

        // basic log4j configurator
        BasicConfigurator.configure();

        Socket sock = new Socket(InetAddress.getLocalHost(), 6013);
        InputStream in = sock.getInputStream();
        BufferedReader bin = new BufferedReader(new InputStreamReader(in));
        PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);
        logger.info("connected");

        client.frame.textFieldWriting.addActionListener(e -> {
            String message = client.frame.textFieldWriting.getText();

            if (message != null) {
                logger.info("client " + message);
                pout.println(message);
                client.frame.textFieldMyMessage.append(message + "\n");
                client.frame.textFieldWriting.setText(null);
            }
        }

        );

        Thread receive = new Thread(new Runnable() {
            String line;

            public void run() {


                try {
                    while ((line = bin.readLine()) != null) {
                        client.frame.textFieldFriendMessage.append(line + "\n");
                    }
                } catch (IOException e1) {

                    e1.printStackTrace();
                }
            }
        });
        receive.start();
    }
}