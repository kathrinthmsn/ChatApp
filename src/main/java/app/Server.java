package app;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements ActionListener {
    Frame frame;
//    ServerSocket server_sock;
//    Socket client;
//    PrintWriter pout;
//    BufferedReader bin;


    public Server(){
        frame = new Frame("(Not so) secret Chat");
    }

    private static final Logger logger = LogManager.getLogger(Server.class);




    public static void main(String args[]) throws IOException {

        // basic log4j configurator
        BasicConfigurator.configure();



        Server server = new Server();

        ServerSocket server_sock = new ServerSocket(6013); // 6013
        Socket client = server_sock.accept();
        System.out.println("someone connected");
        logger.info("client connected");
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out.println("who is it?");

        server.frame.textFieldWriting.addActionListener(e -> {
            String message = server.frame.textFieldWriting.getText();
            if (message!= null) {

                out.println(message);
                server.frame.textFieldMyMessage.append(message +"\n");
                server.frame.textFieldWriting.setText(null);}
        });




        Thread receive =new Thread(new Runnable() {
            String line;
            public void run() {


                try {
                    while ((line = in.readLine())!= null) {
                        server.frame.textFieldPesantMessage.append(line + "\n");}
                } catch (IOException e1) {

                    e1.printStackTrace();
                }
            }});
        receive.start();



    }


		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			String message = frame.textFieldWriting.getText();
			if (message!= null) {

			frame.textFieldMyMessage.append(message +"\n");}

		}




}