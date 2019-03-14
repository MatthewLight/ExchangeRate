import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExchangeRate {
    private JTextField USD_Purchase;
    private JTextField EUR_Purchase;
    private JTextField GBP_Purchase;
    private JTextField PLZ_Purchase;

    private JTextField USD_Sale;
    private JTextField EUR_Sale;
    private JTextField GBP_Sale;
    private JTextField PLZ_Sale;

    private JLabel label;

    private void httpGetRequest() throws Exception {
        LocalDate localDate = LocalDate.now();
        LocalDate yesterday = localDate.minusDays(2);

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
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // JSON get result
        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray jsonArray = jsonResponse.getJSONArray("exchangeRate");

        JSONObject purchase_json_usd = (JSONObject) jsonArray.get(16);
        USD_Purchase.setText(String.valueOf((Double) purchase_json_usd.get("purchaseRate")));

        JSONObject purchase_json_eur = (JSONObject) jsonArray.get(22);
        EUR_Purchase.setText(String.valueOf((Double) purchase_json_eur.get("purchaseRate")));

        JSONObject purchase_json_gbp = (JSONObject) jsonArray.get(15);
        GBP_Purchase.setText(String.valueOf((Double) purchase_json_gbp.get("purchaseRate")));

        JSONObject purchase_json_plz = (JSONObject) jsonArray.get(25);
        PLZ_Purchase.setText(String.valueOf((Double) purchase_json_plz.get("purchaseRate")));

        JSONObject sale_json_usd = (JSONObject) jsonArray.get(16);
        USD_Sale.setText(String.valueOf((Double) sale_json_usd.get("saleRate")));

        JSONObject sale_json_eur = (JSONObject) jsonArray.get(22);
        EUR_Sale.setText(String.valueOf((Double) sale_json_eur.get("saleRate")));

        JSONObject sale_json_gbp = (JSONObject) jsonArray.get(15);
        GBP_Sale.setText(String.valueOf((Double) sale_json_gbp.get("saleRate")));

        JSONObject sale_json_plz = (JSONObject) jsonArray.get(25);
        PLZ_Sale.setText(String.valueOf((Double) sale_json_plz.get("saleRate")));
    }

    private void UI(){
        JFrame frame = new JFrame("Get JSON");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        GridBagLayout gbl = new GridBagLayout();
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(gbl);

        GridBagConstraints c =  new GridBagConstraints();

        label = new JLabel("Purchase");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1; // Положення по горизонталі;
        c.gridy = 0; // Положення по вертикалі;
        c.insets = new Insets(5,15,5,5); // Відступи;
        panel.add(label, c);

        label = new JLabel("Sale");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(5,40,5,5);
        panel.add(label, c);

        label = new JLabel("USD");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5,5,5,5);
        panel.add(label, c);

        label = new JLabel("EUR");
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, c);

        label = new JLabel("GBP");
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, c);

        label = new JLabel("PLZ");
        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, c);

        USD_Purchase = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 80;
        c.insets = new Insets(5,0,0,0);
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 1;
        panel.add(USD_Purchase, c);

        EUR_Purchase = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,0,0,0);
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 2;
        panel.add(EUR_Purchase, c);

        GBP_Purchase = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,0,0,0);
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 3;
        panel.add(GBP_Purchase, c);

        PLZ_Purchase = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,0,0,0);
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 4;
        panel.add(PLZ_Purchase, c);

        USD_Sale = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,10,0,5);
        c.ipadx = 80;
        c.gridx = 2;
        c.gridwidth = 1;
        c.gridy = 1;
        panel.add(USD_Sale, c);

        EUR_Sale = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,10,0,5);
        c.gridx = 2;
        c.gridwidth = 1;
        c.gridy = 2;
        panel.add(EUR_Sale, c);

        GBP_Sale = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,10,0,5);
        c.gridx = 2;
        c.gridwidth = 1;
        c.gridy = 3;
        panel.add(GBP_Sale, c);

        PLZ_Sale = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,10,0,5);
        c.gridx = 2;
        c.gridwidth = 1;
        c.gridy = 4;
        panel.add(PLZ_Sale, c);

        JButton getButton = new JButton("Get Exchange Rate");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 10;      // size
        c.ipady = 0;       // reset to default
        c.weighty = 1.0;   // request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(5,5,10,10);  // padding
        c.gridx = 1;       // row
        c.gridwidth = 2;   // 2 columns wide
        c.gridy = 6;       // column
        panel.add(getButton, c);
        getButton.addActionListener(getButtonListener());

        frame.pack();
    }

    private ActionListener getButtonListener() {
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    httpGetRequest();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        };
        return action;
    }

    public static void main(String[] args) {
        try{
            ExchangeRate call = new ExchangeRate();
            call.UI();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
