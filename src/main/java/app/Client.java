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
    private static final Logger logger = LogManager.getLogger(Server.class);


    public Client(){
        frame = new Frame("(Not so) secret Chat");
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();

        // basic log4j configurator
        BasicConfigurator.configure();

        Socket sock = new Socket(InetAddress.getLocalHost(), 6013);
        InputStream in = sock.getInputStream();
        BufferedReader bin = new BufferedReader(new InputStreamReader(in));
        PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);
        logger.info("connected");

            client.frame.textFieldName.addActionListener(e -> {
            String name = client.frame.textFieldName.getText();

            client.frame.textFieldWriting.addActionListener(e12 -> {
                String message = client.frame.textFieldWriting.getText();

                if (!message.equals("") && !name.equals("")) {
                    logger.info("client " + message);
                    pout.println(name + ": "+ message);
                    client.frame.textFieldMyMessage.append(message + "\n");
                    client.frame.textFieldWriting.setText(null);
                }
            });
        });

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