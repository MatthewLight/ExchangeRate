import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class HttpGetRequest extends UserInterface {
    private StringBuffer response = new StringBuffer();

    void httpGetRequest() throws Exception {
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
}
