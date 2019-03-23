import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JSONParser extends HttpGetRequest{

    void getJSON() {
        try{
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}