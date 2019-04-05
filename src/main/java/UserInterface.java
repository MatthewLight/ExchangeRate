import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class UserInterface{
    private JTextField USD_Purchase = new JTextField();
    private JTextField EUR_Purchase = new JTextField();
    private JTextField GBP_Purchase = new JTextField();
    private JTextField PLZ_Purchase = new JTextField();

    private JTextField USD_Sale = new JTextField();
    private JTextField EUR_Sale = new JTextField();
    private JTextField GBP_Sale = new JTextField();
    private JTextField PLZ_Sale = new JTextField();

    private JLabel label;

    private JButton getButton = new JButton("Get Exchange Rate");

    private StringBuffer response = new StringBuffer();

    void httpGetRequest() throws Exception {
        final LocalDate localDate = LocalDate.now();
        final LocalDate yesterday = localDate.minusDays(2);

        String url = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";
        String date = DateTimeFormatter.ofPattern("dd.MM.yyy").format(yesterday);

        URL obj = new URL(url + date);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url + date);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray jsonArray = jsonResponse.getJSONArray("exchangeRate");

        JSONObject purchase_json_usd = (JSONObject) jsonArray.get(16);
        USD_Purchase.setText(String.valueOf(purchase_json_usd.get("purchaseRate")));

        JSONObject purchase_json_eur = (JSONObject) jsonArray.get(22);
        EUR_Purchase.setText(String.valueOf(purchase_json_eur.get("purchaseRate")));

        JSONObject purchase_json_gbp = (JSONObject) jsonArray.get(15);
        GBP_Purchase.setText(String.valueOf(purchase_json_gbp.get("purchaseRate")));

        JSONObject purchase_json_plz = (JSONObject) jsonArray.get(25);
        PLZ_Purchase.setText(String.valueOf(purchase_json_plz.get("purchaseRate")));

        JSONObject sale_json_usd = (JSONObject) jsonArray.get(16);
        USD_Sale.setText(String.valueOf(sale_json_usd.get("saleRate")));

        JSONObject sale_json_eur = (JSONObject) jsonArray.get(22);
        EUR_Sale.setText(String.valueOf(sale_json_eur.get("saleRate")));

        JSONObject sale_json_gbp = (JSONObject) jsonArray.get(15);
        GBP_Sale.setText(String.valueOf(sale_json_gbp.get("saleRate")));

        JSONObject sale_json_plz = (JSONObject) jsonArray.get(25);
        PLZ_Sale.setText(String.valueOf(sale_json_plz.get("saleRate")));
    }
    void createUI(){
        JFrame frame = new JFrame("Exchange Rate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setBounds(0,0, 240,215);
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
                httpGetRequest();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        };
    }
}
