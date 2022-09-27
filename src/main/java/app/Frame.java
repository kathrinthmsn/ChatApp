package app;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Frame extends JFrame{
    JTextField textFieldWriting = new JTextField();
    JTextArea textFieldMyMessage = new JTextArea(30,25);
    JTextArea textFieldPesantMessage = new JTextArea(30,25);
    JScrollPane jscrollpane = new JScrollPane(textFieldMyMessage);
    JScrollPane jscrollpane2 = new JScrollPane(textFieldPesantMessage);

    private static final Logger logger = LogManager.getLogger(Server.class);


    public Frame(String title) {

        super( title );
        setSize( 500, 400 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );


        setLayout(new BorderLayout());

        add(jscrollpane2, BorderLayout.WEST);
        textFieldPesantMessage.setEditable(false);
        textFieldPesantMessage.setLineWrap(true);

        add(jscrollpane, BorderLayout.EAST);
        textFieldMyMessage.setEditable(false);
        textFieldMyMessage.setLineWrap(true);


        add(textFieldWriting, BorderLayout.SOUTH);
        // textFieldWriting.addActionListener(this);


        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }



    public static void main(String []args) {
        Frame frame = new Frame("(Not so) secret chat");
        frame.setVisible(true);
    }
}
