/*Written by Ositadinma Arimah */

import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

public class Hangman{


    private static JFrame frame_one;

    public JFrame createFrame() {
        return frame_one;
    }

    public static void main(String[] args) {
        {

            final Panel panel = new Panel();
            panel.makePanel();
            panel.makeMenuBar();

            frame_one = new JFrame("Osita Arimah'S of Hangman");
            frame_one.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    try {
                        PrintWriter out = new PrintWriter(new FileWriter(
                                "cat.txt"));
                        String text ;
                        text = panel.cat1.getText();
                        out.println(text);
                        text = panel.cat2.getText();
                        out.println(text);
                        out.close();

                    } catch (IOException e2) {
                    }


                    System.exit(0);

                }
            });
            frame_one.setJMenuBar(panel.getMenu());
            frame_one.getContentPane().add(panel.getPanel());
            // frame_one.getContentPane().add(b.getPlayer1Panel());
            // frame_one.pack(); // Ready to go
            frame_one.setLocation(200, 50); // location
            frame_one.setSize(800, 600); // size of the frame
            frame_one.setVisible(true);

            while (1 > 0) { // back door method to fix time delay
                if (panel.getP2Word() != null)
                    if (panel.getP2Word().length() >= 1) {
                        panel.makeHangMan1();
                        break;
                    }

            }
            panel.solving();
            while (1 > 0) {
                if (panel.firstWordSolved == true) {

                    frame_one.repaint();
                    break;
                }
            }
        }

    }
}