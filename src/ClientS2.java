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
import java.net.UnknownHostException;
import java.util.Scanner;


public class ClientS2 implements ActionListener {
    Frame frame;
    PrintWriter pout;
    InputStream in;
    BufferedReader bin;

    public ClientS2() throws UnknownHostException, IOException {
        frame = new Frame("(Not so) secret Chat");

    }

    /**
     * @param args
     * @throws IOException
     * @throws UnknownHostException
     */


    public static void main(String[] args) throws UnknownHostException, IOException {
        ClientS2 clients2 = new ClientS2();


        Socket sock = new Socket(InetAddress.getLocalHost(), 6013);
        InputStream in = sock.getInputStream();
        BufferedReader bin = new BufferedReader(new InputStreamReader(in));
        PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);

        clients2.frame.textFieldWriting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String message = clients2.frame.textFieldWriting.getText();
                if (message != null) {
                    pout.println(message);
                    clients2.frame.textFieldMyMessage.append(message + "\n");
                    clients2.frame.textFieldWriting.setText(null);
                }

            }
        });

        Thread receive = new Thread(new Runnable() {
            String line;

            public void run() {


                try {
                    while ((line = bin.readLine()) != null) {
                        clients2.frame.textFieldPesantMessage.append(line + "\n");
                    }
                } catch (IOException e1) {

                    e1.printStackTrace();
                }
            }
        });
        receive.start();


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String message = frame.textFieldWriting.getText();
        if (message != null) {
            pout.println(message);
            frame.textFieldMyMessage.append(message + "\n");
        }


    }
}