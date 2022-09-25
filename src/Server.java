import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.*;

public class Server implements ActionListener {
    Frame frame;
    ServerSocket server_sock;
    Socket client;
    PrintWriter pout;
    BufferedReader bin;


    public Server() throws IOException {
        frame = new Frame("(Not so) secret Chat");
    }




    public static void main(String args[]) throws IOException {
        Server server = new Server();

        ServerSocket server_sock = new ServerSocket(6013); // 6013
        Socket client = server_sock.accept();
        System.out.println("someone connected");
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out.println("who is it?");

        server.frame.textFieldWriting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String message = server.frame.textFieldWriting.getText();
                if (message!= null) {
                    out.println(message);
                    server.frame.textFieldMyMessage.append(message +"\n");
                    server.frame.textFieldWriting.setText(null);}

            }});

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
