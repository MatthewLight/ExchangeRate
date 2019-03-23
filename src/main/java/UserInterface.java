import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class UserInterface{
    JTextField USD_Purchase;
    JTextField EUR_Purchase;
    JTextField GBP_Purchase;
    JTextField PLZ_Purchase;

    JTextField USD_Sale;
    JTextField EUR_Sale;
    JTextField GBP_Sale;
    JTextField PLZ_Sale;

    JLabel label;

    JButton getButton;

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

        USD_Purchase = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 80;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(5,0,0,0);
        panel.add(USD_Purchase, constraints);

        EUR_Purchase = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 80;
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(5,0,0,0);
        panel.add(EUR_Purchase, constraints);

        GBP_Purchase = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 80;
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(5,0,0,0);
        panel.add(GBP_Purchase, constraints);

        PLZ_Purchase = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 80;
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.insets = new Insets(5,0,0,0);
        panel.add(PLZ_Purchase, constraints);

        USD_Sale = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 80;
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.insets = new Insets(5,10,0,5);
        panel.add(USD_Sale, constraints);

        EUR_Sale = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 80;
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.insets = new Insets(5,10,0,5);
        panel.add(EUR_Sale, constraints);

        GBP_Sale = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 80;
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.insets = new Insets(5,10,0,5);
        panel.add(GBP_Sale, constraints);

        PLZ_Sale = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 80;
        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.insets = new Insets(5,10,0,5);
        panel.add(PLZ_Sale, constraints);


        getButton = new JButton("Get Exchange Rate");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 10;      // size
        constraints.gridx = 1;       // row
        constraints.gridy = 6;       // column
        constraints.gridwidth = 2;   // 2 columns wide
        constraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
        constraints.insets = new Insets(5,5,10,10);  // padding
        panel.add(getButton, constraints);
        getButton.addActionListener(getButtonListener());

        frame.pack();
    }

    // build method for labels
    private void buildLabel(JPanel panel, GridBagConstraints constraints, String purchase, int positionX, int positionY, int paddingLeft) {
        label = new JLabel(purchase);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = positionX;
        constraints.gridy = positionY;
        constraints.insets = new Insets(5, paddingLeft, 5, 5);
        panel.add(label, constraints);
    }

    private ActionListener getButtonListener() {
        return e -> {
            try {
                HttpGetRequest httpObject = new HttpGetRequest();
                JSONParser getJSONData = new JSONParser();
                httpObject.httpGetRequest();
                getJSONData.getJSON();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        };
    }
}
