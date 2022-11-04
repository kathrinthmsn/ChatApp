package app;

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
    JScrollPane jscrollpaneMyMessage = new JScrollPane(textFieldMyMessage);
    JScrollPane jscrollpaneFriendMessage = new JScrollPane(textFieldFriendMessage);

    public Frame(String title) {

        super( title );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize(600, 400);

        setLayout(new BorderLayout());

        add(textFieldName, BorderLayout.NORTH);
        textFieldName.setBackground(Color.ORANGE);

        add(jscrollpaneFriendMessage, BorderLayout.WEST);
        textFieldFriendMessage.setEditable(false);
        textFieldFriendMessage.setLineWrap(true);

        add(jscrollpaneMyMessage, BorderLayout.EAST);
        textFieldMyMessage.setEditable(false);
        textFieldMyMessage.setLineWrap(true);

        add(textFieldWriting, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
