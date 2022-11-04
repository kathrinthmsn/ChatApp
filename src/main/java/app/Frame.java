package app;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Frame extends JFrame{
    JTextField textFieldWriting = new JTextField();
    JTextField textFieldName = new JTextField();
    JTextArea textFieldMyMessage = new JTextArea(30,25);
    JTextArea textFieldFriendMessage = new JTextArea(30,25);
    JScrollPane jscrollpane = new JScrollPane(textFieldMyMessage);
    JScrollPane jscrollpane2 = new JScrollPane(textFieldFriendMessage);

    private static final Logger logger = LogManager.getLogger(Server.class);


    public Frame(String title) {

        super( title );
        setSize( 500, 400 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );


        setLayout(new BorderLayout());

        add(jscrollpane2, BorderLayout.WEST);
        textFieldFriendMessage.setEditable(false);
        textFieldFriendMessage.setLineWrap(true);

        add(jscrollpane, BorderLayout.EAST);
        textFieldMyMessage.setEditable(false);
        textFieldMyMessage.setLineWrap(true);

        add(textFieldWriting, BorderLayout.SOUTH);

        add(textFieldName, BorderLayout.NORTH);
        textFieldName.setBackground(Color.ORANGE);


        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String []args) {
        Frame frame = new Frame("(Not so) secret chat");
        frame.setVisible(true);


    }
}
