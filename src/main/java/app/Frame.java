package app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Frame extends JFrame implements ActionListener{
    JTextField textFieldWriting = new JTextField();
    JTextArea textFieldMyMessage = new JTextArea(30,25);
    JTextArea textFieldPesantMessage = new JTextArea(30,25);
    JScrollPane jscrollpane = new JScrollPane(textFieldMyMessage);
    JScrollPane jscrollpane2 = new JScrollPane(textFieldPesantMessage);

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
        textFieldWriting.addActionListener(this);


        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

      public void actionPerformed( ActionEvent evt)
      {
        String name = textFieldWriting.getText();

        textFieldMyMessage.append(name +"\n");
        textFieldWriting.setText(null);

        repaint();
      }
    public static void main(String []args) {
        Frame frame = new Frame("(Not so) secret chat");
        frame.setVisible(true);
    }
}
