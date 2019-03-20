import java.awt.*;
import javax.swing.*;

public class GridBagLayoutDemo {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton button;
        JTextField text;
        JLabel label;
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        label = new JLabel("USD");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5,5,5,5);
        pane.add(label, c);

        label = new JLabel("EUR");
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        pane.add(label, c);

        label = new JLabel("GBP");
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        pane.add(label, c);

        label = new JLabel("PLZ");
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        pane.add(label, c);

        text = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 300;
        c.insets = new Insets(5,0,0,0);
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 0;
        pane.add(text, c);

        text = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,0,0,0);
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 1;
        pane.add(text, c);

        text = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,0,0,0);
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 2;
        pane.add(text, c);

        text = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,0,0,0);
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 3;
        pane.add(text, c);

        button = new JButton("Get Exchange Rate JSON");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(5,10,10,10);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 1;   //2 columns wide
        c.gridy = 5;       //third row
        pane.add(button, c);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Get JSON");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}