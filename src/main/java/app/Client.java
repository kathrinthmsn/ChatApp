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
import java.time.LocalTime;


public class Client {
    Frame frame;
    private static final Logger logger = LogManager.getLogger(Server.class);


    public Client(){
        frame = new Frame("(Not so) secret Chat");
    }

    public static void main(String[] args) throws IOException {
        new Client().go();

        // basic log4j configurator
        BasicConfigurator.configure();
    }
    public void go() throws IOException {

        Socket sock = new Socket(InetAddress.getLocalHost(), 6013);
        InputStream inputStream = sock.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter printWriter = new PrintWriter(sock.getOutputStream(), true);
        logger.info("connected");

        frame.textFieldName.addActionListener(e -> {

            String clientsName = frame.textFieldName.getText();

            sendMessagesWithClientsName(printWriter, clientsName);
        });

        readMessagesFromOtherClients(bufferedReader);


    }
    public void sendMessagesWithClientsName(PrintWriter printWriter, String clientsName){
        frame.textFieldWriting.addActionListener(e12 -> {
            String message = frame.textFieldWriting.getText();

            if (!message.equals("") && !clientsName.equals("")) {
                String time = getTime();
                printWriter.println(clientsName + ": "+ message + " (" + time + ")");
                frame.textFieldMyMessage.append(message + " (" + time + ")" + "\n");
                frame.textFieldWriting.setText(null);
                logger.info("client " + message);
            }
        });
    }

    public void readMessagesFromOtherClients(BufferedReader bufferedReader){
        Thread receive = new Thread(new Runnable() {
            String line;

            public void run() {
                try {
                    while ((line = bufferedReader.readLine()) != null) {
                        frame.textFieldFriendMessage.append(line + "\n");
                    }
                } catch (IOException e1) {

                    e1.printStackTrace();
                }
            }
        });
        receive.start();
    }

    public String getTime(){
        int hour = LocalTime.now().getHour();
        String strHour = Integer.toString(hour);
        int minutes= LocalTime.now().getMinute();
        String strMinutes;
        if(minutes < 10){
            strMinutes = "0" + Integer.toString(minutes);
        }
        else {
            strMinutes = Integer.toString(minutes);
        }
        return  strHour + ":" + strMinutes;
    }
}