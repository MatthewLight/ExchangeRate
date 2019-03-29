import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class UserInterface{
    JTextField USD_Purchase = new JTextField();
    JTextField EUR_Purchase = new JTextField();
    JTextField GBP_Purchase = new JTextField();
    JTextField PLZ_Purchase = new JTextField();

    JTextField USD_Sale = new JTextField();
    JTextField EUR_Sale = new JTextField();
    JTextField GBP_Sale = new JTextField();
    JTextField PLZ_Sale = new JTextField();

    JLabel label;

    JButton getButton = new JButton("Get Exchange Rate");;

    void userInterface(){
        JFrame frame = new JFrame("Exchange Rate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        GridBagLayout gbLayout = new GridBagLayout();
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(gbLayout);

        GridBagConstraints constraints =  new GridBagConstraints();

        buildLabel(panel, constraints, "Purchase", 1, 0, 15);

        buildLabel(panel, constraints, "Sale", 2, 0, 37);

        buildLabel(panel, constraints, "USD", 0, 1, 5);

        buildLabel(panel, constraints, "EUR", 0, 2, 5);

        buildLabel(panel, constraints, "GBP", 0, 3, 5);

        buildLabel(panel, constraints, "PLZ", 0, 4, 5);

        buildTextField(constraints, 1, 1, 0, 0);
        panel.add(USD_Purchase, constraints);

        buildTextField(constraints, 1, 2, 0, 0);
        panel.add(EUR_Purchase, constraints);

        buildTextField(constraints, 1, 3, 0, 0);
        panel.add(GBP_Purchase, constraints);

        buildTextField(constraints, 1, 4, 0, 0);
        panel.add(PLZ_Purchase, constraints);

        buildTextField(constraints, 2, 1, 10, 5);
        panel.add(USD_Sale, constraints);

        buildTextField(constraints, 2, 2, 10, 5);
        panel.add(EUR_Sale, constraints);

        buildTextField(constraints, 2, 3, 10, 5);
        panel.add(GBP_Sale, constraints);

        buildTextField(constraints, 2, 4, 10, 5);
        panel.add(PLZ_Sale, constraints);


        buildButton(constraints);
        panel.add(getButton, constraints);
        getButton.addActionListener(getButtonListener());

        frame.pack();
    }


    // build method for labels
    private void buildLabel(JPanel panel, GridBagConstraints constraints, String title, int positionX, int positionY, int paddingLeft) {
        label = new JLabel(title);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = positionX;
        constraints.gridy = positionY;
        constraints.insets = new Insets(5, paddingLeft, 5, 5);
        panel.add(label, constraints);
    }

    // build method for TextFields
    private void buildTextField(GridBagConstraints constraints, int positionX, int positionY, int paddingLeft, int paddingRight){
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 80;
        constraints.gridx = positionX;
        constraints.gridy = positionY;
        constraints.insets = new Insets(5, paddingLeft,0, paddingRight);
    }

    // build method for button
    private void buildButton(GridBagConstraints constraints){
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 10;      // size
        constraints.gridx = 1;       // row
        constraints.gridy = 6;       // column
        constraints.gridwidth = 2;   // 2 columns wide
        constraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
        constraints.insets = new Insets(5,5,10,10);  // padding
    }

    private ActionListener getButtonListener() {
        return e -> {
            try {
                HttpGetRequest httpObject = new HttpGetRequest();
                httpObject.httpGetRequest();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        };
    }
}
